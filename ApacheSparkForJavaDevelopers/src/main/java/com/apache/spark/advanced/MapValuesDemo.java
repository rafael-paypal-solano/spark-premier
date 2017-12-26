package com.apache.spark.advanced;

import java.util.Arrays;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;

import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;

import scala.Tuple2;

public class MapValuesDemo {
	public static void main(String args[]) {
		SparkConf conf = new SparkConf().setMaster( "local" ).setAppName("ApacheSparkForJavaDevelopers" );
		SparkSession sparkSession = SparkSession.builder().config(conf).getOrCreate();
		JavaSparkContext context = new JavaSparkContext(sparkSession.sparkContext());
		JavaPairRDD<String, Double> degreeDirections = context.parallelizePairs(
			Arrays.asList(				
				new Tuple2<String, Double>("South-East", 315.0),
				new Tuple2<String, Double>("South", 270.0),
				new Tuple2<String, Double>("South-West", 225.0),				
				new Tuple2<String, Double>("West", 180.0),
				
				new Tuple2<String, Double>("North-West", 135.0),
				new Tuple2<String, Double>("North", 90.0),
				new Tuple2<String, Double>("South-East", 45.0),
				new Tuple2<String, Double>("East", 0.0)
				
			)
		); 					
		
		System.out.println(degreeDirections.mapValues(angle -> angle * 0.0174533).collect());
	}
}
