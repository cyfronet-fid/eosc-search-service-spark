package pl.cyfronet.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.types.StructType;
import pl.cyfronet.spark.beans.Author;

import java.util.List;
import java.util.Map;

import static org.apache.spark.sql.functions.*;

public class ReadFromParquetToJson {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: JavaWordCount <file>");
            System.exit(1);
        }
        System.out.println("Start:");

        final SparkConf sparkConf = new SparkConf()
                .setAppName("ReadFromParquetToJson");
        final JavaSparkContext javaSparkContext = new JavaSparkContext(sparkConf);
        SQLContext sqlContext = new org.apache.spark.sql.SQLContext(javaSparkContext);
        DataFrame dataFrameSrc = sqlContext.read().parquet("hdfs://nameservice1/user/ymmarsza/dev/part-03826-567b72b5-95e5-4807-8a45-8c3de0dff9de-c000.snappy.parquet");
        DataFrame authors = dataFrameSrc
                .select(
                        col("id"),
                        explode(col("author")).as("authors"));
        authors.show();
        DataFrame df = dataFrameSrc.select(
                dataFrameSrc.col("id").alias("id"),
                dataFrameSrc.col("title.value").alias("title"),
                dataFrameSrc.col("description.value").alias("description"),
                dataFrameSrc.col("subject.value").alias("subject"),
                dataFrameSrc.col("fulltext.value").alias("fulltext"),
                callUDF("convertAuthors", dataFrameSrc.col("author")));
        System.out.println("COUNT: " + df.count());
        df.write().json("hdfs://nameservice1/user/ymmarsza/dev/part-03826.json");
        javaSparkContext.stop();
    }
}
