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
public class AigcDocsSlice implements Serializable {
    private static final long serialVersionUID = -3093489071059867065L;

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 文档ID
     */
    private String docsId;

    /**
     * 知识库ID
     */
    private String knowledgeId;

    /**
     * 文件名称
     */
    private String name;

    /**
     * 字符数量
     */
    private Integer wordNum;

    /**
     * 是否Embedding
     */
    private Boolean status = false;

    /**
     * 创建时间
     */
    private String createTime;
}

