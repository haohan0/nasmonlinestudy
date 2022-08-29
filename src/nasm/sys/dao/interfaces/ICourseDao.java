package nasm.sys.dao.interfaces;

import nasm.commons.PageControl;
import nasm.commons.Pager;
import nasm.model.Course;

public interface ICourseDao {
    /**
     * 修改课程简介
     * @param course
     * @return
     */
    public Integer edit(Course course);

    /**
     * 查看课程简介信息
     * @param course
     * @return
     */
    public Course detail(Course course);

    /**
     * 列出课程简介信息
     * @param course
     * @param pc
     * @return
     */
    public Pager<Course> list(Course course, PageControl pc);
}
