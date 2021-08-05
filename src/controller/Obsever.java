package controller;

import model.Account;

public interface Obsever {
    int searchByAccount(Account account);

    void updateAccount(int index, AlbumManager albumManager);

    boolean aadAccount(AlbumManager albumManager);

    boolean checkInstance(AlbumManager albumManager);
    boolean checkNameAccountIsIntance(String name);
}
