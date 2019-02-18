/*package com.javaConcurrency;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

public class codelist2_5 {
    public class UnsafeCachingFactorizer implements Servlet{

        private final AtomicReference<BigInteger> lastNumber = new AtomicReference<>();
        private final AtomicReference<BigInteger[]> lastFactors = new AtomicReference<>();


        @Override
        public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
            BigInteger i = extractFromRequest(req);
            if(i.equals(lastNumber.get())){
                encodeIntoResponse(servletResponse,lastFactors.get());
            }
            else{
                BigInteger[] factors = factor(i);
                lastNumber.set(i);
                lastFactors.set(factors);
                encodeIntoResponse(servletResponse,factors);
            }
        }
    }

}*/
