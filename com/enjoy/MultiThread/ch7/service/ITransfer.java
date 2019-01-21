package com.enjoy.MultiThread.ch7.service;

import com.enjoy.MultiThread.ch7.UserAccount;

//银行转账接口
public interface ITransfer {
    void transfer(UserAccount from,UserAccount to,int amout) throws InterruptedException;

}
