/*
package com.javaConcurrency;

import java.util.SortedSet;
import java.util.TreeSet;

*/
/*
* 基本类型的局部变量与引用变量的线程封闭性
* *//*

public class codelist3_9 {
    public int loadTheArk(Collection<Animal> candidates){
        SortedSet<Animal> animals;
        int numPairs = 0;
        Animal candidate = null;

        //animals 被封闭在方法中，不要使它们溢出
        animals = new TreeSet<Animal>(new SpeciesGenderComparator());
        animals.addAll(candidates);
        for (Animal a : animals){
            if (candidate == null || !candidate,isPotentialMate(a)){
                candidate = a;
            }else{
                ark.load(new AnimalPair(candidate,a));
                ++numPairs;
                candidate = null;
            }
        }
        return numPairs;

    }

}
*/
