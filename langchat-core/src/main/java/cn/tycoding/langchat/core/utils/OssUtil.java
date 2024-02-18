package cn.tycoding.langchat.core.utils;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.URLUtil;
import cn.tycoding.langchat.core.properties.OssProps;
import java.io.File;
import java.util.Date;
import lombok.SneakyThrows;
import org.springframework.web.multipart.MultipartFile;

/**
 * Oss工具类
 *
 * @author tycoding
 * @since 2023/8/31
 */
public class OssUtil {

    /**
     * 获取Bucket文件夹名称
     *
     * @return 根据日期格式化的文件夹名称
     */
    public static String getBucket() {
        return "/" + DateUtil.format(new Date(), DatePattern.PURE_DATE_PATTERN);
    }

    /**
     * 根据随机值组建文件名称
     *
     * @param fileName 原始文件名称
     * @return 通过雪花算法获取新文件名称
     */
    public static String getName(String fileName) {
        String suffix = FileUtil.getSuffix(fileName);
        return FileUtil.mainName(fileName) + "-" + UUID.randomUUID().toString(true) + "." + suffix;
    }

    /**
     * 获取文件访问URL地址
     *
     * @param url      文件上传服务地址
     * @param bucket   Bucket文件夹路径
     * @param fileName 新文件名称
     * @return 拼接后的文件访问地址
     */
    public static String getUrl(String url, String bucket, String fileName) {
        if (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }
        return URLUtil.normalize(url + bucket + "/" + fileName);
    }

    @SneakyThrows
    public static OssR transfer(OssProps props, MultipartFile file) {
        return handler(props, file, null);
    }

    @SneakyThrows
    public static OssR transfer(OssProps props, File file) {
        return handler(props, null, file);
    }

    @SneakyThrows
    private static OssR handler(OssProps props, MultipartFile webFile, File file) {
        String bucket = getBucket();
        String targetPath = props.getUploadPath() + bucket;
        if (!FileUtil.isDirectory(targetPath)) {
            FileUtil.mkdir(targetPath);
        }
        String originName = webFile == null ? file.getName() : webFile.getOriginalFilename();
        Long originSize = webFile == null ? FileUtil.size(file) : webFile.getSize();
        String targetName = getName(originName);
        File targetFile = new File(targetPath, targetName);

        if (webFile == null) {
            FileUtil.copy(file, targetFile, true);
            FileUtil.del(file);
        } else {
            webFile.transferTo(targetFile);
        }

        return new OssR()
                .setFileName(FileUtil.mainName(originName))
                .setTargetName(targetName)
                .setBucket(bucket)
                .setPath(targetFile.getPath())
                .setSize(originSize)
                .setType(FileUtil.getSuffix(targetName))
                .setUrl(getUrl(props.getRemotePath(), bucket, targetName))
                .setCreateTime(new Date())
                ;
    }
}
