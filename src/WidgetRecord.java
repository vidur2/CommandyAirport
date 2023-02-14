import java.util.HashSet;

public abstract class WidgetRecord<T> {
    protected T id;

    public T getId() {
        return id;
    }
    public void setId(T id) {
        this.id = id;
    }


    protected abstract HashSet<Integer> getForiegnKeys(TableOption table);
    public abstract void addRelation(TableOption table, int foriegnKey);
}