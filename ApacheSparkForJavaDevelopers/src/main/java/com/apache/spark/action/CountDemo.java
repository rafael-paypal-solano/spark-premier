package com.apache.spark.action;

import java.util.Arrays;
import java.util.Map;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import scala.Tuple2;

public class CountDemo {

	public static void count(JavaSparkContext javaSparkContext) {	
		
		JavaRDD<Integer> intRDD = javaSparkContext.parallelize(Arrays.asList(1,2,3,4,4,5,6));
		long count = intRDD.count();
		
		System.out.println(count);
	}
	
	public static void countByKey(JavaSparkContext javaSparkContext) {	
		JavaPairRDD<String,Integer> pairRDD = javaSparkContext.parallelizePairs(
			Arrays.asList(
				new Tuple2<String,Integer>("a", 1),
				new Tuple2<String,Integer>("b", 2),
				new Tuple2<String,Integer>("c", 3),
				new Tuple2<String,Integer>("a", 4)				
			)
		);		
		Map<String, Long> countByKeyMap= pairRDD.countByKey();
		
		countByKeyMap.entrySet().stream().forEach(entry-> System.out.println(String.format("%s=%s", entry.getKey(), entry.getValue())));
		
	}
	
	public static void countByValue(JavaSparkContext javaSparkContext) {
		JavaPairRDD<String,Integer> pairRDD = javaSparkContext.parallelizePairs(
			Arrays.asList(
				new Tuple2<String,Integer>("a", 1),
				new Tuple2<String,Integer>("b", 2),
				new Tuple2<String,Integer>("c", 3),
				new Tuple2<String,Integer>("a", 4),
				new Tuple2<String,Integer>("a", 4)
			)
		);		
		Map<Tuple2<String, Integer>, Long> countByValueMap= pairRDD.countByValue();
		countByValueMap.entrySet().stream().forEach(entry-> System.out.println(String.format("%s=%s", entry.getKey(), entry.getValue())));
	}	
	
	public static void main(String args[]) {
		SparkConf conf = new SparkConf().setMaster( "local" ).setAppName("ApacheSparkForJavaDevelopers" );
		JavaSparkContext javaSparkContext = new JavaSparkContext( conf );

		count(javaSparkContext);
		countByKey(javaSparkContext);
		countByValue(javaSparkContext);
	}
}
