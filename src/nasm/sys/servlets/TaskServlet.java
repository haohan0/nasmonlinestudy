package nasm.sys.servlets;

import nasm.commons.PageControl;
import nasm.commons.Pager;
import nasm.commons.Tools;
import nasm.model.Task;
import nasm.model.Workfile;
import nasm.sys.services.impl.TaskService;
import nasm.sys.services.impl.WorkfileService;
import nasm.sys.services.interfaces.ITaskService;
import nasm.sys.services.interfaces.IWorlfileService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TaskServlet extends HttpServlet {

    ITaskService service = new TaskService();
    IWorlfileService worlfileService = new WorkfileService();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        String cmd = request.getParameter("cmd");
        if (cmd.equals("list")){
            list(request,response);
        }else if (cmd.equals("addtask")){
            addtask(request,response);
        }else if (cmd.equals("edit")){
            edit(request,response);
        }else if (cmd.equals("toedit")){
            toedit(request,response);
        }else if (cmd.equals("userlist")){
            userlist(request,response);
        }else if (cmd.equals("tasklist")){
            tasklist(request,response);
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
        String Task_content = request.getParameter("Task_content");
        Task task = new Task();
        if (Task_content!=null && !Task_content.equals("")){
            task.setTaskcontent(Task_content);
        }

        PageControl pc = new PageControl();
        Integer currindex = 1;
        if (request.getParameter("index")!=null){
            currindex = Integer.parseInt(request.getParameter("index"));
        }
        pc.setCurrentindex(currindex);

        Pager<Task> pager = service.list(task,pc);
        request.setAttribute("pager",pager);
        //getRequestDispatcher转发
        request.getRequestDispatcher("/sys/task/list.jsp").forward(request,response);
    }
    /**
     * 初始化修改页面
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    private void toedit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Task task = new Task();
        task.setTaskid(Integer.parseInt(request.getParameter("id")));
        task = service.detail(task);
        if(task!=null){
            request.setAttribute("item",task);
            request.getRequestDispatcher("/sys/task/edit.jsp").forward(request, response);
        }else{
            request.setAttribute("msg", "需要修改的任务功能不存在。");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    /**
     * 添加试题功能
     */
    private void addtask(HttpServletRequest request,HttpServletResponse response){
        Task task = new Task();
        try{
            BeanUtils.populate(task,request.getParameterMap());
            Integer rtn = service.addtask(task);
            if (rtn>0){
                response.sendRedirect(Tools.Basepath(request,response)+"sys/task?cmd=list");
            }else {
                request.setAttribute("msg","添加试题功能失败！");
                request.getRequestDispatcher("/sys/task/add.jsp").forward(request,response);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 修改试题功能
     */
    private void edit(HttpServletRequest request,HttpServletResponse response){
        Task task = new Task();

        try{
            BeanUtils.populate(task,request.getParameterMap());
            Integer rtn = service.edit(task);
            if (rtn>0){
                //sendRedirect重定向
                response.sendRedirect(Tools.Basepath(request,response)+"sys/task?cmd=list");
            }else {
                request.setAttribute("msg", "编辑试题功能失败！");
                request.getRequestDispatcher("/sys/task/edit.jsp").forward(request, response);
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
    private void userlist(HttpServletRequest request,HttpServletResponse response)
            throws ServletException,IOException {
        String username = request.getParameter("username");
        Task task = new Task();
        Workfile workfile = new Workfile();
        workfile.setUsername(username);


        PageControl pc = new PageControl();
        Integer currindex = 1;
        if (request.getParameter("index")!=null){
            currindex = Integer.parseInt(request.getParameter("index"));
        }
        pc.setCurrentindex(currindex);

        Pager<Workfile> pager2 = worlfileService.list(workfile,pc);
        Pager<Task> pager = service.list(task,pc);
        request.setAttribute("pager",pager);
        request.setAttribute("pager2",pager2);
        //getRequestDispatcher转发
        request.getRequestDispatcher("/user/task/list.jsp").forward(request,response);
    }

    private void tasklist(HttpServletRequest request,HttpServletResponse response)
        throws ServletException,IOException {
        Task task = new Task();
        task.setTaskid(Integer.parseInt(request.getParameter("id")));
        task = service.detail(task);
        request.setAttribute("item",task);
        request.getRequestDispatcher("/user/task/testcode.jsp").forward(request, response);
    }

}
