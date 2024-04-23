package cn.tycoding.langchat.aigc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @author tycoding
 * @since 2024/4/15
 */
@Data
public class AigcKbFile implements Serializable {
    private static final long serialVersionUID = -3093489071059867065L;

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 知识库ID
     */
    private String kbId;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件描述
     */
    private String targetName;

    /**
     * 文件目录
     */
    private String bucket;

    /**
     * 文件类型
     */
    private String type;

    /**
     * 文件大小
     */
    private Long size;

    /**
     * 文件地址
     */
    private String url;

    /**
     * 文件来源
     */
    private String source;

    /**
     * 文件描述
     */
    private String des;

    /**
     * 是否Embedding
     */
    private Boolean isEmbed = false;

    /**
     * 创建时间
     */
    private String createTime;
}

