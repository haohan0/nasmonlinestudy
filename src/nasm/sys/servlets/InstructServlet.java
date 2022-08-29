package nasm.sys.servlets;

import nasm.commons.PageControl;
import nasm.commons.Pager;
import nasm.commons.Tools;
import nasm.model.Instruct;
import nasm.sys.services.impl.InstructService;
import nasm.sys.services.interfaces.IInstructService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class InstructServlet extends HttpServlet {

    IInstructService service = new InstructService();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        String cmd = request.getParameter("cmd");
        if(cmd.equals("list")){
            list(request,response);
        }else if(cmd.equals("add")){
            addinstruct(request,response);
        }else if(cmd.equals("edit")){
            edit(request,response);
        }else if (cmd.equals("toedit")){
            toedit(request,response);
        }else if (cmd.equals("userlist")){
            userlist(request,response);
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

        Instruct instruct = new Instruct();
        instruct.setAid(Integer.parseInt(request.getParameter("id")));
        instruct = service.detail(instruct);
        if(instruct!=null){
            request.setAttribute("item",instruct);
            request.getRequestDispatcher("/sys/instruct/edit.jsp").forward(request, response);
        }else{
            request.setAttribute("msg", "需要修改的指令功能不存在。");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    /**
     * 增加指令功能
     * @param request
     * @param response
     */
    private void addinstruct(HttpServletRequest request, HttpServletResponse response) {
        Instruct instruct = new Instruct();
        try{
            BeanUtils.populate(instruct,request.getParameterMap());
            Integer rtn = service.addinstruct(instruct);
            if (rtn>0){
                response.sendRedirect(Tools.Basepath(request,response)+"sys/instruct?cmd=add");
            }else {
                request.setAttribute("msg","增加指令失败！");
                request.getRequestDispatcher("/sys/instruct/add.jsp").forward(request,response);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     *
     * @param request
     * @param response
     */
    private void list(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,IOException{
        String aname=request.getParameter("aname");
        Instruct instruct = new Instruct();
        if (aname!=null&&!aname.equals("")){
            instruct.setAname(aname);
        }

        PageControl pc = new PageControl();
        Integer currindex = 1;
        if(request.getParameter("index")!=null){
            currindex = Integer.parseInt(request.getParameter("index"));
        }
        pc.setCurrentindex(currindex);
        //pc.setPagesize(5);

        Pager<Instruct> pager = service.list(instruct,pc);
        request.setAttribute("pager",pager);
        request.getRequestDispatcher("/sys/instruct/list.jsp").forward(request, response);
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) {

        Instruct instruct = new Instruct();

        try {
            BeanUtils.populate(instruct, request.getParameterMap());
            Integer rtn = service.edit(instruct);
            if(rtn>0){
                response.sendRedirect(Tools.Basepath(request, response)+"sys/instruct?cmd=list");
            }else{
                request.setAttribute("msg", "编辑试题功能失败！");
                request.getRequestDispatcher("/sys/instruct/edit.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param request
     * @param response
     */
    private void userlist(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,IOException{
        String aname=request.getParameter("aname");
        Instruct instruct = new Instruct();
        if (aname!=null&&!aname.equals("")){
            instruct.setAname(aname);
        }

        PageControl pc = new PageControl();
        Integer currindex = 1;
        if(request.getParameter("index")!=null){
            currindex = Integer.parseInt(request.getParameter("index"));
        }
        pc.setCurrentindex(currindex);
        //pc.setPagesize(5);

        Pager<Instruct> pager = service.list(instruct,pc);
        request.setAttribute("pager",pager);
        request.getRequestDispatcher("/user/instruct/list.jsp").forward(request, response);
    }
}
