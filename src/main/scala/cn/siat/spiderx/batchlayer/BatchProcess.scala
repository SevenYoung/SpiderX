package cn.siat.spiderx.batchlayer

import cascading.tuple.Tuple
import cn.siat.spiderx.batchlayer.datatransfer.batchtransfer.BatchWorkflow
import cn.siat.spiderx.batchlayer.datatransfer.schema.DataUnit
import cn.siat.spiderx.batchlayer.datatransfer.schema.Data
import com.tresata.spark.scalding.{CascadingRDD, HadoopTap}
import com.twitter.maple.hbase.mapred.TableInputFormat
import org.apache.hadoop
import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.client.{Put, HTable}
import org.apache.hadoop.hbase.mapreduce.TableInputFormat
import org.apache.hadoop.hbase.util.Bytes
import org.apache.spark.{SparkConf, SparkContext}

import scala.util.Random


/**
 * Created by SevenYoung on 15-11-21.
 */
object BatchProcess{
  def main(args:Array[String]): Unit ={
    /**
     * This batch layer implemented by the spark using the scalding-spark external dependency.
     */
    //*******************************************The data partition reading conf********************************************//
    //If you specify which fields you want to read , then you can get this specific fields.
    //In the statement below, we just get out the PAGE_VIEW data unit.
//    val masterDataset:HadoopTap = BatchWorkflow.attributeTap(BatchWorkflow.DATA_ROOT + "master", DataUnit._Fields.GPS).asInstanceOf[HadoopTap];

    //If you don't specify the fields you need, then the reader will recursively read the data directory,in this situation
    //it will read all kinds of data unit you write into the master data, this is what we need.
//    val masterDataset:HadoopTap = BatchWorkflow.splitDataTap(BatchWorkflow.DATA_ROOT + "master").asInstanceOf[HadoopTap];


    //*******************************************The data partition reading conf********************************************//


    //*********This's the method to inspect the tuple item in the CascadingRDD.The tuple.getObject(0) has no meaning.********
    //  def getPageProperty(tuple: Tuple) = {
    //    tuple.getObject(1).asInstanceOf[Data].get_dataunit().get_page_property()
    //  }
    //val pageview = new CascadingRDD(sc, masterDataset, new Configuration()).map(tuple => getPageview(tuple))
    //      .map(pageview => (pageview.get_person().get_user_id(),pageview.get_page().get_url())).groupByKey().foreach(println)
    //
    //***************************************************The access method usage *********************************************


    BatchWorkflow.masterDataIngest()

    val gpsDataset:HadoopTap = BatchWorkflow.attributeTap(BatchWorkflow.DATA_ROOT + "master", DataUnit._Fields.GPS).asInstanceOf[HadoopTap];
    val dropOffDataset:HadoopTap = BatchWorkflow.attributeTap(BatchWorkflow.DATA_ROOT + "master", DataUnit._Fields.DROPOFF).asInstanceOf[HadoopTap];



//    val masterDataset:HadoopTap = BatchWorkflow.splitDataTap(BatchWorkflow.DATA_ROOT + "master").asInstanceOf[HadoopTap];

    val conf = new SparkConf().setMaster("local[3]").setAppName("SpiderX")
    val sc = new SparkContext(conf)

    val gpsRaw = new CascadingRDD(sc, gpsDataset, new hadoop.conf.Configuration()).cache()
    val dropOffRaw = new CascadingRDD(sc, dropOffDataset, new hadoop.conf.Configuration()).cache()

    gpsRaw.foreach(println)
    dropOffRaw.foreach(println)
//    //The parsed result is : <car_no:String>,<roadId:Long>,<speed:Double>,<timestamp:Long>
//    val gps = gpsRaw.map(ThriftTapParser.parseGps(_))
//    val roadSpeed = gps.map(tuple => (tuple._2, tuple._3)).groupByKey().mapValues(
//    ite => {
//      val size = ite.size
//      val sum = ite.reduce(_ + _)
//      if(size != 0) sum / size else 0
//    }
//    )
////    roadSpeed.foreach(println)
//
//    val hconf = HBaseConfiguration.create()
//    val tableName = "speed"
//    val writer = new HBaseWriter(hconf)
//    writer.createTableAndColumn(tableName, Bytes.toBytes("avg"))
////    val table = writer.getTable(tableName)
////    val target = if(table.isDefined) table.get else {System.err.println("The table is not initialized"); return }
//
//    roadSpeed.foreachPartition( itr => {
//      val hconf = HBaseConfiguration.create()
//      val tableName = "speed"
//      val writer = new HBaseWriter(hconf)
//      val table = writer.getTable(tableName)
//      val target = table.get
//      itr.foreach(pair => {
//        val put = new Put(Bytes.toBytes(pair._1))
//        put.add(Bytes.toBytes("avg"), Bytes.toBytes("day"), Bytes.toBytes(pair._2))
//        target.put(put)
//      })
//      target.flushCommits()
//      target.close()
//    }
//    )
//

  }
}
