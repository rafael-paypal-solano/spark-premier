package com.apache.spark.format;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.codehaus.jackson.map.ObjectMapper;

public class JSONDemo {
	public static JavaRDD<PersonDetails> loadWithObjectMapper(SparkSession sparkSession) {
		RDD<String> textFile = sparkSession.sparkContext().textFile("input/person-details.json", 1);
		JavaRDD<PersonDetails> parser = textFile
			.toJavaRDD()
			.map(v1 -> new ObjectMapper().readValue(v1, PersonDetails.class));
		parser.foreach(t -> System.out.println(t));
		return parser;
	}
	
	@SuppressWarnings("deprecation")
	public static Dataset <Row> loadWithDataSet(SparkSession sparkSession) {
		RDD<String> textFile = sparkSession.sparkContext().textFile("input/person-details.json", 1);
		StructType schema = new StructType( new StructField[] {
			DataTypes.createStructField("cid", DataTypes.IntegerType, true),
			DataTypes.createStructField("county", DataTypes.StringType, true),
			DataTypes.createStructField("firstName", DataTypes.StringType, true),
			DataTypes.createStructField("sex", DataTypes.StringType, true),
			DataTypes.createStructField("year", DataTypes.StringType, true),
			DataTypes.createStructField("dateOfBirth", DataTypes.TimestampType, true)
		});
		
		Dataset <Row> persons = sparkSession.read().schema(schema).json(textFile);
		persons.printSchema();
		persons.show();	
		
		return persons;
	}
	
	public static void main(String args[]) {
		SparkConf conf = new SparkConf().setMaster( "local" ).setAppName("ApacheSparkForJavaDevelopers" );
		SparkSession sparkSession = SparkSession.builder().config(conf).getOrCreate();
		
		loadWithObjectMapper(sparkSession);
		loadWithDataSet(sparkSession);
	}
}
