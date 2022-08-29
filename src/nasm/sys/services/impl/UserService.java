package nasm.sys.services.impl;

import java.util.List;

import nasm.commons.PageControl;
import nasm.commons.Pager;
import nasm.model.SysFunction;
import nasm.model.Sysuser;
import nasm.sys.dao.impl.UserDao;
import nasm.sys.dao.interfaces.IUserDao;
import nasm.sys.services.interfaces.IUserService;

public class UserService implements IUserService {

	IUserDao dao = new UserDao();
	
	public Sysuser login(Sysuser user) {
		return dao.login(user);
	}

	public List<SysFunction> initpage(Sysuser user) {
		return dao.initpage(user);
	}
	public Pager<Sysuser> list(Sysuser user, PageControl pc) {
		return dao.list(user, pc);
	}

	public Integer add(Sysuser user) {
		try{
			return dao.add(user);
		}catch(Exception e){
			throw new RuntimeException();
		}
		
	}
	public Sysuser detail(Sysuser user) {
		return dao.detail(user);
	}
	public Integer edit(Sysuser user) {
		return dao.edit(user);
	}

	public Integer editpwd(Sysuser user) {
		// TODO Auto-generated method stub
		return dao.editpwd(user);
	}
	public Sysuser stulogin(Sysuser user) {
		return dao.stulogin(user);
	}

	@Override
	public Pager<Sysuser> userlist(Sysuser user, PageControl pc) {
		return dao.userlist(user,pc);
	}

	@Override
	public Pager<Sysuser> usertasklist(Sysuser user, PageControl pc) {
		return dao.usertasklist(user,pc);
	}
}
