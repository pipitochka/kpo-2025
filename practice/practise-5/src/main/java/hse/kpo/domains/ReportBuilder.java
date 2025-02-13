package hse.kpo.domains;

import hse.kpo.records.Report;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * class which makes report.
 */
@Component
public class ReportBuilder {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    private final StringBuilder content = new StringBuilder();

    public ReportBuilder addCustomers(List<Customer> customers)
    {
        content.append("Buyers:");
        customers.forEach(customer -> content.append(String.format(" - %s", customer)));
        content.append("\n");
        return this;
    }

    public ReportBuilder addOperation(String operation)
    {
        content.append(String.format("Operation: %s", operation));
        content.append(System.lineSeparator());
        return this;
    }

    public Report build()
    {
        return new Report(String.format("Отчет за %s", ZonedDateTime.now().format(DATE_TIME_FORMATTER)),
                content.toString());
    }

}
