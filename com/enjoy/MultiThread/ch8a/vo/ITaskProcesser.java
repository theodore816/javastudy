package com.enjoy.MultiThread.ch8a.vo;

/*
*要求框架使用者实现的任务接口。
* data 是方法使用的业务数据类型
* return 方法执行后业务返回的结果
* */
public interface ITaskProcesser<T,R> {
    TaskResult<R> taskExcutor(T data);
}
