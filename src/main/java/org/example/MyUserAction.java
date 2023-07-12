package org.example;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class MyUserAction implements MyAction {

    private static final String ACTION_NAME = "user";
    private Scanner scanner = null;
    List<MyAction> actionList = new ArrayList<MyAction>();

    public MyUserAction(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String getActionName() {
        return MyUserAction.ACTION_NAME;
    }

    @Override
    public void run(String[] args) {
        System.out.println("****************************************");
        System.out.println("您已进入用户子菜单");

        MyUserManager userManager = new MyUserManager();

        MyUserRegisterAction userRegister = new MyUserRegisterAction(scanner, userManager); //注册
        actionList.add(userRegister);

        MyUserLoginAction userLogin = new MyUserLoginAction(scanner, userManager); //登录
        actionList.add(userLogin);

        String userInput = "";

        while(true) {
            System.out.println("1、Register");
            System.out.println("2、Login");
            System.out.print("请输入您的指令 q退出 >");
            userInput = this.scanner.nextLine();
            System.out.println("\n\n");

            String actionName = null;
            for(MyAction oneAction: actionList) {
                actionName = oneAction.getActionName();

                if (userInput.equalsIgnoreCase(actionName)) {
                    oneAction.run(null);
                }
            }

            if (userInput.equals("q")) {
                break;
            }
        }
    }
}
