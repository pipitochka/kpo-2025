package hse.kpo.interfaces.reports;

import java.io.IOException;
import java.io.Writer;
import hse.kpo.domains.reports.Report;

public interface ReportExporter {
    void export(Report report, Writer writer) throws IOException;
}