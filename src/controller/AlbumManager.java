package controller;

import model.Account;
import model.Album;
import model.Song;

import java.io.Serializable;
import java.util.*;


public class AlbumManager implements Serializable {
    private Obsever obsever = AccountManager.getInsntance();
    private List<Album> albumList = new ArrayList<>();
    private Account account;

    public AlbumManager() {

    }

    public AlbumManager(Account account) {
        this.account = account;
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

    public void setAccount(Account account) {
        this.account = account;
    }

    public void addAlbum(Album album) {
        albumList.add(album);
    }

    public void editAlbum(String name, Album album) {
        int index = searchByNameAlbum(name);
        if (index != -1) {
            albumList.remove(index);
            albumList.add(album);
        }

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


    public boolean deleteSong(int indexsong, Album album) {
        return album.delete(indexsong);
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

    public void showAllAlbum() {
        for (Album album : albumList) {
            System.out.println(album);
        }
    }


    public boolean saveInfor() {
        int index = obsever.searchByAccount(this.account);
        if (this.account == null || index == -1) {
            return false;
        } else
            obsever.updateAccount(index, this);
        return true;
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
