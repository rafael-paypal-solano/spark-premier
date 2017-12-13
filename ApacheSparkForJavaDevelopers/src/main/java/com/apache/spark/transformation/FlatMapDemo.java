package com.apache.spark.transformation;

import java.util.Arrays;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class FlatMapDemo {
	@SuppressWarnings("resource")
	public static void main(String args[]) {
		SparkConf conf = new SparkConf().setMaster( "local" ).setAppName("ApacheSparkForJavaDevelopers" );
		JavaSparkContext javaSparkContext = new JavaSparkContext( conf );
		JavaRDD<String> stringRDD =javaSparkContext.parallelize(Arrays.asList("Hello Spark", "Hello Java"));
		JavaRDD<String> result = stringRDD.flatMap(t -> Arrays.asList(t.split(" ")).iterator());
		System.out.println(result.collect());
	}
}
