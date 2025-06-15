package hse.kpo.controllers.ship;

import hse.kpo.domains.engines.FlyEngine;
import hse.kpo.domains.engines.HandEngine;
import hse.kpo.domains.engines.PedalEngine;
import hse.kpo.domains.objects.Ship;
import hse.kpo.dto.requests.ShipRequest;
import hse.kpo.enums.EngineTypes;
import hse.kpo.interfaces.FacadeInterface;
import hse.kpo.interfaces.engines.EngineInterface;
import hse.kpo.services.HseShipService;
import hse.kpo.storages.ShipStorage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * class of ship controller.
 */
@Validated
@RestController
@RequestMapping("api/ships")
@RequiredArgsConstructor
@Tag(name = "Корабли", description = "Управление транспортными средствами")
public class ShipController {
    private final ShipStorage shipStorage;
    private final HseShipService hseShipService;
    private final FacadeInterface hseFacade;

    /**
     * function to take ship by vim.
     *
     * @param vin of ship.
     * @return ship or not found if ship not found.
     */
    @GetMapping("/{vin}")
    @Operation(summary = "Получить корабль по VIN")
    public ResponseEntity<Ship> getShip(@PathVariable int vin) {
        return shipStorage.getShips()
                .stream()
                .filter(ship -> ship.getVin() == vin)
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * function to create ship.
     *
     * @param request information to create ship.
     * @param bindingResult information about requests if it corrects.
     * @return information about ship if requests correct else bad requests.
     */
    @PostMapping
    @Operation(summary = "Создать корабль",
            description = "Для PEDAL требуется pedalSize (1-15)")
    public ResponseEntity<Ship> createShip(
            @Valid @RequestBody ShipRequest request,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        var engineType = EngineTypes.find(request.engineType());
        if (engineType.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "No this type");
        }

        var ship = switch (engineType.get()) {
            case EngineTypes.PEDAL -> hseFacade.addPedalShip(request.pedalSize());
            case EngineTypes.HAND -> hseFacade.addHandShip();
            case EngineTypes.LEVITATION -> hseFacade.addFlyingShip();
            default -> throw new RuntimeException();
        };

        return ResponseEntity.status(HttpStatus.CREATED).body(ship);
    }

    @PostMapping("/sell")
    @Operation(summary = "Продать все доступные корабли")
    public ResponseEntity<Void> sellAllCars() {
        hseShipService.sellShips();
        return ResponseEntity.ok().build();
    }

    /**
     * function to sell ship by vim.
     *
     * @param vin of car.
     * @return ok if it sold else not found.
     */
    @PostMapping("/sell/{vin}")
    @Operation(summary = "Продать корабли по VIN")
    public ResponseEntity<Void> sellShip(@PathVariable int vin) {
        var shipOptional = shipStorage.getShips().stream()
                .filter(c -> c.getVin() == vin)
                .findFirst();

        if (shipOptional.isPresent()) {
            var ship = shipOptional.get();
            shipStorage.getShips().remove(ship);
            // Логика продажи (упрощенно)
            hseFacade.sell();
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    private Ship createShipFromRequest(ShipRequest request, int vin) {
        EngineInterface engine = switch (EngineTypes.valueOf(request.engineType())) {
            case PEDAL -> new PedalEngine(request.pedalSize());
            case HAND -> new HandEngine();
            case LEVITATION -> new FlyEngine();
        };
        return new Ship(engine, vin);
    }

    /**
     * function to update information about ship.
     *
     * @param vin of ship.
     * @param request new information.
     * @return new ship information or not found if car will not be found.
     */
    @PutMapping("/{vin}")
    @Operation(summary = "Обновить корабли")
    public ResponseEntity<Ship> updateShip(
            @PathVariable int vin,
            @Valid @RequestBody ShipRequest request) {

        return shipStorage.getShips().stream()
                .filter(ship -> ship.getVin() == vin)
                .findFirst()
                .map(existingCar -> {
                    var updatedShip = createShipFromRequest(request, vin);
                    shipStorage.getShips().remove(existingCar);
                    shipStorage.addExistingShip(updatedShip);
                    return ResponseEntity.ok(updatedShip);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{vin}")
    @Operation(summary = "Удалить корабли")
    public ResponseEntity<Void> deleteShip(@PathVariable int vin) {
        boolean removed = shipStorage.getShips().removeIf(ship -> ship.getVin() == vin);
        return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    /**
     * function to get all ships with filters.
     *
     * @param engineType for filter may be null.
     * @param minVin for filter may be null.
     * @return list of ships with filter.
     */
    @GetMapping
    @Operation(summary = "Получить все корабли с фильтрацией",
            parameters = {
                @Parameter(name = "engineType", description = "Фильтр по типу двигателя"),
                @Parameter(name = "minVin", description = "Минимальный VIN")
            })
    public List<Ship> getAllShips(
            @RequestParam(required = false) String engineType,
            @RequestParam(required = false) Integer minVin) {

        return shipStorage.getShips().stream()
                .filter(ship -> engineType == null || ship.getEngineType().equals(engineType))
                .filter(ship -> minVin == null || ship.getVin() >= minVin)
                .toList();
    }

}
