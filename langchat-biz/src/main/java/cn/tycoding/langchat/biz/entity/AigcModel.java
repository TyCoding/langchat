package cn.tycoding.langchat.biz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author tycoding
 * @since 2024/1/4
 */
@Data
@Accessors(chain = true)
public class AigcModel implements Serializable {
    private static final long serialVersionUID = -19545329638997333L;

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    private String model;
    private String provider;
    private String name;
    private Integer responseLimit;
    private Double temperature = 0.2;
    private Double topP = 0.0;
    private String apiKey;
    private String secretKey;
    private String baseUrl;
    private String endpoint;
    private String geminiLocation;
    private String geminiProject;
    private String azureDeploymentName;
    private String imageSize;
    private String imageQuality;
    private String imageStyle;
    private String modelType;
    private Integer dimensions;
}

