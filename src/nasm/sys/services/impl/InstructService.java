package nasm.sys.services.impl;

import nasm.commons.PageControl;
import nasm.commons.Pager;
import nasm.model.Instruct;
import nasm.sys.dao.impl.InstructDao;
import nasm.sys.dao.interfaces.IInstructDao;
import nasm.sys.services.interfaces.IInstructService;

public class InstructService implements IInstructService {
    IInstructDao dao = new InstructDao();

    @Override
    public Pager<Instruct> list(Instruct instruct, PageControl pc) {
        return dao.list(instruct,pc);
    }

    @Override
    public Instruct detail(Instruct instruct) {
        return dao.detail(instruct);
    }

    @Override
    public Integer edit(Instruct instruct) {
        return dao.edit(instruct);
    }

    @Override
    public Integer addinstruct(Instruct instruct) {
        try {
            return dao.addinstruct(instruct);
        }catch (Exception e){
            throw new RuntimeException();
        }

    }
}
