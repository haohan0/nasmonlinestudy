package nasm.sys.dao.interfaces;

import nasm.commons.PageControl;
import nasm.commons.Pager;
import nasm.model.Task;

public interface ITaskDao {
    /**
     * 添加任务
     */
    Integer addtask(Task task);

    /**
     * 修改任务
     */
    Integer edit(Task task);

    /**
     * 列出任务
     */
    Pager<Task> list(Task task, PageControl pc);

    /**
     * 单个对象信息
     */
    Task detail(Task task);


}
