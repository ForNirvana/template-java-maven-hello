package org.example;

import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;


public class User {
    int userId;   //客户ID
    String userName;   //用户名
    String userLevel;   //用户级别
    String userRegistrationTime;   //用户注册时间
    String userPhone;   //用户手机号
    String userEmail;   //用户邮箱
    String password;   //用户密码

    Goods g1 = new Goods();
    Goods []trolley = new Goods[maxSize];
    Goods [][]history = new Goods[maxHistory][maxSize];
    public static int maxSize = 100;
    public static int numUser = 2;
    public int numTrolley = 0;
    public static int maxHistory = 5;
    public int numHistory = 0;

    User(){}
    
    User(int userId, String username, String userLevel, String userRegistrationTime, String userPhone, String userEmail, String password) {
        this.userId = userId;
        this.userName = username;
        this.userLevel = userLevel;
        this.userRegistrationTime = userRegistrationTime;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
        this.password = password;
    }

    public void quickSortUser(User[] users,int low,int high){
        int i,j;
        User t = new User();
        User temp = new User();
        if(low>high){
            return;
        }
        i = low;
        j = high;
        temp = users[low];
        while (i<j) {
            while (temp.userId <= users[j].userId&&i<j) {
                j--;
            }
            while (temp.userId >= users[i].userId&&i<j) {
                i++;
            }
            if (i<j) {
                t = users[j];
                users[j] = users[i];
                users[i] = t;
            }
        }
        users[low] = users[i];
        users[i] = temp;
        quickSortUser(users, low, j-1);
        quickSortUser(users, j+1, high);
    }

    public int findUserId(User[] users,int userId)   //折半查找，返回用户下标
    {
        quickSortUser(users,0,numUser-1);
        int left = 0;
        int right = numUser-1;
        while(left <= right)
        {
            int mid = (left+right)/2;
            if(users[mid].userId == userId)
            {
                return mid;
            }
            else if(users[mid].userId>userId)
            {
                right = mid-1;
            } else {left = mid+1;}
        }
        return -1;
    }

    public int findUserName(User[] users, String userName){   //遍历，返回下标
        for(int i = 0; i < numUser; i++){
            if(users[i].userName.equals(userName)){
                return i;
            }
        }
        return -1;
    }

    public void userSignUp(User[] users) {   //用户注册
        Scanner sc = new Scanner(System.in);
        int Id = numUser + 1;
        if(findUserId(users,Id) != -1){
            System.out.println("已存在相同ID的用户!");
            for(int i = 1; i < maxSize; i++){
                if(findUserId(users,i) == -1){
                    Id = i;
                    System.out.println("已重新分配好ID!");
                    break;
                }
            }
        }
        System.out.print("请输入要注册的用户名(不少于5个字符): ");
        while(true){
            String userName = sc.next();
            if(userName.length() < 5){
                System.out.print("非法输入!要注册的用户名不少于5个字符,请重新输入: ");
            }else
                break;
        }

        if(findUserName(users, userName) == -1){
            System.out.print("请输入要注册的用户密码");
            while(true){
                String password = sc.next();
                if(!password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$")){
                    System.out.print("非法输入!要注册的密码不少于8个字符,且必须是大小写字母、数字和标点符号的组合,请重新输入: ");
                }else
                    break;
            }
            System.out.print("请输入要注册的用户级别");
            String userLevel = sc.next();
            System.out.print("请输入要注册的用户手机号");
            String userPhone = sc.next();
            System.out.print("请输入要注册的用户邮箱");
            String userEmail = sc.next();
            SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间 
            sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");// a为am/pm的标记  
            Date date = new Date();// 获取当前时间 
            String userRegistrationTime = sdf.format(date);

            User newUser = new User(Id, userName, userLevel, userRegistrationTime, userPhone, userEmail, password);
            users[numUser] = newUser;
            numUser++;
            quickSortUser(users,0,numUser-1);
            System.out.println("注册成功!");
        }else {
           System.out.println("用户名已存在，注册失败!");
        }
    }

    public void userResetPassword(User user1){   //修改密码
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入原始密码");
        String pass = sc.nextLine();
        if(user1.password.equals(pass)) {
            System.out.println("请输入修改后的密码");
            while(true){
                String reader = sc.nextLine();
                if(!reader.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$")){
                    System.out.println("非法输入!要注册的密码不少于8个字符,且必须是大小写字母、数字和标点符号的组合,请重新输入: ");
                }else{
                    user1.password = reader;
                    break;
                }
            }
        }
        else
            System.out.println("原始密码错误，修改密码失败!");
    }

    public void printAllUser(User[] users) {   //打印所有用户信息
        System.out.println(" =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  = 用户列表 =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  = ");
        System.out.printf("%-9s\t\t%-9s\t\t%-9s\t\t%-9s\t\t%-9s\t\t%-9s\t\t\n", "用户ID", "用户名", "用户级别", "用户注册时间", "用户手机号", "用户邮箱");
        for (int i = 0;i<User.numUser;i++) {
            System.out.printf("%-9d\t\t%-9s\t\t%-9s\t\t%-9s\t\t%-9s\t\t%-9s\t\t\n",users[i].userId, users[i].userName, users[i].userLevel, users[i].userRegistrationTime, users[i].userPhone, users[i].userEmail);
        }
        System.out.println();
    }

    public void del(User[] users) {   //删除用户
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入要删除用户的ID: ");
        try {
            int id = sc.nextInt();
            if (numUser == 0) {
                System.out.println("没有用户可以删除!");
                return;
            }
            int index = findUserId(users, id);
            if (index == -1) {
                System.out.println("没有找到该用户，删除失败!");
                return;
            }

            System.out.println("请确认是否删除,是请输入1,否请输入0: ");
            int flag = sc.nextInt();
            if(flag == 0){
                System.out.println("删除取消");
            }else{
                for (int i = index; i < numUser - 1; i++) {
                    users[i] = users[i + 1];
                }
                numUser--;
                System.out.println("删除成功!");                
            }
        } catch (Exception e){
            System.out.println("非法输入!");
            sc.next();
        }
    }

    public void lookUpUser(User[] users){   //查找用户
        Scanner sc = new Scanner(System.in);
        System.out.print("用户查找,请输入用户名:");
        String userName = sc.next();
        int userIndex = findUserName(users,userName);
        if(userIndex == -1){
            System.out.println("找不到该用户!");
        }
        else {
            System.out.printf("%-9s\t\t%-9s\t\t%-9s\t\t%-9s\t\t%-9s\t\t%-9s\t\t\n", "用户ID", "用户名", "用户级别", "用户注册时间", "用户手机号", "用户邮箱");
            System.out.printf("%-9d\t\t%-9s\t\t%-9s\t\t%-9s\t\t%-9s\t\t%-9s\t\t\n",users[userIndex].userId, users[userIndex].userName, users[userIndex].userLevel, users[userIndex].userRegistrationTime, users[userIndex].userPhone, users[userIndex].userEmail);
            System.out.println();
        }
    }

    //购物车
    public int findTrolleyIndex(Goods[] trolley, int id)   //折半查找，返回商品下标
    {
        g1.quickSortGood(trolley,0,numTrolley-1);
        int left = 0;
        int right = numTrolley-1;
        while(left <= right)
        {
            int mid = (left+right)/2;
            if(trolley[mid].id == id)
            {
                return mid;
            }
            else if(trolley[mid].id>id)
            {
                right = mid-1;
            } else {left = mid+1;}
        }
        return -1;
    }

    public double overheadCal(Goods[] trolley){
        double sum = 0;
        for (int i = 0;i<numTrolley;i++) {
            sum += trolley[i].num*trolley[i].retailPrice;
        }
        return sum;
    }

    public void printTrolley(Goods[] trolley) {   //打印购物车
        System.out.println(" =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  = 购物车 =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  = ");
        System.out.printf("%-9s\t\t%-9s\t\t%-9s\t\t%-9s\t\t%-9s\n", "商品编号", "商品名称", "商品零售价", "购买数量", "小计");
        for (int i = 0;i<numTrolley;i++) {
            System.out.printf("%-9d\t\t%-9s\t\t%-9.2f\t\t%-9d\t\t%-9.2f\n", trolley[i].id, trolley[i].name, trolley[i].retailPrice, trolley[i].num, trolley[i].num*trolley[i].retailPrice);
        }
        System.out.println("-----------------总计------------------");
        System.out.println("需要花费"+overheadCal(trolley)+"元");
        System.out.println();
    }
    
    public void addTrolley(Goods[] goods,Goods[] trolley){   //添加购物车商品
        Scanner sc = new Scanner(System.in);
        int id,index,num;
        System.out.println("请输入要添加到购物车的商品ID: ");
        try {
            id = sc.nextInt();
            index = g1.findGoodIndex(goods, id);
            if (index  ==  -1) {
                System.out.println("找不到商品，添加购物车失败!");
            } else {
                if (goods[index].num <= 0) {
                    System.out.println("该商品已卖完，无法添加至购物车!");
                    return;
                }
                System.out.println("请输入要添加到购物车的商品数量: ");
                num = sc.nextInt();
                if (num > goods[index].num) {
                    System.out.println("添加数量超出提供范围，添加失败!");
                    return;
                }
                for (int i = 0; i < numTrolley; i++) {
                    if (id == trolley[i].id) {
                        if (trolley[i].num + num > goods[index].num) {
                            System.out.println("添加数量超出提供范围，添加失败!");
                            return;
                        } else {
                            trolley[i].num += num;
                        }
                        return;
                    }
                }
                Goods newTrolley = new Goods(id, goods[index].name, goods[index].manufacturer, goods[index].productionDate, goods[index].model, goods[index].buyingPrice, goods[index].retailPrice , num);
                trolley[numTrolley] = newTrolley;
                numTrolley++;
                g1.quickSortGood(trolley, 0, numTrolley - 1);
                System.out.println("添加购物车成功!");
            }
        }catch (Exception e){
            System.out.println("非法输入!");
            sc.next();
        }
    }
    
    public void delTrolley(Goods[] trolley) {   //从购物车移除商品
        if (numTrolley == 0)
        {
            System.out.println("购物车中没有商品可以移除了!");
            return;
        }
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("请输入要移除商品的ID: ");
            int id = sc.nextInt();
            int index = findTrolleyIndex(trolley, id);
            if (index  ==  -1) {
                System.out.println("购物车中没有找到该商品，移除失败!");
                return;
            }

            System.out.println("请确认是否移除,是请输入1,否请输入0: ");
            int flag = sc.nextInt();
            if(flag == 0){
                System.out.println("移除取消");
            }else{
                for (int i = index; i < numTrolley - 1; i++) {
                    trolley[i] = trolley[i + 1];
                }
                numTrolley--;
                System.out.println("移除成功!");                
            }
        } catch (Exception e){
            System.out.println("非法输入!");
            sc.next();
        }
    }
    
    public void changeTrolleyNum(Goods[] goods,Goods[] trolley) {   //修改购物车商品数量
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要修改商品的ID: ");
        try {
            int id = sc.nextInt();
            int trolleyIndex = findTrolleyIndex(trolley, id);
            if (trolleyIndex  ==  -1) {
                System.out.println("没有找到该商品，无法修改!");
                return;
            }
            int goodsIndex = g1.findGoodIndex(goods, id);
            System.out.print("请输入修改后的数量: ");
            int num = sc.nextInt();
            if (num <= goods[goodsIndex].num && num > 0) {
                trolley[trolleyIndex].num = num;
                System.out.println("修改成功!");
            } else {
                System.out.println("购买数量超出提供范围，修改失败!");
            }
        }catch (Exception e){
            System.out.println("非法输入!");
            sc.next();
        }
    }
    
    public void checkOut(Goods[] goods,Goods[] trolley,Goods[][] history){   //购物历史
        int goodsIndex = -1;
        if(numTrolley <= 0){
            System.out.println("购物车中没有商品，无法结账");
            return;
        }
        for (int i = 0;i<numTrolley;i++){//检查购买数量是否超过提供范围
            goodsIndex = g1.findGoodIndex(goods,trolley[i].id);
            if(goods[goodsIndex].num<trolley[i].num){
                System.out.println("商品ID为"+goods[goodsIndex].id+"的"+goods[goodsIndex].name+"的购买数量超出提供范围，结账失败");
                return;
            }
        }
        for(int i = 0; i < maxHistory; i++){
            if(history[i][0] == null) {
                for (int j = 0; j < numTrolley; j++) {
                    history[i][j] = trolley[j];
                }
                numHistory++;
                if (history[i][0] !=  null) {
                    history[i][0].numHistoryTrolley = numTrolley;
                }
                break;
            }
        }
        System.out.println("需要支付"+overheadCal(trolley)+"元");
        System.out.println("请选择使用:1、微信 2、支付宝 3、银行卡支付: ");
        Scanner sc = new Scanner(System.in);
        int buy = sc.nextInt();
        for (int i = 0; i < numTrolley; i++){
            goodsIndex = g1.findGoodIndex(goods,trolley[i].id);
            goods[goodsIndex].num -= trolley[i].num;
            trolley[i] = null;
        }
        numTrolley = 0;
        System.out.println("支付成功!\n");
    }

    public void printHistory(Goods[][] history) {   //打印历史记录
        if(numHistory == 0){
            System.out.println("当前无历史记录!");
            return;
        }
        int historyId;
        System.out.println(" =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  = 购物历史 =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  = ");
        for(int i = 0;i<numHistory;i++)
            System.out.printf("历史记录 %d\t\t",i);
        System.out.println();
        System.out.println("请输入要查看的历史记录");
        Scanner sc = new Scanner(System.in);
        historyId = sc.nextInt();
        if(historyId<0||historyId>maxHistory||historyId>numHistory-1){
            System.out.println("未找到该历史记录");
            return;
        }
        System.out.printf(" =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  = 历史记录%d =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  =  = \n", historyId);
        System.out.printf("%-9s\t\t%-9s\t\t%-9s\t\t%-9s\t\t%-9s\n", "商品编号", "商品名称", "商品单价", "购买数量", "小计");
        for (int i = 0;i<history[historyId][0].numHistoryTrolley;i++) {
            System.out.printf("%-9d\t\t%-9s\t\t%-9.2f\t\t%-9d\t\t%-9.2f\n", history[historyId][i].id, history[historyId][i].name, history[historyId][i].retailPrice, history[historyId][i].num, history[historyId][i].num*history[historyId][i].retailPrice);
        }
        System.out.println("-----------------总计------------------");
        double sum = 0;
        for (int i = 0;i<history[historyId][0].numHistoryTrolley;i++) {
            sum += history[historyId][i].num*history[historyId][i].retailPrice;
        }
        System.out.println("需要花费"+sum+"元");
        System.out.println();
    }
}
