package pl.cyfronet.spark;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import static org.apache.spark.sql.functions.col;
import static org.apache.spark.sql.functions.explode;

public class ReadFromParquetToJsonJob {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: JavaWordCount <file>");
            System.exit(1);
        }
        System.out.println("Start:");

        final SparkSession spark = SparkSession.builder()
                .enableHiveSupport()
                .config("spark.sql.queryExecutionListeners","")
                .config("spark.extraListeners","")
                .appName("ReadFromParquetToJson").getOrCreate();
        Dataset<Row> dataset = spark.read().parquet("hdfs://nameservice1/user/ymmarsza/dev/part-03826-567b72b5-95e5-4807-8a45-8c3de0dff9de-c000.snappy.parquet");
//        [ {
//            "affiliation":[{
//            "value":"University of Nebraska–Lincoln"
//            }],
//            "fullname":"Yunshen Zhou",
//            "pid":[{
//            "qualifier":{
//                "classid":"URL", "classname":"URL", "schemeid":"dnet:pid_types", "schemename":"dnet:pid_types"
//            },"value":"https://academic.microsoft.com/#/detail/2307057694"
//            }],
//            "rank":2
//        } , {} , {} ]
//        List<Author> authorsList = (List<Author>) dataFrameSrc.select("author");
//        ---
//        Col
//        Array
//        ---Object
//        ------fullname
//        ------pid[0].value

        Dataset ds = dataset.select(
                dataset.col("id").alias("id"),
                dataset.col("title.value").alias("title"),
                dataset.col("description.value").alias("description"),
                dataset.col("subject.value").alias("subject"),
                dataset.col("fulltext.value").alias("fulltext"),
                dataset.col("author").alias("authors"));
        dataset.withColumn("author", explode(col("author")))
                .select(col("id"),
                        col("author.fullname"),
                        col("author.rank"),
                        col("author.pid.value")).show();
//        ds.show();
        System.out.println("COUNT: " + ds.count());
        ds.write().json("hdfs://nameservice1/user/ymmarsza/dev/part-03826.json");
        spark.stop();
    }
}
