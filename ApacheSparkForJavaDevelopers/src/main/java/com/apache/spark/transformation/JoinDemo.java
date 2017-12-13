package com.apache.spark.transformation;

import java.util.Arrays;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.Optional;

import scala.Tuple2;

public class JoinDemo {
	@SuppressWarnings("resource")
	public static void main(String args[]) {
		SparkConf conf = new SparkConf().setMaster( "local" ).setAppName("ApacheSparkForJavaDevelopers" );
		JavaSparkContext javaSparkContext = new JavaSparkContext( conf );		
		JavaPairRDD<String, String> pairRDD1 = javaSparkContext.parallelizePairs(Arrays.asList(
			new Tuple2<String,String>("B", "A"),
			new Tuple2<String,String>("C", "D"),
			new Tuple2<String,String>("D", "A"),
			new Tuple2<String,String>("A", "B")
		));
		JavaPairRDD<String, Integer> pairRDD2 = javaSparkContext.parallelizePairs(Arrays.asList(
			new Tuple2<String,Integer>("B", 2),
			new Tuple2<String,Integer>("C", 5),
			new Tuple2<String,Integer>("D", 7),
			new Tuple2<String,Integer>("A", 8)
		));	
		
		JavaPairRDD<String, Tuple2<String, Integer>>joinedRDD = pairRDD1.join(pairRDD2);
		JavaPairRDD<String, Tuple2<Optional<String>, Integer>> rightJoinedRDD = pairRDD1.rightOuterJoin(pairRDD2);
		JavaPairRDD<String, Tuple2<String, Optional<Integer>>> leftJoinedRDD = pairRDD1.leftOuterJoin(pairRDD2);
		
		System.out.println(joinedRDD.collect());
		System.out.println(rightJoinedRDD.collect());
		System.out.println(leftJoinedRDD.collect());
	}
}
