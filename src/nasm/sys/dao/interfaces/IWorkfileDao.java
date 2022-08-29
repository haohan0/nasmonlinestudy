package nasm.sys.dao.interfaces;

import nasm.commons.PageControl;
import nasm.commons.Pager;
import nasm.model.Workfile;

import java.util.List;

public interface IWorkfileDao {
    /**
     * 上传文件
     */
    public Integer upfile(Workfile workfile);

    /**
     * 得到对应用户的数据
     */
    public Pager<Workfile> list(Workfile workfile, PageControl pc);

    /**
     * 下载查看文件
     */
    public List<Workfile> findfile(Workfile workfile);

    /**
     * 任务详情
     */
    Pager<Workfile> taskworklist(Workfile workfile,PageControl pc);
}
