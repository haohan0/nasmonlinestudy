package nasm.sys.dao.impl;

import nasm.commons.DBUnitHelper;
import nasm.commons.PageControl;
import nasm.commons.Pager;
import nasm.model.Task;
import nasm.sys.dao.interfaces.ITaskDao;

import java.util.List;

public class TaskDao implements ITaskDao {

    public Integer addtask(Task task) {
        String sql="INSERT INTO task(taskname,taskcontent,taskstate)" +
                " VALUE(?,?,?)";
        Integer rtn = DBUnitHelper.executeUpdate(sql,task.getTaskname(),task.getTaskcontent(),task.getTaskstate());
        return rtn;
    }


    public Pager<Task> list(Task task, PageControl pc) {
        String sql = "SELECT TASKID,TASKNAME,TASKCONTENT,TASKSTATE FROM TASK WHERE TASKID>0";
        Pager<Task> pager;
        String Task_id= "TASKID";
        if (task.getTaskcontent()!=null && !task.getTaskcontent().equals("")){
            sql += "AND taskcontent LIKE '%'" + "?" + "'%'";
            pager = DBUnitHelper.execlist(sql,pc,Task.class,Task_id,task.getTaskcontent());
        }else {
            pager = DBUnitHelper.execlist(sql, pc, Task.class,Task_id, null);
        }
        return pager;
    }


    public Task detail(Task task) {
        String sql = "SELECT * FROM " +
                " task WHERE Taskid=? ";
        List<Task> list = DBUnitHelper.executeQuery(sql, Task.class, task.getTaskid());
        return list.get(0);
    }


    public Integer edit(Task task) {
        String sql = "UPDATE task SET taskname=?,taskcontent=?,taskstate=? WHERE taskid=?";
        Integer rtn = DBUnitHelper.executeUpdate(sql,task.getTaskname(),task.getTaskcontent(),task.getTaskstate(),task.getTaskid());
        return rtn;
    }
}
