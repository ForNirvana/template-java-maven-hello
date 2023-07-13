package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<MyAction> actionList = new ArrayList<MyAction>();

        MyAdminiAction admini = new MyAdminiAction(scanner); //创建管理员对象
        actionList.add(admini);

        MyUserAction user = new MyUserAction(scanner); //创建用户对象
        actionList.add(user);

        String userInput = "";

        while (true) {
            System.out.println("****************************************");
            System.out.println("1、admini");
            System.out.println("2、user");
            System.out.print("请选择您的身份 exit退出 >");
            userInput = scanner.nextLine();
            System.out.println("");

            String actionName = null;
            for(MyAction oneAction: actionList) {
                actionName = oneAction.getActionName();

                if (userInput.equalsIgnoreCase(actionName)) {
                    oneAction.run(null);
                }
            }

            if (userInput.equals("exit")) {
                break;
            }
        }

        scanner.close();
        System.out.println("Done.");
    }
}