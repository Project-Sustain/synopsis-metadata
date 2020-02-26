package sustain.synopsis.metadata.query.data.cassandra;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.Result;
import sustain.synopsis.metadata.query.data.DataConnection;

import java.util.List;

public class CassandraDataConnection implements DataConnection {

    private CassandraConnection cassandraConnection;
    private Mapper<CassandraStrand> cassandraStrandMapper;

    public CassandraDataConnection(CassandraConnection cassandraConnection) {
        this.cassandraConnection = cassandraConnection;
        this.cassandraStrandMapper = cassandraConnection.getManager().mapper(CassandraStrand.class);
    }

    @Override
    public List<CassandraStrand> getStrandsForQuery(String query) {
        ResultSet results =  cassandraConnection.getSession().execute(query);
        Result<CassandraStrand> strands = cassandraStrandMapper.map(results);
        return strands.all();
    }
    
}
