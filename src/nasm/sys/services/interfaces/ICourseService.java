package nasm.sys.services.interfaces;

import nasm.commons.PageControl;
import nasm.commons.Pager;
import nasm.model.Course;

public interface ICourseService {
    /**
     * 编辑课程简介
     * @param course
     * @return
     */
    public Integer edit(Course course);

    /**
     * 课程简介列表
     * @param course
     * @return
     */
    public Course detail(Course course);

    /**
     * 查询全部试题
     * @param
     * @return
     */
    public Pager<Course> list(Course course, PageControl pc);
}
