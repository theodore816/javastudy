package com.enjoy.MultiThread.ch6.mypool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MyThreadPool {
    //默认线程个数
    private static int WORK_NUM = 5;
    //队列默认任务个数
    private static int TASK_COUNT = 100;

    //工作线程组
    private WorkThread[] workThreads;

    //任务队列
    private final BlockingQueue<Runnable> taskQueue;
    //希望启动的线程个数
    private final int work_num;

    public MyThreadPool() {
        this(WORK_NUM,TASK_COUNT);
    }

    public MyThreadPool(int work_num,int taskCount){
        if (work_num <= 0) work_num = WORK_NUM;
        if(taskCount <= 0) taskCount = TASK_COUNT;
        this.work_num = work_num;
        taskQueue = new ArrayBlockingQueue<>(taskCount);
        workThreads = new WorkThread[work_num];
        for (int i = 0;i < work_num;i++){
            workThreads[i] = new WorkThread();
            workThreads[i].start();
        }
        Runtime.getRuntime().availableProcessors();
    }

    //执行任务，将任务加入队列
    public void execute(Runnable task){
        try {
            taskQueue.put(task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //销毁线程池
    public void destory(){
        System.out.println("reday to close pool");
        for(int i = 0;i < work_num;i++){
            workThreads[i].stopWorker();
            workThreads[i] = null;//help gc
        }
        taskQueue.clear();
    }

    @Override
    public String toString() {
        return "WorkThread number:" + work_num + "wait task number" + taskQueue.size();
    }

    /*
    * 内部类工作线程
    * */
    private class WorkThread extends Thread{
        @Override
        public void run(){
            Runnable r = null;
            try{
                while (!isInterrupted()){
                    r = taskQueue.take();
                    if(r!=null){
                        System.out.println(getId() + "ready execute: " + r);
                        r.run();
                    }
                    r = null;
                }
            }catch (Exception e){

            }
        }

        public void stopWorker(){
            interrupt();
        }

    }
}
