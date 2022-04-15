package pl.cyfronet.spark;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import static org.apache.spark.sql.functions.*;

public class ReadFromParquetToJsonJob {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: ReadFromParquetToJsonJob <file>");
            System.exit(1);
        }
        System.out.println("Start:");

        final SparkSession spark = SparkSession.builder()
                .enableHiveSupport()
                .config("spark.sql.queryExecutionListeners","")
                .config("spark.extraListeners","")
                .appName("ReadFromParquetToJson").getOrCreate();
        Dataset<Row> dataset = spark.read().parquet("hdfs://nameservice1/user/ymmarsza/dev/part-03826-567b72b5-95e5-4807-8a45-8c3de0dff9de-c000.snappy.parquet");
//        Dataset<Row> dataset = spark.read().parquet("src/main/resources/parquet/part-03826-567b72b5-95e5-4807-8a45-8c3de0dff9de-c000.snappy.parquet");

        Dataset ds = dataset.select(
                dataset.col("id").alias("id"),
                //TODO: pids check
                dataset.col("pid.value").alias("pid"),
                dataset.col("title.value").alias("title"),
                dataset.col("description.value").alias("description"),
                dataset.col("subject.value").alias("subject"),
                dataset.col("publisher.value").alias("publisher"),
                dataset.col("bestaccessright.classname").alias("bestaccessright"),
                dataset.col("language.classname").alias("language"),
                dataset.col("fulltext.value").alias("fulltext"),
                dataset.col("instance.dateofacceptance.value").alias("published"),
                dataset.col("author.fullname").alias("author_names"),
                //authors pids
                // organizations
                //projects
                dataset.col("author.pid").alias("author_pids2"),
                flatten(dataset.col("author.pid")).as("author-pids"),
                expr("author.pid[0].value").as("author-pids")
//                flatten(col("author.pid.value")).as("author-pids")
                );
        ds.show();
        System.out.println("COUNT: " + ds.count());
        ds.write().json("hdfs://nameservice1/user/ymmarsza/dev/part-03826.json");
//        ds.write().json("src/main/resources/json/part-03826.json");
        spark.stop();
    }
}
