package com.apache.spark.transformation;

import java.util.Arrays;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class MapAndFilterDemo {
	
	@SuppressWarnings("resource")
	public static final void main(String args[]) {
		SparkConf conf = new SparkConf().setMaster( "local" ).setAppName("ApacheSparkForJavaDevelopers" );
		JavaSparkContext javaSparkContext = new JavaSparkContext( conf );
		List<Integer> intList = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		JavaRDD<Integer> intRDD = javaSparkContext.parallelize(intList);
		JavaRDD<Integer> result = intRDD.map(x-> x + 1);
		JavaRDD<Integer> pairs = intRDD.filter(x -> x % 2 == 0);
		
		System.out.println(result.collect());
		System.out.println(pairs.collect());
	}
}
