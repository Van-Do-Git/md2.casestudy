package view;

import controller.AccountManager;
import controller.AlbumManager;
import controller.SongManager;
import model.Account;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountClient {
    public static AccountManager accountManager = AccountManager.getInsntance();
    public static SongClient songClient = new SongClient();
    public static AlbumClient albumClient = new AlbumClient();

    public static void main(String[] args) {
        String choseLogin;
        String regexChoseLogin = "^[1-3]+$";
        boolean checkoutLogin;
        Scanner scannerLogin = new Scanner(System.in);
        do {
            System.out.println("Menu:");
            System.out.println("1: to login");
            System.out.println("2: to signin");
            System.out.println("3: to exit");
            choseLogin = scannerLogin.nextLine();
            checkoutLogin = validate(choseLogin, regexChoseLogin) && choseLogin.equals("3");
            switch (choseLogin) {
                case "1":
                    if (login() == -1) {
                        System.out.println("User or pass is not true");
                        break;
                    } else {
                        AlbumManager albumManager = accountManager.getListAccount().get(login());
                        albumClient.manager(albumManager);
                    }

                case "2":
                    if (singin()) {
                        System.out.println("singin sucessful,chose 1 to login and then manager your Album");
                    } else {
                        System.out.println("tài khoản đã tồn tại, mời đăng ký lại");
                    }
                    break;
                case "3":
                    System.exit(0);
                    break;
            }
        } while (!checkoutLogin);
        System.out.println("abc");
    }

    private static int login() {
        String user;
        String pass;
        Scanner scanner = new Scanner(System.in);
        System.out.println("input user:");
        user = scanner.nextLine();
        System.out.println("input pass");
        pass = scanner.nextLine();
        Account account = new Account().withUser(user).withUser(pass);
        int indexAccount = accountManager.searchByAccount(account);
        return indexAccount;
    }

    private static boolean singin() {
        String user;
        String pass;
        Scanner scanner = new Scanner(System.in);
        String regexUser = "^[^\\s]{8,12}$";
        String regexPass = "^[0-9]{8,12}$";
        boolean checkData;
        do {
            System.out.println("input user(8-12 ký từ viết liền không dấu)");
            user = scanner.nextLine();
            System.out.println("input pass(số 8 - 12 ký tự số viết liền)");
            pass = scanner.nextLine();
            checkData = validate(user, regexUser) && validate(pass, regexPass);
        } while (!checkData);
        Account account = new Account().withUser(user).withUser(pass);
        boolean isIntance = accountManager.checkNameAccountIsIntance(user);//kiểm tra xem tài khoản có bị trùng tên ko?
        if (!isIntance) {
            AlbumManager albumManager = new AlbumManager(account);
            accountManager.aadAccount(albumManager);
            return true;
        } else {
            return false;
        }
    }

    public static boolean validate(String string, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }
}
