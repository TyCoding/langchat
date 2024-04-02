package cn.tycoding.langchat.biz.entity;

import cn.tycoding.langchat.common.dto.OssR;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author tycoding
 * @since 2024/1/6
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysOss extends OssR {

    private static final long serialVersionUID = -250127374910520163L;

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 文件描述
     */
    private String des;
}
