package com.apache.spark.transformation;

import java.util.Arrays;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import scala.Tuple2;

public class SparkWordCount {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		SparkConf conf =new SparkConf().setMaster("local").setAppName("WordCount");
		JavaSparkContext javaSparkContext = new JavaSparkContext(conf);
		JavaRDD<String> inputData = javaSparkContext.textFile("input/Apology_by_Plato.txt");
		
		JavaPairRDD<String, Integer> flattenPairs = inputData.flatMapToPair(
				text -> Arrays.asList(text.split(" ")).stream().map(word ->new Tuple2<String,Integer>(word,1)).iterator());
		JavaPairRDD<String, Integer> wordCountRDD = flattenPairs.reduceByKey((v1,v2) -> v1+v2);	
		wordCountRDD.saveAsTextFile("output/Apology_by_Plato_Out");
	}
}
