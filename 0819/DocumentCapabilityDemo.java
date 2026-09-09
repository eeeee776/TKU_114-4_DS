interface Exportable {
    void export();
}

interface Compressible {
    void compress();
}

class BackupDocument implements Exportable, Compressible {
    private String name;

    BackupDocument(String name) {
        this.name = name;
    }

    @Override
    public void export() {
        System.out.println("Exporting: " + name);
    }

    @Override
    public void compress() {
        System.out.println("Compressing: " + name);
    }
}

public class DocumentCapabilityDemo {
    public static void main(String[] args) {
        BackupDocument doc = new BackupDocument("ProjectData.bak");
        
        Exportable exp = doc;
        Compressible comp = doc;

        exp.export();
        comp.compress();
    }
}