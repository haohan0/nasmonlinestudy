package nasm.sys.servlets;

import nasm.commons.PageControl;
import nasm.commons.Pager;
import nasm.commons.Tools;
import nasm.model.Grade;
import nasm.model.Sysuser;
import nasm.sys.services.impl.GradeService;
import nasm.sys.services.impl.UserService;
import nasm.sys.services.interfaces.IGradeService;
import nasm.sys.services.interfaces.IUserService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GradeServlet extends HttpServlet {
    IGradeService service = new GradeService();
    IUserService userService = new UserService();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String cmd = request.getParameter("cmd");
        if (cmd.equals("add")){
            addgrade(request,response);
        }else if (cmd.equals("userlist")) {
            userlist(request, response);
        }else if (cmd.equals("edit")){
            edit(request,response);
        }else if (cmd.equals("toedit")){
            toedit(request,response);
        }else if (cmd.equals("stulist")){
            stulist(request,response);
        }

    }




    private void addgrade(HttpServletRequest request, HttpServletResponse response) {
        Grade grade = new Grade();
        try{
            BeanUtils.populate(grade,request.getParameterMap());
            Integer rtn = service.addgrade(grade);
            if (rtn>0){
                response.sendRedirect(Tools.Basepath(request,response)+"sys/student?cmd=gradelist&id="+grade.getUsername());
            }else{
                request.setAttribute("grade",grade);
                request.setAttribute("msg", "增加成绩功能失败或请不要添加重复成绩！");
                request.getRequestDispatcher("/sys/grade/add.jsp").forward(request, response);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void userlist(HttpServletRequest request, HttpServletResponse response)
        throws ServletException,IOException{

        String username = request.getParameter("username");
        Grade grade = new Grade();
        if (username!=null && !username.equals("")){
            grade.setUsername(username);
        }
        PageControl pc = new PageControl();
        Integer currindex = 1;
        if (request.getParameter("index")!=null){
            currindex = Integer.parseInt(request.getParameter("index"));
        }
        pc.setCurrentindex(currindex);
        //pc.setPagesize(5);
        Pager<Grade> pager = service.userlist(grade,pc);
        request.setAttribute("pager",pager);
        request.getRequestDispatcher("sys/grade/list.jsp").forward(request,response);
    }

    /**
     * 学生查看成绩
     * @param request
     * @param response
     */
    private void stulist(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,IOException{

        String username = request.getParameter("username");
        Grade grade = new Grade();
        Sysuser user = new Sysuser();

        if(username!=null && !username.equals("")){
            grade.setUsername(username);
            user.setUsername(username);
        }

        PageControl pc = new PageControl();
        Integer currindex = 1;
        if(request.getParameter("index")!=null){
            currindex = Integer.parseInt(request.getParameter("index"));
        }
        pc.setCurrentindex(currindex);
        //pc.setPagesize(5);

        Pager<Sysuser> pager = userService.userlist(user,pc);
        Pager<Grade> pager2 = service.userlist(grade,pc);
        request.setAttribute("user",user);
        request.setAttribute("pager",pager);
        request.setAttribute("pager2", pager2);

        request.getRequestDispatcher("/user/grade/list.jsp").forward(request, response);
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) {
        Grade grade = new Grade();

        try {
            BeanUtils.populate(grade,request.getParameterMap());
            Integer rtn = service.edit(grade);
            if (rtn>0){
                response.sendRedirect(Tools.Basepath(request,response)+"sys/student?cmd=gradelist&id="+grade.getUsername());
            }else{
                request.setAttribute("grade",grade);
                request.setAttribute("msg", "编辑成绩功能失败！");
                request.getRequestDispatcher("/sys/grade/edit.jsp").forward(request, response);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 初始化修改页面
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    private void toedit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Grade grade = new Grade();
        grade.setUsername(request.getParameter("username"));
        grade = service.detail(grade);
        if(grade!=null){
            request.setAttribute("item",grade);
            request.getRequestDispatcher("/sys/grade/edit.jsp?username="+grade.getUsername()).forward(request, response);
        }else{
            request.setAttribute("msg", "需要修改的成绩功能不存在。");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
