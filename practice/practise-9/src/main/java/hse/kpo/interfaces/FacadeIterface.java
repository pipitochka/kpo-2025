package hse.kpo.interfaces;

import hse.kpo.domains.objects.Car;
import hse.kpo.domains.objects.Ship;
import hse.kpo.domains.reports.Report;
import hse.kpo.enums.ReportFormat;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;

public interface FacadeIterface {

    public Car addPedalCar(int pedalSize);
    public Car addHandCar();
    public Car addFlyingCar();
    public Ship addFlyingShip();
    public Ship addHandShip();
    public Ship addPedalShip(int pedalSize);
    public void sell();
    public Report generateReport();
    public void exportReport(ReportFormat format, Writer writer);
    public void transportReport(ReportFormat format, Writer writer) throws IOException;
}
