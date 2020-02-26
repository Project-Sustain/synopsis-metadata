package sustain.synopsis.metadata.rest.dataset;

import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.web.bind.annotation.*;
import sustain.synopsis.metadata.DatasetInfoOuterClass;
import sustain.synopsis.metadata.DatasetServiceGrpc;
import sustain.synopsis.metadata.DatasetServiceOuterClass.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DatasetController {

    private final String host = "localhost";
    private final int port = 44098;
    private Channel channel = ManagedChannelBuilder.forAddress(host, port)
            .usePlaintext()
            .build();
    private DatasetServiceGrpc.DatasetServiceBlockingStub stub = DatasetServiceGrpc.newBlockingStub(channel);

    @RequestMapping(path="/api/datasets", method=RequestMethod.POST)
    public void registerDataset(@RequestBody @Valid DatasetInfo info) {
        RegisterDatasetRequest req = RegisterDatasetRequest.newBuilder()
                .setDatasetInfo(DatasetInfoOuterClass.DatasetInfo.newBuilder()
                        .setDatasetId(info.getId())
                        .setTemporalBracketLength(info.getTemporalBracketLength())
                        .setGeohashLength(info.getGeohashLength())
                        .setBinConfig(info.getBinConfig())
                        .build())
                .build();

        RegisterDatasetResponse registerDatasetResponse = stub.registerDataset(req);
    }

    @RequestMapping(path="api/datasets/{id}", method=RequestMethod.GET)
    @ResponseBody
    public DatasetInfo getDataset(@PathVariable("id") String id) {
        GetDatasetInfoRequest req = GetDatasetInfoRequest.newBuilder()
                .setId(id)
                .build();

        GetDatasetInfoResponse dInfoResp = stub.getDatasetInfo(req);
        DatasetInfoOuterClass.DatasetInfo dInfo = dInfoResp.getDatasetInfo();

        return new DatasetInfo(dInfo);
    }

    @RequestMapping(path="api/datasets", method=RequestMethod.GET)
    @ResponseBody
    public List<DatasetInfo> getDatasets() {
        GetDatasetInfosRequest req = GetDatasetInfosRequest.newBuilder()
                .build();
        GetDatasetInfosResponse dInfosResp = stub.getDatasetInfos(req);
        List<DatasetInfo> myList = new ArrayList<>();
        for (DatasetInfoOuterClass.DatasetInfo d : dInfosResp.getInfoList()) {
            myList.add(new DatasetInfo(d));
        }
        return myList;
    }



}
