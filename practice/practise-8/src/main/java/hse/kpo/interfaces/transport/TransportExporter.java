package hse.kpo.interfaces.transport;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public interface TransportExporter {
    void export(List<Transport> transports, Writer writer) throws IOException;
}