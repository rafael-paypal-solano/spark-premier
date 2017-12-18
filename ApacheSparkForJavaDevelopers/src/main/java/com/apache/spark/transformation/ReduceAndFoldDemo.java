package com.apache.spark.transformation;

import java.util.Arrays;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class ReduceAndFoldDemo {
	
	private static void reduce(JavaRDD<Integer> list) {
		int sum=list.reduce((a,b)->a+b);
		System.out.println("The sum of all the elements of RDD using reduce is "+sum);		
	}
	
	private static void fold(JavaRDD<Integer> list) {
		int sumWithBias=list.fold(1, (a,b)->a+b);
		System.out.println("The sum of all the elements of RDD using fold is "+sumWithBias);
	}
	
	@SuppressWarnings("resource")
	public static void main(String args[]) {
		SparkConf conf = new SparkConf().setMaster( "local" ).setAppName("ApacheSparkForJavaDevelopers" );
		JavaSparkContext javaSparkContext = new JavaSparkContext( conf );
		JavaRDD<Integer> intRDD = javaSparkContext.parallelize(Arrays.asList(1,1,1));
		reduce(intRDD);
		fold(intRDD);
	}
}
