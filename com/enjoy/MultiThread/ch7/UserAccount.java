package com.enjoy.MultiThread.ch7;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//用户账户实体类
public class UserAccount {

    //账户名字和余额
    private final String name;
    private int money;

    private final Lock lock = new ReentrantLock();

    public Lock getLock() {
        return lock;
    }

    public UserAccount(String name, int money) {
        this.name = name;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "name='" + name + '\'' +
                ", money=" + money +
                ", lock=" + lock +
                '}';
    }
    //转入金额
    public void addMoney(int amount){
        money += amount;
    }
    //转出金额
    public void flyMoney(int amount){
        money -= amount;
    }
}
