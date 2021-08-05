package view;

import controller.SongManager;
import model.FactorySong;
import model.KindOfSong;
import model.Song;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SongClient {
    public static SongManager songManager = SongManager.getInstance();

    public static void managerSong() {
        String chose;
        String regexChose = "^[1-5]+$";
        Scanner scanner = new Scanner(System.in);
        boolean checkout;
        do {
            System.out.println("Menu:");
            System.out.println("1: to add newSong");
            System.out.println("2: to edit song");
            System.out.println("3: to delete song");
            System.out.println("4: to showAll song");
            System.out.println("5: to exit");
            chose = scanner.nextLine();
            checkout = validate(chose, regexChose) && chose.equals("5");
            switch (chose) {
                case "1":
                    if (addNewSong()) {
                        System.out.println("add sucessful");
                    } else
                        System.out.println("the song is exist");
                    break;
                case "2":
                    if (editSong()) {
                        System.out.println("edit sucessful");
                    } else
                        System.out.println("is duplicate");
                    break;
                case "3":
                    if (deleteSong()) {
                        System.out.println("delete sucessful");
                    } else
                        System.out.println("not found");
                    break;
                case "4":
                    showAllSong();
                    break;
            }
        } while (!checkout);
    }

    public static boolean validate(String string, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

    public static Song creatSong() {
        FactorySong factorySong = new FactorySong();
        Song song = null;
        String nameSong;
        String author;
        String chose;
        String regexChose = "^[1-3]+$";
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("KindofSong:");
            System.out.println("1: to creat Rock");
            System.out.println("2: to creat Rap");
            System.out.println("3: to creat class");
            chose = scanner.nextLine();
            switch (chose) {
                case "1":
                    song = factorySong.creatSong(KindOfSong.ROCK);
                    break;
                case "2":
                    song = factorySong.creatSong(KindOfSong.RAP);
                    break;
                case "3":
                    song = factorySong.creatSong(KindOfSong.CLASSIC);
                    break;
                default:
                    break;
            }
        } while (!validate(chose, regexChose));
        String regexData = "\\b.*$";
        boolean checkdata;
        do {
            System.out.println("input name song");
            nameSong = scanner.nextLine();
            System.out.println("input author");
            author = scanner.nextLine();
            checkdata = validate(nameSong, regexData) && validate(author, regexData);
        } while (!checkdata);

        return song.withNameSong(nameSong).withAuthor(author);
    }

    public static boolean addNewSong() {
        Song newSong = creatSong();
        return songManager.addSong(newSong);
    }

    public static boolean editSong() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("input name song are you want to edit");
        String nameOfOldSong = scanner.nextLine();
        System.out.println("input info of song to edit");
        Song newSong = creatSong();
        int index = songManager.search(nameOfOldSong);
        return songManager.edit(index, newSong);
    }

    public static boolean deleteSong() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("input name song are you want to delete");
        String nameOfOldSong = scanner.nextLine();
        int index = songManager.search(nameOfOldSong);
        return songManager.delete(index);
    }

    public static Song returSongInList(String name) {
        int index = songManager.search(name);
        if (index != -1) {
            return songManager.getSongList().get(index);
        } else
            return null;
    }

    public static void showAllSong() {
        for (Song song : songManager.getSongList()) {
            System.out.println(song);
        }
    }
}

