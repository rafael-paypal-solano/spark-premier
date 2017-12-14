package com.apache.spark.format;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

public class CSVDemo {
	private static void CSVToPOJO(SparkSession sparkSession) {
		JavaRDD<Movie> moviesRDD = sparkSession.read().textFile("input/movies.csv")
				.javaRDD().filter(str -> !(null == str))
				.filter(str -> !(str.length()==0))
				.filter(str -> !str.contains("movieId"))
				.map(str -> MovieFactory.createMovie(str, ","));
				moviesRDD.foreach(m -> System.out.println(m));				
	}
	
	private static void CSVToDataset(SparkSession sparkSession) {
		Dataset<Row> csv = sparkSession.read().format("com.databricks.spark.csv")
				.option("header", "true")
				.option("inferSchema", "true")
				.load("input/movies.csv").toDF();
		
		
		csv.printSchema();
		csv.show();
	}
	
	private static Dataset<Row> CSVToDatasetWithCustomSchema(SparkSession sparkSession) {
		StructType customSchema = new StructType(new StructField[] {
			new StructField("movieId", DataTypes.LongType, true, Metadata.empty()),
			new StructField("title", DataTypes.StringType, true, Metadata.empty()),
			new StructField("genre", DataTypes.StringType, true, Metadata.empty())
		});
		Dataset<Row> csv = sparkSession.read().format("com.databricks.spark.csv")
			.option("header", "true")
			.option("delimiter", "|")
			.option("quote", "\"")
			.schema(customSchema)
			.load("input/movies2.csv");
		
		csv.printSchema();
		csv.show();		
		
		return csv;
	}
	
	private static void saveDataSet(SparkSession sparkSession, Dataset<Row> csv, String fileName) {
		csv.write()
		.format("com.databricks.spark.csv")
		.option("header", "true")
		.option("delimiter", "|")
		.option("quote", "\"")
		.save(fileName);		
	}
	
	public static void main(String args[]) {
		SparkConf conf = new SparkConf().setMaster( "local" ).setAppName("ApacheSparkForJavaDevelopers" );
		SparkSession sparkSession = SparkSession.builder().config(conf).getOrCreate();
		
		CSVToPOJO(sparkSession);
		CSVToDataset(sparkSession);
		saveDataSet(sparkSession, CSVToDatasetWithCustomSchema(sparkSession), "input/movies-saved.csv");
	}
}
