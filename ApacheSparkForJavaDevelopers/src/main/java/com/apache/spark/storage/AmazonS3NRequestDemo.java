package com.apache.spark.storage;

import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

/*
 https://console.aws.amazon.com/iam/home?#/security_credential
 https://hadoop.apache.org/docs/stable/hadoop-aws/tools/hadoop-aws/index.html#Authentication_properties
 */
public class AmazonS3NRequestDemo { 

	private static JavaRDD<String> readFile(
		JavaSparkContext javaSparkContext,
		String accessKey,
		String secretAccessKey,
		String bucketName,
		String filePath// https://s3.amazonaws.com/nemesys-catalogs/boucher.txt
	) {

		final String url="s3n://"+bucketName+"/"+filePath;
		
		System.out.println(String.format("url='%s', awsAccessKeyId='%s',awsSecretAccessKey='%s'", url, accessKey, secretAccessKey));
		javaSparkContext.hadoopConfiguration().set("fs.s3n.awsAccessKeyId", accessKey);
		javaSparkContext.hadoopConfiguration().set("fs.s3n.awsSecretAccessKey", secretAccessKey);

		return javaSparkContext.textFile(url);
	}
	// https://aws.amazon.com/s3/pricing/
	public static void main(String[] args) {
		SparkConf conf = new SparkConf().setMaster( "local" ).setAppName("ApacheSparkForJavaDevelopers" );
		JavaSparkContext javaSparkContext = new JavaSparkContext( conf );
		JavaRDD<String>  file = readFile(
			javaSparkContext,			
			System.getenv("AWS_ACCESS_KEY_ID"), 
			System.getenv("AWS_SECRET_ACCESS_KEY"),
			args[0], 
			args[1]
		);
		List<String> productNames = file.collect();
		System.out.println(productNames);
	}

}
