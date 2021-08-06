package view;

import controller.AlbumManager;
import model.Album;

import java.util.Scanner;


public class AlbumClient {
    public static SongClient songClient = new SongClient();
    public static Validate validate = Validate.getInstance();

    public static void manager(AlbumManager albumManager) {
        String chose;
        String regexChose = "^[0-9]+$";
        Scanner scanner = new Scanner(System.in);
        boolean checkout;
        do {
            System.out.println("Menu:");
            System.out.println("1: to add newAlbum");
            System.out.println("2: to edit Album");
            System.out.println("3: to delete Album");
            System.out.println("4: to search Album");
            System.out.println("5: to delete a Song of Album");
            System.out.println("6: to add a Song on Album");
            System.out.println("7: to search song by name");
            System.out.println("8: to showAll Album");
            System.out.println("9: to save infor");
            System.out.println("10: to entry SongClient");
            System.out.println("0: to exit");
            chose = scanner.nextLine();
            checkout = validate.validate(chose, regexChose) && chose.equals("0");
            switch (chose) {
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    break;
                case "6":
                    break;
                case "7":
                    break;
                case "8":
                    break;
                case "9":
                    break;
                case "10":
                    break;
                case "0":
                    break;
            }
        } while (!checkout);
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
                System.out.println("try again");
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
            return;
        } else {
            do {
                System.out.println("Input new name");
                newName = scanner.nextLine();
                checkData = validate.validate(newName, regexNewName) && !newName.equals(nameAlbum);
                if (!checkData) {
                    System.out.println("String is emty  duplicate old name");
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
                checkData = chose == "1" || chose == "2";
            } while (!checkData);
            if (chose == "1") {
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
}
