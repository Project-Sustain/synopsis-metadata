package sustain.synopsis.metadata.rest.dataset;

import sustain.synopsis.metadata.DatasetInfoOuterClass;

import javax.validation.constraints.NotNull;

public class DatasetInfo {

    @NotNull
    private String id;
    @NotNull
    private Long temporalBracketLength;
    @NotNull
    private Integer geohashLength;
    @NotNull
    private String binConfig;

    public DatasetInfo() {
    }

    public DatasetInfo(String id, Long temporalBracketLength, Integer geohashLength, String binConfig) {
        this.id = id;
        this.temporalBracketLength = temporalBracketLength;
        this.geohashLength = geohashLength;
        this.binConfig = binConfig;
    }

    public DatasetInfo(DatasetInfoOuterClass.DatasetInfo dInfo) {
        this.id = dInfo.getDatasetId();
        this.temporalBracketLength = dInfo.getTemporalBracketLength();
        this.geohashLength = dInfo.getGeohashLength();
        this.binConfig = dInfo.getBinConfig();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getTemporalBracketLength() {
        return temporalBracketLength;
    }

    public void setTemporalBracketLength(Long temporalBracketLength) {
        this.temporalBracketLength = temporalBracketLength;
    }

    public Integer getGeohashLength() {
        return geohashLength;
    }

    public void setGeohashLength(Integer geohashLength) {
        this.geohashLength = geohashLength;
    }

    public String getBinConfig() {
        return binConfig;
    }

    public void setBinConfig(String binConfig) {
        this.binConfig = binConfig;
    }

}
