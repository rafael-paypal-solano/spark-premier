package com.apache.spark.action;

import java.util.Arrays;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class FirstAndTakeDemo {

	private static void printFirst(JavaRDD<Integer> intRDD) {
		System.out.println(intRDD.first());
	}
	
	private static void printTopN(JavaRDD<Integer> intRDD, int n) {
		System.out.println(intRDD.top(n));
	}
	
	private static void printn(JavaRDD<Integer> intRDD, int n) {
		System.out.println(intRDD.take(n));
	}
	
	private static void printnOrdered(JavaRDD<Integer> intRDD, int n) {
		System.out.println(intRDD.takeOrdered(n));
	}	
	
	private static void printnSample(JavaRDD<Integer> intRDD, int n) {
		System.out.println(intRDD.takeSample(false, n));
	}
	
	@SuppressWarnings("resource")
	public static void main(String args[]) {
		SparkConf conf = new SparkConf().setMaster( "local" ).setAppName("ApacheSparkForJavaDevelopers" );
		JavaSparkContext javaSparkContext = new JavaSparkContext( conf );
		JavaRDD<Integer> intRDD = javaSparkContext.parallelize(Arrays.asList(
			  8,  2, 1,  4,  5,  6,  7,  3,
			100, 23, 45, 67, 45, 12, 49, 32,
			 90, 72, 24, 89, 21, 36, 77, 41
		));
		
		printFirst(intRDD);
		printn(intRDD, 5);
		printnOrdered(intRDD, 5);
		printnSample(intRDD, 5);
		printnSample(intRDD, 5);
		printTopN(intRDD, 5);
	}
}
