package com.theodore.test.CountDownLatch;

import java.util.concurrent.CountDownLatch;

public class DataBaseChecker extends ExternalChecker {

    public DataBaseChecker (CountDownLatch latch)  {
        super(latch,"DataBase Service");
    }

    @Override
    public void verifyService()
    {
        System.out.println("Checking " + this.get_serviceName());
        try
        {
            Thread.sleep(8000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println(this.get_serviceName() + " is UP");
    }

}
