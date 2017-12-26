package com.apache.spark.advanced;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import scala.Tuple2;

public class MapPartitionsToPair {
	
	private static boolean isPrime(int number) {
	    return number > 1
	      && IntStream.rangeClosed(2, (int) Math.sqrt(number))
	      .noneMatch(n -> (number % n == 0));
	}
	
	private static Iterator<Tuple2<String, Boolean>> byIndexNature(Iterator<Row> rows) {
		final List<Tuple2<String, Boolean>> list = new LinkedList<Tuple2<String, Boolean>>();
		
		rows.forEachRemaining(row -> {
			int rowId = row.getInt(row.fieldIndex("rowId"));
			String orderId = row.getString(row.fieldIndex("orderId"));
			
			list.add(new Tuple2<String, Boolean>(orderId, isPrime(rowId)));
		});
		return list.iterator();
	}
	
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
		
		JavaPairRDD<String,Boolean> classifiedOrders =  superStoreSalesDataSet.mapPartitionsToPair(
			MapPartitionsToPair::byIndexNature
		);
		
		System.out.println(classifiedOrders.collect());
	}
}
