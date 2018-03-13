# Spark Premier # 

## Introduction ##
 
 This project contains practice exercises from my Spark training. Most of them where implemented in python (3.6) because bussiness issues (the company I work for is a Python shop).

## Datasets ##

I provided a convenience bas script (namely bin/download-data) to download all the datasets referenced by python scripts. Datasets are downloaded into src/datasets folder and they are fully documented in their respective readme.txt (or readme.md) files; more information about these datasets can be found in Alex Gude's [*The Nine Must-Have Datasets for Investigating Recommender Systems*](https://gab41.lab41.org/the-nine-must-have-datasets-for-investigating-recommender-systems-ce9421bf981c) article.


## Optimizing Spark, ( [Excerpt from Benjamin Herdta's @ spark.tc ](http://www.spark.tc/blas-libraries-in-mllib/) ) ##

When I make use of Apache Spark’s MLlib component, I see the following warnings in your application’s logfile:

```
18/03/13 10:49:18 WARN BLAS: Failed to load implementation from: com.github.fommil.netlib.NativeSystemBLAS
18/03/13 10:49:18 WARN BLAS: Failed to load implementation from: com.github.fommil.netlib.NativeRefBLAS
```

Apache Spark makes use of a component called netlib-java, which provides a Java API for Linear Algebra routines, such as BLAS, LAPACK, etc. The netlib-java package doesn’t implement these directly, but rather delegates incoming calls to one of three implementations, in the following order:

1. A system-specific library, such as OpenBLAS, Intel MKL, or Nvidia’s cuBLAS, among others
2. A built-in native reference implementation written in Fortran, provided by netlib.org
3. A pure-java implementation, provided by F2J.

The two warnings above mean that the first two implementations were not usable, and MLlib is using the Java implementations under the covers. If your program has large data sizes and spends a lot of time in BLAS functions, you may get a significant performance boost by enabling native BLAS.

### Building Spark with netlib-lgpl ###

1. [Install OpenBLAS](https://gist.github.com/dusenberrymw/d24fcefcd245cfacbc60d3b4caccc560).
2. [Install R](https://www.digitalocean.com/community/tutorials/how-to-install-r-on-ubuntu-16-04-2) and set **R_HOME**="???" in your ~/.bashrc file.
4. Run R as sudo and install **kinitr** --install.packages('knitr', dependencies = TRUE)--.
3. Download [Spark 2.3](https://www.apache.org/dyn/closer.lua/spark/spark-2.3.0/spark-2.3.0.tgz) and upack it somewhere.

```
./dev/make-distribution.sh --name spark-netlib --pip --r --tgz -Psparkr -Phadoop-2.7 -Phive -Phive-thriftserver -Pmesos -Pyarn -Pkubernetes -Pnetlib-lgpl

```
