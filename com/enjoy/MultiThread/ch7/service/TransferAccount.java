package com.enjoy.MultiThread.ch7.service;

import com.enjoy.MultiThread.ch7.UserAccount;

//不安全的转账动作
public class TransferAccount implements ITransfer {

    @Override
    public void transfer(UserAccount from, UserAccount to, int amout) throws InterruptedException {
        synchronized (from){//先锁转出
            System.out.println(Thread.currentThread().getName() + "get" + from.getName());
            Thread.sleep(100);
            synchronized (to){//后锁转入
                System.out.println(Thread.currentThread().getName() + "get" + to.getName());
                from.flyMoney(amout);
                to.addMoney(amout);
            }
        }
    }
}
