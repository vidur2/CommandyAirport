import java.sql.ResultSet;
import java.util.HashMap;

import java.sql.ResultSet;
import java.util.HashMap;

public class AirlineTable extends Table<String> {

    
    public AirlineTable(HashMap<String, WidgetRecord<String>> results, ResultSet rs) {
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
    public WidgetRecord<String> getItem(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String addRecord(WidgetRecord<String> w) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String loadResults() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String editRecord(WidgetRecord<String> w) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String deleteRecord(WidgetRecord<String> w) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int fetchLastId() {
        // TODO Auto-generated method stub
        return 0;
    }
    
}
