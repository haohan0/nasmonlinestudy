package nasm.sys.servlets;

import nasm.commons.PageControl;
import nasm.commons.Pager;
import nasm.commons.Tools;
import nasm.model.Sysuser;
import nasm.model.Task;
import nasm.model.Workfile;
import nasm.sys.services.impl.TaskService;
import nasm.sys.services.impl.WorkfileService;
import nasm.sys.services.interfaces.ITaskService;
import nasm.sys.services.interfaces.IWorlfileService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class WorkServlet extends HttpServlet {
    IWorlfileService service = new WorkfileService();
    ITaskService taskService = new TaskService();
    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String cmd= request.getParameter("cmd");
        if (cmd.equals("upfile")){
            upfile(request,response);
        }else if (cmd.equals("findfile")){
            findfile(request,response);
        }else if (cmd.equals("toadd")){
            toadd(request,response);
        }else if (cmd.equals("taskworklist")){
            taskworklist(request,response);
        }
    }

    private void taskworklist(HttpServletRequest request, HttpServletResponse response)
        throws ServletException,IOException{
        // TODO Auto-generated method stub
        String username = request.getParameter("id");
        Task task = new Task();
        Workfile workfile = new Workfile();
        workfile.setUsername(username);

        PageControl pc = new PageControl();
        Integer currindex = 1;
        if(request.getParameter("index")!=null){
            currindex = Integer.parseInt(request.getParameter("index"));
        }
        pc.setCurrentindex(currindex);
        //pc.setPagesize(5);

        Pager<Task> pager2 = taskService.list(task,pc);
        Pager<Workfile> pager = service.list(workfile,pc);
        request.setAttribute("pager", pager);
        request.setAttribute("pager2",pager2);
        request.getRequestDispatcher("/sys/student/tasklist.jsp").forward(request, response);
    }

    private void toadd(HttpServletRequest request, HttpServletResponse response)
          throws ServletException,IOException  {
        Workfile workfile = new Workfile();
        Integer taskid =Integer.parseInt(request.getParameter("id"));
        if(taskid>0){
            request.setAttribute("id",taskid);
            request.getRequestDispatcher("/user/workfile/addfile.jsp").forward(request, response);
        }else{
            request.setAttribute("msg", "需要上传文件的题目不存在。");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    private void upfile(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{

        //得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
        String savePath = this.getServletContext().getRealPath("/WEB-INF/upload");
        File file = new File(savePath);
        //判断上传文件的保存目录是否存在
        if (!file.exists() && !file.isDirectory()) {
            System.out.println(savePath+"目录不存在，需要创建");
            //创建目录
            file.mkdir();
        }
        //消息提示
        String message = "";
        try{
            //使用Apache文件上传组件处理文件上传步骤：
            //1、创建一个DiskFileItemFactory工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //2、创建一个文件上传解析器
            ServletFileUpload upload = new ServletFileUpload(factory);
            //解决上传文件名的中文乱码
            upload.setHeaderEncoding("UTF-8");
            //3、判断提交上来的数据是否是上传表单的数据
            if(!ServletFileUpload.isMultipartContent(request)){
                //按照传统方式获取数据
                return;
            }
            //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
            List<FileItem> list = upload.parseRequest(request);
            for(FileItem item : list){
                //如果fileitem中封装的是普通输入项的数据
                if(item.isFormField()){
                    String name = item.getFieldName();
                    //解决普通输入项的数据的中文乱码问题
                    String value = item.getString("UTF-8");
                    //value = new String(value.getBytes("iso8859-1"),"UTF-8");
                    System.out.println(name + "=" + value);
                }else{//如果fileitem中封装的是上传文件
                    //得到上传的文件名称，
                    String filename = item.getName();
                    System.out.println(filename);
                    if(filename==null || filename.trim().equals("")){
                        continue;
                    }
                    //注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如： c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
                    //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
                    filename = filename.substring(filename.lastIndexOf("\\")+1);
                    //获取item中的上传文件的输入流
                    InputStream in = item.getInputStream();
                    //创建一个文件输出流
                    FileOutputStream out = new FileOutputStream(savePath + "\\" + filename);
                    //创建一个缓冲区
                    byte buffer[] = new byte[1024];
                    //判断输入流中的数据是否已经读完的标识
                    int len = 0;
                    //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
                    while((len=in.read(buffer))>0){
                        //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
                        out.write(buffer, 0, len);
                    }
                    //关闭输入流
                    in.close();
                    //关闭输出流
                    out.close();
                    //删除处理文件上传时生成的临时文件
                    item.delete();
                    message = "文件上传成功！";
                }
            }
        }catch (Exception e) {
            message= "文件上传失败！";
            e.printStackTrace();
        }
        request.setAttribute("message",message);
        request.getRequestDispatcher("/workfile/message.jsp").forward(request, response);
    }

    /**
     * @Method: makeFileName
     * @Description: 生成上传文件的文件名，文件名以：uuid+"_"+文件的原始名称
     * @param filename 文件的原始名称
     * @return uuid+"_"+文件的原始名称
     */
    private String makeFileName(String filename){  //2.jpg
        //为防止文件覆盖的现象发生，要为上传文件产生一个唯一的文件名
        return UUID.randomUUID().toString()+"_"+filename;
    }

    /**
     * 为防止一个目录下面出现太多文件，要使用hash算法打散存储
     * @Method: makePath
     * @Description:
     *
     * @param filename 文件名，要根据文件名生成存储目录
     * @param savePath 文件存储路径
     * @return 新的存储目录
     */
    private String makePath(String filename,String savePath){
        //用日期得到文件名的
        Calendar date=Calendar.getInstance();
        SimpleDateFormat format1=new SimpleDateFormat( "yyyy-MM-dd");
        String name=format1.format(date.getTime());
        String dir = savePath + "\\" + name;  //upload\2\3  upload\3\5
        File file=new File(dir);
        //如果目录不存在
        if(!file.exists()){
            //创建目录
            file.mkdirs();
        }
        return dir;
    }


    private void findfile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fileName = request.getParameter("filename");
        //上传的文件都是保存在AttFilePath(D:/Code)目录下的子目录当中
        String savePath = request.getParameter("filepath");
        //通过文件名找出文件的所在目录
        //String path = findFileSavePathByFileName(fileName,savePath);
        //得到要下载的文件
        //File file = new File(savePath + "\\" + fileName);
        File file = new File(savePath);
        //如果文件不存在
        if(!file.exists()){
            request.setCharacterEncoding("utf-8");
            response.getWriter().println("<script type='text/javascript'>alert('您要下载的资源被删除啦！')</script>");
        }
        //处理文件名
        String realname = fileName.substring(fileName.indexOf("_")+1);
        //设置响应头，控制浏览器下载该文件
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(realname, "UTF-8"));
        //读取要下载的文件，保存到文件输入流
        FileInputStream in = new FileInputStream(savePath);
        //创建输出流
        OutputStream out = response.getOutputStream();
        //创建缓冲区
        byte buffer[] = new byte[1024];
        int len = 0;
        //循环将输入流中的内容读取到缓冲区当中
        while((len=in.read(buffer))>0){
            //输出缓冲区的内容到浏览器，实现文件下载
            out.write(buffer, 0, len);
        }
        //关闭文件输入流
        in.close();
        //关闭输出流
        out.close();
    }

    /*
     * @Method: findFileSavePathByFileName
     * @Description: 通过文件名和存储上传文件根目录找出要下载的文件的所在路径
     * @param filename 要下载的文件名
     * @param saveRootPath 上传文件保存的根目录，也就是/WEB-INF/upload目录
     * @return 要下载的文件的存储目录
     */
    public String findFileSavePathByFileName(String filename,String saveRootPath){
        //用日期得到文件名的
        Calendar date=Calendar.getInstance();
        SimpleDateFormat format1=new SimpleDateFormat( "yyyy-MM-dd");
        String name=format1.format(date.getTime());
        String dir = saveRootPath + "\\" + name;
        File file=new File(dir);
        //如果目录不存在
        if(!file.exists()){
            //创建目录
            file.mkdirs();
        }
        return dir;
    }
}
