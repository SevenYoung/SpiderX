package cn.siat.spiderx.batchlayer.datatransfer.tap;

import cn.siat.spiderx.batchlayer.datatransfer.schema.Data;
import cn.siat.spiderx.batchlayer.datatransfer.schema.DataUnit;
import org.apache.thrift.TBase;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.TUnion;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.meta_data.StructMetaData;

import java.util.*;


public class SplitDataPailStructure extends DataPailStructure {
    //This is an interface for both edges and properties.
  protected static interface FieldStructure {
    public boolean isValidTarget(String[] dirs);
    public void fillTarget(List<String> ret, Object val);
  }

  public static HashMap<Short, FieldStructure> validFieldMap =
    new HashMap<Short, FieldStructure>();

  private static Map<TFieldIdEnum, FieldMetaData>
    getMetadataMap(Class c) 
  {
    try {
      Object o = c.newInstance();
      return (Map) c.getField("metaDataMap").get(o);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  protected static class EdgeStructure implements FieldStructure {
    public boolean isValidTarget(String[] dirs) { return true; }
    public void fillTarget(List<String> ret, Object val) { }
  }

  protected static class PropertyStructure implements FieldStructure {
      //A property is a Thrift struct containing a property value field; this is the ID for that field.
    private TFieldIdEnum valueId;
      //The set of Thrift IDs of the property value types.
    private HashSet<Short> validIds;

    private static TFieldIdEnum getIdForClass(
      Map<TFieldIdEnum, FieldMetaData> meta,
      Class toFind) 
    {
      for(TFieldIdEnum k: meta.keySet()) {
        FieldValueMetaData md = meta.get(k).valueMetaData;
        if(md instanceof StructMetaData) {
          if(toFind.equals(((StructMetaData) md).structClass)) {
            return k;
          }
        }
      }
      throw new RuntimeException("Could not find " + toFind.toString() +
                                 " in " + meta.toString());
    }

    public PropertyStructure(Class prop) {
      try {
        Map<TFieldIdEnum, FieldMetaData> propMeta = getMetadataMap(prop);
        Class valClass = Class.forName(prop.getName() + "Value");
          //Parses the Thrift metadata to get the field ID of the property value
        valueId = getIdForClass(propMeta, valClass);

        validIds = new HashSet<Short>();
        Map<TFieldIdEnum, FieldMetaData> valMeta = getMetadataMap(valClass);
        for(TFieldIdEnum valId: valMeta.keySet()) {
            //Parses the metadata to get all valid field IDs of the property value
          validIds.add(valId.getThriftFieldId());
        }
      } catch(Exception e) {
        throw new RuntimeException(e);
      }
    }

    public boolean isValidTarget(String[] dirs) {
        //The vertical partitioning of a property value has a depth of at least two
      if(dirs.length<2) return false;
      try {
        short s = Short.parseShort(dirs[1]);
        return validIds.contains(s);
      } catch(NumberFormatException e) {
        return false;
      }
    }
  
    public void fillTarget(List<String> ret, Object val) {
      ret.add("" + ((TUnion) ((TBase)val)
              .getFieldValue(valueId))
              .getSetField()
              .getThriftFieldId());//Uses the Thrift IDs(the property value)to create the directory path for the current fact.
    }
  }

    //Thrift code to inspect and iterate over the DataUnit object
  static {
    for(DataUnit._Fields k: DataUnit.metaDataMap.keySet()) {
      FieldValueMetaData md = DataUnit.metaDataMap.get(k).valueMetaData;
      FieldStructure fieldStruct;
      if(md instanceof StructMetaData && ((StructMetaData) md)
         .structClass
         .getName()
         .endsWith("Property")) //Properties are identified by the class name of the inspected object.
      {
        fieldStruct = new PropertyStructure(((StructMetaData) md)
                                            .structClass);
      } else {
        fieldStruct = new EdgeStructure();//If class name doesn't end with "Property", it must be an edge.
      }
      validFieldMap.put(k.getThriftFieldId(), fieldStruct);
    }
  }

    public String[] deleteThree(String[] dirs){
        List<String> res = new LinkedList<String>();
        int count = 0;
        for(String dir : dirs){
            if(count++ >= 3)
                res.add(dir);
        }
        String[] resDir = new String[res.size()];
        res.toArray(resDir);
        return resDir;

    }

  @Override
  public boolean isValidTarget(String[] dirs) {
    if(dirs.length==0) return false;
      short id = 0;
    try {
        if(dirs.length >= 4){
            id = Short.parseShort(dirs[3]);
            FieldStructure s = validFieldMap.get(id);
            if(s == null) return false;
            else return s.isValidTarget(deleteThree(dirs));
        }
        else {
            id = Short.parseShort(dirs[0]);
            FieldStructure s = validFieldMap.get(id);
        //The validity check first verifies the DataUnit field ID is in the field map.
            if(s==null) return false;
            else return s.isValidTarget(dirs);
        } //Any additional checks are passed to the FieldStructure.
    } catch(NumberFormatException e) {
      return false;
    }
  }

  @Override
  public List<String> getTarget(Data object) {
    List<String> ret = new ArrayList<String>();
    DataUnit du = object.getDataunit();
    short id = du.getSetField().getThriftFieldId();
      //The top-level directory is determined by inspecting the DataUnit.
    ret.add("" + id);
      //Any further partitioning is passed to the FieldStructure.
    validFieldMap.get(id).fillTarget(ret, du.getFieldValue());
    return ret;
  }
}
