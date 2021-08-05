package controller;

import model.Account;
import model.Album;
import model.Song;

import java.util.*;


public class AlbumManager {
    private Obsever obsever = AccountManager.getInsntance();
    private String nameManager;
    private List<Album> albumList = new ArrayList<>();
    private Account account;

    public AlbumManager(String nameManager, Account account) {
        this.nameManager = nameManager;
        this.account = account;
    }

    public String getNameManager() {
        return nameManager;
    }

    public void setNameManager(String nameManager) {
        this.nameManager = nameManager;
    }

    public List<Album> getAlbumList() {
        return albumList;
    }

    public void setAlbumList(List<Album> albumList) {
        this.albumList = albumList;
    }

    public Account getAccount() {
        return account;
    }

    public void addAlbum(Album album) {
        albumList.add(album);
    }

    public void editAlbum(Album album) {
        albumList.add(album);
    }

    public void deleteAlbum(int index) {
        albumList.remove(index);
    }

    public int searchByNameAlbum(String name) {
        for (int i = 0; i < albumList.size(); i++) {
            if (albumList.get(i).equals(name)) {
                return i;
            }
        }
        return -1;
    }


    public boolean deleteSong(String nameSong, String nameAlbum) {
        int indexAlbum = searchByNameAlbum(nameAlbum);
        if (indexAlbum != -1) {
            int indexSong = albumList.get(indexAlbum).searchByNameSong(nameSong);
            return albumList.get(indexAlbum).delete(indexSong);
        }
        return false;
    }

    public boolean addSong(Song song, String nameAlbum) {
        int indexAlbum = searchByNameAlbum(nameAlbum);
        if (indexAlbum != -1) {
            return albumList.get(indexAlbum).addSong(song);
        }
        return false;
    }

    public boolean editSong(Song song, String nameAlbum, int indexSong) {
        int indexAlbum = searchByNameAlbum(nameAlbum);
        if (indexAlbum != -1) {
            return albumList.get(indexAlbum).editSong(indexSong, song);
        }
        return false;
    }


    public void saveInfor() {
        int index = obsever.searchByAccount(this.account);
        obsever.updateAccount(index, this);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AlbumManager) {
            AlbumManager another = (AlbumManager) obj;
            if (another.getAccount().equals(this.account))
                return true;
        }
        return false;
    }

}
