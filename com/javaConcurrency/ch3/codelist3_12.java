package com.javaConcurrency;

import jdk.nashorn.internal.ir.annotations.Immutable;

import java.math.BigInteger;
import java.util.Arrays;

public class codelist3_12 {
    @Immutable
    class OneValueCache{
        private final BigInteger lastNumber;
        private final BigInteger[] lastFactors;

        public OneValueCache(BigInteger i, BigInteger[] factors){
            lastNumber = i;
            lastFactors = Arrays.copyOf(factors, factors.length);
        }

        public BigInteger[] getFactors(BigInteger i){
            if(lastNumber == null || !lastNumber.equals(i))
                return null;
            else
                return Arrays.copyOf(lastFactors,lastFactors.length);
        }
    }
}
