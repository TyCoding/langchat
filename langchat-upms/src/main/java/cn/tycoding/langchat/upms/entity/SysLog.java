package cn.tycoding.langchat.upms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统日志表(Log)实体类
 *
 * @author tycoding
 * @since 2024/4/15
 */
@Data
@Accessors(chain = true)
public class SysLog implements Serializable {
    private static final long serialVersionUID = -39039111282732175L;

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 操作用户
     */
    private String username;

    /**
     * 日志类型
     */
    private Integer type;

    /**
     * 操作描述
     */
    private String operation;

    /**
     * 请求URL
     */
    private String url;

    /**
     * 耗时(毫秒)
     */
    private Long time;

    /**
     * 操作方法
     */
    private String method;

    /**
     * 操作参数
     */
    private String params;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 用户代理
     */
    private String userAgent;

    /**
     * 操作时间
     */
    private Date createTime;
}
