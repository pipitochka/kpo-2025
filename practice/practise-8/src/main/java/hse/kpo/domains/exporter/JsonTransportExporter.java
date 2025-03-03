package hse.kpo.domains.exporter;

import com.fasterxml.jackson.databind.ObjectMapper;
import hse.kpo.interfaces.transport.Transport;
import hse.kpo.interfaces.reports.TransportExporter;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class JsonTransportExporter implements TransportExporter {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void export(List<Transport> transports, Writer writer) throws IOException {
        transports.forEach(transport ->
        {
            try {
                objectMapper.writeValue(writer, String.format("%d,%s,%s\n",
                        transport.getVin(),
                        transport.getTransportType(),
                        transport.getEngineType()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
