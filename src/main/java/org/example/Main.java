package org.example;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

class Account{
    String name, password;
}

class Commodity{
    String name, price;
}

public class Main{
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        String Input = "";

        Account adm = new Account(); //管理员账号
        adm.name = "qinghao";
        adm.password = "2023";

        int enrollnums = 0; //用户账号
        Account[] accounts = new Account[10];
        for(int i = 0; i < 10; i++){
            accounts[i] = new Account();
        }

        while(true){
            System.out.println("****************************************");
            System.out.println("请选择您的身份");
            System.out.println("1、管理员");
            System.out.println("2、用户");
            System.out.println("3、退出应用");
            System.out.print("请输入您的身份(请输入序号) >");
            Input = scanner.nextLine();

            while(true){
                if(Input.equals("1")){
                    Administrator administrator = new Administrator();
                    administrator.login(adm, enrollnums, accounts);
                    break;
                }else if(Input.equals("2")){
                    User user = new User();
                    user.enrollAndLogin(enrollnums, accounts);
                    break;
                }else if(Input.equals("3")){
                    break;
                }else {
                    System.out.print("您输入的不是上述序号,请重新输入 >");
                    Input = scanner.nextLine();
                }
            }

            if(Input.equals("3")){
                break;
            }
        }

        scanner.close();
        System.out.println("OK.");
    }
}

class Administrator {
    public static void login(Account adm, int enrollnums, Account[] accounts){ //管理员登录
        Scanner scanner = new Scanner(System.in);
        String admname = "";
        String password = "";

        System.out.println("****************************************");
        System.out.println("您已进入登录页面");
        while(true){
            System.out.print("请输入您的用户名 >");
            admname = scanner.nextLine();
            System.out.print("请输入您的密码 >");
            password = scanner.nextLine();

            if(admname.equals(adm.name) && password.equals(adm.password)){
                System.out.println("登录成功!");
                Administrator admi = new Administrator();
                admi.home(adm, enrollnums, accounts);
                break;
            }else{
                System.out.println("您输入的用户名或密码有误,请重新输入");
            }
        }
    }

    private static void home(Account adm, int enrollnums, Account[] accounts) {
        Scanner scanner = new Scanner(System.in);
        String administratorInput = "";

        while(true) {
            System.out.println("****************************************");
            System.out.println("您当前在管理员应用首页");
            System.out.println("1、密码管理");
            System.out.println("2、客户管理");
            System.out.println("3、商品管理");
            System.out.println("4、退出登录");
            System.out.print("请输入您的指令(请输入序号) >");
            administratorInput = scanner.nextLine();
            System.out.println("");
            System.out.println("");

            while(true){
                if(administratorInput.equals("1")) {
                    Administrator.password(adm, enrollnums, accounts);
                    break;
                }else if (administratorInput.equals("2")) {
                    Administrator.client();
                    break;
                }else if (administratorInput.equals("3")) {
                    Administrator.commodity();
                    break;
                }else if (administratorInput.equals("4")) {
                    break;
                }else {
                    System.out.print("您输入的不是上述序号,请重新输入 >");
                    administratorInput = scanner.nextLine();
                }
            }

            if(administratorInput.equals("4")){
                break;
            }
        }
    }

    private static void password(Account adm, int enrollnums, Account[] accounts){ //密码管理
        Scanner scanner = new Scanner(System.in);
        String administratorInput = "";

        while(true){
            System.out.println("****************************************");
            System.out.println("您已进入密码管理页面");
            System.out.println("1、修改自身密码");
            System.out.println("2、重置用户密码");
            System.out.println("3、返回首页");
            System.out.print("请输入您的指令(请输入序号) >");
            administratorInput = scanner.nextLine();
            System.out.println("");
            System.out.println("");

            while(true){
                if(administratorInput.equals("1")) {
                    Administrator.revisePassword(adm);
                    break;
                }else if (administratorInput.equals("2")) {
                    Administrator.reset(enrollnums, accounts);
                    break;
                }else if (administratorInput.equals("3")) {
                    break;
                }else {
                    System.out.print("您输入的不是上述序号,请重新输入 >");
                    administratorInput = scanner.nextLine();
                }
            }

            if(administratorInput.equals("3")){
                break;
            }
        }
    }

    private static void revisePassword(Account adm){ //修改管理员自身密码
        Scanner scanner = new Scanner(System.in);
        String passwordold = "";
        String passwordnew = "";

        while(true){
            System.out.print("请输入原密码 >");
            passwordold = scanner.nextLine();
            System.out.print("请输入新密码 >");
            passwordnew = scanner.nextLine();

            if(!passwordold.equals(adm.password)){
                System.out.println("原密码有误,请重新输入");
            }else{
                break;
            }
        }

        System.out.println("修改成功!");
    }

    private static void reset(int enrollnums, Account[] accounts){ //重置用户密码
        Scanner scanner = new Scanner(System.in);
        String username = "";

        while(true){
            int fact = 0;

            System.out.print("请输入要重置密码的用户的用户名 >");
            username = scanner.nextLine();

            for(int i = 0; i < enrollnums; i++){
                if(username.equals(accounts[i])){
                    accounts[i].password = "0000";
                    System.out.println("重置成功!");
                    fact = 1;
                    break;
                }
            }
            System.out.println("您输入的用户名有误或该用户尚未注册");

            if(fact == 1){
                break;
            }
        }
    }

    private static void client(){
        Scanner scanner = new Scanner(System.in);
        String administratorInput = "";

        while(true) {
            System.out.println("****************************************");
            System.out.println("您已进入客户管理页面");
            System.out.println("1、列出所有客户信息");
            System.out.println("2、删除客户信息");
            System.out.println("3、查询客户信息");
            System.out.println("4、返回首页");
            System.out.print("请输入您的指令(请输入序号) >");
            administratorInput = scanner.nextLine();
            System.out.println("");
            System.out.println("");

            while(true){
                if(administratorInput.equals("1")) {
                    Administrator.listClient();
                    break;
                }else if (administratorInput.equals("2")) {
                    Administrator.deleteClient();
                    break;
                }else if (administratorInput.equals("3")) {
                    Administrator.inquireClient();
                    break;
                }else if (administratorInput.equals("4")) {
                    break;
                }else {
                    System.out.print("您输入的不是上述序号,请重新输入 >");
                    administratorInput = scanner.nextLine();
                }
            }

            if(administratorInput.equals("4")){
                break;
            }
        }
    }

    private static void listClient(){
        System.out.println("罗列成功!");
    }

    private static void deleteClient(){
        System.out.println("删除成功!");
    }

    private static void inquireClient(){
        System.out.println("查询成功!");
    }

    private static void commodity(){
        Scanner scanner = new Scanner(System.in);
        String administratorInput = "";

        while(true) {
            System.out.println("****************************************");
            System.out.println("您已进入商品管理页面");
            System.out.println("1、列出所有商品信息");
            System.out.println("2、添加商品信息");
            System.out.println("3、修改商品信息");
            System.out.println("4、删除商品信息");
            System.out.println("5、查询商品信息");
            System.out.println("6、返回首页");
            System.out.print("请输入您的指令(请输入序号) >");
            administratorInput = scanner.nextLine();
            System.out.println("");
            System.out.println("");

            while(true){
                if(administratorInput.equals("1")) {
                    Administrator.listCommodity();
                    break;
                }else if (administratorInput.equals("2")) {
                    Administrator.add();
                    break;
                }else if (administratorInput.equals("3")) {
                    Administrator.reviseCommodity();
                    break;
                }else if (administratorInput.equals("4")) {
                    Administrator.deleteCommodity();
                    break;
                }else if (administratorInput.equals("5")) {
                    Administrator.inquireCommodity();
                    break;
                }else if (administratorInput.equals("6")) {
                    break;
                }else {
                    System.out.print("您输入的不是上述序号,请重新输入 >");
                    administratorInput = scanner.nextLine();
                }
            }

            if(administratorInput.equals("6")){
                break;
            }
        }
    }

    private static void listCommodity(){
        System.out.println("罗列成功!");
    }

    private static void add(){
        System.out.println("添加成功!");
    }

    private static void reviseCommodity(){
        System.out.println("修改成功!");
    }

    private static void deleteCommodity(){
        System.out.println("删除成功!");
    }

    private static void inquireCommodity(){
        System.out.println("查询成功!");
    }
}

class User{
    public static void enrollAndLogin(int enrollnums, Account[] accounts){
        Scanner scanner = new Scanner(System.in);
        String userInput = "";

        System.out.println("****************************************");
        System.out.println("您已进入注册登录页面");
        System.out.println("1、注册");
        System.out.println("2、登录");
        System.out.print("请输入您的指令(请输入序号) >");
        userInput = scanner.nextLine();
        System.out.println("");
        System.out.println("");

        while(true){
            if(userInput.equals("1")) {
                User.enroll(enrollnums, accounts);
                enrollnums++;
                break;
            }else if (userInput.equals("2")) {
                User.login(enrollnums, accounts);
                break;
            }else {
                System.out.print("您输入的不是上述序号,请重新输入 >");
                userInput = scanner.nextLine();
            }
        }

        User user = new User();
        user.home();
    }

    private static void enroll(int enrollnums, Account[] accounts){ //用户注册
        Scanner scanner = new Scanner(System.in);
        int k = 0;

        if(enrollnums == 2){
            System.out.println("对不起,注册人数已达上限,无法再进行注册");
            return;
        }

        System.out.println("****************************************");
        System.out.println("您已进入注册页面");
        System.out.print("请输入用户名 >");
        accounts[enrollnums].name = scanner.nextLine();
        System.out.print("请输入密码 >");
        accounts[enrollnums].password = scanner.nextLine();

        ;;
        System.out.println("注册成功!您已成为第"+(enrollnums+1)+"位用户");
    }

    private static void login(int enrollnums, Account[] accounts){ //用户登录
        Scanner scanner = new Scanner(System.in);
        String username = "";
        String password = "";

        System.out.println("****************************************");
        System.out.println("您已进入登录页面");
        while(true){
            int fact = 0;
            System.out.print("请输入您的用户名 >");
            username = scanner.nextLine();
            System.out.print("请输入您的密码 >");
            password = scanner.nextLine();

            for(int i = 0; i < enrollnums+1; i++){
                if(username.equals(accounts[i].name) && password.equals(accounts[i].password)){
                    System.out.println("登录成功!");
                    fact = 1;
                    break;
                }else{
                    System.out.println("您输入的用户名或密码有误,请重新输入");
                    break;
                }
            }

            if(fact == 1){
                break;
            }
        }
    }

    public static void home() {
        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        int enrollnums = 0;
        Account[] accounts = new Account[10];
        for(int i = 0; i < 10; i++){
            accounts[i] = new Account();
        }

        while(true) {
            System.out.println("****************************************");
            System.out.println("您当前在用户应用首页");
            System.out.println("1、密码管理");
            System.out.println("2、购物");
            System.out.println("3、退出登录");
            System.out.print("请输入您的指令(请输入序号) >");
            userInput = scanner.nextLine();
            System.out.println("");
            System.out.println("");

            while(true){
                if (userInput.equals("1")) {
                    User.password();
                    break;
                }else if (userInput.equals("2")) {
                    User.shopping();
                    break;
                }else if (userInput.equals("3")) {
                    break;
                }else {
                    System.out.print("您输入的不是上述序号,请重新输入 >");
                    userInput = scanner.nextLine();
                }
            }

            if(userInput.equals("3")){
                break;
            }
        }
    }

    private static void password(){
        Scanner scanner = new Scanner(System.in);
        String administratorInput = "";

        while(true){
            System.out.println("****************************************");
            System.out.println("您已进入密码管理页面");
            System.out.println("1、修改密码");
            System.out.println("2、重置密码");
            System.out.println("3、返回首页");
            System.out.print("请输入您的指令(请输入序号) >");
            administratorInput = scanner.nextLine();
            System.out.println("");
            System.out.println("");

            while(true){
                if(administratorInput.equals("1")) {
                    User.revisePassword();
                    break;
                }else if (administratorInput.equals("2")) {
                    User.reset();
                    break;
                }else if (administratorInput.equals("3")) {
                    break;
                }else {
                    System.out.print("您输入的不是上述序号,请重新输入 >");
                    administratorInput = scanner.nextLine();
                }
            }

            if(administratorInput.equals("3")){
                break;
            }
        }
    }

    private static void revisePassword(){
        System.out.println("修改成功!");
    }

    private static void reset(){
        System.out.println("重置成功!");
    }

    private static void shopping(){
        Scanner scanner = new Scanner(System.in);
        String userInput = "";

        while(true) {
            System.out.println("****************************************");
            System.out.println("您已进入购物页面");
            System.out.println("1、将商品加入购物车");
            System.out.println("2、从购物车中移除商品");
            System.out.println("3、修改购物车中的商品");
            System.out.println("4、模拟结账");
            System.out.println("5、查看购物历史");
            System.out.println("6、返回首页");
            System.out.print("请输入您的指令(请输入序号) >");
            userInput = scanner.nextLine();
            System.out.println("");
            System.out.println("");

            while(true){
                if(userInput.equals("1")) {
                    User.add();
                    break;
                }else if (userInput.equals("2")) {
                    User.delete();
                    break;
                }else if (userInput.equals("3")) {
                    User.reviseCommodity();
                    break;
                }else if (userInput.equals("4")) {
                    User.mock();
                    break;
                }else if (userInput.equals("5")) {
                    User.view();
                    break;
                }else if (userInput.equals("6")) {
                    break;
                }else {
                    System.out.print("您输入的不是上述序号,请重新输入 >");
                    userInput = scanner.nextLine();
                }
            }

            if(userInput.equals("6")){
                break;
            }
        }
    }

    private static void add(){
        System.out.println("添加成功!");
    }

    private static void delete(){
        System.out.println("删除成功!");
    }

    private static void reviseCommodity(){
        System.out.println("修改成功!");
    }

    private static void mock(){
        System.out.println("模拟成功!");
    }

    private static void view(){
        System.out.println("查看成功!");
    }
}


