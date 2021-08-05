package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Album implements Serializable {
    String nameAlbum;
    private List<Song> listSong = new ArrayList<>();

    public Album(String nameAlbum) {
        this.nameAlbum = nameAlbum;
    }

    public String getNameAlbum() {
        return nameAlbum;
    }

    public void setNameAlbum(String nameAlbum) {
        this.nameAlbum = nameAlbum;
    }

    public List<Song> getListSong() {
        return listSong;
    }

    public void setListSong(List<Song> list) {
        listSong = list;
    }

    public boolean addSong(Song song) {
        int checkNameSong = searchByNameSong(song.getNameSong());
        if (checkNameSong != -1) {
            try {
                listSong.add(song);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    public boolean editSong(int index, Song song) {
        try {
            listSong.remove(index);
            return listSong.add(song);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public boolean delete(int index) {
        try {
            listSong.remove(index);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public int searchByNameSong(String name) {
        for (int i = 0; i < listSong.size(); i++) {
            if (listSong.get(i).getNameSong().equals(name))
                return i;
        }
        return -1;
    }

    public void showAllSong() {
        for (Song song : listSong) {
            System.out.println(song);
        }
    }
}