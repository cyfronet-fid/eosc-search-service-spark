package pl.cyfronet.spark.beans;

import java.io.Serializable;

public class DataInfo implements Serializable {
    private boolean deletedbyinference;
    private boolean inferred;
    private boolean invisible;
    private String inferenceprovenance;
    private String trust;
    private Provenanceaction provenanceaction;

    public boolean isDeletedbyinference() {
        return deletedbyinference;
    }

    public void setDeletedbyinference(boolean deletedbyinference) {
        this.deletedbyinference = deletedbyinference;
    }

    public boolean isInferred() {
        return inferred;
    }

    public void setInferred(boolean inferred) {
        this.inferred = inferred;
    }

    public boolean isInvisible() {
        return invisible;
    }

    public void setInvisible(boolean invisible) {
        this.invisible = invisible;
    }

    public String getInferenceprovenance() {
        return inferenceprovenance;
    }

    public void setInferenceprovenance(String inferenceprovenance) {
        this.inferenceprovenance = inferenceprovenance;
    }

    public String getTrust() {
        return trust;
    }

    public void setTrust(String trust) {
        this.trust = trust;
    }

    public Provenanceaction getProvenanceaction() {
        return provenanceaction;
    }

    public void setProvenanceaction(Provenanceaction provenanceaction) {
        this.provenanceaction = provenanceaction;
    }
}
