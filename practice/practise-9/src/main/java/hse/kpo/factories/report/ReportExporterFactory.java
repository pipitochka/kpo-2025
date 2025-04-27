package hse.kpo.factories.report;

import hse.kpo.domains.exporter.CsvTransportExporter;
import hse.kpo.domains.exporter.XmlTransportExporter;
import hse.kpo.domains.reports.JsonReportExporter;
import hse.kpo.domains.reports.MarkdownReportExporter;
import hse.kpo.enums.ReportFormat;
import hse.kpo.interfaces.reports.ReportExporter;
import org.springframework.stereotype.Component;

@Component
public class ReportExporterFactory {
    public ReportExporter create(ReportFormat format) {
        return switch (format) {
            case JSON -> new JsonReportExporter();
            case MARKDOWN -> new MarkdownReportExporter();
            default -> throw new IllegalArgumentException("Unsupported format: " + format);
        };
    }
}
