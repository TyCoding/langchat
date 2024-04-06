package cn.tycoding.langchat.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author tycoding
 * @since 2023/8/31
 */
@Data
@ConfigurationProperties("langchat.oss")
public class OssProps {

    /**
     * 文件上传地址
     */
    private String uploadPath = System.getProperty("user.dir") + "/target/classes/static";

    /**
     * 文件访问地址
     */
    private String remotePath = "http://127.0.0.1:9000";
}
