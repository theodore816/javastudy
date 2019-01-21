package com.enjoy.MultiThread.ch7;

import com.enjoy.MultiThread.ch5.bq.Test;

public class NormalDeadLock {
    private static Object valueFirst = new Object();
    private static Object valueSecond = new Object();

    private static void firstToSecond() throws InterruptedException{
        String threadName = Thread.currentThread().getName();
        synchronized (valueFirst){
            System.out.println(threadName + " get first");
            Thread.sleep(100);
            synchronized (valueSecond){
                System.out.println(threadName + " get second");
            }
        }
    }

    private static void secondToFirst() throws InterruptedException{
        String threadName = Thread.currentThread().getName();
        synchronized (valueSecond){
            System.out.println(threadName + " get second");
            Thread.sleep(100);
            synchronized (valueFirst){
                System.out.println(threadName + " get first");
            }
        }
    }

    private static class TestThread extends Thread{
        private String name;

        public TestThread(String name){
            this.name = name;
        }

        public void run(){
            Thread.currentThread().setName(name);
            try{
                secondToFirst();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args){
        Thread.currentThread().setName("TestDeadLock");
        TestThread testThread = new TestThread("SubTestThread");
        testThread.start();
        try{
            firstToSecond();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }


}
