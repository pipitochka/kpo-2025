package hse.kpo.factories.report;

import hse.kpo.domains.exporter.CsvTransportExporter;
import hse.kpo.domains.exporter.JsonTransportExporter;
import hse.kpo.domains.exporter.MarkdownTransportExporter;
import hse.kpo.domains.exporter.XmlTransportExporter;
import hse.kpo.enums.ReportFormat;
import hse.kpo.interfaces.reports.TransportExporter;
import org.springframework.stereotype.Component;

@Component
public class TransportExporterFactory {
    public TransportExporter create(ReportFormat format) {
        return switch (format) {
            case JSON -> new JsonTransportExporter();
            case MARKDOWN -> new MarkdownTransportExporter();
            case CSV -> new CsvTransportExporter();
            case XML -> new XmlTransportExporter();
            default -> throw new IllegalArgumentException("Unsupported format: " + format);
        };
    }
}
