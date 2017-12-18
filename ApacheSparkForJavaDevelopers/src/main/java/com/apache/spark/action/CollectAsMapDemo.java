package com.apache.spark.action;
import scala.Tuple2;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class CollectAsMapDemo {
	@SuppressWarnings("resource")
	public static void main(String args[]) {
		SparkConf conf = new SparkConf().setMaster( "local" ).setAppName("ApacheSparkForJavaDevelopers" );
		JavaSparkContext javaSparkContext = new JavaSparkContext( conf );
		List<Tuple2<String, Integer>> list= Arrays.asList(
			new Tuple2<String,Integer>("a", 1),
			new Tuple2<String,Integer>("b", 2),
			new Tuple2<String,Integer>("c", 3),
			new Tuple2<String,Integer>("a", 4)			
		);
		JavaPairRDD<String,Integer> pairRDD = javaSparkContext.parallelizePairs(list);		
		Map<String, Integer> collectMap=pairRDD.collectAsMap();
		System.out.println(collectMap);
	}
}
