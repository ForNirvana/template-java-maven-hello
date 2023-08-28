package org.example;

import java.util.Scanner;

public class Admin {
    private String adminName;
    String password;
    public static int maxSize=10;
    public static int numAdmin=2;
    Admin(String adminName, String password) {
        this.adminName = adminName;
        this.password = password;
    }
    public int findAdminName(Admin[] admins, String adminName){    //遍历，返回下标
        for(int i=0;i<numAdmin;i++){
            if(admins[i].adminName.equals(adminName)){
                return i;
            }
        }
        return -1;
    }
    public void adminResetPassword(Admin admin1) {
        Scanner sc = new Scanner(System.in);
        System.out.println("修改密码:请输入原始密码");
        String pass = sc.next();
        if (admin1.password.equals(pass)) {
            System.out.println("请输入修改后的密码");
            Scanner reader = new Scanner(System.in);
            admin1.password = reader.nextLine();
        } else
            System.out.println("原始密码错误，无法修改密码！");
    }
}