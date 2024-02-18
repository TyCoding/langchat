package cn.tycoding.langchat.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author tycoding
 * @since 2023/8/4
 */
@Data
@Accessors(chain = true)
public class LcConversation implements Serializable {

    private static final long serialVersionUID = -19545329638997333L;

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 会话标题
     */
    private String title;

    /**
     * 模型ID
     */
    private String modelId;

    /**
     * 模型名称
     */
    private String model;

    /**
     * 对话模型
     */
    private String chatModel;

    /**
     * 创建时间
     */
    private String createTime;

}

