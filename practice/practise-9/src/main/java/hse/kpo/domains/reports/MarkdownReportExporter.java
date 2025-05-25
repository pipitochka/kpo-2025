package hse.kpo.domains.reports;

import hse.kpo.interfaces.reports.ReportExporter;
import java.io.IOException;
import java.io.Writer;

/**
 * class of json markdown exporter.
 */
public class MarkdownReportExporter implements ReportExporter {
    @Override
    public void export(Report report, Writer writer) throws IOException {
        writer.write("# " + report.title() + "\n\n");
        writer.write(report.content());
        writer.flush();
    }
}
