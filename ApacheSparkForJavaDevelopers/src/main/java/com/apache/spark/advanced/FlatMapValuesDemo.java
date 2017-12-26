package com.apache.spark.advanced;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;

import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;

import scala.Tuple2;

public class FlatMapValuesDemo {
	public static void main(String args[]) {
		SparkConf conf = new SparkConf().setMaster( "local" ).setAppName("ApacheSparkForJavaDevelopers" );
		SparkSession sparkSession = SparkSession.builder().config(conf).getOrCreate();
		JavaSparkContext context = new JavaSparkContext(sparkSession.sparkContext());		
		JavaPairRDD<String, List<Double>> degreeDirections = context.parallelizePairs(
			Arrays.asList(				
				new Tuple2<String, List<Double>>("South-East", Arrays.asList(315.0, 315.0 * 0.0174533)),
				new Tuple2<String, List<Double>>("South", Arrays.asList(270.0, 270.0 * 0.0174533)),
				new Tuple2<String, List<Double>>("South-West", Arrays.asList(225.0, 315.0 * 0.0174533)),				
				new Tuple2<String, List<Double>>("West", Arrays.asList(180.0, 180.0 * 0.0174533)),
				
				new Tuple2<String, List<Double>>("North-West", Arrays.asList(135.0, 135.0 * 0.0174533)),
				new Tuple2<String, List<Double>>("North", Arrays.asList(90.0, 90.0 * 0.0174533)),
				new Tuple2<String, List<Double>>("South-East", Arrays.asList(45.0, 45.0 * 0.0174533)),
				new Tuple2<String, List<Double>>("East", Arrays.asList(0.0, 0.0))
				
			)
		); 					
		
		
		List<Tuple2<String, Double>> result = degreeDirections
		.flatMapValues(list -> {
			List<Double> values = new LinkedList<Double>();			
			list.forEach( v -> values.add(v));
			return values;
		}).collect();
		
		System.out.println(result);
	}
}
