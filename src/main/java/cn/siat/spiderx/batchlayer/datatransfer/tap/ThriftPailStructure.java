package cn.siat.spiderx.batchlayer.datatransfer.tap;

import backtype.hadoop.pail.PailStructure;
import org.apache.thrift.TBase;
import org.apache.thrift.TDeserializer;
import org.apache.thrift.TException;
import org.apache.thrift.TSerializer;

import java.util.Collections;
import java.util.List;

public abstract class ThriftPailStructure<T extends Comparable> 
  implements PailStructure<T>
{

    /**
     * The constructor of the Thrift object must be implemented in the child class.
     * @return
     */
  protected abstract T createThriftObject();

  private transient TDeserializer des;


  private TDeserializer getDeserializer() {
    if(des==null) des = new TDeserializer();
    return des;
  }

  public T deserialize(byte[] record) {
      //A new Thrift object is constructed prior to deserialization
    T ret = createThriftObject();
    try {
      getDeserializer().deserialize((TBase)ret, record);
    } catch (TException e) {
      throw new RuntimeException(e);
    }
    return ret;
  }

    //It's Thrify utilities for serializing object to binary arrays.
  private transient TSerializer ser;

  private TSerializer getSerializer() {
    if(ser==null) ser = new TSerializer();
    return ser;
  }

  public byte[] serialize(T obj) {
    try {
      return getSerializer().serialize((TBase)obj);
    } catch (TException e) {
      throw new RuntimeException(e);
    }
  }

  public boolean isValidTarget(String... dirs) {
    return true;
  }

  public List<String> getTarget(T object) {
    return Collections.EMPTY_LIST;
  }
}
