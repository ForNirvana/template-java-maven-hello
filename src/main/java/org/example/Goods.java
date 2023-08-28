package org.example;

import java.util.Scanner;

public class Goods {
    int id;
    int num;
    double price;
    String name;
    public static int maxSize=100;
    public static int numGood=3;
    int numHistoryTrolley=0;
    public Goods(int id, double price, String name,int num) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.num=num;
    }

    public int getId() {
        return id;
    }
    public double getPrice() {
        return price;
    }
    public String getName() {
        return name;
    }
    public Goods() {
        maxSize = 100;
    }

    public void quickSortGood(Goods[] goods,int low,int high){
        int i,j;
        Goods midGood=new Goods();
        Goods temp=new Goods();
        if(low>high){
            return;
        }
        i=low;
        j=high;
        temp = goods[low];
        while (i<j) {
            while (temp.id<=goods[j].id&&i<j) {
                j--;
            }
            while (temp.id>=goods[i].id&&i<j) {
                i++;
            }
            if (i<j) {
                midGood = goods[j];
                goods[j] = goods[i];
                goods[i] = midGood;
            }
        }
        goods[low] = goods[i];
        goods[i] = temp;
        quickSortGood(goods, low, j-1);
        quickSortGood(goods, j+1, high);
    }
    public int findGoodIndex(Goods[] goods, int id)//折半查找，返回商品下标
    {
        quickSortGood(goods,0,numGood-1);
        int left=0;
        int right=numGood-1;
        while(left<=right)
        {
            int mid=(left+right)/2;
            if(goods[mid].id == id)
            {
                return mid;
            }
            else if(goods[mid].id>id)
            {
                right=mid-1;
            } else {left=mid+1;}
        }
        return -1;
    }
    public int findGoodsName(Goods[] goods, String goodsName){    //遍历，返回下标
        for(int i=0;i<numGood;i++){
            if(goods[i].name.equals(goodsName)){
                return i;
            }
        }
        return -1;
    }
    public void lookUpGoods(Goods[] goods){
        Scanner sc = new Scanner(System.in);
        System.out.print("商品查找,请输入商品名称:");
        String goodsName=sc.next();
        int goodsIndex=findGoodsName(goods,goodsName);
        if(goodsIndex==-1){
            System.out.println("找不到该商品!");
        }
        else {
            System.out.printf("%-12s\t\t%-12s\t\t%-12s\t\t%-12s\n", "商品ID", "商品名称", "商品单价","商品数量");
            System.out.printf("%-12d\t\t%-12s\t\t%-12.2f\t\t%-12d\n", goods[goodsIndex].id, goods[goodsIndex].name, goods[goodsIndex].price,goods[goodsIndex].num);
            System.out.println();
        }
    }

    public void printAllGoods(Goods[] goods) {
        System.out.println("====================商品清单====================");
        System.out.printf("%-12s\t\t%-12s\t\t%-12s\t\t%-12s\n", "商品ID", "商品名称", "商品单价","商品数量");
        for (int i=0;i<numGood;i++) {
            System.out.printf("%-12d\t\t%-12s\t\t%-12.2f\t\t%-12d\n",goods[i].getId(),goods[i].getName(),goods[i].getPrice(),goods[i].num);
        }
        System.out.println();
    }

    //增加商品
    public void add(Goods[] goods) {
        int Id=-1;
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入要添加商品的ID：");
        try {
            Id = sc.nextInt();
            if (findGoodIndex(goods, Id) != -1) {
                System.out.println("已存在相同ID的商品!");
                return;
            }
            System.out.print("请输入要添加商品的价格：");
            double price = sc.nextDouble();
            System.out.print("请输入要添加商品的名称");
            String name = sc.next();
            System.out.print("请输入要添加商品的数量：");
            int num = sc.nextInt();
        }
        catch (Exception e){
            System.out.println("非法输入!");
            sc.next();
            return;
        }
        while(true){
            if(num<0){
                System.out.print("非法输入！请重新输入商品数量：");
                num = sc.nextInt();
            }
            else
                break;
        }
        Goods newGood=new Goods(Id,price,name,num);
        goods[numGood]=newGood;
        numGood++;
        quickSortGood(goods,0,numGood-1);
        System.out.println("添加成功！");
    }
    //删除商品
    public void del(Goods[] goods) {
        if (numGood==0)
        {
            System.out.println("没有商品可以删除了!");
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入要删除商品的ID：");
        try {
            int id = sc.nextInt();
        }
        catch (Exception e){
            System.out.println("非法输入!");
            sc.next();
            return;
        }
        int index= findGoodIndex(goods,id);
        if(index==-1) {
            System.out.println("没有找到该商品，删除失败！");
            return;
        }
        for(int i=index; i<numGood-1; i++) {
            goods[i] = goods[i + 1];
        }
        numGood--;
        System.out.println("删除成功！");
    }
    //修改商品
    public void change(Goods[] goods) {
        int index=-1;
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要修改商品的ID：");
        try {
            int id = sc.nextInt();
            index = findGoodIndex(goods, id);
            if (index == -1) {
                System.out.println("没有找到该商品，无法修改！");
                return;
            }
            System.out.println("请输入修改后的ID：");
            int newId = sc.nextInt();
            if (findGoodIndex(goods, newId) != -1) {
                System.out.println("已存在相同ID的商品!");
                return;
            } else
                goods[index].id = newId;
            System.out.println("请输入修改后的价格：");
            double newPrice = sc.nextDouble();
            goods[index].price = newPrice;
            System.out.println("请输入修改后的商品名称：");
            String newName = sc.next();
            goods[index].name = newName;
            System.out.print("请输入修改后的商品的数量：");
            int num = sc.nextInt();
        }
        catch (Exception e){
            System.out.println("非法输入！");
            sc.next();
            return;
        }
        while(true){
            if(num<0){
                System.out.print("非法输入！请重新输入商品数量：");
                try {
                    num = sc.nextInt();
                }
                catch (Exception e){
                    System.out.println("非法输入！");
                    sc.next();
                    return;
                }
            }
            else{
                goods[index].num=num;
                break;
            }

        }
        quickSortGood(goods,0,numGood-1);
        System.out.println("修改成功!");
    }



}
