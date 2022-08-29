package nasm.sys.services.interfaces;

import nasm.commons.PageControl;
import nasm.commons.Pager;
import nasm.model.Instruct;

public interface IInstructService {
    /**
     * 查看指令列表
     * @param instruct
     * @return
     */
    public Pager<Instruct> list(Instruct instruct,PageControl pc);

    /**
     * 查找单个指令
     * @param instruct
     * @return
     */
    public Instruct detail(Instruct instruct);

    /**
     * 修改指令信息
     * @param instruct
     * @return
     */
    public Integer edit(Instruct instruct);

    /**
     * 添加一个指令
     * @param instruct
     * @return
     */
    public Integer addinstruct(Instruct instruct);
}
