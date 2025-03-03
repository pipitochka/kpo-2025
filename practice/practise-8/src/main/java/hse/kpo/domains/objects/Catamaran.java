package hse.kpo.domains.objects;

import lombok.ToString;

/**
 * class for willed catamaran.
 */
@ToString
public class Catamaran extends Car {

    public Catamaran(Ship ship, int vin) {
        super(vin, ship.getEngine());
    }

    @Override
    public String getTransportType(){
        return "Catamaran";
    }
}
