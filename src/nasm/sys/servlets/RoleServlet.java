package nasm.sys.servlets;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nasm.commons.PageControl;
import nasm.commons.Pager;
import nasm.commons.Tools;
import nasm.model.SysFunction;
import nasm.model.Sysrole;
import nasm.sys.services.interfaces.IRoleService;
import org.apache.commons.beanutils.BeanUtils;

import nasm.sys.services.impl.RoleService;

public class RoleServlet extends HttpServlet {

	IRoleService service = new RoleService();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		if(cmd.equals("list")){
			list(request,response);
		}else if(cmd.equals("add")){
			add(request,response);
		}else if(cmd.equals("initrole")){
			initrole(request,response);
		}else if(cmd.equals("saveright")){
			saveright(request,response);
		}else if(cmd.equals("toedit")){
			toedit(request,response);
		}else if(cmd.equals("edit")){
			edit(request,response);
		}
	}

	/**
	 * 保存角色权限
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void saveright(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		String[] funids = request.getParameterValues("ckrr");
		String roleid = request.getParameter("roleid");
		Integer rtn = service.saveright(roleid, funids);
		if(rtn>0){
			response.sendRedirect(Tools.Basepath(request, response)+"sys/role?cmd=list");
		}else{
			request.setAttribute("msg", "保存角色权限失败");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}

	/**
	 * 初始化权限分配页面
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void initrole(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Sysrole role = new Sysrole();
		role.setRoleid(Integer.parseInt(request.getParameter("roleid")));
		List<SysFunction> list = service.initfunlist(role);
		request.setAttribute("list",list);

		role = service.detail(role);
		request.setAttribute("role", role);
		request.getRequestDispatcher("/sys/role/right.jsp").forward(request, response);
	}

	/**
	 * 新增角色
	 * @param request
	 * @param response
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) {

		Sysrole role = new Sysrole();
		try {
			BeanUtils.populate(role, request.getParameterMap());
			Integer rtn = service.add(role);
			if(rtn>0){
				response.sendRedirect(Tools.Basepath(request, response)+"sys/role?cmd=list");
			}else{
				request.setAttribute("msg", "保存角色失败");
				request.getRequestDispatcher("/sys/role/add.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取角色列表
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String sname = request.getParameter("sname");
		Sysrole role = new Sysrole();
		if(sname!=null && !sname.equals("")){
			role.setRolename(sname);
		}

		PageControl pc = new PageControl();
		Integer currindex = 1;
		if(request.getParameter("index")!=null){
			currindex = Integer.parseInt(request.getParameter("index"));
		}
		pc.setCurrentindex(currindex);
		//pc.setPagesize(5);

		Pager<Sysrole> pager = service.list(role, pc);
		request.setAttribute("pager", pager);
		request.getRequestDispatcher("/sys/role/list.jsp").forward(request, response);
	}
	/**
	 * 修改角色功能
	 * @param request
	 * @param response
	 */
	private void edit(HttpServletRequest request, HttpServletResponse response) {

		Sysrole role = new Sysrole();

		try {
			BeanUtils.populate(role, request.getParameterMap());
			Integer rtn = service.edit(role);
			if(rtn>0){
				response.sendRedirect(Tools.Basepath(request, response)+"sys/role?cmd=list");
			}else{
				request.setAttribute("msg", "保存角色失败！");
				request.getRequestDispatcher("/sys/role/edit.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 角色初始化修改页面
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void toedit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Sysrole role = new Sysrole();
		role.setRoleid(Integer.parseInt(request.getParameter("id")));
		role = service.detail(role);
		if(role!=null){
			request.setAttribute("item",role);
			request.getRequestDispatcher("/sys/role/edit.jsp").forward(request, response);
		}else{
			request.setAttribute("msg", "需要修改的角色功能不存在。");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}
}
