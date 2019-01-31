package com.theodore.test.CountDownLatch;

import java.util.concurrent.CountDownLatch;

public class CacheChecker extends ExternalChecker {


    public CacheChecker (CountDownLatch latch)  {
        super(latch,"Cache Service");
    }

    @Override
    public void verifyService()
    {
        System.out.println("Checking " + this.get_serviceName());
        try
        {
            Thread.sleep(6000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println(this.get_serviceName() + " is UP");
    }
}
