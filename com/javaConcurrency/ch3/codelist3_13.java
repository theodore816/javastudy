/*package com.javaConcurrency;

import java.math.BigInteger;

public class codelist3_13 {
    @ThreadSafe
    public class VolatileCachedFactorizer implements Servlet{
        private volatile OneValueCache cache = new OneValueCache(null,null);

        public void service(ServletRequest req, ServletResponse resp){
            BigInteger i = extractFromRequest(req);
            BigInteger[] factors = cache.getFactors(i);
            if(factors == null){
                factors = factor(i);
                cache = new OneValueCache(i,factors);
            }
            encodeIntoResponse(resp,factors);
        }
    }
}*/
