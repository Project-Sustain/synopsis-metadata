package sustain.synopsis.metadata.ingest;

public interface IngestDataSource {

    public int insertSession(String datasetId, String clientId) throws Exception;

}
