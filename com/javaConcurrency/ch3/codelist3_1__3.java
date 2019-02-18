/*package com.javaConcurrency;

public class codelist3_1__3 {
    //3-1 没有同步机制 不可见
    public class NoVisibility{
        private static boolean ready;
        private static int number;

        private static class ReaderThread extends Thread{
            public void run(){
                while (!ready)
                    Thread.yield();
                System.out.println(number);
            }
        }
        public static void main(String[] args){
            new ReaderThread().start();
            number = 42;
            ready = true;
        }
    }

    //3-2 失效数据
    @NotThreadSafe
    public class MutableInteger{
        private int value;

        public int get() {return value;}
        public void set(int value) {this.value = value;}
    }

    //3-3 安全
    @ThreadSafe
    public class SynchronizedInteger{
        @GuardedBy("this") private int value;
        public synchronized int get(){return value;}
        public synchronized void set(int value){this.value = value;}
    }
}*/
