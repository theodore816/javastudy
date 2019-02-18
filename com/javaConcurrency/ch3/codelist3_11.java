package com.javaConcurrency;

import jdk.nashorn.internal.ir.annotations.Immutable;

import java.util.HashSet;
import java.util.Set;

public class codelist3_11 {
    //在可变对象的基础上构建不可变类
    @Immutable
    public final class ThreeStooges{
        private final Set<String> stooges = new HashSet<String>();

        public ThreeStooges(){
            stooges.add("moe");
            stooges.add("larry");
            stooges.add("curly");
        }

        public boolean isStooge(String name){
            return stooges.contains(name);
        }
    }
}
