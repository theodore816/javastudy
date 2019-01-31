package com.theodore.test.CountDownLatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ApplicationStartupUtil {

    private static List<ExternalChecker> _services;

    private static CountDownLatch _latch;

    private ApplicationStartupUtil(){}

    private final static ApplicationStartupUtil INSTANCE = new ApplicationStartupUtil();

    public static ApplicationStartupUtil getInstance(){
        return INSTANCE;
    }

    public static boolean checkExternalServices()throws Exception{
        _latch = new CountDownLatch(3);

        _services = new ArrayList<ExternalChecker>();
        _services.add(new NetworkChecker(_latch));
        _services.add(new CacheChecker(_latch));
        _services.add(new DataBaseChecker(_latch));

        Executor executor = Executors.newFixedThreadPool(_services.size());

        for (final ExternalChecker v:_services){
            executor.execute(v);
        }
        _latch.await();

        for (final ExternalChecker v:_services){
            if (!v.is_serviceUp()){
                return false;
            }
        }
        return true;
    }

}
