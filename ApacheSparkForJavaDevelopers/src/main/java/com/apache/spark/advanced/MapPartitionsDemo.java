package com.apache.spark.advanced;

import java.util.ArrayList;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;


public class MapPartitionsDemo {
	@SuppressWarnings("unused")
	public static void main(String args[]) {
		SparkConf conf = new SparkConf().setMaster( "local" ).setAppName("ApacheSparkForJavaDevelopers" );
		SparkSession sparkSession = SparkSession.builder().config(conf).getOrCreate();
		JavaRDD<Row> superStoreSalesDataSet = sparkSession.read()
				.format("com.databricks.spark.csv")
				.option("header", "true")
				.option("inferSchema", "true")
				.option("delimiter", "\t")
				.load("input/superstore-sales.csv")
				.toJavaRDD();
		
		JavaRDD<Row> productPrices =  superStoreSalesDataSet.mapPartitions( //Map partitions can simultaneously perform filtering and transformation 
			iterator ->  {
				ArrayList<Row> list = new ArrayList<Row>();
				
				while(iterator.hasNext()) {
					Row row = iterator.next();					
					String productId = row.getString(row.fieldIndex("productId"));
					String productName = row.getString(row.fieldIndex("productName"));
					double sales =row.getDouble(row.fieldIndex("sales"));
					int quantity = row.getInt(row.fieldIndex("quantity"));
					double discount = row.getDouble(row.fieldIndex("discount"));
					double profit = row.getDouble(row.fieldIndex("profit"));
					
					if(!(profit < 0)) {
						double price = ((sales + profit) * (1-discount)) / quantity;
						list.add(RowFactory.create(productId, productName, price));
					}
					
				}
				return list.iterator();
			}
		);
		
	}
}
