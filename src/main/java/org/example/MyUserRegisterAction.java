package org.example;

import java.util.Scanner;
// import Login.Login;
// import pojo.SignIn;

public class MyUserRegisterAction implements MyAction {

    private static final String ACTION_NAME = "register";

    private Scanner scanner = null;
    private MyUserManager userManager = null;

    public MyUserRegisterAction(Scanner scanner, MyUserManager userManager) {
        this.scanner = scanner;
        this.userManager = userManager;
    }

    @Override
    public String getActionName() {
        return MyUserRegisterAction.ACTION_NAME;
    }

    @Override
    public void run(String[] args) {
        System.out.println("****************************************");
        System.out.println("现在你在用户注册子菜单里.");
        while (true) {
            System.out.print("请输入用户名:");
            String username = this.scanner.nextLine();

            System.out.print("请输入密码:");
            String password = this.scanner.nextLine();

            boolean success = this.userManager.registerUser(username, password);
            
            if (success) {
                break;
            }
        }
    }
}

class MyUserLoginAction implements MyAction {

    private static final String ACTION_NAME = "login";

    private Scanner scanner = null;
    private MyUserManager userManager = null;

    public MyUserLoginAction(Scanner scanner, MyUserManager userManager) {
        this.scanner = scanner;
        this.userManager = userManager;
    }

    @Override
    public String getActionName() {
        return MyUserLoginAction.ACTION_NAME;
    }

    @Override
    public void run(String[] args) {
        System.out.println("****************************************");
        System.out.println("现在你在用户登录子菜单里.");

        System.out.print("用户名：");
        String username = this.scanner.nextLine();

        System.out.print("密码:");
        String password = this.scanner.nextLine();

        boolean success = this.userManager.login(username, password);

        if (success) {
            System.out.println("登录成功!\n\n");
        } else {
            System.out.println("登录失败，返回上层目录\n\n");
        }
    }
}
