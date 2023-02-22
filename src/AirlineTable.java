import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import java.sql.ResultSet;
import java.util.HashMap;

public class AirlineTable extends Table<String> {

    public AirlineTable(HashMap<String, WidgetRecord<String>> results, ResultSet rs) {
        super(results);
        // TODO Auto-generated constructor stub
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
        // Creating SQL Query
        String query = "UPDATE airline SET ein=? WHERE id=?;";

        try {

            // Prepared statements parameterize and protect against SQL injection attacks
            PreparedStatement preparedStatement = this.conn.prepareStatement(query);

            // Putting in fields for SQL Query
            preparedStatement.setString(1, w.getEIN());
            preparedStatement.setInt(2, w.getID());

            // Execute SQL Query
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
        // loadResults(); -> for GUI
        return "Airline Record Updated";
    }

    @Override
    public String deleteRecord(WidgetRecord<String> w) {
        // Creating SQL Query
        String query = "DELETE FROM airline WHERE id=?;";

        try {

            // Putting in fields for SQL Query
            PreparedStatement preparedStatement = this.conn.prepareStatement(query);
            preparedStatement.setInt(1, w.getID());

            // Executing SQL Query
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
        // loadResults(); -> for GUI
        return "Airline Record Deleted.";
    }

    }

    @Override
    public int fetchLastId() {
        // TODO Auto-generated method stub
        return 0;
    }

}
