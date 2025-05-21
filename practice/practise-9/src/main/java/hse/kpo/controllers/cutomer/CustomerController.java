package hse.kpo.controllers.cutomer;

import hse.kpo.domains.objects.Customer;
import hse.kpo.dto.CarRequest;
import hse.kpo.dto.CustomerRequest;
import hse.kpo.interfaces.FacadeIterface;
import hse.kpo.interfaces.providers.CustomerProviderInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
@Tag(name = "Покупатели", description = "Управление покупателями")
public class CustomerController {
    private final FacadeIterface hseFacade;

    private final CustomerProviderInterface customerProvider;

    @PostMapping
    @Operation(summary = "Создать покупателя",
            description = "Для PEDAL требуется pedalSize (1-15)")
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody CustomerRequest request,
                                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        var customer = new Customer(request.getName(), request.getLegPower(), request.getHandPower(), request.getIq());

        customerProvider.addCustomer(customer);

        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }

    @GetMapping()
    @Operation(summary = "Получить всех покупателей")
    public ResponseEntity<List<Customer>> getCustomers() {
        return ResponseEntity.ok(customerProvider.getCustomers());
    }

    @GetMapping("/{name}")
    @Operation(summary = "Получить покупателя по имени")
    public ResponseEntity<Customer> getCustomer(@PathVariable String name) {
        return customerProvider.getCustomers().stream()
                .filter(customer -> customer.getName().equals(name))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
