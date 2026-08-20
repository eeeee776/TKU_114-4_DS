interface Exportable { void export(); }
interface Compressible { void compress(); }

class BackupDocument implements Exportable, Compressible {
    @Override
    public void export() { System.out.println("匯出文件資料..."); }

    @Override
    public void compress() { System.out.println("壓縮文件大小..."); }
}

public class DocumentCapabilityDemo {
    public static void main(String[] args) {
        BackupDocument doc = new BackupDocument();
        
        Exportable exp = doc;
        Compressible comp = doc;

        exp.export();
        // exp.compress(); // 編譯錯誤，Exportable 參考只能看到 export()
        
        comp.compress();
        // comp.export();  // 編譯錯誤，Compressible 參考只能看到 compress()
    }
}