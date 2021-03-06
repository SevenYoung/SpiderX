/**
 * Autogenerated by Thrift Compiler (0.9.3)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package cn.siat.spiderx.batchlayer.datatransfer.schema;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.3)", date = "2015-12-08")
public class GpsEdge implements org.apache.thrift.TBase<GpsEdge, GpsEdge._Fields>, java.io.Serializable, Cloneable, Comparable<GpsEdge> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("GpsEdge");

  private static final org.apache.thrift.protocol.TField CAR_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("car_id", org.apache.thrift.protocol.TType.STRUCT, (short)1);
  private static final org.apache.thrift.protocol.TField MATCH_COORD_FIELD_DESC = new org.apache.thrift.protocol.TField("match_coord", org.apache.thrift.protocol.TType.STRUCT, (short)2);
  private static final org.apache.thrift.protocol.TField SRC_COORD_FIELD_DESC = new org.apache.thrift.protocol.TField("src_coord", org.apache.thrift.protocol.TType.STRUCT, (short)3);
  private static final org.apache.thrift.protocol.TField SPEED_FIELD_DESC = new org.apache.thrift.protocol.TField("speed", org.apache.thrift.protocol.TType.DOUBLE, (short)4);
  private static final org.apache.thrift.protocol.TField ROAD_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("road_id", org.apache.thrift.protocol.TType.STRUCT, (short)5);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new GpsEdgeStandardSchemeFactory());
    schemes.put(TupleScheme.class, new GpsEdgeTupleSchemeFactory());
  }

  public CarID car_id; // required
  public Coordinate match_coord; // required
  public Coordinate src_coord; // required
  public double speed; // required
  public RoadId road_id; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    CAR_ID((short)1, "car_id"),
    MATCH_COORD((short)2, "match_coord"),
    SRC_COORD((short)3, "src_coord"),
    SPEED((short)4, "speed"),
    ROAD_ID((short)5, "road_id");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // CAR_ID
          return CAR_ID;
        case 2: // MATCH_COORD
          return MATCH_COORD;
        case 3: // SRC_COORD
          return SRC_COORD;
        case 4: // SPEED
          return SPEED;
        case 5: // ROAD_ID
          return ROAD_ID;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __SPEED_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.ROAD_ID};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.CAR_ID, new org.apache.thrift.meta_data.FieldMetaData("car_id", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, CarID.class)));
    tmpMap.put(_Fields.MATCH_COORD, new org.apache.thrift.meta_data.FieldMetaData("match_coord", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, Coordinate.class)));
    tmpMap.put(_Fields.SRC_COORD, new org.apache.thrift.meta_data.FieldMetaData("src_coord", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, Coordinate.class)));
    tmpMap.put(_Fields.SPEED, new org.apache.thrift.meta_data.FieldMetaData("speed", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.DOUBLE)));
    tmpMap.put(_Fields.ROAD_ID, new org.apache.thrift.meta_data.FieldMetaData("road_id", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, RoadId.class)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(GpsEdge.class, metaDataMap);
  }

  public GpsEdge() {
  }

  public GpsEdge(
    CarID car_id,
    Coordinate match_coord,
    Coordinate src_coord,
    double speed)
  {
    this();
    this.car_id = car_id;
    this.match_coord = match_coord;
    this.src_coord = src_coord;
    this.speed = speed;
    setSpeedIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public GpsEdge(GpsEdge other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetCar_id()) {
      this.car_id = new CarID(other.car_id);
    }
    if (other.isSetMatch_coord()) {
      this.match_coord = new Coordinate(other.match_coord);
    }
    if (other.isSetSrc_coord()) {
      this.src_coord = new Coordinate(other.src_coord);
    }
    this.speed = other.speed;
    if (other.isSetRoad_id()) {
      this.road_id = new RoadId(other.road_id);
    }
  }

  public GpsEdge deepCopy() {
    return new GpsEdge(this);
  }

  @Override
  public void clear() {
    this.car_id = null;
    this.match_coord = null;
    this.src_coord = null;
    setSpeedIsSet(false);
    this.speed = 0.0;
    this.road_id = null;
  }

  public CarID getCar_id() {
    return this.car_id;
  }

  public GpsEdge setCar_id(CarID car_id) {
    this.car_id = car_id;
    return this;
  }

  public void unsetCar_id() {
    this.car_id = null;
  }

  /** Returns true if field car_id is set (has been assigned a value) and false otherwise */
  public boolean isSetCar_id() {
    return this.car_id != null;
  }

  public void setCar_idIsSet(boolean value) {
    if (!value) {
      this.car_id = null;
    }
  }

  public Coordinate getMatch_coord() {
    return this.match_coord;
  }

  public GpsEdge setMatch_coord(Coordinate match_coord) {
    this.match_coord = match_coord;
    return this;
  }

  public void unsetMatch_coord() {
    this.match_coord = null;
  }

  /** Returns true if field match_coord is set (has been assigned a value) and false otherwise */
  public boolean isSetMatch_coord() {
    return this.match_coord != null;
  }

  public void setMatch_coordIsSet(boolean value) {
    if (!value) {
      this.match_coord = null;
    }
  }

  public Coordinate getSrc_coord() {
    return this.src_coord;
  }

  public GpsEdge setSrc_coord(Coordinate src_coord) {
    this.src_coord = src_coord;
    return this;
  }

  public void unsetSrc_coord() {
    this.src_coord = null;
  }

  /** Returns true if field src_coord is set (has been assigned a value) and false otherwise */
  public boolean isSetSrc_coord() {
    return this.src_coord != null;
  }

  public void setSrc_coordIsSet(boolean value) {
    if (!value) {
      this.src_coord = null;
    }
  }

  public double getSpeed() {
    return this.speed;
  }

  public GpsEdge setSpeed(double speed) {
    this.speed = speed;
    setSpeedIsSet(true);
    return this;
  }

  public void unsetSpeed() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __SPEED_ISSET_ID);
  }

  /** Returns true if field speed is set (has been assigned a value) and false otherwise */
  public boolean isSetSpeed() {
    return EncodingUtils.testBit(__isset_bitfield, __SPEED_ISSET_ID);
  }

  public void setSpeedIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __SPEED_ISSET_ID, value);
  }

  public RoadId getRoad_id() {
    return this.road_id;
  }

  public GpsEdge setRoad_id(RoadId road_id) {
    this.road_id = road_id;
    return this;
  }

  public void unsetRoad_id() {
    this.road_id = null;
  }

  /** Returns true if field road_id is set (has been assigned a value) and false otherwise */
  public boolean isSetRoad_id() {
    return this.road_id != null;
  }

  public void setRoad_idIsSet(boolean value) {
    if (!value) {
      this.road_id = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case CAR_ID:
      if (value == null) {
        unsetCar_id();
      } else {
        setCar_id((CarID)value);
      }
      break;

    case MATCH_COORD:
      if (value == null) {
        unsetMatch_coord();
      } else {
        setMatch_coord((Coordinate)value);
      }
      break;

    case SRC_COORD:
      if (value == null) {
        unsetSrc_coord();
      } else {
        setSrc_coord((Coordinate)value);
      }
      break;

    case SPEED:
      if (value == null) {
        unsetSpeed();
      } else {
        setSpeed((Double)value);
      }
      break;

    case ROAD_ID:
      if (value == null) {
        unsetRoad_id();
      } else {
        setRoad_id((RoadId)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case CAR_ID:
      return getCar_id();

    case MATCH_COORD:
      return getMatch_coord();

    case SRC_COORD:
      return getSrc_coord();

    case SPEED:
      return getSpeed();

    case ROAD_ID:
      return getRoad_id();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case CAR_ID:
      return isSetCar_id();
    case MATCH_COORD:
      return isSetMatch_coord();
    case SRC_COORD:
      return isSetSrc_coord();
    case SPEED:
      return isSetSpeed();
    case ROAD_ID:
      return isSetRoad_id();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof GpsEdge)
      return this.equals((GpsEdge)that);
    return false;
  }

  public boolean equals(GpsEdge that) {
    if (that == null)
      return false;

    boolean this_present_car_id = true && this.isSetCar_id();
    boolean that_present_car_id = true && that.isSetCar_id();
    if (this_present_car_id || that_present_car_id) {
      if (!(this_present_car_id && that_present_car_id))
        return false;
      if (!this.car_id.equals(that.car_id))
        return false;
    }

    boolean this_present_match_coord = true && this.isSetMatch_coord();
    boolean that_present_match_coord = true && that.isSetMatch_coord();
    if (this_present_match_coord || that_present_match_coord) {
      if (!(this_present_match_coord && that_present_match_coord))
        return false;
      if (!this.match_coord.equals(that.match_coord))
        return false;
    }

    boolean this_present_src_coord = true && this.isSetSrc_coord();
    boolean that_present_src_coord = true && that.isSetSrc_coord();
    if (this_present_src_coord || that_present_src_coord) {
      if (!(this_present_src_coord && that_present_src_coord))
        return false;
      if (!this.src_coord.equals(that.src_coord))
        return false;
    }

    boolean this_present_speed = true;
    boolean that_present_speed = true;
    if (this_present_speed || that_present_speed) {
      if (!(this_present_speed && that_present_speed))
        return false;
      if (this.speed != that.speed)
        return false;
    }

    boolean this_present_road_id = true && this.isSetRoad_id();
    boolean that_present_road_id = true && that.isSetRoad_id();
    if (this_present_road_id || that_present_road_id) {
      if (!(this_present_road_id && that_present_road_id))
        return false;
      if (!this.road_id.equals(that.road_id))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_car_id = true && (isSetCar_id());
    list.add(present_car_id);
    if (present_car_id)
      list.add(car_id);

    boolean present_match_coord = true && (isSetMatch_coord());
    list.add(present_match_coord);
    if (present_match_coord)
      list.add(match_coord);

    boolean present_src_coord = true && (isSetSrc_coord());
    list.add(present_src_coord);
    if (present_src_coord)
      list.add(src_coord);

    boolean present_speed = true;
    list.add(present_speed);
    if (present_speed)
      list.add(speed);

    boolean present_road_id = true && (isSetRoad_id());
    list.add(present_road_id);
    if (present_road_id)
      list.add(road_id);

    return list.hashCode();
  }

  @Override
  public int compareTo(GpsEdge other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetCar_id()).compareTo(other.isSetCar_id());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCar_id()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.car_id, other.car_id);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetMatch_coord()).compareTo(other.isSetMatch_coord());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetMatch_coord()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.match_coord, other.match_coord);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetSrc_coord()).compareTo(other.isSetSrc_coord());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSrc_coord()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.src_coord, other.src_coord);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetSpeed()).compareTo(other.isSetSpeed());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSpeed()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.speed, other.speed);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetRoad_id()).compareTo(other.isSetRoad_id());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRoad_id()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.road_id, other.road_id);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("GpsEdge(");
    boolean first = true;

    sb.append("car_id:");
    if (this.car_id == null) {
      sb.append("null");
    } else {
      sb.append(this.car_id);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("match_coord:");
    if (this.match_coord == null) {
      sb.append("null");
    } else {
      sb.append(this.match_coord);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("src_coord:");
    if (this.src_coord == null) {
      sb.append("null");
    } else {
      sb.append(this.src_coord);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("speed:");
    sb.append(this.speed);
    first = false;
    if (isSetRoad_id()) {
      if (!first) sb.append(", ");
      sb.append("road_id:");
      if (this.road_id == null) {
        sb.append("null");
      } else {
        sb.append(this.road_id);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (car_id == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'car_id' was not present! Struct: " + toString());
    }
    if (match_coord == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'match_coord' was not present! Struct: " + toString());
    }
    if (src_coord == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'src_coord' was not present! Struct: " + toString());
    }
    // alas, we cannot check 'speed' because it's a primitive and you chose the non-beans generator.
    // check for sub-struct validity
    if (match_coord != null) {
      match_coord.validate();
    }
    if (src_coord != null) {
      src_coord.validate();
    }
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class GpsEdgeStandardSchemeFactory implements SchemeFactory {
    public GpsEdgeStandardScheme getScheme() {
      return new GpsEdgeStandardScheme();
    }
  }

  private static class GpsEdgeStandardScheme extends StandardScheme<GpsEdge> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, GpsEdge struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // CAR_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.car_id = new CarID();
              struct.car_id.read(iprot);
              struct.setCar_idIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // MATCH_COORD
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.match_coord = new Coordinate();
              struct.match_coord.read(iprot);
              struct.setMatch_coordIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // SRC_COORD
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.src_coord = new Coordinate();
              struct.src_coord.read(iprot);
              struct.setSrc_coordIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // SPEED
            if (schemeField.type == org.apache.thrift.protocol.TType.DOUBLE) {
              struct.speed = iprot.readDouble();
              struct.setSpeedIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // ROAD_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.road_id = new RoadId();
              struct.road_id.read(iprot);
              struct.setRoad_idIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      if (!struct.isSetSpeed()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'speed' was not found in serialized data! Struct: " + toString());
      }
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, GpsEdge struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.car_id != null) {
        oprot.writeFieldBegin(CAR_ID_FIELD_DESC);
        struct.car_id.write(oprot);
        oprot.writeFieldEnd();
      }
      if (struct.match_coord != null) {
        oprot.writeFieldBegin(MATCH_COORD_FIELD_DESC);
        struct.match_coord.write(oprot);
        oprot.writeFieldEnd();
      }
      if (struct.src_coord != null) {
        oprot.writeFieldBegin(SRC_COORD_FIELD_DESC);
        struct.src_coord.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(SPEED_FIELD_DESC);
      oprot.writeDouble(struct.speed);
      oprot.writeFieldEnd();
      if (struct.road_id != null) {
        if (struct.isSetRoad_id()) {
          oprot.writeFieldBegin(ROAD_ID_FIELD_DESC);
          struct.road_id.write(oprot);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class GpsEdgeTupleSchemeFactory implements SchemeFactory {
    public GpsEdgeTupleScheme getScheme() {
      return new GpsEdgeTupleScheme();
    }
  }

  private static class GpsEdgeTupleScheme extends TupleScheme<GpsEdge> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, GpsEdge struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      struct.car_id.write(oprot);
      struct.match_coord.write(oprot);
      struct.src_coord.write(oprot);
      oprot.writeDouble(struct.speed);
      BitSet optionals = new BitSet();
      if (struct.isSetRoad_id()) {
        optionals.set(0);
      }
      oprot.writeBitSet(optionals, 1);
      if (struct.isSetRoad_id()) {
        struct.road_id.write(oprot);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, GpsEdge struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.car_id = new CarID();
      struct.car_id.read(iprot);
      struct.setCar_idIsSet(true);
      struct.match_coord = new Coordinate();
      struct.match_coord.read(iprot);
      struct.setMatch_coordIsSet(true);
      struct.src_coord = new Coordinate();
      struct.src_coord.read(iprot);
      struct.setSrc_coordIsSet(true);
      struct.speed = iprot.readDouble();
      struct.setSpeedIsSet(true);
      BitSet incoming = iprot.readBitSet(1);
      if (incoming.get(0)) {
        struct.road_id = new RoadId();
        struct.road_id.read(iprot);
        struct.setRoad_idIsSet(true);
      }
    }
  }

}

