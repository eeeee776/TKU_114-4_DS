abstract class MediaFile {
    private String filename;

    public MediaFile(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }
}

interface Playable {
    void play();
}

interface Compressible {
    void compress();
}

class ImageFile extends MediaFile implements Compressible {
    public ImageFile(String filename) { super(filename); }

    @Override
    public void compress() {
        System.out.println(getFilename() + " -> 執行影像有損壓縮...");
    }
}

class AudioFile extends MediaFile implements Playable {
    public AudioFile(String filename) { super(filename); }

    @Override
    public void play() {
        System.out.println(getFilename() + " -> 播放音效...");
    }
}

class VideoFile extends MediaFile implements Playable, Compressible {
    public VideoFile(String filename) { super(filename); }

    @Override
    public void play() {
        System.out.println(getFilename() + " -> 播放影片與聲音...");
    }

    @Override
    public void compress() {
        System.out.println(getFilename() + " -> 執行影片重新編碼壓縮...");
    }
}

public class MediaProcessingSystem {
    public static void main(String[] args) {
        MediaFile[] files = {
            new ImageFile("vacation.jpg"),
            new AudioFile("podcast.mp3"),
            new VideoFile("tutorial.mp4")
        };

        for (MediaFile file : files) {
            System.out.println("處理檔案：" + file.getFilename());
            
            if (file instanceof Playable p) {
                p.play();
            }
            if (file instanceof Compressible c) {
                c.compress();
            }
            System.out.println();
        }
    }
}