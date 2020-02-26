package sustain.synopsis.metadata.query.repr;

public class ColumnReference {

    public enum Type {
        MEAN,MIN,MAX
    }

    private Type type;
    private String name;

    public ColumnReference(Type type, String name) {
        this.type = type;
        this.name = name;
    }

}
