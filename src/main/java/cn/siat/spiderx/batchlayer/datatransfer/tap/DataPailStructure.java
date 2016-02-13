package cn.siat.spiderx.batchlayer.datatransfer.tap;

import cn.siat.spiderx.batchlayer.datatransfer.schema.Data;

//This pail doesn't use vertical partitioning
public class DataPailStructure extends ThriftPailStructure<Data> {
  @Override
  protected Data createThriftObject() {
    return new Data();
  }

  public Class getType() {
    return Data.class;
  }
}
