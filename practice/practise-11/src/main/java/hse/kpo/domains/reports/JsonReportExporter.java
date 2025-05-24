package hse.kpo.domains.reports;

import com.fasterxml.jackson.databind.ObjectMapper;
import hse.kpo.interfaces.reports.ReportExporter;
import java.io.IOException;
import java.io.Writer;

/**
 * class of json report exporter.
 */
public class JsonReportExporter implements ReportExporter {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void export(Report report, Writer writer) throws IOException {
        objectMapper.writeValue(writer, report);
    }
}