interface ReportExporter {
    void export(String title, int[] values);
}

class CsvExporter implements ReportExporter {
    @Override
    public void export(String title, int[] values) {
        System.out.print(title + " [CSV]: ");
        if (values == null) {
            System.out.println("No data");
            return;
        }
        for (int i = 0; i < values.length; i++) {
            System.out.print(values[i] + (i < values.length - 1 ? "," : ""));
        }
        System.out.println();
    }
}

class JsonExporter implements ReportExporter {
    @Override
    public void export(String title, int[] values) {
        System.out.print(title + " [JSON]: [");
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                System.out.print(values[i] + (i < values.length - 1 ? ", " : ""));
            }
        }
        System.out.println("]");
    }
}

class TextExporter implements ReportExporter {
    @Override
    public void export(String title, int[] values) {
        System.out.print(title + " [TXT]: ");
        if (values == null) {
            System.out.println("Empty");
            return;
        }
        for (int val : values) {
            System.out.print(val + " ");
        }
        System.out.println();
    }
}

public class ReportExporterFactory {
    static ReportExporter createExporter(String format) {
        if ("csv".equalsIgnoreCase(format)) {
            return new CsvExporter();
        } else if ("json".equalsIgnoreCase(format)) {
            return new JsonExporter();
        }
        return new TextExporter();
    }

    static void exportReport(ReportExporter exporter, String title, int[] values) {
        exporter.export(title, values);
    }

    public static void main(String[] args) {
        int[] data = {10, 20, 30};
        
        ReportExporter exp1 = createExporter("csv");
        ReportExporter exp2 = createExporter("json");
        ReportExporter exp3 = createExporter("unknown");

        exportReport(exp1, "Sales", data);
        exportReport(exp2, "Users", data);
        exportReport(exp3, "Logs", data);
        exportReport(exp1, "EmptyReport", null);
    }
}