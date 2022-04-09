package pl.cyfronet.spark.beans;

import java.io.Serializable;

public class Fulltext implements Serializable {
    private DataInfo dataInfo;
    private String value;

    public DataInfo getDataInfo() {
        return dataInfo;
    }

    public void setDataInfo(DataInfo dataInfo) {
        this.dataInfo = dataInfo;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
