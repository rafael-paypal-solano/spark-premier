package com.apache.spark.transformation;

import java.util.Arrays;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;

import scala.Tuple2;

public class ReduceByKeyDemo {
	@SuppressWarnings("resource")
	public static void main(String args[]) {
		SparkConf conf = new SparkConf().setMaster( "local" ).setAppName("ApacheSparkForJavaDevelopers" );
		JavaSparkContext javaSparkContext = new JavaSparkContext( conf );		
		JavaPairRDD<String, Double> tupleRDD = javaSparkContext.parallelize(Arrays.asList(
			new Tuple2<String,Double>("a", 2.0),
			new Tuple2<String,Double>("a", 3.0),
			new Tuple2<String,Double>("a", 1.0),
			new Tuple2<String,Double>("b", 4.0),
			new Tuple2<String,Double>("b", 5.0),
			new Tuple2<String,Double>("a", 6.0))
		).mapToPair(t-> t);
		
		JavaPairRDD<String, Double> reduced = tupleRDD.reduceByKey((v1, v2) -> v1 + v2);
		
		
		System.out.println(reduced.collect());
	}
}
