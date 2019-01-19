package com.enjoy.MultiThread.ch5.bq;

public class Order {
    private final String orderNo;//订单编号
    private final double orderMoney;//订单金额

    public Order(String orderNo, double orderMoney) {
        this.orderNo = orderNo;
        this.orderMoney = orderMoney;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public double getOrderMoney() {
        return orderMoney;
    }
}
