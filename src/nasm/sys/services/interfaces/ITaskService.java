package nasm.sys.services.interfaces;

import nasm.commons.PageControl;
import nasm.commons.Pager;
import nasm.model.Task;

public interface ITaskService {
    /**
     *添加一道题目
     * @param
     * @return
     */
    public Integer addtask(Task task);

    /**
     * 查询全部试题
     * @param
     * @return
     */
    public Pager<Task> list(Task task, PageControl pc);

    /**
     *查看单个试题
     * @param
     * @return
     */
    public Task detail(Task task);

    /**
     * 修改试题
     * @param
     * @return
     */
    public Integer edit(Task task);
}
