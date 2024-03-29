package WarehouseServer;

import WarehouseShared.Config;
import WarehouseShared.Position;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.util.ArrayList;
import Enums.LocationType;


/**
 * Keeps track of important locations.
 * To be used server-side to route robots towards these locations when necessary.
 *
 * @author Maxim
 * @author Thimoty
 * @since 7/1/2023
 */

public class LocationManager {
    public static ArrayList<Position> triagingStations = new ArrayList<>();
    public static ArrayList<Position> chargingStations = new ArrayList<>();
    public static ArrayList<Position> dropOffStations = new ArrayList<>();

    public static void createLocation(LocationType location) {
        try{
            ArrayList<Position> positions = new ArrayList<>();
            for (JsonElement element : Config.getConfig().get(location.toString()).getAsJsonArray()) {
                JsonArray jsonPosition = element.getAsJsonArray();
                float x = jsonPosition.get(0).getAsFloat();
                float y = jsonPosition.get(1).getAsFloat();
                positions.add(new Position(x,y));
            }

            switch(location) {
                case triagingStation:
                    triagingStations = positions;
                    break;
                case chargingStation:
                    chargingStations = positions;
                    break;
                case dropOffStation:
                    dropOffStations = positions;
                    break;
            }

        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

   public static void createLocations() {
        createLocation(LocationType.triagingStation);
        createLocation(LocationType.chargingStation);
        createLocation(LocationType.dropOffStation);
   }

   public static Position getNearestLocation(Position pos, LocationType location) {
        try {
            ArrayList<Position> locationArray = null;
            switch (location) {
                case triagingStation:
                    locationArray = triagingStations;
                    break;
                case chargingStation:
                    locationArray = chargingStations;
                    break;
                case dropOffStation:
                    locationArray = dropOffStations;
                    break;
            }

            Position nearestLocation = null;
            float nearestDistance = Float.MAX_VALUE;

            for (Position loc : locationArray) {
                float distance = (float) Math.sqrt(Math.pow(pos.x - loc.x, 2) + Math.pow(pos.y - loc.y, 2));
                if (distance < nearestDistance) {
                    nearestDistance = distance;
                    nearestLocation = loc;
                }
            }
            if (nearestLocation == null) {
                if (chargingStations.size() > 0) {
                    nearestLocation = chargingStations.get(0);
                }
            }
            return nearestLocation;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
   }

   public static Position getNearestRobotLocation(RobotObject robot, LocationType location) {
        return getNearestLocation(robot.position, location);
   }

}
