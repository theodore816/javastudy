/*
package com.javaConcurrency;

import java.sql.Connection;
import java.sql.DriverManager;

*/
/*
* 使用 ThreadLocal 来维持线程封闭性
* *//*

public class codelist3_10 {
    private static ThreadLocal<Connection> conectionHolder = new ThreadLocal<>(){
        public Connection initialValue(){
            return DriverManager.getConnection(DB_URL);
        }
    };

    public static Connection getConnection(){
        return connectionHolder.get();
    }
}
*/
