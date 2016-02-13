package cn.siat.spiderx.batchlayer.datatransfer.batchtransfer;

import backtype.cascading.tap.PailTap;
import backtype.cascading.tap.PailTap.PailTapOptions;
import backtype.hadoop.pail.Pail;
import backtype.hadoop.pail.Pail.TypedRecordOutputStream;
import backtype.hadoop.pail.PailSpec;
import backtype.hadoop.pail.PailStructure;
import cascalog.ops.IdentityBuffer;
import cascalog.ops.RandLong;
import cn.siat.spiderx.batchlayer.datatransfer.schema.Data;
import cn.siat.spiderx.batchlayer.datatransfer.schema.DataUnit;
import cn.siat.spiderx.batchlayer.datatransfer.tap.DataPailStructure;
import cn.siat.spiderx.batchlayer.datatransfer.tap.SplitDataPailStructure;
import jcascalog.Api;
import jcascalog.Subquery;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static cn.siat.spiderx.batchlayer.datatransfer.utils.DataGen.*;


/**
 * The entire batch layer for SpiderX.
 */
public class BatchWorkflow {
    public static final String ROOT = "/tmp/spiderX/";
    public static final String DATA_ROOT = ROOT + "data/";
    public static final String MASTER_ROOT = DATA_ROOT + "master";
    public static final String NEW_ROOT = DATA_ROOT + "new";
    public static final String SEPARATOR = ",";


    public static void initTestData() throws Exception {
        FileSystem fs = FileSystem.get(new Configuration());
        fs.delete(new Path(DATA_ROOT), true);
        fs.mkdirs(new Path(DATA_ROOT));

        Pail masterPail = Pail.create(MASTER_ROOT, new SplitDataPailStructure());
        Pail<Data> newPail = Pail.create(NEW_ROOT, new DataPailStructure());

        TypedRecordOutputStream os = (TypedRecordOutputStream) newPail.openWrite();
        readOd("/Users/SevenYoung/Downloads/od/2015-11-19/od.in", os);
        readGps("/Users/SevenYoung/Downloads/gps/2015-11-19/gps.in", os);
        os.close();

    }

    public static void readGps(String path, TypedRecordOutputStream os) throws IOException {
        FileInputStream fis = new FileInputStream(path);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        String line = null;
        while((line = br.readLine()) != null){
            //This version the gps info format is
            //<car_no>,<str_match_time>,<roadId>,<speed>,<match_lng>,<match_lat>,<src_lng>,<src_lat>
            String[] gps = line.split(SEPARATOR);
            if (gps.length != 8) continue;
            os.writeObject(makeGps(gps[0], timeConvert(gps[1],"yyyy-MM-dd HH:mm:ss"), Long.parseLong(gps[2]), Double.parseDouble(gps[3]), Double.parseDouble(gps[4]),
                    Double.parseDouble(gps[5]), Double.parseDouble(gps[6]), Double.parseDouble(gps[7])));
        }
    }

    public static void readOd(String path, TypedRecordOutputStream os) throws IOException {
        FileInputStream fis = new FileInputStream(path);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        String line = null;
        while((line = br.readLine()) != null){
            //This version the od info format is
            //<car_no>,<pickupTime>,<closetGpsTime>,<diffT1>,<pickup_lng>,<pickup_lat>,<dropoffTime>,<closetGpsTime>,<diff2>,<dropoff_lng>,<dropoff_lat>,<distance_in_line>
            String[] od = line.split(SEPARATOR);
            long identity = randomLong();
            if(od.length != 12) continue;
            os.writeObject(
                    makePickUp(od[0],timeConvert(od[1],"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"),Double.parseDouble(od[4]),Double.parseDouble(od[5]),identity)
            );
            os.writeObject(
                    makeDropOff(od[0],timeConvert(od[6],"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"),Double.parseDouble(od[9]),Double.parseDouble(od[10]),identity,Double.parseDouble(od[11]))
            );
        }
    }

    public static long randomLong(){
        int counter = 0;
        Random rand = new Random();
        Random rand2 = new Random();

        if (++counter == 0) rand = new Random(); // reset every 4 billion values.

        long rdLong = rand.nextLong() ^ rand2.nextLong() << 1;
        return rdLong;
    }

    public static long timeConvert(String time, String parseFormat){
        DateFormat format = new SimpleDateFormat(parseFormat);
        try{
            Date date = format.parse(time);
            return date.getTime();
        }catch (ParseException e) {
            System.err.println("Your date format not consistent with parser");
            e.printStackTrace();
            return -1;
        }
    }



    //This is the ussge for writing multi properties.
//    public static void makePersonProperty(TypedRecordOutputStream os ,int userId, String name, int gender,
//                                          String city, String state,String country) throws IOException {
//        os.writeObject(makePersonPropertyName(userId, name));
//        os.writeObject(makePersonPropertyGender(userId, gender));
//        os.writeObject(makePersonPropertyLoc(userId, city, state, country));
//
//    }


    //The batch process need to know how to serialize and deserialize these objects(Thrift)
    //so they can be transferred between machines during MapReduce jobs.
    //This is used in the process procedure.
    public static void setApplicationConf() {
      Map conf = new HashMap();
      String sers = "backtype.hadoop.ThriftSerialization";
      sers += ",";
      sers += "org.apache.hadoop.io.serializer.WritableSerialization";
      conf.put("io.serializations", sers);
      Api.setApplicationConf(conf);
    }

    //For pails using the SplitDataPailStructure, you can construct a PailTap that reads only the equiv edges contained in the pail.
    //This is useful for that, the master/ folder will receive every kind of edges or property types. With this function we extract
    //the type we just need.

    //!!!!!This method just used to constraint the read !!!!!
    public static PailTap attributeTap(
            String path,
            final DataUnit._Fields... fields) {
        PailTapOptions opts = new PailTapOptions();//Relays the custom configuration to the pailTap
        //The attrs are an array of lists, each list contains the directory path of a subfolder to be used as input.
        //e.g:  Creates a list containing the relative path of the equiv edges
        //PailtTapOptions opts = new PailTapOptions();
//        opts.attrs = new List[] {
//                new ArrayList<String>(){{add("" + DataUnit._Fields.EQUIV.getThriftFieldId());}}
//        };


        opts.attrs = new List[] {
                        new ArrayList<String>() {{
                           for(DataUnit._Fields field: fields) {
                               add("" + field.getThriftFieldId());
                           }
                        }}
                        };

        //When sinking data from queries into brand-new pails,you must declare the type of
        //records you'll be writing to the PailTap, you do this by setting the spec option
        //to contain the appropriate PailStructure.
        opts.spec = new PailSpec(
                      (PailStructure) new SplitDataPailStructure());

        return new PailTap(path, opts);//Creates the tap with the specified options
    }


    //SplitDataTap and dataTap is used to sink

    public static PailTap splitDataTap(String path) {
        PailTapOptions opts = new PailTapOptions();
        opts.spec = new PailSpec(
                      (PailStructure) new SplitDataPailStructure());
        return new PailTap(path, opts);
    }

    public static PailTap dataTap(String path) {
        PailTapOptions opts = new PailTapOptions();
        opts.spec = new PailSpec(
                      (PailStructure) new DataPailStructure());
        return new PailTap(path, opts);
    }

    //opts has spec and attrs properties.
    //The spec used for the writer, the writer know the place to write accord to this property
    //The attrs used for the reader, the reader get the specified subset.


    public static void appendNewDataToMasterDataPail(Pail masterPail,
            Pail snapshotPail) throws IOException {
        Pail shreddedPail = shred();
        masterPail.absorb(shreddedPail);
    }

    public static void ingest(Pail masterPail, Pail newDataPail)
            throws IOException {
        FileSystem fs = FileSystem.get(new Configuration());
        fs.delete(new Path("/tmp/swa"), true);
        fs.mkdirs(new Path("/tmp/swa"));

        Pail snapshotPail = newDataPail.snapshot(
                              "/tmp/swa/newDataSnapshot");
        appendNewDataToMasterDataPail(masterPail, snapshotPail);
        newDataPail.deleteSnapshot(snapshotPail);
    }


    public static Pail shred() throws IOException {
        PailTap source = dataTap("/tmp/swa/newDataSnapshot");
        PailTap sink = splitDataTap("/tmp/swa/shredded");

        //Force the reduce to decrease the number of files.
        Subquery reduced = new Subquery("?rand", "?data")
            .predicate(source, "_", "?data-in")
            .predicate(new RandLong()).out("?rand")//Assigns a random number to each record
            .predicate(new IdentityBuffer(), "?data-in").out("?data");
        //The IdentityBuffer will do a reduce,the number of reducer can be contolled in the job conf.
        //Why we do a reduce? originally the result file number is typeNumber*mapperNumber, but the mapperNumber you
        //can't control , so we control the reducer number and then the result file number is typeNumber*reducerNumber

        Api.execute(
            sink,
            new Subquery("?data")
                .predicate(reduced, "_", "?data"));
        Pail shreddedPail = new Pail("/tmp/swa/shredded");
//        shreddedPail.consolidate();
        return shreddedPail;
    }

//    public static void normalizeURLs() {
//        //
////        Tap masterDataset = splitDataTap(DATA_ROOT + "master");
//        Tap masterDataset = attributeTap(DATA_ROOT + "master", DataUnit._Fields.PERSON_PROPERTY);
////        Tap outTap = splitDataTap("/tmp/swa/normalized_urls");
////        Tap outTap = new PailTap("/tmp/swa/debug");
//        Tap outTap = new StdoutTap();
//        Api.execute(outTap,
//                new Subquery("?normalized")
//                        .predicate(masterDataset, "_", "?raw")
////                .predicate(new NormalizeURL(), "?raw")
//                        .predicate(new Debug(), "?raw")
//                        .out("?normalized"));
//    }
////
//
//
//    public static class Debug extends CascalogFunction {
//        public void operate(FlowProcess process, FunctionCall call) {
//            System.out.println("DEBUG: " + call.getArguments().toString());
//            call.getOutputCollector().add(new Tuple(1));
//        }
//    }



    public static void masterDataIngest() throws Exception {
        setApplicationConf();
        initTestData();

        Pail masterPail = new Pail(MASTER_ROOT);
        Pail newDataPail = new Pail(NEW_ROOT);

        ingest(masterPail, newDataPail);
    }
}
