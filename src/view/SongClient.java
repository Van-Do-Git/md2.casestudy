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
    public static void main(String[] args) {
        SongManager songManager = SongManager.getInstance();
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
        System.out.println("input name song");
        nameSong = scanner.nextLine();
        System.out.println("input ");
        author = scanner.nextLine();
        return song.withNameSong(nameSong).withAuthor(author);
    }
}
