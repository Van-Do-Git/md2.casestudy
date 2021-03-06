package controller;


import model.Account;
import storage.FileManager;
import storage.IFileManager;

import java.io.Serializable;
import java.util.List;

public class AccountManager implements Obsever, Serializable {
    private static AccountManager insntance;
    public static final String accPath = "src/storage/account.txt";
    public static final IFileManager<AlbumManager> fileManager = FileManager.getIntance();
    private List<AlbumManager> listAccount;

    private AccountManager() {
    }

    public static AccountManager getInsntance() {
        if (insntance == null) {
            insntance = new AccountManager();
            insntance.listAccount = fileManager.readFile(accPath);
        }
        return insntance;
    }

    public List<AlbumManager> getListAccount() {
        return listAccount;
    }

    public void setListAccount(List<AlbumManager> listAccount) {
        this.listAccount = listAccount;
    }

    @Override
    public int searchByAccount(Account account) {
        for (int i = 0; i < listAccount.size(); i++) {
            if (account.equals(listAccount.get(i).getAccount())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void updateAccount(int index, AlbumManager albumManager) {
        listAccount.remove(index);
        listAccount.add(index, albumManager);
        fileManager.writeFile(listAccount, accPath);
    }

    @Override
    public boolean aadAccount(AlbumManager albumManager) {
        if (!checkInstance(albumManager)) {
            listAccount.add(albumManager);
            fileManager.writeFile(listAccount, accPath);
            return true;
        }
        return false;
    }

    @Override
    public boolean checkInstance(AlbumManager albumManager) {
        return listAccount.contains(albumManager);
    }

    @Override
    public boolean checkNameAccountIsIntance(String name) {
        for (int i = 0; i < listAccount.size(); i++) {
            if (name.equals(listAccount.get(i).getAccount().getUser())) {
                return true;
            }
        }
        return false;
    }
}
