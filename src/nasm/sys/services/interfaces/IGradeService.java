package nasm.sys.services.interfaces;

import nasm.commons.PageControl;
import nasm.commons.Pager;
import nasm.model.Grade;

public interface IGradeService {
    /**
     * 添加一个成绩
     * @param grade
     * @return
     */
    public Integer addgrade(Grade grade);

    /**
     * 编辑指定学生对应指定题目的成绩
     * @param grade
     * @return
     */
    public Integer edit(Grade grade);

    /**
     * 根据学生查看成绩
     */
    public Pager<Grade> userlist(Grade grade, PageControl pc);

    /**
     * 成绩列表
     * @param grade
     * @return
     */
    public Grade detail(Grade grade);



}
