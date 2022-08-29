package nasm.sys.servlets;

import nasm.commons.PageControl;
import nasm.commons.Pager;
import nasm.commons.Tools;
import nasm.model.Course;
import nasm.sys.services.impl.CourseService;
import nasm.sys.services.interfaces.ICourseService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CourseServlet extends HttpServlet {
    ICourseService service = new CourseService();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String cmd = request.getParameter("cmd");
        if (cmd.equals("edit")){
            edit(request,response);
        }else if (cmd.equals("list")){
            list(request,response);
        }else if (cmd.equals("toedit")){
            toedit(request,response);
        }else if (cmd.equals("userlist")){
            userlist(request,response);
        }
    }

    private void edit(HttpServletRequest request,HttpServletResponse response){
        Course course = new Course();
        try{
            BeanUtils.populate(course, request.getParameterMap());
            Integer rtn = service.edit(course);
            if(rtn>0){
                response.sendRedirect(Tools.Basepath(request, response)+"sys/course?cmd=list");
            }else{
                request.setAttribute("msg", "编辑课程功能失败！");
                request.getRequestDispatcher("/sys/course/edit.jsp").forward(request, response);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 查询试题功能
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    private void list(HttpServletRequest request,HttpServletResponse response)
            throws ServletException,IOException {
        String ccontent = request.getParameter("ccontent");
        Course course = new Course();
        if (ccontent!=null && !ccontent.equals("")){
            course.setCcontent(ccontent);
        }

        PageControl pc = new PageControl();
        Integer currindex = 1;
        if (request.getParameter("index")!=null){
            currindex = Integer.parseInt(request.getParameter("index"));
        }
        pc.setCurrentindex(currindex);

        Pager<Course> pager = service.list(course,pc);
        request.setAttribute("pager",pager);
        //getRequestDispatcher转发
        request.getRequestDispatcher("/sys/course/list.jsp").forward(request,response);
    }
    /**
     * 初始化修改页面
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    private void toedit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Course course = new Course();
        course.setCid(Integer.parseInt(request.getParameter("id")));
        course = service.detail(course);
        if(course!=null){
            request.setAttribute("item",course);
            request.getRequestDispatcher("/sys/course/edit.jsp").forward(request, response);
        }else{
            request.setAttribute("msg", "需要修改的任务功能不存在。");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    /**
     * 查询试题功能
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    private void userlist(HttpServletRequest request,HttpServletResponse response)
            throws ServletException,IOException {
        String ccontent = request.getParameter("ccontent");
        Course course = new Course();
        if (ccontent!=null && !ccontent.equals("")){
            course.setCcontent(ccontent);
        }

        PageControl pc = new PageControl();
        Integer currindex = 1;
        if (request.getParameter("index")!=null){
            currindex = Integer.parseInt(request.getParameter("index"));
        }
        pc.setCurrentindex(currindex);

        Pager<Course> pager = service.list(course,pc);
        request.setAttribute("pager",pager);
        //getRequestDispatcher转发
        request.getRequestDispatcher("/user/course/list.jsp").forward(request,response);
    }
}
