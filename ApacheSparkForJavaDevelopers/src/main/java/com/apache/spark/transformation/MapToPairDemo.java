package com.apache.spark.transformation;

import java.util.Arrays;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import scala.Tuple2;

public class MapToPairDemo {
	@SuppressWarnings("resource")
	public static void main(String args[]) {
		SparkConf conf = new SparkConf().setMaster( "local" ).setAppName("ApacheSparkForJavaDevelopers" );
		JavaSparkContext javaSparkContext = new JavaSparkContext( conf );
		List<Integer> intList = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		JavaRDD<Integer> intRDD = javaSparkContext.parallelize(intList);		
		JavaPairRDD<String, Integer> mapToPair = intRDD.mapToPair(i -> (i % 2 == 0) ? new Tuple2<String, Integer>("even", i) : new Tuple2<String, Integer>("odd", i));
		
		System.out.println(mapToPair.collect());
	}
}
