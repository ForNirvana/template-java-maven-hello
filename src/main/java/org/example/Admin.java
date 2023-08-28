package org.example;

import java.util.Scanner;

public class Admin{
    private String adminName;
    String password;
    public static int maxSize=10;

    Admin(String adminName, String password){
        this.adminName = adminName;
        this.password = password;
    }

    public int findAdminName(String adminName){   //查找管理员
        if(adminName.equals(this.adminName)){
            return 1;
        }
        return -1;
    }

    public void adminResetPassword(Admin admin) {   //修改管理员密码
        Scanner sc = new Scanner(System.in);
        System.out.println("修改密码:请输入原始密码");
        String pass = sc.next();
        if (admin.password.equals(pass)) {
            System.out.println("请输入修改后的密码");
            Scanner reader = new Scanner(System.in);
            admin.password = reader.nextLine();
        } else
            System.out.println("原始密码错误，无法修改密码！");
    }
}