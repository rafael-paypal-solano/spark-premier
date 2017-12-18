package com.apache.spark.action;

import java.util.Arrays;
import java.util.Comparator;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class MinMaxDemo {
	@SuppressWarnings("resource")
	public static void main(String args[]) {
		SparkConf conf = new SparkConf().setMaster( "local" ).setAppName("ApacheSparkForJavaDevelopers" );
		JavaSparkContext javaSparkContext = new JavaSparkContext( conf );
		JavaRDD<Integer> intRDD = javaSparkContext.parallelize(Arrays.asList(
			1,2,3,4,5,6,7,8
		));
		
		System.out.println(String.format(			
			"Min=%d, Max=%s",
			intRDD.min(Comparator.naturalOrder()),
			intRDD.max(Comparator.naturalOrder())
		));
		
		
	}
}
