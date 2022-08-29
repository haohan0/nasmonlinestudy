package nasm.sys.services.interfaces;

import nasm.commons.PageControl;
import nasm.commons.Pager;
import nasm.model.Workfile;

import java.util.List;

public interface IWorlfileService {
    /**
     * 上传文件
     */
    public Integer upfile(Workfile workfile);

    /**
     * 查看指定用户的文件
     */
    Pager<Workfile> list(Workfile workfile, PageControl pc);

    /**
     * 下载文件
     */
    List<Workfile> findfile(Workfile workfile);

    /**
     * 任务详情
     */
    Pager<Workfile> taskworklist(Workfile workfile,PageControl pc);
}
