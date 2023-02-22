import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class CrewTable extends Table<Integer> {

    private int biggestInt = 0;

    public CrewTable(HashMap<Integer, WidgetRecord<Integer>> results) {
        super(results);
    }

    @Override
    public void mapDatabase() {
        try {
            Statement statement = this.conn.createStatement();

            statement.executeUpdate("DELETE FROM Crew;");

            this.conn.createStatement().executeQuery("SET FOREIGN_KEY_CHECKS=0;");

            PreparedStatement insert = this.conn.prepareStatement("INSERT INTO Crew (id, ein) VALUES (?, ?);");
            for (Map.Entry<Integer,WidgetRecord<Integer>> mapElement : this.results.entrySet()) {
                CrewWidgetRecord record = (CrewWidgetRecord) mapElement.getValue();
                insert.setInt(1, mapElement.getKey());
                insert.setString(2, record.getEin());
                insert.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String getDbConfigPath() {
        return "/Users/vidurmodgil/Desktop/DATA/School/12th Grade/AirportBuild/app.properties";
    }

    @Override
    public WidgetRecord<Integer> getItem(Integer id) {
        return this.results.get(id);
    }

    @Override
    public String addRecord(WidgetRecord<Integer> w) {
        this.biggestInt += 1;
        w.id = this.fetchLastId();
        this.results.put(this.fetchLastId(), w);
        return null;
    }

    @Override
    public String loadResults() {
        try {
            Statement stmt = this.conn.createStatement();
            this.rs = stmt.executeQuery("SELECT * FROM Crew;");

            while (this.rs.next()) {
                CrewWidgetRecord record = new CrewWidgetRecord(rs.getNString("ein"), null, null, 0, null);

                if (record.getId() > biggestInt) {
                    this.biggestInt = record.getId();
                }
                this.results.put(record.getId(), record);
            }
        } catch (SQLException e){
            e.printStackTrace();
            return e.toString();
        }
        return null;
    }

    @Override
    public String editRecord(WidgetRecord<Integer> w) {
        if (results.keySet().contains(w.getId())) {
            this.results.put(w.getId(), w);
        } else {
            return "Error: Record does not exist";
        }
        return null;
    }

    @Override
    public String deleteRecord(WidgetRecord<Integer> w) {
        int iUsed = 0;
        boolean found = false;

        for (Map.Entry<Integer,WidgetRecord<Integer>> mapElement : this.results.entrySet()) {
            CrewWidgetRecord record = (CrewWidgetRecord) mapElement.getValue();
            CrewWidgetRecord w2 = (CrewWidgetRecord) w;
            if (record.getEin() == w2.getEin()) {
                iUsed = mapElement.getKey();
                found = true;
                break;
            }
        }
        
        if (found) {
            this.results.remove(iUsed);
        }
        return null;
    }

    @Override
    public int fetchLastId() {
        return this.biggestInt;
    }
    
}
