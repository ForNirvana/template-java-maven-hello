package org.example;

public class MyUserAccounts{

    private String username;
    private String password;

    public MyUserAccounts(String username, String password){
        super();
        this.username = username;
        this.password = password;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getUsername(){
        return username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return password;
    }

    public MyUserAccounts(){}
}