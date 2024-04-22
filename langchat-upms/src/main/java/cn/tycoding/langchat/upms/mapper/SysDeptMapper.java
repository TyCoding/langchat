package cn.tycoding.langchat.upms.mapper;

import cn.tycoding.langchat.upms.entity.SysDept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 部门表(Dept)表数据库访问层
 *
 * @author tycoding
 * @since 2024/4/15
 */
@Mapper
public interface SysDeptMapper extends BaseMapper<SysDept> {

}
