abstract class MediaFile {
    private String filename;

    MediaFile(String filename) {
        this.filename = filename;
    }

    String getFilename() {
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
    ImageFile(String filename) {
        super(filename);
    }

    @Override
    public void compress() {
        System.out.println("Compressing image: " + getFilename());
    }
}

class AudioFile extends MediaFile implements Playable {
    AudioFile(String filename) {
        super(filename);
    }

    @Override
    public void play() {
        System.out.println("Playing audio: " + getFilename());
    }
}

class VideoFile extends MediaFile implements Playable, Compressible {
    VideoFile(String filename) {
        super(filename);
    }

    @Override
    public void play() {
        System.out.println("Playing video: " + getFilename());
    }

    @Override
    public void compress() {
        System.out.println("Compressing video: " + getFilename());
    }
}

public class MediaProcessingSystem {
    public static void main(String[] args) {
        MediaFile[] files = {
            new ImageFile("photo.jpg"),
            new AudioFile("music.mp3"),
            new VideoFile("movie.mp4")
        };

        for (MediaFile file : files) {
            System.out.println("Processing " + file.getFilename() + "...");
            if (file instanceof Playable playable) {
                playable.play();
            }
            if (file instanceof Compressible compressible) {
                compressible.compress();
            }
            System.out.println();
        }
    }
}