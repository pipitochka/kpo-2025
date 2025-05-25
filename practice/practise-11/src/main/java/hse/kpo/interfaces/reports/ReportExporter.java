package hse.kpo.interfaces.reports;

import hse.kpo.domains.reports.Report;
import java.io.IOException;
import java.io.Writer;

/**
 * interface of report exporter.
 */
public interface ReportExporter {
    void export(Report report, Writer writer) throws IOException;
}