package nasm.sys.services.impl;

import nasm.commons.PageControl;
import nasm.commons.Pager;
import nasm.model.Workfile;
import nasm.sys.dao.impl.WorkfileDao;
import nasm.sys.dao.interfaces.IWorkfileDao;
import nasm.sys.services.interfaces.IWorlfileService;

import java.util.List;

public class WorkfileService implements IWorlfileService {
    IWorkfileDao dao = new WorkfileDao();

    @Override
    public Integer upfile(Workfile workfile) {
        try {
            return dao.upfile(workfile);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public Pager<Workfile> list(Workfile workfile, PageControl pc) {
        return dao.list(workfile,pc);
    }

    @Override
    public List<Workfile> findfile(Workfile workfile) {
        return dao.findfile(workfile);
    }

    @Override
    public Pager<Workfile> taskworklist(Workfile workfile, PageControl pc) {
        return dao.taskworklist(workfile,pc);
    }
}
