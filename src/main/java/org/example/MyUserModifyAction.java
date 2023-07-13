package org.example;

import java.util.Scanner;

public class MyUserModifyAction implements MyAction {

    private static final String ACTION_NAME = "modify";

    private Scanner scanner = null;
    private MyUserManager userManager = null;

    public MyUserModifyAction(Scanner scanner, MyUserManager userManager) {
        this.scanner = scanner;
        this.userManager = userManager;
    }

    @Override
    public String getActionName() {
        return MyUserModifyAction.ACTION_NAME;
    }

    @Override
    public void run(String[] args) {
        System.out.println("****************************************");
        System.out.println("现在你在用户修改密码子菜单里.");

        System.out.print("请输入你的用户名:");
        String username = this.scanner.nextLine();

        System.out.print("请输入原密码:");
        String passwordOld = this.scanner.nextLine();

        System.out.print("请输入新密码:");
        String passwordNew = this.scanner.nextLine();

        boolean success = this.userManager.Modify(username, passwordOld, passwordNew);

        if(success){
            System.out.println("密码修改成功，返回上层目录\n\n");
        }else {
            System.out.println("密码修改失败，返回上层目录\n\n");
        }
    }
}

class MyUserResetAction implements MyAction {

    private static final String ACTION_NAME = "reset";

    private Scanner scanner = null;
    private MyUserManager userManager = null;

    public MyUserResetAction(Scanner scanner, MyUserManager userManager) {
        this.scanner = scanner;
        this.userManager = userManager;
    }

    @Override
    public String getActionName() {
        return MyUserResetAction.ACTION_NAME;
    }

    @Override
    public void run(String[] args) {
        System.out.println("****************************************");
        System.out.println("现在你在用户重置密码子菜单里.");

        System.out.print("请输入你的用户名:");
        String username = this.scanner.nextLine();

        System.out.print("请输入原密码:");
        String passwordOld = this.scanner.nextLine();

        String passwordNew = "0000";

        boolean success = this.userManager.Modify(username, passwordOld, passwordNew);

        if(success){
            System.out.println("密码重置成功，返回上层目录\n\n");
        }else {
            System.out.println("密码重置失败，返回上层目录\n\n");
        }
    }
}