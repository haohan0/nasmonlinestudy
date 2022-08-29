package nasm.model;

public class Workfile {
    private Integer fileid;
    private String filename;
    private String filepath;
    private String date;
    private String username;
    private Integer taskid;
    private Integer workstate;

    public Integer getFileid() {
        return fileid;
    }

    public void setFileid(Integer fileid) {
        this.fileid = fileid;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getTaskid() {
        return taskid;
    }

    public void setTaskid(Integer taskid) {
        this.taskid = taskid;
    }

    public Integer getWorkstate() {
        return workstate;
    }

    public void setWorkstate(Integer workstate) {
        this.workstate = workstate;
    }

    @Override
    public String toString() {
        return "Workfile{" +
                "fileid=" + fileid +
                ", filename='" + filename + '\'' +
                ", filepath='" + filepath + '\'' +
                ", date='" + date + '\'' +
                ", username='" + username + '\'' +
                ", taskid=" + taskid +
                ", workstate=" + workstate +
                '}';
    }
}
