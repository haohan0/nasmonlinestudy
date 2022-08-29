package nasm.sys.services.impl;

import nasm.commons.PageControl;
import nasm.commons.Pager;
import nasm.model.Grade;
import nasm.sys.dao.impl.GradeDao;
import nasm.sys.dao.interfaces.IGradeDao;
import nasm.sys.services.interfaces.IGradeService;

public class GradeService implements IGradeService {
    IGradeDao dao = new GradeDao();

    @Override
    public Integer addgrade(Grade grade) {
        return dao.addgrade(grade);
    }

    @Override
    public Integer edit(Grade grade) {
        return dao.edit(grade);
    }

    @Override
    public Pager<Grade> userlist(Grade grade, PageControl pc) {
        return dao.userlist(grade,pc);
    }

    @Override
    public Grade detail(Grade grade) {
        return dao.detail(grade);
    }

}
