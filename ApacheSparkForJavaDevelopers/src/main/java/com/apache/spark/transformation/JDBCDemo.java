package com.apache.spark.transformation;

import java.util.List;
import java.util.Properties;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;;

public class JDBCDemo {
	@SuppressWarnings("deprecation")
	public static void main(String args[]) throws ClassNotFoundException {
		SparkConf conf = new SparkConf().setMaster( "local" ).setAppName("ApacheSparkForJavaDevelopers" );
		JavaSparkContext sparkContext = new JavaSparkContext( conf );
		
		SQLContext sqlContext = new SQLContext(sparkContext);
		Properties properties = new Properties();
		
		Class.forName("com.mysql.jdbc.Driver");
		properties.put("user", "root");
		properties.put("password", "vasorange#1");
		
		List<Row> productCatalog =  sqlContext
				.read()
				.jdbc("jdbc:mysql://localhost:3306/pidata_test", "product_catalog", properties).
				collectAsList();
		for(Row row: productCatalog) {
			System.out.println(row.getString(0));
		}
	}
}
