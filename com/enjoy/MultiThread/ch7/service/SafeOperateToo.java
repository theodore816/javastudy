package com.enjoy.MultiThread.ch7.service;

import com.enjoy.MultiThread.ch7.UserAccount;
import com.enjoy.MultiThread.tools.SleepTools;

import java.util.Random;

//用 trylock 保证不会产生锁
public class SafeOperateToo implements ITransfer {
    @Override
    public void transfer(UserAccount from, UserAccount to, int amout) throws InterruptedException {
        Random r = new Random();
        while (true){
            if (from.getLock().tryLock()){
                try{
                    System.out.println(Thread.currentThread().getName() + " get " + from.getName());
                    if (to.getLock().tryLock()){
                        try{
                            System.out.println(Thread.currentThread().getName() + " get " + to.getName());
                            from.flyMoney(amout);
                            to.addMoney(amout);
                            break;
                        }finally {
                            to.getLock().unlock();
                        }
                    }
                }finally {
                    from.getLock().unlock();
                }

            }
            SleepTools.ms(r.nextInt(10));//为了保证两个锁之间不会互相谦让，导致谁都没有获得锁
        }
    }
}
