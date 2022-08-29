package nasm.sys.services.impl;

import nasm.commons.PageControl;
import nasm.commons.Pager;
import nasm.model.Task;
import nasm.sys.dao.impl.TaskDao;
import nasm.sys.dao.interfaces.ITaskDao;
import nasm.sys.services.interfaces.ITaskService;

public class TaskService implements ITaskService {
    ITaskDao dao = new TaskDao();

    @Override
    public Integer addtask(Task task) {
        try{
            return dao.addtask(task);
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    @Override
    public Pager<Task> list(Task task, PageControl pc) {
        return dao.list(task, pc);
    }

    @Override
    public Task detail(Task task) {
        return dao.detail(task);
    }

    @Override
    public Integer edit(Task task) {
        return dao.edit(task);
    }
}
