package cn.tycoding.langchat.flow.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author tycoding
 * @since 2024/6/17
 */
@Data
@Accessors(chain = true)
public class AigcFlow implements Serializable {
    private static final long serialVersionUID = -94320446004505219L;

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 模型名称
     */
    private String name;

    /**
     * 流程图
     */
    private String flow;

    /**
     * Flow Chain EL脚本
     */
    private String script;

    /**
     * Flow类型
     */
    private String flowType = "type";

    /**
     * 描述
     */
    private String des;

    /**
     * 是否发布
     */
    private Boolean isPublish = false;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 发布时间
     */
    private Date publishTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}

