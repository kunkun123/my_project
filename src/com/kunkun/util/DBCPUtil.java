package com.kunkun.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.apache.commons.dbcp.managed.BasicManagedDataSource;

@SuppressWarnings("unused")
public class DBCPUtil {
    private static DataSource datasource;
    static{
    	try {
			InputStream in = DBCPUtil.class.getClassLoader().getResourceAsStream("dbcpconfig.properties");
			Properties props = new Properties();
			props.load(in);
			datasource = BasicDataSourceFactory.createDataSource(props);
		} catch (Exception e) {
			throw new ExceptionInInitializerError();
		}
    }
    
    public static DataSource getDataSource(){
    	return datasource;
    }
    
    public static Connection getConnection(){
    	try {
			return datasource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException();
		}
    }
}
