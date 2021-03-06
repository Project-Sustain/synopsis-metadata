package sustain.synopsis.metadata.query.data.cassandra;

import com.datastax.driver.mapping.annotations.Field;
import com.datastax.driver.mapping.annotations.UDT;
import java.util.List;

@UDT(keyspace="synopsis_cassandra", name="summary")
public class CassandraSummary {

    @Field(name="n")
    private long n;

    @Field(name="mean")
    private List<Double> mean;

    @Field(name="m2")
    private List<Double> m2;

    @Field(name="min")
    private List<Double> min;

    @Field(name="max")
    private List<Double> max;

    @Field(name="ss")
    private List<Double> ss;

    public long getN() {
        return n;
    }

    public void setN(long n) {
        this.n = n;
    }

    public List<Double> getMean() {
        return mean;
    }

    public void setMean(List<Double> mean) {
        this.mean = mean;
    }

    public List<Double> getM2() {
        return m2;
    }

    public void setM2(List<Double> m2) {
        this.m2 = m2;
    }

    public List<Double> getMin() {
        return min;
    }

    public void setMin(List<Double> min) {
        this.min = min;
    }

    public List<Double> getMax() {
        return max;
    }

    public void setMax(List<Double> max) {
        this.max = max;
    }

    public List<Double> getSs() {
        return ss;
    }

    public void setSs(List<Double> ss) {
        this.ss = ss;
    }

}
