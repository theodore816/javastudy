package com.javaConcurrency;

import java.awt.*;
/*import java.util.EventListener;

*//*
* 使用工厂方法来防止 this 引用在构造过程中溢出
* *//*
public class codelist3_8 {
    public class SafeListener{
        private final EventListener listener;

        private SafeListener(){
            listener = new EventListener() {
                public void onEvent(Event e){
                    doSomething(e);
                }
            };
        }

        public static SafeListener newInstance(EventSource source){
            SafeListener safe = new SafeListener();
            source.registerListener(safe.listener);
            return safe;
        }
    }
}*/
