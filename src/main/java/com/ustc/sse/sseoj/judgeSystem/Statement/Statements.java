package com.ustc.sse.sseoj.judgeSystem.Statement;

import com.mysql.cj.jdbc.Driver;
import com.ustc.sse.sseoj.judgeSystem.constant;
import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author 邱乃光
 * @version 1.0
 * @date 2020/3/1 14:52
 */
public class Statements {
        @Getter
        private Statement statement;
        private Connection conn;
        public Statements() {
            try{
                //新建驱动，注册驱动
               // com.mysql.jdbc.Driver driverOne = new com.mysql.jdbc.Driver();
                Driver driverOne = new Driver();
                DriverManager.registerDriver(driverOne);
                //连接的三个参数，建立连接
                //远程使用
//                conn = DriverManager.getConnection(
//                        "jdbc:mysql://60.205.201.17:3306/smoke_monitoring?"
//                                + "useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai", "nainaiguang",
//                        "guang110507");
                //调试使用
//                conn = DriverManager.getConnection(
//                        "jdbc:mysql://rm-wz92kq7j65jh6ao65ao.mysql.rds.aliyuncs.com:3306/smoke_monitoring_system?"
//                                + "useUnicode=true&characterEncoding=utf-8&useSSL=false", "root",
//                        "Guang110507");


                //服务器使用
                conn = DriverManager.getConnection(
                        "jdbc:mysql://"+ constant.judgeMysqlServer+":"+constant.judgeMysqlPort+"/jol?"
                                + "useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai", constant.judgeMysqlUser,
                        constant.judgeMysqlPassword);



                //建立sql信使：
                statement = (Statement) conn.createStatement();
            } catch (Exception e1) {
               e1.printStackTrace();
            }
        }

    /**
     * 释放连接
     * @throws SQLException
     */
    public void close() throws SQLException {
          statement.close();
          conn.close();
      }

}


