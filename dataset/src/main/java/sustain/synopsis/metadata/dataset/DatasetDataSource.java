package sustain.synopsis.metadata.dataset;

import sustain.synopsis.metadata.DatasetInfoOuterClass;
import sustain.synopsis.metadata.DatasetInfoOuterClass.DatasetInfo;

import java.util.List;

public interface DatasetDataSource {

    public void insertDataset(DatasetInfo datasetInfo) throws Exception;
    public DatasetInfo getDatasetInfo(String id) throws Exception;
    public List<DatasetInfo> getDatasetInfos() throws Exception;

}
