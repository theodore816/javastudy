package com.enjoy.MultiThread.ch7;

import com.enjoy.MultiThread.ch7.service.ITransfer;
import com.enjoy.MultiThread.ch7.service.SafeOperate;
import com.enjoy.MultiThread.ch7.service.SafeOperateToo;
import com.enjoy.MultiThread.ch7.service.TransferAccount;

//模拟公司转账
public class PayCompay {
    private static class TransferThread extends Thread{
        private String name;
        private UserAccount from;
        private UserAccount to;
        private int amount;
        private ITransfer transfer;

        public TransferThread(String name, UserAccount from, UserAccount to, int amount, ITransfer transfer) {
            this.name = name;
            this.from = from;
            this.to = to;
            this.amount = amount;
            this.transfer = transfer;
        }

        @Override
        public void run() {
            Thread.currentThread().setName(name);
            try{
                transfer.transfer(from,to,amount);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args){
        PayCompay payCompay = new PayCompay();
        UserAccount zhangsan = new UserAccount("zhangsan",20000);
        UserAccount lisi = new UserAccount("lisi",20000);
        ITransfer transfer = new SafeOperateToo();//new SafeOperate or new TransferAccount
        TransferThread zhangsanToLisi = new TransferThread("zhangsanToLisi",zhangsan,lisi,2000,transfer);
        TransferThread lisiToZhangsan = new TransferThread("lisiToZhangsan",lisi,zhangsan,4000,transfer);
        zhangsanToLisi.start();
        lisiToZhangsan.start();
    }

}
