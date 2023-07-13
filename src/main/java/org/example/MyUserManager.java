package org.example;

import java.util.ArrayList;
import java.util.List;

public class MyUserManager {

    public List<MyUserAccounts> accountList = new ArrayList<MyUserAccounts>(); //创建用户账户列表

    public boolean registerUser(String username, String password) { //注册验证

        MyUserAccounts account = new MyUserAccounts(username, password); //创建用户账户对象

        if(accountList.contains(account)){
            System.out.println("该用户名已被注册，请重新注册");

            return false;
        }else{
            accountList.add(new MyUserAccounts(username, password));
            System.out.println("注册账户成功！\n\n");

            return true;
        }
    }

    public boolean login(String username, String password) { //登录验证

        




        return false;
    }

    public boolean Modify(String username, String passwordOld, String passwordNew){ //密码修改验证

        return false;
    }

    public boolean Reset(String username, String passwordOld, String passwordNew){ //密码重置验证

        return false;
    }
}
