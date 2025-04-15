package hse.kpo.domains.exporter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import hse.kpo.interfaces.transport.Transport;
import hse.kpo.interfaces.reports.TransportExporter;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Objects;

public class JsonTransportExporter implements TransportExporter {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void export(List<Transport> transports, Writer writer) throws IOException {
        objectMapper.writeValue(writer, transports);
    }
}
