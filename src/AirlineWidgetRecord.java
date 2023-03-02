
import java.util.HashSet;

import java.util.HashSet;

public class AirlineWidgetRecord extends WidgetRecord<Integer> {

    private Integer id;

    private String ein;

    private HashSet<Integer> flightKeys;

    private HashSet<Integer> crewKeys;

    private HashSet<Integer> airportKeys;

    private HashSet<Integer> scheduledFlightKeys;





    //Add Constructor here
    public AirlineWidgetRecord(String ein, HashSet<Integer> flightKeys, HashSet<Integer> crewKeys,
                               HashSet<Integer> airportKeys, HashSet<Integer> scheduledFlightKeys){
        this.ein = ein;
        this.flightKeys = flightKeys;
        this.crewKeys = crewKeys;
        this.airportKeys = airportKeys;
        this.scheduledFlightKeys = scheduledFlightKeys;
    }




    public Integer getId(){
        return this.id;
    }

    public String getEin(){
        return this.ein;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public void setEin(String ein){
        this.ein = ein;
    }
    @Override
    protected HashSet<Integer> getForiegnKeys(TableOption table) {
        switch (table) {
            case kAirports:
                HashSet<Integer> retVal = new HashSet<>();
                retVal.add(airportKeys);
                return retVal;
            case kCrews;
                return crewKeys;
            case kFlight:
                return flightKeys;
            case kScheduledFlights:
                return scheduledFlightKeys;
            default:
                break;

        }

        return new HashSet<>();
    }

    @Override
    public void addRelation(TableOption table, int foriegnKey) {
        switch (table) {
            case kCrews:
                crewKeys.add(foriegnKey);
                break;
            case kFlight:
                flightKeys.add(foriegnKey);
                break;
            case kScheduledFlights:
                scheduledFlightKeys.add(foriegnKey);
                break;
            case kAirports:
                airportKeys.add(foriegnKey);
            default:
                break;
        }
        
    }
    

}
