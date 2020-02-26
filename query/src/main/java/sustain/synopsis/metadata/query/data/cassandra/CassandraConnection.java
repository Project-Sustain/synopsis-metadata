package sustain.synopsis.metadata.query.data.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.MappingManager;

public class CassandraConnection {

    private Cluster cluster;
    private Session session;
    private MappingManager manager;

    public CassandraConnection(String host, String keyspace) {
        cluster = Cluster.builder()
                .addContactPoint(host)
                .build();
        session = cluster.connect(keyspace);
        manager = new MappingManager(session);
    }

    public void close() {
        cluster.close();
    }

    public Cluster getCluster() {
        return cluster;
    }

    public Session getSession() {
        return session;
    }

    public MappingManager getManager() {
        return manager;
    }

}
