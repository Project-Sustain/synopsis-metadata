package sustain.synopsis.metadata.query.repr;

import java.util.Arrays;
import java.util.List;

public class SelectClause {

    List<ColumnReference> columns;

    public SelectClause(ColumnReference... columns) {
        this.columns = Arrays.asList(columns);
    }

}
