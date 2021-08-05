package controller;

import model.Song;
import storage.FileManager;
import storage.IFileManager;

import java.util.ArrayList;
import java.util.List;

public class SongManager {
    private static SongManager instance;
    private List<Song> songList = new ArrayList<>();
    public static final String FILE_PATH_SONG = "src/storage/songs.txt";
    public static final IFileManager<Song> fileManager = FileManager.getIntance();

    private SongManager() {
        this.songList = fileManager.readFile(FILE_PATH_SONG);
    }

    public static SongManager getInstance() {
        if (instance == null) {
            instance = new SongManager();
        }
        return instance;
    }

    public List<Song> getSongList() {
        return songList;
    }

    public void setSongList(List<Song> songList) {
        this.songList = songList;
        fileManager.writeFile(songList, FILE_PATH_SONG);
    }

    public boolean addSong(Song song) {
        if (songList.contains(song)) {
            return false;
        } else {
            songList.add(song);
            fileManager.writeFile(songList, FILE_PATH_SONG);
            return true;
        }
    }

    public void edit(int index, Song song) {
        songList.remove(index);
        songList.add(index, song);
        fileManager.writeFile(songList, FILE_PATH_SONG);
    }

    public void delete(int index) {
        songList.remove(index);
        fileManager.writeFile(songList, FILE_PATH_SONG);
    }
}
