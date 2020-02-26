package sustain.synopsis.metadata.query.repr;

import sustain.synopsis.metadata.DatasetServiceOuterClass;
import sustain.synopsis.metadata.query.Cluster;
import sustain.synopsis.metadata.query.data.cassandra.CassandraStrand;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class Query {

    private SelectClause selectClause;
    private FromClause fromClause;
    private WhereClause whereClause;

    public Query setSelectClause(SelectClause selectClause) {
        this.selectClause = selectClause;
        return this;
    }

    public Query setFromClause(FromClause fromClause) {
        this.fromClause = fromClause;
        return this;
    }

    public Query setWhereClause(WhereClause whereClause) {
        this.whereClause = whereClause;
        return this;
    }

    public String execute(Cluster cluster) throws QueryException, ExecutionException, InterruptedException {
        if (selectClause == null) {
            throw new QueryException();
        }
        if (fromClause == null) {
            throw new QueryException();
        }

        DatasetServiceOuterClass.GetDatasetSessionsResponse datasetSessionsResponse = cluster.getDatasetStub().getDatasetSessions(DatasetServiceOuterClass.GetDatasetSessionsRequest.newBuilder()
                .setDatasetId(fromClause.getDatasetId())
                .build()).get();

        DatasetServiceOuterClass.Session session = datasetSessionsResponse.getSession(0);
        long sessionId = session.getSessionId();

        String sql = "SELECT * from strand where session_id="+sessionId;
        List<CassandraStrand> cassandraStrands = cluster.getDataConnection().getStrandsForQuery(sql).get();
        for (CassandraStrand strand : cassandraStrands)
        {
//            for (SelectClause s : selectClause) {
//
//            }
        }

        return "";

    }






}
