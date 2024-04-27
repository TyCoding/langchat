package cn.tycoding.langchat.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author tycoding
 * @since 2024/4/15
 */
@Data
@Accessors(chain = true)
public class AigcStructCol implements Serializable {
    private static final long serialVersionUID = 548724967827903685L;

    /**
     * 主键
     */
    @TableId(type = IdType.INPUT)
    private String id;

    /**
     * 知识库ID
     */
    private String knowledgeId;

    /**
     * 文档ID
     */
    private String docsId;

    /**
     * 列名称
     */
    private String label;

    /**
     * 列索引
     */
    private Integer index;
}

