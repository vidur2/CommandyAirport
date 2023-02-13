import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Properties;

public abstract class Table<T> {
    protected Connection conn;
    protected HashMap<T, WidgetRecord<T>> results;
    protected ResultSet rs;

    
    public Table(HashMap<T, WidgetRecord<T>> results) {
        this.results = results;
    }


    public boolean connectDb() {
		try {
			String loadPath = getDbConfigPath();
			FileInputStream inFile = new FileInputStream(loadPath);
			Properties props = new Properties();
			props.load(inFile);
			
			conn = DriverManager.getConnection(props.getProperty("dbUrl"), props.getProperty("dbUser"), null);
			//Internal test  code
			loadResults();
		
		}catch(Exception e) {
			System.out.println(e);
			return false;
		}
		return true;
	}
		
	public boolean disconnectDb() {
		try {
            mapDatabase();
			conn.close();
		}catch(Exception e){
			System.out.println(e);
			return false;
		}
		return true;
	}


    public ArrayList<WidgetRecord<T>> findById(HashSet<Integer> ids) {
        ArrayList<WidgetRecord<T>> retVal = new ArrayList<>();
        for (Integer id: ids) {
            retVal.add(results.get(id));
        }
        return retVal;
    }

    public Connection getConn() {
        return conn;
    }
    public void setConn(Connection conn) {
        this.conn = conn;
    }
    public HashMap<T, WidgetRecord<T>> getResults() {
        return results;
    }
    public void setResults(HashMap<T, WidgetRecord<T>> results) {
        this.results = results;
    }
    public ResultSet getRs() {
        return rs;
    }
    public void setRs(ResultSet rs) {
        this.rs = rs;
    }


    public abstract void mapDatabase();

    protected abstract String getDbConfigPath();

    public abstract WidgetRecord<T> getItem(T id);

    public abstract String addRecord(WidgetRecord<T> w);

    public abstract String loadResults(); 

    public abstract String editRecord(WidgetRecord<T> w);

    public abstract String deleteRecord(WidgetRecord<T> w);

    public abstract int fetchLastId();

}
