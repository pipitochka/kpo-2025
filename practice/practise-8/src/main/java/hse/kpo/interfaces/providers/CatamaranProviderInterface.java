package hse.kpo.interfaces.providers;

import hse.kpo.domains.objects.Catamaran;
import hse.kpo.domains.objects.Customer;

public interface CatamaranProviderInterface {
    Catamaran takeCatamaran(Customer customer);
}
