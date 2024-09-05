package cn.tycoding.langchat.biz.vo;

import lombok.Data;

/**
 * @author GB
 * @desc
 * @since 2024-09-04
 */
@Data
public class FileAnalysisMonitorVO {

    /**
     * 解析速率
     */
    private Double speed;

    /**
     * 文件名
     */
    private String name;

    /**
     * 解析状态
     */
    private Integer status;

    private String docsId;
}
