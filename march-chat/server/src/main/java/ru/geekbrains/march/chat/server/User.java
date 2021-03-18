package ru.geekbrains.march.chat.server;

public class User {
    String login;
    String password;
    String nickName;

    public User(String login, String password, String nickName) {
        this.login = login;
        this.password = password;
        this.nickName = nickName;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getNickName() {
        return nickName;
    }

}
