/*package com.javaConcurrency;

import java.util.EventListener;

public class codelist3_5__7 {
    //1.发布 将对象的引用保存到一个公有静态变量中
    public static Set<Secret> knownSecrets;

    public void initialize(){
        knownSecrets = new HashSet<Secret>();
    }
    //----------
    //2.发布本应为私有的数组 任何调用者都能修改
    class UnsafeStates{
        private String[] states = new String[]{
                "ak","al"...
        }
        public String[] getStates (){return states;}
    }
    //--------
    //3.隐式的使this引用溢出
    public class ThisEscape{
        public ThisEscape(EventSource source){
            source.registerListener(new EventListener(){
                public void onEvent(Event e){
                    doSomething(e);
                }
            });
        }
    }
}*/
