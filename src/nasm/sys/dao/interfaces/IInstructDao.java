package nasm.sys.dao.interfaces;

import nasm.commons.PageControl;
import nasm.commons.Pager;
import nasm.model.Instruct;

public interface IInstructDao {

    /**
     * 列出指令
     * @param instruct
     * @param pc
     * @return
     */
    public Pager<Instruct> list(Instruct instruct, PageControl pc);

    /**
     * 获取单个指令信息
     * @param instruct
     * @return
     */
    public Instruct detail(Instruct instruct);

    /**
     * 修改指令
     * @param instruct
     * @return
     */
    public Integer edit(Instruct instruct);

    /**
     * 添加指令
     * @param instruct
     * @return
     */
    public Integer addinstruct(Instruct instruct);
}
