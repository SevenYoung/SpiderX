namespace java cn.siat.spiderx.batchlayer.datatransfer.schema

//Time is the important field to keep external truth.
union CarID{
    1:string car_number;
}

union RoadId{
    1:string name;
    2:i64 road_id;
}

struct Coordinate{
    1:double longitute;
    2:double latitude;
}

struct GpsEdge{
    1:required CarID car_id;
    2:required Coordinate match_coord;
    3:required Coordinate src_coord;
    4:required double speed;
    5:optional RoadId road_id;
}

struct PickUpEdge{
    1:required CarID car_id;
    2:required Coordinate pickup_coord;
    3:required i64 identity;
    4:optional RoadId road_id;
}

struct DropOffEdge{
    1:required CarID car_id;
    2:required Coordinate dropoff_coord;
    3:required i64 identity;
    4:optional RoadId road_id;
    5:optional double distance;
}

struct Pedigree {
    1:required i64 timestamp;
}

union DataUnit{
    1: GpsEdge gps;
    2: PickUpEdge pickup;
    3: DropOffEdge dropoff;
}

struct Data{
    1:required Pedigree pedigree;
    2:required DataUnit dataunit;
}





