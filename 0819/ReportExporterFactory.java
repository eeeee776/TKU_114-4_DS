interface ReportExporter {
    String export(String title, int[] values);
}

class CsvExporter implements ReportExporter {
    @Override
    public String export(String title, int[] values) {
        StringBuilder sb = new StringBuilder(title).append("\\n");
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                sb.append(values[i]);
                if (i < values.length - 1) sb.append(",");
            }
        }
        return sb.toString();
    }
}

class JsonExporter implements ReportExporter {
    @Override
    public String export(String title, int[] values) {
        StringBuilder sb = new StringBuilder("{\\n  \"title\": \"").append(title).append("\",\\n  \"values\": [");
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                sb.append(values[i]);
                if (i < values.length - 1) sb.append(", ");
            }
        }
        return sb.append("]\\n}").toString();
    }
}

class TextExporter implements ReportExporter {
    @Override
    public String export(String title, int[] values) {
        StringBuilder sb = new StringBuilder("--- ").append(title).append(" ---\\n");
        if (values != null) {
            for (int val : values) {
                sb.append("- ").append(val).append("\\n");
            }
        }
        return sb.toString();
    }
}

public class ReportExporterFactory {
    static ReportExporter createExporter(String format) {
        if ("csv".equalsIgnoreCase(format)) return new CsvExporter();
        if ("json".equalsIgnoreCase(format)) return new JsonExporter();
        return new TextExporter(); // 不支援的格式預設回傳 TextExporter
    }

    static void exportReport(ReportExporter exporter, String title, int[] values) {
        // 主流程完全不依賴具體的 Exporter 類別 (不使用 instanceof)
        System.out.println(exporter.export(title, values));
        System.out.println();
    }

    public static void main(String[] args) {
        int[] data = {150, 300, 450, 600};

        exportReport(createExporter("CSV"), "第一季營收", data);
        exportReport(createExporter("JSON"), "第一季營收", data);
        exportReport(createExporter("XML"), "第一季營收", data); // 測試不支援格式
        
        // 測試 null 安全性
        exportReport(createExporter("csv"), "無資料報表", null);
    }
}