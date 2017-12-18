package com.apache.spark.sql;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoder;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class CreateDatasetWithEncoders {
	public static void main(String args[]) {
		SparkConf conf = new SparkConf().setMaster( "local" ).setAppName("ApacheSparkForJavaDevelopers" );
		SparkSession sparkSession = SparkSession.builder().config(conf).getOrCreate();
		Dataset<Row> csv = sparkSession.read()
				.format("com.databricks.spark.csv")
				.option("header", "true")
				.option("delimiter", "|")
				.option("quote", "\"")				
				.load("input/employees.csv");
		Encoder<Employee> employeeEncoder = Encoders.bean(Employee.class);
		Dataset<Employee> payroll = csv.as(employeeEncoder);
		payroll.show();
		
	}
}
