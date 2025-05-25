package hse.kpo.domains.exporter;

import com.fasterxml.jackson.databind.ObjectMapper;
import hse.kpo.interfaces.reports.TransportExporter;
import hse.kpo.interfaces.transport.Transport;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

/**
 * class of json transport exporter.
 */
public class JsonTransportExporter implements TransportExporter {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void export(List<Transport> transports, Writer writer) throws IOException {
        objectMapper.writeValue(writer, transports);
    }
}
