package hse.kpo.controllers.reports;

import hse.kpo.dto.ReportRequest;
import hse.kpo.enums.ReportFormat;
import hse.kpo.facade.Hse;
import hse.kpo.interfaces.FacadeInterface;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.io.FileWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


/**
 * class of report controller.
 */
@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
@Tag(name = "Отчеты", description = "Управление транспортными средствами")
public class ReportController {

    private final FacadeInterface hseFacade;
    private final Hse hse;

    /**
     * function to create report.
     *
     * @param reportRequest information to create report.
     * @param bindingResult information about request if it corrects.
     * @return ok if request correct else bad request.
     */
    @PostMapping()
    public ResponseEntity<Void> createReport(@Valid @RequestBody ReportRequest reportRequest,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        try (FileWriter fileWriter = new FileWriter("reports/" + reportRequest.getFilename())) {
            hseFacade.transportReport(reportRequest.getReportFormat(), fileWriter);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        return ResponseEntity.ok().build();
    }
}
