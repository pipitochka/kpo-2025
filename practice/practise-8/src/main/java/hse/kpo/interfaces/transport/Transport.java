package hse.kpo.interfaces.transport;

import hse.kpo.domains.objects.Customer;

public interface Transport {
    boolean isCompatible(Customer customer);
    int getVin();
    String getEngineType();
    String getTransportType();
}
