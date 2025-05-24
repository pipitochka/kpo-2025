package hse.kpo.domains.exporter;

import hse.kpo.interfaces.reports.TransportExporter;
import hse.kpo.interfaces.transport.Transport;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

/**
 * class of markdown transport exporter.
 */
public class MarkdownTransportExporter implements TransportExporter {

    @Override
    public void export(List<Transport> transports, Writer writer) throws IOException {
        transports.forEach(transport -> {
            try {
                writer.write(String.format("""
    <Vehicle>
        <VIN>%d</VIN>
        <Type>%s</Type>
        <Engine>
            <Type>%s</Type>
        </Engine>
    </Vehicle>
                    """,
                        transport.getVin(),
                        transport.getTransportType(),
                        transport.getEngineType()
                ));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
