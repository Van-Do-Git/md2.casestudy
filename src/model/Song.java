package model;

import java.io.Serializable;

public abstract class Song implements Serializable {
    private String nameSong;
    private String author;

    public Song() {
    }

    public Song withNameSong(String nameSong) {
        this.nameSong = nameSong;
        return this;
    }

    public Song withAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getNameSong() {
        return nameSong;
    }

    public void setNameSong(String nameSong) {
        this.nameSong = nameSong;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Song{" +
                "nameSong='" + nameSong + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Song) {
            return ((Song) obj).getNameSong().equals(this.getNameSong());
        }
        return false;
    }
}
