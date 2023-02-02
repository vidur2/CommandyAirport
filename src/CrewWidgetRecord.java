package CommandyAirport.src;

import java.util.HashSet;

public class CrewWidgetRecord extends WidgetRecord {
    private String ein;
    private HashSet<Integer> flightKeys;
    private HashSet<Integer> crewMemberKeys;
    private int airlineKeys;
    private HashSet<Integer> scheduledFlightKeys;

    public CrewWidgetRecord(String ein, HashSet<Integer> flightKeys, HashSet<Integer> crewMemberKeys, int airlineKeys,
            HashSet<Integer> scheduledFlightKeys) {
        this.ein = ein;
        this.flightKeys = flightKeys;
        this.crewMemberKeys = crewMemberKeys;
        this.airlineKeys = airlineKeys;
        this.scheduledFlightKeys = scheduledFlightKeys;
    }

    public String getEin() {
        return ein;
    }
    public void setEin(String ein) {
        this.ein = ein;
    }

    @Override
    protected HashSet<Integer> getForiegnKeys(TableOption table) {
        switch (table) {
            case kAirline:
                HashSet<Integer> retVal = new HashSet<>();
                retVal.add(airlineKeys);
                return retVal;
            case kCrewMembers:
                return crewMemberKeys;
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
            case kCrewMembers:
                crewMemberKeys.add(foriegnKey);
                break;
            case kFlight:
                flightKeys.add(foriegnKey);
                break;
            case kScheduledFlights:
                scheduledFlightKeys.add(foriegnKey);
                break;
            default:
                break;
        }
        
    }
}
