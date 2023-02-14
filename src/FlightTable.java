import java.sql.ResultSet;
import java.util.HashMap;

public class FlightTable extends Table<Integer> {


    public FlightTable(HashMap<Integer, WidgetRecord<Integer>> results, ResultSet rs) {
        super(results);
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
    public WidgetRecord<Integer> getItem(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String addRecord(WidgetRecord<Integer> w) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String loadResults() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String editRecord(WidgetRecord<Integer> w) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String deleteRecord(WidgetRecord<Integer> w) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int fetchLastId() {
        // TODO Auto-generated method stub
        return 0;
    }
    

}
