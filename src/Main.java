import java.sql.ResultSet;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        CrewTable table = new CrewTable(new HashMap<>());

        table.connectDb();
        table.addRecord(new CrewWidgetRecord("hello", null, null, 0, null));
        table.addRecord(new CrewWidgetRecord("there", null, null, 0, null));
        table.deleteRecord(new CrewWidgetRecord("there", null, null, 0, null));

        table.disconnectDb();
    }
}
