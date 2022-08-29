package nasm.sys.dao.interfaces;

import nasm.commons.PageControl;
import nasm.commons.Pager;
import nasm.model.Grade;

public interface IGradeDao {

    /**
     * 添加成绩
     * @param grade
     * @return
     */
    public Integer addgrade(Grade grade);

    /**
     * 修改成绩
     * @param grade
     * @return
     */
    public Integer edit(Grade grade);

    /**
     * 列出学生成绩信息
     * @param grade
     * @param pc
     * @return
     */
    public Pager<Grade> userlist(Grade grade, PageControl pc);

    /**
     * 单个成绩信息
     * @param grade
     * @return
     */
    public Grade detail(Grade grade);
}
