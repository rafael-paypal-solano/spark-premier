package com.apache.spark.advanced;

import java.util.ArrayList;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import scala.Tuple2;

public class AggregateByKeyDemo {
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
		JavaPairRDD<String, Double> pairedRDD = superStoreSalesDataSet.mapPartitionsToPair(//Map partitions can simultaneously perform filtering and transformation 
			iterator ->  {
				ArrayList<Tuple2<String, Double>> list = new ArrayList<Tuple2<String, Double>>();
				
				iterator.forEachRemaining(row -> list.add(new Tuple2<String, Double>(
					row.getString(row.fieldIndex("productName")),
					row.getDouble(row.fieldIndex("sales"))	
				)));
				
				return list.iterator();
			}
		);
		
		JavaPairRDD<String, Integer> countRDD = pairedRDD.aggregateByKey(0, (v1, v2)-> v1+1, (v1, v2)->v1 +v2);
		countRDD.foreach( x -> System.out.println(x));
	}
}
