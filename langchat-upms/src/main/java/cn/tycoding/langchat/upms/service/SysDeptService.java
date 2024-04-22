package cn.tycoding.langchat.upms.service;

import cn.hutool.core.lang.tree.Tree;
import cn.tycoding.langchat.upms.entity.SysDept;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 部门表(Dept)表服务接口
 *
 * @author tycoding
 * @since 2024/4/15
 */
public interface SysDeptService extends IService<SysDept> {

    List<SysDept> list(SysDept sysDept);

    List<Tree<Object>> tree(SysDept sysDept);

    void delete(Long id);

}
