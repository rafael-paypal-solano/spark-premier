package com.apache.spark.transformation;

import java.util.Arrays;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;

import scala.Tuple2;

public class CoGroupDemo {
	public static void main(String[] args) {
		SparkConf conf = new SparkConf().setMaster( "local" ).setAppName("ApacheSparkForJavaDevelopers" );
		JavaSparkContext javaSparkContext = new JavaSparkContext( conf );				
		JavaPairRDD<String, String> pairRDD1 = javaSparkContext.parallelizePairs(Arrays.asList(
			new Tuple2<String,String>("B", "X"),
			new Tuple2<String,String>("B", "Y"),
			new Tuple2<String,String>("A", "Z"),
			new Tuple2<String,String>("A", "W")			
		));
		JavaPairRDD<String, Integer> pairRDD2 = javaSparkContext.parallelizePairs(Arrays.asList(
			new Tuple2<String,Integer>("B", 2),
			new Tuple2<String,Integer>("B", 5),
			new Tuple2<String,Integer>("A", 7),
			new Tuple2<String,Integer>("A", 8)			
		));
		
		JavaPairRDD<String, Tuple2<Iterable<String>, Iterable<Integer>>> cogroupedRDD = pairRDD1.cogroup(pairRDD2);
		JavaPairRDD<String, Tuple2<Iterable<String>, Iterable<Integer>>> groupWithRDD = pairRDD1.cogroup(pairRDD2);
		
		System.out.println(cogroupedRDD.collect());
		System.out.println(groupWithRDD.collect());
	}
}
