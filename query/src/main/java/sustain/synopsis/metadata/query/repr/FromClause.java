package sustain.synopsis.metadata.query.repr;

public class FromClause {

    private String datasetId;

    public FromClause(String datasetId) {
        this.datasetId = datasetId;
    }

    public String getDatasetId() {
        return datasetId;
    }
}
