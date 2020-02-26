package sustain.synopsis.metadata.query.data.cassandra;

import com.datastax.driver.mapping.annotations.*;
import java.nio.ByteBuffer;

@Table(keyspace = "synopsis_cassandra", name="strand")
public class CassandraStrand {

    @PartitionKey(0)
    @Column(name="geohash")
    private String geohash;

    @PartitionKey(1)
    @Column(name="session_id")
    private Long sessionId;

    @ClusteringColumn(0)
    @Column(name="from_ts")
    private Long fromTs;

    @ClusteringColumn(1)
    @Column(name="features_key")
    private ByteBuffer featuresKey;

    @ClusteringColumn(2)
    @Column(name="bins_key")
    private ByteBuffer binsKey;

    @FrozenValue
    @Column(name="summary")
    private CassandraSummary summary;

    public String getGeohash() {
        return geohash;
    }

    public void setGeohash(String geohash) {
        this.geohash = geohash;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public Long getFromTs() {
        return fromTs;
    }

    public void setFromTs(Long fromTs) {
        this.fromTs = fromTs;
    }

    public ByteBuffer getFeaturesKey() {
        return featuresKey;
    }

    public void setFeaturesKey(ByteBuffer featuresKey) {
        this.featuresKey = featuresKey;
    }

    public ByteBuffer getBinsKey() {
        return binsKey;
    }

    public void setBinsKey(ByteBuffer binsKey) {
        this.binsKey = binsKey;
    }

    public CassandraSummary getSummary() {
        return summary;
    }

    public void setSummary(CassandraSummary summary) {
        this.summary = summary;
    }

}