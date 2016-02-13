package cn.siat.spiderx.batchlayer.datatransfer.utils;


import cn.siat.spiderx.batchlayer.datatransfer.schema.*;

public class DataGen {
    public static Pedigree makePedigree(long timeSecs) {
        return new Pedigree(timeSecs);

    }

    public static Data makeGps(String carNo, long millsecs, long roadId, double speed, double match_lng, double match_lat, double src_lng, double src_lat){
        return new Data(
                makePedigree(millsecs),
                DataUnit.gps(
                        new GpsEdge(
                                CarID.car_number(carNo),
                                new Coordinate(match_lng, match_lat),
                                new Coordinate(src_lng, src_lat),
                                speed
                        ).setRoad_id(RoadId.road_id(roadId))
                )
        );
    }

    public static Data makeDropOff(String carNo, long millsecs, double dropoff_lng, double dropoff_lat, long identify, double distance){
        return new Data(
                makePedigree(millsecs),
                DataUnit.dropoff(
                        new DropOffEdge(
                                CarID.car_number(carNo),
                                new Coordinate(dropoff_lng, dropoff_lat),
                                identify
                        ).setDistance(distance)
                )
        );
    }

    public static Data makePickUp(String carNo, long millsecs, double pickup_lng, double pickup_lat, long identify){
        return new Data(
                makePedigree(millsecs),
                DataUnit.pickup(
                        new PickUpEdge(
                                CarID.car_number(carNo),
                                new Coordinate(pickup_lng, pickup_lat),
                                identify
                        )
                )
        );
    }

}
