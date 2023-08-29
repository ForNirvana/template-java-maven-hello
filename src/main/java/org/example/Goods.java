package org.example;

import java.util.Scanner;

public class Goods {
    int id;   //商品编号
    String name;   //商品名称
    String manufacturer;   //生产厂家
    int productionDate;   //生产日期
    int model;   //型号
    double buyingPrice;   //进货价
    double retailPrice;   //零售价
    int num;   //数量

    public static int maxSize = 100;
    public static int numGood = 3;
    int numHistoryTrolley = 0;

    public Goods(int id, String name, String manufacturer, int productionDate, int model, double buyingPrice, double retailPrice, int num){
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.productionDate = productionDate;
        this.model = model;
        this.buyingPrice = buyingPrice;
        this.retailPrice = retailPrice;
        this.num = num;
    }

    public int getId() {   //获取商品信息
        return id;
    }
    public String getName() {
        return name;
    }
    public String getManufacturer() {
        return manufacturer;
    }
    public int getProductionDate() {
        return productionDate;
    }
    public int getModel() {
        return model;
    }
    public double getBuyingPrice() {
        return buyingPrice;
    }
    public double getRetailPrice() {
        return retailPrice;
    }
    public int getNum() {
        return num;
    }
    public Goods() {
        maxSize = 100;
    }

    public void quickSortGood(Goods[] goods, int low, int high){   //给商品排序
        int i, j;
        Goods midGood = new Goods();
        Goods temp = new Goods();
        if(low > high){
            return;
        }
        i = low;
        j = high;
        temp = goods[low];
        while (i<j) {
            while (temp.id <= goods[j].id && i < j) {
                j--;
            }
            while (temp.id >= goods[i].id && i < j) {
                i++;
            }
            if (i < j) {
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

    public int findGoodIndex(Goods[] goods, int id)   //折半查找，返回商品下标
    {
        quickSortGood(goods, 0, numGood-1);
        int left = 0;
        int right = numGood - 1;
        while(left <= right)
        {
            int mid = (left+right)/2;
            if(goods[mid].id == id)
            {
                return mid;
            }
            else if(goods[mid].id > id)
            {
                right = mid - 1;
            } else {left = mid + 1;}
        }
        return -1;
    }

    public int findGoodsName(Goods[] goods, String goodsName){   //遍历商品，返回下标
        for(int i = 0; i < numGood; i++){
            if(goods[i].name.equals(goodsName)){
                return i;
            }
        }
        return -1;
    }

    public void lookUpGoods(Goods[] goods){   //查询商品信息
        Scanner sc = new Scanner(System.in);
        System.out.print("商品查找,请输入商品名称:");
        String goodsName = sc.next();
        int goodsIndex = findGoodsName(goods,goodsName);
        if(goodsIndex == -1){
            System.out.println("找不到该商品!");
        }
        else {
            System.out.printf("%-9s\t\t%-9s\t\t%-9s\t\t%-9s\t\t%-9s\t\t%-9s\t\t%-9s\t\t%-9s\n", "商品编号", "商品名称", "生产厂家", "生产日期", "型号", "进货价", "零售价", "数量");
            System.out.printf("%-9d\t\t%-9s\t\t%-9s\t\t%-9d\t\t%-9d\t\t%-9.2f\t\t%-9.2f\t\t%-9d\n", goods[goodsIndex].id, goods[goodsIndex].name, goods[goodsIndex].manufacturer, goods[goodsIndex].productionDate, goods[goodsIndex].model, goods[goodsIndex].buyingPrice, goods[goodsIndex].retailPrice, goods[goodsIndex].num);
            System.out.println();
        }
    }

    public void printAllGoods(Goods[] goods) {   //列出所有商品信息 
        System.out.println("====================商品清单====================");
        System.out.printf("%-9s\t\t%-9s\t\t%-9s\t\t%-9s\t\t%-9s\t\t%-9s\t\t%-9s\t\t%-9s\n", "商品编号", "商品名称", "生产厂家", "生产日期", "型号", "进货价", "零售价", "数量");
        for (int i = 0; i < numGood; i++) {
            System.out.printf("%-9d\t\t%-9s\t\t%-9s\t\t%-9d\t\t%-9d\t\t%-9.2f\t\t%-9.2f\t\t%-9d\n", goods[i].getId(), goods[i].getName(), goods[i].getManufacturer(), goods[i].getProductionDate(), goods[i].getModel(), goods[i].getBuyingPrice(), goods[i].getRetailPrice(), goods[i].num);
        }
        System.out.println();
    }

    public void add(Goods[] goods) {   //增加商品
        int Id = -1;
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入要添加商品的编号: ");
            Id = sc.nextInt();
            if (findGoodIndex(goods, Id) != -1) {
                System.out.println("已存在相同的商品!");
                return;
            }
            System.out.print("请输入要添加商品的名称: ");
            String name = sc.next();
            System.out.print("请输入要添加商品的生产厂家: ");
            String manufacturer = sc.next();
            System.out.print("请输入要添加商品的生产日期: ");
            int productionDate = sc.nextInt();
            System.out.print("请输入要添加商品的型号: ");
            int model = sc.nextInt();
            System.out.print("请输入要添加商品的进货价: ");
            double buyingPrice = sc.nextDouble();
            System.out.print("请输入要添加商品的零售价: ");
            double retailPrice = sc.nextDouble();
            System.out.print("请输入要添加商品的数量: ");
            int num = sc.nextInt();

        while(true){
            if(num < 0){
                System.out.print("非法输入!请重新输入商品数量: ");
                num = sc.nextInt();
                break;
            }
            else
                break;
        }

        Goods newGood = new Goods(Id, name, manufacturer, productionDate, model, buyingPrice, retailPrice, num);
        goods[numGood] = newGood;
        numGood++;
        quickSortGood(goods, 0, numGood-1);
        System.out.println("添加商品成功!");
    }

    public void del(Goods[] goods) {   //删除商品
        if (numGood == 0)
        {
            System.out.println("没有商品可以删除了!");
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("请输入要删除商品的ID: ");
        try {
            int id = sc.nextInt();
        }
        catch (Exception e){
            System.out.println("非法输入!");
            sc.next();
            return;
        }

        int index= findGoodIndex(goods, id);
        if(index == -1) {
            System.out.println("没有找到该商品，删除失败!");
            return;
        }

        System.out.println("请确认是否删除,是请输入1,否请输入0: ");
        int flag = sc.nextInt();
        if(flag == 0){
            System.out.println("删除取消");
        }else{
            for(int i = index; i < numGood - 1; i++) {
                goods[i] = goods[i + 1];
            }
            numGood--;
            System.out.println("删除商品成功!");            
        }
    }

    public void change(Goods[] goods) {   //修改商品
        int index = -1;
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要修改商品的编号: ");

        try {
            int id = sc.nextInt();
            index = findGoodIndex(goods, id);
            if (index == -1) {
                System.out.println("没有找到该商品，无法修改!");
                return;
            }
            System.out.println("请输入修改后的编号: ");
            int newId = sc.nextInt();
            if (findGoodIndex(goods, newId) != -1) {
                System.out.println("已存在相同编号的商品!");
                return;
            } else
                goods[index].id = newId;

            System.out.print("请输入修改后商品的名称: ");
            String name = sc.next();
            System.out.print("请输入修改后商品的生产厂家: ");
            String manufacturer = sc.next();
            System.out.print("请输入修改后商品的生产日期: ");
            int productionDate = sc.nextInt();
            System.out.print("请输入修改后商品的型号: ");
            int model = sc.nextInt();
            System.out.print("请输入修改后商品的进货价: ");
            double buyingPrice = sc.nextDouble();
            System.out.print("请输入修改后商品的零售价: ");
            double retailPrice = sc.nextDouble();
            System.out.print("请输入修改后商品的数量: ");
            int num = sc.nextInt();
        }
        catch (Exception e){
            System.out.println("非法输入!");
            sc.next();
            return;
        }

        while(true){
            if(num < 0){
                System.out.print("非法输入!请重新输入商品数量: ");
                try {
                    num = sc.nextInt();
                    break;
                }
                catch (Exception e){
                    System.out.println("非法输入!");
                    sc.next();
                    return;
                }
            }
            else{
                goods[index].num = num;
                break;
            }
        }

        quickSortGood(goods, 0, numGood - 1);
        System.out.println("修改成功!");
    }
}
