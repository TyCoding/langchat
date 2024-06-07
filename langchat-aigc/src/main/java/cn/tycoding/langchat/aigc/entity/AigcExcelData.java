package cn.tycoding.langchat.aigc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author tycoding
 * @since 2024/4/15
 */
@Data
@Accessors(chain = true)
@TableName(autoResultMap = true)
public class AigcExcelData implements Serializable {
    private static final long serialVersionUID = 548724967827903685L;

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 行索引
     */
    private Integer rowIndex;

    /**
     * 行数据
     */
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private List<String> data;

    /**
     * 知识库ID
     */
    private String knowledgeId;

    /**
     * 文档ID
     */
    private String docsId;
}

