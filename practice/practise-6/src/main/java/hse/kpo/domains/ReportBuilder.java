package hse.kpo.domains;

import hse.kpo.records.Report;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * class which makes report.
 */
@Component
public class ReportBuilder {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    private final StringBuilder content = new StringBuilder();

    /**
     * function add list of customers to report.
     *
     * @param customers list of people who will be added to report.
     * @return this.
     */
    public ReportBuilder addCustomers(List<Customer> customers) {
        content.append("Buyers:");
        customers.forEach(customer -> content.append(String.format(" - %s", customer)));
        content.append("\n");
        return this;
    }

    /**
     * function add operation of customers to report.
     *
     * @param operation to string.
     * @return this.
     */
    public ReportBuilder addOperation(String operation) {
        content.append(String.format("Operation: %s", operation));
        content.append(System.lineSeparator());
        return this;
    }

    /**
     * function which make report.
     *
     * @return report.
     */
    public Report build() {
        return new Report(String.format("Отчет за %s", ZonedDateTime.now().format(DATE_TIME_FORMATTER)),
                content.toString());
    }

}
