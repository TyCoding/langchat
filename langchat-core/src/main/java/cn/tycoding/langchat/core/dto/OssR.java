package cn.tycoding.langchat.core.dto;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * OSS 文件对象
 *
 * @author tycoding
 * @since 2024/1/30
 */
@Data
@Accessors(chain = true)
public class OssR implements Serializable {
    private static final long serialVersionUID = 5117927170776709434L;

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
     * 文件绝对路径
     */
    private String path;

    /**
     * 创建时间
     */
    private Date createTime;
}
