package nasm.model;

public class Grade {
    private Integer gradeid;
    private Integer gradeday;
    private Integer gradetask;
    private String username;


    public Integer getGradeid() {
        return gradeid;
    }

    public void setGradeid(Integer gradeid) {
        this.gradeid = gradeid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public Integer getGradeday() {
        return gradeday;
    }

    public void setGradeday(Integer gradeday) {
        this.gradeday = gradeday;
    }

    public Integer getGradetask() {
        return gradetask;
    }

    public void setGradetask(Integer gradetask) {
        this.gradetask = gradetask;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "gradeid=" + gradeid +
                ", gradeday=" + gradeday +
                ", gradetask=" + gradetask +
                ", username='" + username + '\'' +
                '}';
    }
}
