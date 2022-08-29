package nasm.sys.dao.impl;

import nasm.commons.DBUnitHelper;
import nasm.commons.PageControl;
import nasm.commons.Pager;
import nasm.model.Workfile;
import nasm.sys.dao.interfaces.IWorkfileDao;

import java.util.List;

public class WorkfileDao implements IWorkfileDao {

    public Integer upfile(Workfile workfile) {
        String sql = "INSERT INTO workfile(filename,filepath,date,username,taskid) VALUE(?,?,?,?,?)";
        Integer rtn = DBUnitHelper.executeUpdate(sql,workfile.getFilename(),workfile.getFilepath(),workfile.getDate(),workfile.getUsername(),workfile.getTaskid());
        return rtn;
    }


    public Pager<Workfile> list(Workfile workfile, PageControl pc) {
        String sql = "SELECT * FROM workfile WHERE fileid>0 AND username=?";
        Pager<Workfile> pager;
        String fileid = "fileid";
        pager = DBUnitHelper.execlist(sql,pc,Workfile.class,fileid,workfile.getUsername());
        return pager;
    }


    public List<Workfile> findfile(Workfile workfile) {
        String sql = "SELECT * FROM workfile WHERE filename LIKE '%'" + "?" + "'%'";
        List<Workfile> list;
        list = DBUnitHelper.executeQuery(sql,Workfile.class,workfile.getFilename());
        return list;
    }

    public Pager<Workfile> taskworklist(Workfile workfile, PageControl pc) {
        String sql = "SELECT filename,filepath,taskid FROM workfile WHERE fileid>0 AND username=? ";
        String fileid="fileid";
        Pager<Workfile> pager = null;
        pager = DBUnitHelper.execlist(sql,pc,Workfile.class,fileid,workfile.getUsername());
        return pager;
    }
}
