package nasm.sys.dao.impl;

import nasm.commons.DBUnitHelper;
import nasm.commons.PageControl;
import nasm.commons.Pager;
import nasm.model.Instruct;
import nasm.sys.dao.interfaces.IInstructDao;

import java.util.List;

public class InstructDao implements IInstructDao {

    public Pager<Instruct> list(Instruct instruct,PageControl pc) {
        String sql = "SELECT aid,aname,acontent,aexam FROM instruct WHERE aid>0";
        Pager<Instruct> pager;
        String AI_id= "aid";
        if (instruct.getAname()!=null && !instruct.getAname().equals("")){
            sql += "AND aname LIKE '%'" + "?" + "'%'";
            pager = DBUnitHelper.execlist(sql,pc,Instruct.class,AI_id,instruct.getAname());
        }else {
            pager = DBUnitHelper.execlist(sql, pc, Instruct.class,AI_id, null);
        }
        return pager;
    }


    public Instruct detail(Instruct instruct) {
        String sql = "SELECT * FROM instruct WHERE aid=?";
        List<Instruct> list = DBUnitHelper.executeQuery(sql,Instruct.class,instruct.getAid());
        return list.get(0);
    }


    public Integer edit(Instruct instruct) {
        String sql = "UPDATE instruct SET aname=?,acontent=?,aexam=? WHERE aid=?";
        Integer rtn = DBUnitHelper.executeUpdate(sql,instruct.getAname(),instruct.getAcontent(),instruct.getAexam(),instruct.getAid());
        return rtn;
    }


    public Integer addinstruct(Instruct instruct) {
        String sql = "INSERT INTO instruct(aname,acontent,aexam) VALUE(?,?,?)";
        Integer rtn = DBUnitHelper.executeUpdate(sql,instruct.getAname(),instruct.getAcontent(),instruct.getAexam());
        return rtn;
    }
}
