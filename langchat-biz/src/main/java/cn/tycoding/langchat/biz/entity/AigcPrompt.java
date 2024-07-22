package cn.tycoding.langchat.biz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author tycoding
 * @since 2024/1/19
 */
@Data
public class AigcPrompt implements Serializable {

    private static final long serialVersionUID = -19545329638997333L;

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * Prompt
     */
    private String prompt;

    /**
     * 图标
     */
    private String icon;

    /**
     * 应用描述
     */
    private String des;

    /**
     * 创建时间
     */
    private Date createTime;
}

