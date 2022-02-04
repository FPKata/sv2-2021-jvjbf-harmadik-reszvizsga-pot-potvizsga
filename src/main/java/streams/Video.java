package streams;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Video {
    private String title;
    private int length;
    private LocalDate uploadDate;
    private List<String> hashTags;

    public Video(String title, int length, LocalDate uploadDate, List<String> hashTags) {
        this.title = title;
        this.length = length;
        this.uploadDate = uploadDate;
        this.hashTags = hashTags;
    }

    public String getTitle() {
        return title;
    }

    public int getLength() {
        return length;
    }

    public LocalDate getUploadDate() {
        return uploadDate;
    }

    public List<String> getHashTags() {
        return hashTags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Video video = (Video) o;
        return Objects.equals(uploadDate, video.uploadDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uploadDate);
    }
}
