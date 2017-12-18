package com.apache.spark.sql;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

public class CreateDatasetWithStructType {
	public static void main(String args[]) {
		SparkConf conf = new SparkConf().setMaster( "local" ).setAppName("ApacheSparkForJavaDevelopers" );
		SparkSession sparkSession = SparkSession.builder().config(conf).getOrCreate();
		
		//Create a RDD
		JavaRDD<String> deptRDD = sparkSession.sparkContext()
				.textFile("input/departments.txt", 1)
				.toJavaRDD();	
		
		//Convert the RDD to RDD<Rows>
		JavaRDD<Row> rows = deptRDD.filter(string -> !string.contains("department")).map(string -> {
			String columns[] = string.split("\\,");
			return RowFactory.create(columns[0], columns[1]);
		});
		
		//Create schema
		List<StructField> structFieldList = Arrays.stream(deptRDD.first().split(",")).map(
				 fieldName -> DataTypes.createStructField(fieldName, DataTypes.StringType, true)
		 ).collect(Collectors.toList());
		
		StructType schema = DataTypes.createStructType(structFieldList);
		Dataset<Row> deptDf = sparkSession.createDataFrame(rows, schema);
		deptDf.printSchema();
		deptDf.show();		
	}
}
