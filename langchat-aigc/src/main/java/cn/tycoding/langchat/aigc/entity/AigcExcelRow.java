package cn.tycoding.langchat.aigc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author tycoding
 * @since 2024/4/15
 */
@Data
@Accessors(chain = true)
public class AigcExcelRow implements Serializable {
    private static final long serialVersionUID = 548724967827903685L;

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_UUID)
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
     * 列索引
     */
    private Integer colIndex;

    /**
     * 行值
     */
    private String value;
}

