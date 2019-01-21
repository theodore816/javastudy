package com.enjoy.MultiThread.ch7.service;

import com.enjoy.MultiThread.ch7.UserAccount;
//不会产生死锁的安全转账 通过hashcode保证顺序
public class SafeOperate implements ITransfer {

    private static Object tieLock = new Object();//加时赛锁

    @Override
    public void transfer(UserAccount from, UserAccount to, int amout) throws InterruptedException {
        int fromHash = System.identityHashCode(from);
        int toHash = System.identityHashCode(to);

        if(fromHash<toHash){
            synchronized (from){
                System.out.println(Thread.currentThread().getName() + "get" + from.getName());
                Thread.sleep(100);
                synchronized (to){
                    System.out.println(Thread.currentThread().getName() + "get" + to.getName());
                    from.flyMoney(amout);
                    to.addMoney(amout);
                }
            }
        }else if(toHash<fromHash){
            synchronized (to){
                System.out.println(Thread.currentThread().getName() + "get" + to.getName());
                Thread.sleep(100);
                synchronized (from){
                    System.out.println(Thread.currentThread().getName() + "get" + from.getName());
                    from.flyMoney(amout);
                    to.addMoney(amout);
                }
            }
        }else{//哈希冲突
            synchronized (tieLock){
                synchronized (from){
                    synchronized (to){
                        from.flyMoney(amout);
                        to.addMoney(amout);
                    }
                }
            }
        }
    }
}
