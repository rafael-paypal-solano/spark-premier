package com.apache.spark.transformation;

import java.util.Arrays;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class CartesianDemo {
	public static void main(String[] args) {
		SparkConf conf = new SparkConf().setMaster( "local" ).setAppName("ApacheSparkForJavaDevelopers" );
		JavaSparkContext javaSparkContext = new JavaSparkContext( conf );
		JavaRDD<String> strings = javaSparkContext.parallelize(Arrays.asList("a","b","c"));
		JavaRDD<Integer> integers = javaSparkContext.parallelize(Arrays.asList(1,2,3,4,5));
		
		System.out.println(strings.cartesian(integers).collect()); // --> List<Tuple2<String, Integer>>
		//System.out.println(strings.cartesian(integers).collectAsMap());
	}
}
