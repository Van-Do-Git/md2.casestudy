package model;

public class Account {
    private String user;
    private String pass;

    public Account() {
    }

    public Account withUser(String user) {
        this.user = user;
        return this;
    }

    public Account withPass(String pass) {
        this.pass = pass;
        return this;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Account) {
            Account anotherAcc = (Account) obj;
            if (this.user.equals(anotherAcc.user))
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Account{" +
                "user='" + user + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}
