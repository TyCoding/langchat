package cn.tycoding.langchat.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @author tycoding
 * @since 2023/11/15
 */
@Data
public class LcUser implements Serializable {

    private static final long serialVersionUID = -94827981963832107L;

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 状态 0锁定 1有效
     */
    private Boolean status;

    /**
     * 创建时间
     */
    private Date createTime;
}
