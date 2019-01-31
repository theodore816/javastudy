package com.theodore.test.CountDownLatch;

import java.util.concurrent.CountDownLatch;

public abstract class ExternalChecker implements Runnable{

    private CountDownLatch _latch;
    private String _serviceName;
    private boolean _serviceUp;

    public ExternalChecker(CountDownLatch _latch, String _serviceName) {
        this._latch = _latch;
        this._serviceName = _serviceName;
        this._serviceUp = false;
    }

    @Override
    public void run(){
        try{
            verifyService();
            _serviceUp = true;
        }catch (Throwable t){
            t.printStackTrace(System.err);
            _serviceUp = false;
        }finally {
            if (_latch != null){
                _latch.countDown();
            }
        }
    }

    public String get_serviceName() {
        return _serviceName;
    }

    public boolean is_serviceUp(){
        return _serviceUp;
    }

    public abstract void verifyService();
}
