package hse.kpo;

import hse.kpo.domains.ReportBuilder;
import hse.kpo.domains.objects.Customer;
import hse.kpo.enums.ProductionTypes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Observer;


@Component
@RequiredArgsConstructor
public class ReportSalesObserver implements SalesObserver {
    private final hse.kpo.services.storage.CustomerStorage customerStorage;

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
