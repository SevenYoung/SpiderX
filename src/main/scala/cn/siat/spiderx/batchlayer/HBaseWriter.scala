package cn.siat.spiderx.batchlayer

import org.apache.hadoop.hbase.{TableName, HColumnDescriptor, HTableDescriptor}
import org.apache.hadoop.hbase.client.{HBaseAdmin, HTable}
import org.apache.hadoop.hbase.util.Bytes
import org.apache.hadoop.conf.Configuration

import scala.collection.mutable.ArrayBuffer

/**
 * Created by SevenYoung on 15-12-13.
 */
class HBaseWriter(val conf:Configuration) {
  private var htable = None:Option[HTable]

  def createTableAndColumn(table:String, columnFamilys:Array[Byte]*) = {
    val admin = new HBaseAdmin(conf)
    val tableName = TableName.valueOf(table)
    val tableDesc = new HTableDescriptor(tableName)
    columnFamilys.foreach(cf => {
      val meta = new HColumnDescriptor(cf)
      tableDesc.addFamily(meta)
    })
    if(admin.tableExists(tableName)){
      if(admin.isTableEnabled(tableName)){
        admin.disableTable(tableName)
      }
      admin.deleteTable(tableName)
    }
    admin.createTable(tableDesc)
  }

//  def createTableAndColumn(table:String, columnFamilys:String*) = {
//    val cfs = new ArrayBuffer[String]()
//    columnFamilys.foldLeft(cfs)((bf,item) => bf.+=:(item))
//    val res = cfs.toArray.map(Bytes.toBytes(_))
//    createTableAndColumn(table, res)
//  }

  def getTable(tableName:String) = {
    htable = Some(new HTable(conf, tableName))
    htable
  }


}
