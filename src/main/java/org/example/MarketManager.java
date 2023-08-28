package org.example;

import java.util.Scanner;

public class MarketManager {
    public static void main(String[] args) {
        int authority=-1;//0->admin  1->user
        int act=-1,userResId;
        String username,userPassword,adminName,adminPassword,goodsName;
        int userIndex=-1,adminIndex = -1,goodsIndex;
        Scanner sc = new Scanner(System.in);
        Goods g1=new Goods(1,10,"笔记本",100);
        Goods g2=new Goods(2,2,"西红柿",50);
        Goods g3=new Goods(3,5,"辣条",30);
        Goods []goods=new Goods[Goods.maxSize];
        goods[0]=g1; goods[1]=g2; goods[2]=g3;
        Admin a1=new Admin("aa1","123");
        Admin a2=new Admin("aa2","234");
        Admin []admins=new Admin[Admin.maxSize];
        admins[0]=a1; admins[1]=a2;
        User u1=new User(1,"uu1","345");
        User u2=new User(2,"uu2","456");
        User []users=new User[User.maxSize];
        users[0]=u1; users[1]=u2;

        while (true){
            if(authority==-1){
                System.out.println("===============超市管理系统登陆界面==============");
                System.out.println("1.用户登录 2.管理员登录 3.用户注册 4.退出系统");
                System.out.println("请输入要执行的操作");
                try{
                    act=sc.nextInt();
                }
                catch (Exception e){
                    System.out.println("非法输入，请按提示输入操作");
                    sc.next();
                    act=-1;
                }
                switch (act){
                    case 1:
                        System.out.println("请输入用户名");
                        username=sc.next();
                        if(u1.findUserName(users,username)!=-1){
                            userIndex=u1.findUserName(users,username);
                            System.out.println("请输入密码");
                            userPassword=sc.next();
                            if(users[userIndex].password.equals(userPassword)){
                                System.out.println("用户登陆成功!");
                                authority=1;
                            }
                            else{
                                System.out.println("密码错误!");
                            }
                        }
                        else
                            System.out.println("未找到该用户名!");
                        break;
                    case 2:
                        System.out.println("请输入管理员账号");
                        adminName=sc.next();
                        if(a1.findAdminName(admins,adminName)!=-1){
                            adminIndex=a1.findAdminName(admins,adminName);
                            System.out.println("请输入密码");
                            adminPassword=sc.next();
                            if(admins[adminIndex].password.equals(adminPassword)){
                                System.out.println("管理员登陆成功!");
                                authority=0;
                            }
                            else{
                                System.out.println("密码错误!");
                            }
                        }
                        else
                            System.out.println("未找到该管理员账号!");
                        break;
                    case 3:
                        u1.userSignUp(users);
                        break;
                    case 4:
                        return;
                }
            }
            if (authority==0) {
                System.out.println("***********************超市管理系统管理员界面***********************");
                System.out.println("1.货物清单 2.增加商品 3.删除商品 4.修改商品 5.查找商品 6.用户列表");
                System.out.println("7.查找用户 8.删除用户 9.更改密码 10.修改用户密码 0.退出登录");
                System.out.print("请输入要执行的操作：");
                try{
                    act=sc.nextInt();
                }
                catch (Exception e){
                    System.out.println("非法输入，请按提示输入操作");
                    sc.next();
                    act=-1;
                }
                switch (act){
                    case 1:
                        g1.printAllGoods(goods);
                        break;
                    case 2:
                        g1.add(goods);
                        break;
                    case 3:
                        g1.del(goods);
                        break;
                    case 4:
                        g1.change(goods);
                        break;
                    case 5:
                        g1.lookUpGoods(goods);
                        break;
                    case 6:
                        u1.printAllUser(users);
                        break;
                    case 7:
                        u1.lookUpUser(users);
                        break;
                    case 8:
                        u1.del(users);
                        break;
                    case 9:
                        a1.adminResetPassword(admins[adminIndex]);
                        break;
                    case 10:
                        System.out.println("重置用户密码：请输入要重置用户的ID");
                        try {
                            userResId = sc.nextInt();
                            userIndex = u1.findUserId(users, userResId);
                            if(userIndex!=-1){
                                u1.userResetPassword(users[userIndex]);
                            }
                            else{
                                System.out.println("未找到该用户，修改密码失败!");
                            }
                        }catch (Exception e){
                            System.out.println("非法输入，请按提示输入");
                            sc.next();
                            userResId=-1;
                        }
                        break;
                    case 0:
                        authority=-1;
                        break;
                }
            }
            if (authority==1) {
                System.out.println("=======================超市管理系统用户界面======================");
                System.out.println("1.商品清单 2.查找商品 3.将商品加入购物车 4.从购物车移除商品 5.修改购物车中的商品数量");
                System.out.println("6.查看购物车 7.结账 8.查看购物历史 9.修改密码 0.退出登录");
                System.out.print("请输入要执行的操作：");
                try{
                    act=sc.nextInt();
                }
                catch (Exception e){
                    System.out.println("非法输入，请按提示输入操作");
                    sc.next();
                    act=-1;
                }
                switch (act){
                    case 1:
                        g1.printAllGoods(goods);
                        break;
                    case 2:
                        g1.lookUpGoods(goods);
                        break;
                    case 3:
                        users[userIndex].addTrolley(goods,users[userIndex].trolley);
                        break;
                    case 4:
                        users[userIndex].delTrolley(users[userIndex].trolley);
                        break;
                    case 5:
                        users[userIndex].changeTrolleyNum(goods,users[userIndex].trolley);
                        break;
                    case 6:
                        users[userIndex].printTrolley(users[userIndex].trolley);
                        break;
                    case 7:
                        users[userIndex].checkOut(goods,users[userIndex].trolley,users[userIndex].history);
                        break;
                    case 8:
                        users[userIndex].printHistory(users[userIndex].history);
                        break;
                    case 9:
                        users[userIndex].userResetPassword(users[userIndex]);
                        break;
                    case 0:
                        authority=-1;
                        break;
                }
            }
        }


    }
}





