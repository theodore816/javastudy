package com.enjoy.MultiThread.ch8a;

import com.enjoy.MultiThread.ch8a.vo.ITaskProcesser;
import com.enjoy.MultiThread.ch8a.vo.JobInfo;
import com.enjoy.MultiThread.ch8a.vo.TaskResult;
import com.enjoy.MultiThread.ch8a.vo.TaskResultType;

import java.util.List;
import java.util.Map;
import java.util.concurrent.*;


/**
 * 框架的主体类，也是调用者主要使用的类
 */
public class PendingJobPool {

    //保守估计
    private static final int THREAD_COUNT = Runtime.getRuntime().availableProcessors();

    //有界队列
    private static BlockingQueue<Runnable> taskQueue = new ArrayBlockingQueue<>(5000);

    //创建固定大小有界队列线程池
    private static ExecutorService taskExecutor
            = new ThreadPoolExecutor(THREAD_COUNT,THREAD_COUNT,60, TimeUnit.SECONDS,taskQueue);

    //job 存放容器
    private static ConcurrentHashMap<String, JobInfo<?>> jobInfoMap = new ConcurrentHashMap<>();

    public static Map<String, JobInfo<?>> getMap(){
        return jobInfoMap;
    }

    private static CheckJobProcesser checkJob
            = CheckJobProcesser.getInstance();

    //单例模式---
    private PendingJobPool(){}

    private static class JobPoolHolder{
        public static PendingJobPool pool = new PendingJobPool();
    }

    public static PendingJobPool getInstance(){
        return JobPoolHolder.pool;
    }
    //单例模式---

    //调用者注册工作，如工作名，任务的处理器等等
    public<R> void registerJob(String jobName, int jobLength, ITaskProcesser<?,?> taskProcesser,long expireTime){
        JobInfo<R> jobInfo = new JobInfo(jobName,jobLength,taskProcesser,expireTime);
        if (jobInfoMap.putIfAbsent(jobName,jobInfo) != null){
            throw new RuntimeException("当前任务已经注册");
        }

    }

    //调用者提交任务
    public <T,R> void putTask(String jobName,T t){
        JobInfo<R> jobInfo = getJob(jobName);
        PendingTask<T,R> task = new PendingTask<>(jobInfo,t);
        taskExecutor.execute(task);
    }

    //根据工作名称检索工作
    private <R> JobInfo<R> getJob(String jobName){
        JobInfo<R> jobInfo = (JobInfo<R>) jobInfoMap.get(jobName);
        if (null == jobInfo){
            throw new RuntimeException(jobName + "是个非法任务");
        }
        return jobInfo;
    }

    //对工作中的任务进行包装，提交给线程池使用，并处理任务的结果，写入缓存以供查询
    private static class PendingTask<T,R> implements Runnable{
        private JobInfo<R> jobInfo;
        private T processData;

        public PendingTask(JobInfo<R> jobInfo,T processData){
            this.jobInfo = jobInfo;
            this.processData = processData;
        }

        @Override
        public void run(){
            R r = null;
            ITaskProcesser<T,R> taskProcesser = (ITaskProcesser<T,R>) jobInfo.getTaskProcesser();
            TaskResult<R> result = null;
            //调用业务员人员实现的方法
            result = taskProcesser.taskExcutor(processData);
            //要做检查，防止异常

            try{
                if (result == null){
                    result = new TaskResult<R>(TaskResultType.Exception,r,"result id null");
                }
                if (result.getResultType() == null) {
                    if (result.getReason() == null) {
                        result = new TaskResult<R>(TaskResultType.Exception, r, "reason is null");
                    } else {
                        result = new TaskResult<R>(TaskResultType.Exception, r,
                                "result is null,but reason:" + result.getReason());
                    }
                }
            }catch(Exception e){
                    e.printStackTrace();
                    result = new TaskResult<R>(TaskResultType.Exception,r,e.getMessage());
            }finally {
                jobInfo.addTaskResult(result,checkJob);
            }
        }
    }

    //获得每个任务的处理详情
    public <R> List<TaskResult<R>> getTaskDetail(String jobName){
        JobInfo<R> jobInfo = getJob(jobName);
        return jobInfo.getTaskDetail();
    }

    //获得工作的整体处理进度
    public <R> String getTaskProgess(String jobName) {
        JobInfo<R> jobInfo = getJob(jobName);
        return jobInfo.getTotalProcess();
    }

}
