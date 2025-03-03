package hse.kpo.domains.reports;

import hse.kpo.interfaces.reports.ReportExporter;

import java.io.IOException;
import java.io.Writer;

public class JsonReportExporter implements ReportExporter {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void export(Report report, Writer writer) throws IOException {
        objectMapper.writeValue(writer, report);
    }
}