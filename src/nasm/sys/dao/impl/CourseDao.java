package nasm.sys.dao.impl;

import nasm.commons.DBUnitHelper;
import nasm.commons.PageControl;
import nasm.commons.Pager;
import nasm.model.Course;
import nasm.sys.dao.interfaces.ICourseDao;

import java.util.List;

public class CourseDao implements ICourseDao {
    @Override
    public Integer edit(Course course) {
        // TODO Auto-generated method stub
        String sql = "UPDATE course SET cname=?,ccontent=?" +
                "WHERE cid=?";
        Integer rtn = DBUnitHelper.executeUpdate(sql,course.getCname(),course.getCcontent(),course.getCid());
        return rtn;
    }

    @Override
    public Course detail(Course course) {
        // TODO Auto-generated method stub
        String sql = "SELECT * FROM " +
                " course WHERE cid=? ";
        List<Course> list = DBUnitHelper.executeQuery(sql, Course.class, course.getCid());
        return list.get(0);
    }

    public Pager<Course> list(Course course, PageControl pc) {
        String sql = "SELECT * FROM course WHERE CID>0";
        Pager<Course> pager;
        String CID= "CID";
        if (course.getCcontent()!=null && !course.getCcontent().equals("")){
            sql += "AND ccontent LIKE '%'" + "?" + "'%'";
            pager = DBUnitHelper.execlist(sql,pc,Course.class,CID,course.getCcontent());
        }else {
            pager = DBUnitHelper.execlist(sql, pc, Course.class,CID, null);
        }
        return pager;
    }
}
