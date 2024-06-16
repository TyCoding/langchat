package cn.tycoding.langchat.aigc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author tycoding
 * @since 2024/1/4
 */
@Data
@Accessors(chain = true)
public class AigcModel implements Serializable {

    private static final long serialVersionUID = -19545329638997333L;

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    private String model;
    private String provider;
    private String name;
    private Integer responseLimit;
    private Double temperature;
    private Double topP;
    private String apiKey;
    private String baseUrl;
}

