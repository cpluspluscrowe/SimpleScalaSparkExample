import scala.math.random
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by CCROWE on 8/16/2017.
  */
object Main {
  def main(args: Array[String]): Unit = {
    val logFile = "C:\\Users\\CCrowe\\Documents\\Random Programs\\largeTextFile.txt"
    val conf = new SparkConf().setAppName("Simple Application").setMaster("local[*]")
    val spark = new SparkContext(conf);
    val slices = if (args.length > 0) args(0).toInt else 2
    val n = 100000 * slices
    val count = spark.parallelize(1 to n, slices).map { i =>
      val x = random * 2 - 1;
      val y = random * 2 - 1;
      if (x*x + y*y < 1) 1 else 0
    }.reduce(_ + _)
    println("Pi is roughly " + 4.0 * count / n)
    spark.stop()
  }
}