package cn.siat.spiderx.batchlayer.datatransfer.tap;

import backtype.cascading.tap.PailTap;
import backtype.hadoop.pail.PailSpec;
import backtype.hadoop.pail.PailStructure;
import cn.siat.spiderx.batchlayer.datatransfer.schema.DataUnit;
import org.apache.thrift.TFieldIdEnum;

import java.util.ArrayList;
import java.util.List;


public class SplitDataPailTap extends PailTap {
    public static class SplitDataPailTapOptions {
        public PailSpec spec = null;
        public String fieldName = "data";

        public SplitDataPailTapOptions() {

        }

        public SplitDataPailTapOptions(PailSpec spec, String fieldName) {
            this.spec = spec;
            this.fieldName = fieldName;
        }
    }

    public SplitDataPailTap(String root, SplitDataPailTapOptions options, DataUnit._Fields[] attrs) {
        super(root, new PailTapOptions(PailTap.makeSpec(options.spec, getSpecificStructure()), options.fieldName, toAttrs(attrs), null));
    }

    public SplitDataPailTap(String root, SplitDataPailTapOptions options, int[] attrs) {
        super(root, new PailTapOptions(PailTap.makeSpec(options.spec, getSpecificStructure()), options.fieldName, toAttrs(attrs), null));
    }

    public SplitDataPailTap(String root, SplitDataPailTapOptions options, List<TFieldIdEnum>[] attrs) {
        super(root, new PailTapOptions(PailTap.makeSpec(options.spec, getSpecificStructure()), options.fieldName, toAttrs(attrs), null));
    }

    public SplitDataPailTap(String root, SplitDataPailTapOptions options) {
        this(root, options, (DataUnit._Fields[]) null);
    }

    public SplitDataPailTap(String root) {
        this(root, new SplitDataPailTapOptions());
    }

    public SplitDataPailTap(String root, DataUnit._Fields[] attrs) {
        this(root, new SplitDataPailTapOptions(), attrs);
    }

    public SplitDataPailTap(String root, int[] attrs) {
        this(root, new SplitDataPailTapOptions(), attrs);
    }

    public SplitDataPailTap(String root, List<TFieldIdEnum>[] attrs) {
        this(root, new SplitDataPailTapOptions(), attrs);
    }

    protected static PailStructure getSpecificStructure() {
        return new SplitDataPailStructure();
    }

    protected static List<String>[] toAttrs(DataUnit._Fields[] spec) {
        if(spec==null) return null;
        List<String>[] ret = new List[spec.length];
        for(int i=0; i<spec.length; i++) {
            List<String> a = new ArrayList<String>();
            a.add("" + spec[i].getThriftFieldId());
            ret[i] = a;
        }
        return ret;
    }

    protected static List<String>[] toAttrs(int[] spec) {
        if(spec==null) return null;
        List<String>[] ret = new List[spec.length];
        for(int i=0; i<spec.length; i++) {
            List<String> a = new ArrayList<String>();
            a.add("" + spec[i]);
            ret[i] = a;
        }
        return ret;
    }

    protected static List<String>[] toAttrs(List<TFieldIdEnum>[] spec) {
        if(spec==null) return null;
        List<String>[] ret = new List[spec.length];
        for(int i=0; i<spec.length; i++) {
            List<String> a = new ArrayList<String>();
            List<TFieldIdEnum> conv = spec[i];
            for(TFieldIdEnum j: conv) {
                a.add("" + j.getThriftFieldId());
            }
            ret[i] = a;
        }
        return ret;
    }
}