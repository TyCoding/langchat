package cn.tycoding.langchat.common.properties;

import cn.hutool.core.io.FileUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.ResourceLoader;

import java.net.URL;

/**
 * @author tycoding
 * @since 2023/8/31
 */
@Data
@ConfigurationProperties("langchat.oss")
public class OssProps {

    private final String PATH = "/file";

    @Value("${server.port:8080}")
    private String serverPort;

    private final ResourceLoader resourceLoader;

    public OssProps(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    /**
     * 文件上传地址
     */
    private String uploadPath;

    /**
     * 文件访问地址
     */
    private String remotePath;

    public String getRemotePath() {
        if (this.remotePath == null) {
            return "http://127.0.0.1:" + serverPort + PATH;
        }
        return remotePath;
    }

    public String getUploadPath() {
        if (this.uploadPath == null) {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            URL resource = classLoader.getResource("static" + PATH);
            if (resource != null) {
                return resource.getPath();
            } else {
                String path = classLoader.getResource("").getPath() + "static" + PATH;
                FileUtil.mkdir(path);
                return path;
            }
        }
        return uploadPath;
    }
}
