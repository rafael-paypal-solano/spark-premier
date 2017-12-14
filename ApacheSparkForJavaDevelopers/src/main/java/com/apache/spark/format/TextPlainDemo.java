package com.apache.spark.format;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class TextPlainDemo {

	private static Person createPerson(String line) {
		String[] parts = line.split("~");
		return new Person(parts [0], Integer.parseInt( parts [1].trim()), parts[2]);		
	}
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		SparkConf conf = new SparkConf().setMaster( "local" ).setAppName("ApacheSparkForJavaDevelopers" );
		JavaSparkContext javaSparkContext = new JavaSparkContext( conf );	
		JavaRDD<String> textFile = javaSparkContext.textFile("input/contacts.txt");
		JavaRDD<Person> people = textFile.map(line -> createPerson(line));
		
		people.foreach(p -> System.out.println(p));		
	}
}
