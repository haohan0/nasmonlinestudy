package nasm.sys.dao.impl;

import nasm.commons.DBUnitHelper;
import nasm.commons.PageControl;
import nasm.commons.Pager;
import nasm.model.Grade;
import nasm.sys.dao.interfaces.IGradeDao;

import java.util.List;

public class GradeDao implements IGradeDao {
    @Override
    public Integer addgrade(Grade grade) {
        String sql = "INSERT INTO grade(username,gradeday,gradetask) VALUE(?,?,?)";
        Integer rtn = DBUnitHelper.executeUpdate(sql,grade.getUsername(),grade.getGradeday(),grade.getGradetask());
        return rtn;
    }

    @Override
    public Integer edit(Grade grade) {
        String sql = "UPDATE grade SET gradeday=?,gradetask=?"+
                " WHERE username=?";
        Integer rtn = DBUnitHelper.executeUpdate(sql,grade.getGradeday(),grade.getGradetask(),grade.getUsername());
        return rtn;
    }

    @Override
    public Pager<Grade> userlist(Grade grade, PageControl pc) {
        String sql = "SELECT gradeid,username,gradeday,gradetask FROM grade WHERE gradeid>0 AND USERNAME=?";
        Pager<Grade> pager;
        String Grade_id= "gradeid";
        pager = DBUnitHelper.execlist(sql, pc, Grade.class,Grade_id, grade.getUsername());
        return pager;
    }

    @Override
    public Grade detail(Grade grade) {
        String sql = "SELECT * FROM " +
                " Grade WHERE username=? ";
        List<Grade> list = DBUnitHelper.executeQuery(sql, Grade.class, grade.getUsername());
        return list.get(0);
    }


}
