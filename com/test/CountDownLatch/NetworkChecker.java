package com.theodore.test.CountDownLatch;

import java.util.concurrent.CountDownLatch;

public class NetworkChecker extends ExternalChecker
{

    public NetworkChecker (CountDownLatch latch)  {
        super(latch,"Network Service");
    }

    @Override
    public void verifyService()
    {
        System.out.println("Checking " + this.get_serviceName());
        try
        {
            Thread.sleep(7000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println(this.get_serviceName() + " is UP");
    }

}
