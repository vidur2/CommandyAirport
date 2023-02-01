import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public abstract class WidgetRecord {
    protected int id;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }


    protected abstract HashSet<Integer> getForiegnKeys(TableOption table);
    public abstract void addRelation(TableOption table, int foriegnKey);
}
