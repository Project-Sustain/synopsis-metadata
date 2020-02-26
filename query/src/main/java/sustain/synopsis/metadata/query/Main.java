package sustain.synopsis.metadata.query;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import sustain.synopsis.metadata.config.ClusterConfig;
import sustain.synopsis.metadata.query.repr.*;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, QueryException {
        String configPath = args[0];
        YAMLMapper mapper = new YAMLMapper(new YAMLFactory());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        ClusterConfig config = mapper.readValue(new File(configPath), ClusterConfig.class);
        Cluster cluster = new Cluster(config);


        Query q = new Query()
                .setSelectClause(new SelectClause(new ColumnReference(ColumnReference.Type.MAX, "temperature_surface")))
                .setFromClause(new FromClause("myDataset"));




    }

}
