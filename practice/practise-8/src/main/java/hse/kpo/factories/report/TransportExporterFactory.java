package hse.kpo.factories.report;

import hse.kpo.domains.exporter.JsonTransportExporter;
import hse.kpo.domains.exporter.MarkdownTransportExporter;
import hse.kpo.enums.ReportFormat;
import hse.kpo.interfaces.reports.TransportExporter;
import org.springframework.stereotype.Component;

@Component
public class TransportExporterFactory {
    public TransportExporter create(ReportFormat format) {
        return switch (format) {
            case JSON -> new JsonTransportExporter();
            case MARKDOWN -> new MarkdownTransportExporter();
            default -> throw new IllegalArgumentException("Unsupported format: " + format);
        };
    }
}
