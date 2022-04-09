package pl.cyfronet.spark.beans;

import java.util.List;

public class Author {
    private String fullname;
    private String surname;
    private String name;
    private int rank;
    private List<Pid> pid;
    private List<Affiliation> affiliation;

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public List<Pid> getPid() {
        return pid;
    }

    public void setPid(List<Pid> pid) {
        this.pid = pid;
    }

    public List<Affiliation> getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(List<Affiliation> affiliation) {
        this.affiliation = affiliation;
    }
}
