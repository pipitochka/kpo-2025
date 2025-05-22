package hse.kpo.domains.exporter;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import hse.kpo.interfaces.reports.TransportExporter;
import hse.kpo.interfaces.transport.Transport;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

/**
 * class of markdown transport exporter.
 */
public class XmlTransportExporter implements TransportExporter {
    private final XmlMapper xmlMapper = new XmlMapper();

    @Override
    public void export(List<Transport> transports, Writer writer) throws IOException {
        xmlMapper.writeValue(writer, transports);
    }
}
