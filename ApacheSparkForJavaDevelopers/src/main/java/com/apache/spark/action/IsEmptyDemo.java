package com.apache.spark.action;

import java.util.Arrays;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class IsEmptyDemo {
	
	@SuppressWarnings("resource")
	public static void main(String args[]) {
		SparkConf conf = new SparkConf().setMaster( "local" ).setAppName("ApacheSparkForJavaDevelopers" );
		JavaSparkContext javaSparkContext = new JavaSparkContext( conf );
		JavaRDD<Integer> intRDD = javaSparkContext.parallelize(Arrays.asList(1,2,3));
		boolean isRDDEmpty= intRDD.filter(a-> a.equals(5)).isEmpty();
		System.out.println("The RDD is empty ::"+isRDDEmpty);
	}
}
