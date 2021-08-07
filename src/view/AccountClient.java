package view;

import controller.AccountManager;
import controller.AlbumManager;
import model.Account;

import java.util.List;
import java.util.Scanner;

public class AccountClient {
    public static AccountManager accountManager = AccountManager.getInsntance();
    public static Validate validate = Validate.getInstance();

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
            checkoutLogin = validate.validate(choseLogin, regexChoseLogin) && choseLogin.equals("3");
            switch (choseLogin) {
                case "1":
                    int index = login();
                    if (index == -1) {
                        System.out.println("User or pass is not true");
                    } else {
                        AlbumManager albumManager = accountManager.getListAccount().get(index);
                        AlbumClient.manager(albumManager);
                    }
                    break;
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
        accountManager = AccountManager.getInsntance();
        String user;
        String pass;
        Scanner scanner = new Scanner(System.in);
        System.out.println("input user:");
        user = scanner.nextLine();
        System.out.println("input pass");
        pass = scanner.nextLine();
        List<AlbumManager> accountList = accountManager.getListAccount();
        for (int i = 0; i < accountList.size(); i++) {
            boolean checkUser = accountList.get(i).getAccount().getUser().equals(user);
            boolean checkPass = accountList.get(i).getAccount().getPass().equals(pass);
            if (checkUser && checkPass) {
                return i;
            }
        }
        return -1;
    }

    private static boolean singin() {
        Scanner scanner = new Scanner(System.in);
        String user;
        String pass;
        String regexUser = "^[^\\s]{8,12}$";
        String regexPass = "^[0-9]{8,12}$";
        boolean checkData;
        do {
            System.out.println("input user(8-12 ký tự viết liền không dấu)");
            user = scanner.nextLine();
            System.out.println("input pass(8-12 ký tự số viết liền)");
            pass = scanner.nextLine();
            checkData = validate.validate(user, regexUser) && validate.validate(pass, regexPass);
        } while (!checkData);
        Account account = new Account().withUser(user).withPass(pass);
        boolean isIntance = accountManager.checkNameAccountIsIntance(user);//kiểm tra xem tài khoản có bị trùng tên ko?
        if (!isIntance) {
            AlbumManager albumManager = new AlbumManager(account);
            accountManager.aadAccount(albumManager);
            accountManager = AccountManager.getInsntance();
            return true;
        } else {
            return false;
        }
    }
}
