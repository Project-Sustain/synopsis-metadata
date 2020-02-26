package sustain.synopsis.metadata.query.data;

import sustain.synopsis.metadata.query.data.cassandra.CassandraStrand;

import java.util.List;

public interface DataConnection {

    public List<CassandraStrand> getStrandsForQuery(String query);

}
