package cn.siat.spiderx.batchlayer

import java.io.IOException

import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.client.{HTable}
import org.apache.hadoop.hbase.util.Bytes

/**
 * Created by SevenYoung on 15-12-13.
 */
object HBaseReader extends App{
  val conf = HBaseConfiguration.create()
  val table = new HTable(conf, "speed")
  val reader = table.getScanner(Bytes.toBytes("avg"))
  var res = reader.next()
  try{
    while(res != null){
      val rowKey = Bytes.toLong(res.getRow)
      val value = Bytes.toDouble(res.getValue(Bytes.toBytes("avg"), Bytes.toBytes("day")))
      println(rowKey + " " + value)
      res = reader.next()
    }
  }finally {
    reader.close()
    table.close()
  }

}
