package nasm.sys.services.impl;

import nasm.commons.PageControl;
import nasm.commons.Pager;
import nasm.model.SysFunction;
import nasm.sys.dao.impl.FunDao;
import nasm.sys.dao.interfaces.IFunDao;
import nasm.sys.services.interfaces.IFunService;

public class FunService implements IFunService {

	IFunDao dao = new FunDao();

	//添加
	public Integer addfun(SysFunction fun) {
		return dao.addfun(fun);
	}

	//查看
	public Pager<SysFunction> list(SysFunction fun, PageControl pc) {
		return dao.list(fun, pc);
	}

	//详情
	public SysFunction detail(SysFunction fun) {
		return dao.detail(fun);
	}

	//编辑
	public Integer edit(SysFunction fun) {
		return dao.edit(fun);
	}

}
