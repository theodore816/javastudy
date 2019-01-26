package com.enjoy.MultiThread.ch8a.vo;


import com.enjoy.MultiThread.ch8a.CheckJobProcesser;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

/*
* 提交给框架执行的工作实体类
* */
public class JobInfo<R> {
    //工作名字
    private final String jobName;
    //工作的任务个数
    private final int jobLength;
    //工作任务处理器
    private final ITaskProcesser<?,?> taskProcesser;
    //任务计数器
    private AtomicInteger successCount;
    private AtomicInteger taskProcesserCount;
    //放入已完成的队列
    private LinkedBlockingDeque<TaskResult<R>> taskDetailQueue;
    //完成任务超时 定时器 超时清除
    private final long expireTime;

    public JobInfo(String jobName, int jobLength, ITaskProcesser<?, ?> taskProcesser, long expireTime) {
        this.jobName = jobName;
        this.jobLength = jobLength;
        this.taskProcesser = taskProcesser;
        //开发人员不应该修改任务的计数 由框架来控制
        this.successCount = new AtomicInteger();
        this.taskProcesserCount = new AtomicInteger();
        this.taskDetailQueue = new LinkedBlockingDeque<TaskResult<R>>(jobLength);
        this.expireTime = expireTime;
    }

    public ITaskProcesser<?, ?> getTaskProcesser() {
        return taskProcesser;
    }

    //返回成功处理的结果数
    public int getSuccessCount() {
        return successCount.get();
    }

    //返回当前已处理的结果数
    public int getTaskProcesserCount() {
        return taskProcesserCount.get();
    }

    //提供工作中失败的次数
    public int getFailCount() {
        return taskProcesserCount.get() - successCount.get();
    }


    public String getTotalProcess() {
        return "Success["+successCount.get()+"]/Current["
                +taskProcesserCount.get()+"] Total["+jobLength+"]";
    }

    //获取每个任务结果，从头部获取
    public List<TaskResult<R>> getTaskDetail(){
        List<TaskResult<R>> taskList = new LinkedList<>();
        TaskResult<R> taskResult;
        while((taskResult = taskDetailQueue.pollFirst()) != null){
            taskList.add(taskResult);
        }
        return  taskList;
    }

    //从业务角度来讲，保证最终一致性就行，不需要加锁，影响性能。已经用了源自操作和并发安全队列
    public void addTaskResult(TaskResult<R> result, CheckJobProcesser checkJob){
        if (TaskResultType.Success.equals(result.getResultType())){
            successCount.incrementAndGet();
        }
        taskDetailQueue.addLast(result);//结果从尾部添加
        taskProcesserCount.incrementAndGet();
        if (taskProcesserCount.get() == jobLength){
            checkJob.putJob(jobName,expireTime);
        }

    }


}
