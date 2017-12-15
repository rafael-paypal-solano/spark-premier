package com.apache.spark.format;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.SparkSession;
import org.codehaus.jackson.map.ObjectMapper;

public class JSONDemo {
	public static void main(String args[]) {
		SparkConf conf = new SparkConf().setMaster( "local" ).setAppName("ApacheSparkForJavaDevelopers" );
		SparkSession sparkSession = SparkSession.builder().config(conf).getOrCreate();
		RDD<String> textFile = sparkSession.sparkContext().textFile("input/person-details.json", 1);
		JavaRDD<PersonDetails> parser = textFile
			.toJavaRDD()
			.map(v1 -> new ObjectMapper().readValue(v1, PersonDetails.class));
		parser.foreach(t -> System.out.println(t));
	}
}
