package cn.siat.spiderx.batchlayer

import cascading.tuple.Tuple
import cn.siat.spiderx.batchlayer.datatransfer.schema.{Data, DataUnit}

/**
 * Created by SevenYoung on 15-12-9.
 */
//The time field always set to the last position
object ThriftTapParser {
  /**
   * @param tuple
   * @return <car_id><road_id>,<speed>,<timestamp>
   */
  def parseGps(tuple: Tuple) = {
    val tmp = parseDataUnit(tuple)
    val time = tmp._1
    val gps = tmp._2.getGps
    Tuple4(gps.getCar_id.getCar_number, gps.getRoad_id.getRoad_id, gps.getSpeed, time)
  }

  def parseDropOff(tuple: Tuple) = {
//TODO: Parse the tuple into dropOff info depending on the business demond.
  }

  def parsePickUp(tuple: Tuple) = {
//TODO: Parse the tuple into pickup info depending on the business demond
  }


  def parseDataUnit(tuple:Tuple) ={
    val tmp = tuple.getObject(1).asInstanceOf[Data]
    Tuple2(tmp.getPedigree.getTimestamp, tmp.getDataunit)
  }
}
