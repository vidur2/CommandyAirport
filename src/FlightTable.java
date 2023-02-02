import java.sql.ResultSet;
import java.util.HashMap;

public class FlightTable extends Table {


    public FlightTable(HashMap<Integer, WidgetRecord> results, ResultSet rs) {
        super(results, rs);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void mapDatabase() {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected String getDbConfigPath() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public WidgetRecord getItem(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String addRecord(WidgetRecord w) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String loadResults() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String editRecord(WidgetRecord w) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String deleteRecord(WidgetRecord w) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int fetchLastId() {
        // TODO Auto-generated method stub
        return 0;
    }
    

}
