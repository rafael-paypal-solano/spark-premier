package com.apache.spark.transformation;

import java.util.Arrays;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class IntersectionDemo {
	@SuppressWarnings("resource")
	public static void main(String args[]) {
		SparkConf conf = new SparkConf().setMaster( "local" ).setAppName("ApacheSparkForJavaDevelopers" );
		JavaSparkContext javaSparkContext = new JavaSparkContext( conf );
		JavaRDD<Integer> intRDD1 = javaSparkContext.parallelize(Arrays.asList(1,2,3));
		JavaRDD<Integer> intRDD2 = javaSparkContext.parallelize(Arrays.asList(3,4,5,6));
		System.out.println(intRDD1.intersection(intRDD2).collect());		
	}
}
