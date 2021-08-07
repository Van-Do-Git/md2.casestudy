package view;

import controller.AlbumManager;
import model.Album;
import model.Song;

import java.util.Scanner;


public class AlbumClient {
    public static Validate validate = Validate.getInstance();

    public static void manager(AlbumManager albumManager) {
        String chose;
        String regexChose = "^[0-9]{1,2}$";
        Scanner scanner = new Scanner(System.in);
        boolean checkout;
        do {
            System.out.println("Menu:");
            System.out.println("1: to add newAlbum");
            System.out.println("2: to edit name Album");
            System.out.println("3: to delete Album");
            System.out.println("4: to search Album");
            System.out.println("5: to delete a Song of Album");
            System.out.println("6: to add a Song on Album");
            System.out.println("7: to search song by name");
            System.out.println("8: to showAll Album");
            System.out.println("9: to save Data");
            System.out.println("10: to entry SongClient");
            System.out.println("11: to showAll song of a Album");
            System.out.println("12: to edit name song of a Album");
            System.out.println("0: to logout");
            chose = scanner.nextLine();
            checkout = validate.validate(chose, regexChose) && chose.equals("0");
            switch (chose) {
                case "1":
                    addAlbum(albumManager);
                    break;
                case "2":
                    editNameAlbum(albumManager);
                    break;
                case "3":
                    deleteAlbum(albumManager);
                    break;
                case "4":
                    searchAlbum(albumManager);
                    break;
                case "5":
                    deleteSongOfAlbum(albumManager);
                    break;
                case "6":
                    addSongOnAlbum(albumManager);
                    break;
                case "7":
                    searchSongByName(albumManager);
                    break;
                case "8":
                    showAllAlbum(albumManager);
                    break;
                case "9":
                    saveData(albumManager);
                    System.out.println("Save data sucessful");
                    break;
                case "10":
                    SongClient.managerSong();
                    break;
                case "11":
                    showAllSongOfAlbum(albumManager);
                    break;
                case "12":
                    editNameSongOfAlbum(albumManager);
                    break;
                case "0":
                    break;
            }
        } while (!checkout);
    }

    private static void showAllSongOfAlbum(AlbumManager albumManager) {
        albumManager.showAllAlbum();
        Scanner scanner = new Scanner(System.in);
        String nameAlbum;
        System.out.println("input name of Album to show all Song");
        nameAlbum = scanner.nextLine();
        int indexAlbum = albumManager.searchByNameAlbum(nameAlbum);
        if (indexAlbum == -1) {
            System.out.println("wrong name of Album!");
        } else {
            albumManager.getAlbumList().get(indexAlbum).showAllSong();
        }
    }

    public static Album creatNewAlbum(AlbumManager albumManager) {
        Scanner scanner = new Scanner(System.in);
        String nameAlbum;
        String regexNameAlbum = "^\\b.*$";
        boolean checkData;
        do {
            System.out.println("input name Album");
            nameAlbum = scanner.nextLine();
            int indexAlbum = albumManager.searchByNameAlbum(nameAlbum);//kiểm tra xem tên album đã tồn tại chưa?
            checkData = validate.validate(nameAlbum, regexNameAlbum) && indexAlbum == -1;
            if (!checkData)
                System.out.println("Album name be duplicate. Try again");
        } while (!checkData);
        return new Album(nameAlbum);
    }

    public static void addAlbum(AlbumManager albumManager) {
        Album newAlbum = creatNewAlbum(albumManager);
        albumManager.addAlbum(newAlbum);
    }

    public static void editNameAlbum(AlbumManager albumManager) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input name album edit");
        String nameAlbum = scanner.nextLine();
        String newName;
        String regexNewName = "^\\b.*$";
        boolean checkData;
        int index = albumManager.searchByNameAlbum(nameAlbum);
        if (index == -1) {
            System.out.println("not fond");
        } else {
            do {
                System.out.println("Input new name");
                newName = scanner.nextLine();
                checkData = validate.validate(newName, regexNewName) && !newName.equals(nameAlbum);
                if (!checkData) {
                    System.out.println("String is emty or duplicate old name");
                    System.out.println("Try again");
                }
            } while (!checkData);
            albumManager.getAlbumList().get(index).setNameAlbum(newName);
            albumManager.showAllAlbum();
        }
    }

    public static void deleteAlbum(AlbumManager albumManager) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input name album delete");
        String name = scanner.nextLine();
        int index = albumManager.searchByNameAlbum(name);
        if (index == -1) {
            System.out.println("not found");
        } else {
            boolean checkData;
            String chose;
            do {
                System.out.println("input 1 to confirm delete");
                System.out.println("input 2 to skip");
                chose = scanner.nextLine();
                checkData = chose.equals("1") || chose.equals("2");
            } while (!checkData);
            if (chose.equals("1")) {
                albumManager.deleteAlbum(index);
                albumManager.showAllAlbum();
            }
        }
    }

    public static int searchAlbum(AlbumManager albumManager) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input name album to search");
        String nameAlbum = scanner.nextLine();
        return albumManager.searchByNameAlbum(nameAlbum);
    }

    public static void deleteSongOfAlbum(AlbumManager albumManager) {
        albumManager.showAllAlbum();
        Scanner scanner = new Scanner(System.in);
        String nameAlbum;
        String nameSong;
        int indexAlbum;
        int indexSong;
        System.out.println("input name of Album to delete a Song");
        nameAlbum = scanner.nextLine();
        indexAlbum = albumManager.searchByNameAlbum(nameAlbum);
        if (indexAlbum == -1) {
            System.out.println("Not found this Album");
        } else {
            albumManager.getAlbumList().get(indexAlbum).showAllSong();
            System.out.println("input name of Song to delete");
            nameSong = scanner.nextLine();
            indexSong = albumManager.getAlbumList().get(indexAlbum).searchByNameSong(nameSong);
            if (indexSong == -1) {
                System.out.println("Not found this Song");
            } else {
                String confirm;
                boolean checkData;
                do {
                    System.out.println("Are you sure to delete?");
                    System.out.println("input yes to delete");
                    System.out.println("input no to skip");
                    confirm = scanner.nextLine();
                    checkData = confirm.equals("yes") || confirm.equals("no");
                } while (!checkData);
                if (confirm.equals("yes")) {
                    albumManager.deleteSong(indexSong, indexAlbum);
                    System.out.println("Delete sucessful");
                }
            }
        }
    }

    public static void editNameSongOfAlbum(AlbumManager albumManager) {
        albumManager.showAllAlbum();
        Scanner scanner = new Scanner(System.in);
        String nameAlbum;
        String nameSong;
        int indexAlbum;
        int indexSong;
        System.out.println("input name of Album to edit name Song");
        nameAlbum = scanner.nextLine();
        indexAlbum = albumManager.searchByNameAlbum(nameAlbum);
        if (indexAlbum == -1) {
            System.out.println("Not found this Album");
        } else {
            albumManager.getAlbumList().get(indexAlbum).showAllSong();
            System.out.println("input name of Song to edit");
            nameSong = scanner.nextLine();
            indexSong = albumManager.getAlbumList().get(indexAlbum).searchByNameSong(nameSong);
            if (indexSong == -1) {
                System.out.println("Not found this Song");
            } else {
                String newName = scanner.nextLine();
                String regexNewName = "^\\b.*$";
                boolean checkData;
                do {
                    System.out.println("Input new name, not emty and not duplicate old name");
                    newName = scanner.nextLine();
                    checkData = newName.equals(nameSong) || !validate.validate(regexNewName, newName);
                    if (checkData) {
                        System.out.println("try again");
                    }
                } while (checkData);
                albumManager.getAlbumList().get(indexAlbum).getListSong().get(indexSong).setNameSong(newName);
                System.out.println("edit name sucessful");
            }
        }
    }

    public static void addSongOnAlbum(AlbumManager albumManager) {
        Scanner scanner = new Scanner(System.in);
        SongClient.showAllSong();
        System.out.println("chose a Song to add on your Album");
        String nameSong = scanner.nextLine();
        String nameAlbum;
        Song newSong = SongClient.returSongInList(nameSong);
        if (newSong == null) {
            System.out.println("try again");
            addSongOnAlbum(albumManager);
        } else {
            albumManager.showAllAlbum();
            System.out.println("chose a Album to add this Song");
            nameAlbum = scanner.nextLine();
            int index = albumManager.searchByNameAlbum(nameAlbum);
            if (index == -1) {
                System.out.println("try again");
                addSongOnAlbum(albumManager);
            } else {
                System.out.println("add sucessful");
                albumManager.addSongs(newSong, index);
            }
        }
    }

    public static void searchSongByName(AlbumManager albumManager) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("input name Song are you want to search");
        String nameSong = scanner.nextLine();
        for (Album album : albumManager.getAlbumList()) {
            for (Song song : album.getListSong()) {
                if (song.getNameSong().equals(nameSong)) {
                    System.out.println(album.getNameAlbum() + song);
                    return;
                }
            }
        }
        System.out.println("not found :))");
    }

    public static void showAllAlbum(AlbumManager albumManager) {
        albumManager.showAllAlbum();
    }

    public static void saveData(AlbumManager albumManager) {
        albumManager.saveInfor();
    }
}
