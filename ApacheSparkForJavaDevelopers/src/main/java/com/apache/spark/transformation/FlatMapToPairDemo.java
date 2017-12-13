package com.apache.spark.transformation;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import scala.Tuple2;

public class FlatMapToPairDemo {
	@SuppressWarnings("resource")
	public static void main(String args[]) {
		SparkConf conf = new SparkConf().setMaster( "local" ).setAppName("ApacheSparkForJavaDevelopers" );
		JavaSparkContext javaSparkContext = new JavaSparkContext( conf );
		JavaRDD<String> stringRDD =javaSparkContext.parallelize(Arrays.asList("Hello Spark", "Hello Java"));
		JavaPairRDD<String, Integer> result = stringRDD.flatMapToPair(s -> Arrays.asList(s.split(" ")).stream()
				.map(token -> new Tuple2<String, Integer>(token, token.length())).collect(Collectors.toList())
				.iterator());
		System.out.println(result.collect());
		
		
	}
}
