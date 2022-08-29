package nasm.sys.services.impl;

import nasm.commons.PageControl;
import nasm.commons.Pager;
import nasm.model.Course;
import nasm.sys.dao.impl.CourseDao;
import nasm.sys.dao.interfaces.ICourseDao;
import nasm.sys.services.interfaces.ICourseService;

public class CourseService implements ICourseService {
    ICourseDao dao = new CourseDao();

    @Override
    public Integer edit(Course course) {
        return dao.edit(course);
    }

    @Override
    public Course detail(Course course) {
        return dao.detail(course);
    }

    @Override
    public Pager<Course> list(Course course, PageControl pc) {
        return dao.list(course,pc);
    }
}
