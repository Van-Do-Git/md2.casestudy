package view;

import controller.AlbumManager;
import model.Account;
import model.Album;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AlbumClient {
    public static SongClient songClient = new SongClient();

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
            System.out.println("5: to delete Song of Album");
            System.out.println("6: to add Song of Album");
            System.out.println("7: to showAll Album");
            System.out.println("8: to save infor");
            System.out.println("9: to entry SongClient");
            System.out.println("0: to exit");
            chose = scanner.nextLine();
            checkout = validate(chose, regexChose) && chose.equals("0");
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
            checkData = validate(nameAlbum, regexNameAlbum) && indexAlbum == -1;
            if (!checkData)
                System.out.println("try again");
        } while (!checkData);
        return new Album(nameAlbum);
    }

    public static void addAlbum(AlbumManager albumManager, Album album) {
        albumManager.addAlbum(album);
    }

    public static void editAlbum(AlbumManager albumManager, Album album, String name) {
        albumManager.editAlbum(name, album);
    }

    public static void deleteAlbum(AlbumManager albumManager, String name) {
        int index = albumManager.searchByNameAlbum(name);
        albumManager.deleteAlbum(index);
    }

    public static int searchAlbum(AlbumManager albumManager, String name) {
        return albumManager.searchByNameAlbum(name);
    }



    public static boolean validate(String string, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

}
