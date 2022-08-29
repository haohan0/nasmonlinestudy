package nasm.model;

public class Task {
    private Integer taskid;
    private String taskname;
    private String taskcontent;
    private Integer taskstate;

    public Integer getTaskid() {
        return taskid;
    }

    public void setTaskid(Integer taskid) {
        this.taskid = taskid;
    }

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    public String getTaskcontent() {
        return taskcontent;
    }

    public void setTaskcontent(String taskcontent) {
        this.taskcontent = taskcontent;
    }

    public Integer getTaskstate() {
        return taskstate;
    }

    public void setTaskstate(Integer taskstate) {
        this.taskstate = taskstate;
    }

    @Override
    public String toString() {
        return "task{" +
                "taskid=" + taskid +
                ", taskname='" + taskname + '\'' +
                ", taskcontent='" + taskcontent + '\'' +
                ", taskstate=" + taskstate +
                '}';
    }
}
