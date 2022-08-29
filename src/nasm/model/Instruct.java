package nasm.model;

public class Instruct {
    private Integer aid;
    private String aname;
    private String acontent;
    private String aexam;

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getAcontent() {
        return acontent;
    }

    public void setAcontent(String acontent) {
        this.acontent = acontent;
    }

    public String getAexam() {
        return aexam;
    }

    public void setAexam(String aexam) {
        this.aexam = aexam;
    }

    @Override
    public String toString() {
        return "Instruct{" +
                "aid=" + aid +
                ", aname='" + aname + '\'' +
                ", acontent='" + acontent + '\'' +
                ", aexam='" + aexam + '\'' +
                '}';
    }
}
