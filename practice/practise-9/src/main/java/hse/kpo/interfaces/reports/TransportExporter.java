package hse.kpo.interfaces.reports;

import hse.kpo.interfaces.transport.Transport;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public interface TransportExporter {
    void export(List<Transport> transports, Writer writer) throws IOException;
}