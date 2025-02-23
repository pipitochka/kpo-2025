package hse.kpo.domains.sales;

import hse.kpo.builders.ReportBuilder;
import hse.kpo.domains.objects.Customer;
import hse.kpo.enums.ProductionTypes;
import hse.kpo.interfaces.sales.SalesObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * class of sales observer.
 */
@Component
@RequiredArgsConstructor
public class ReportSalesObserver implements SalesObserver {
    private final hse.kpo.storages.CustomerStorage customerStorage;

    private final ReportBuilder reportBuilder = new ReportBuilder();

    public hse.kpo.records.Report buildReport() {
        return reportBuilder.build();
    }

    public void checkCustomers() {
        reportBuilder.addCustomers(customerStorage.getCustomers());
    }

    @Override
    public void onSale(Customer customer, ProductionTypes productType, int vin) {
        String message = String.format(
                "Продажа: %s VIN-%d клиенту %s (Сила рук: %d, Сила ног: %d, IQ: %d)",
                productType, vin, customer.getName(),
                customer.getHandPower(), customer.getLegPower(), customer.getIq()
        );
        reportBuilder.addOperation(message);
    }
}
