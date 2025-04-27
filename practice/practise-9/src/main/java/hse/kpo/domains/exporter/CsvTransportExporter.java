package hse.kpo.domains.exporter;

import hse.kpo.interfaces.reports.TransportExporter;
import hse.kpo.interfaces.transport.Transport;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class CsvTransportExporter implements TransportExporter {
    @Override
    public void export(List<Transport> transports, Writer writer) throws IOException {
        writer.write("id,name,type\n"); // <-- подставь свои реальные поля у Transport

        for (Transport transport : transports) {
            writer.write(String.format("%s,%s,%s\n",
                    transport.getVin(),
                    transport.getTransportType(),
                    transport.getEngineType()
            ));
        }
        writer.flush();
    }
}
