package cn.tycoding.langchat.server.component;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.tycoding.langchat.common.constant.PromptConst;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author tycoding
 * @since 2023/12/28
 */
@Slf4j
@Component
public class PromptStore {

    private static final Map<String, String> promptMap = new HashMap<>();

    @PostConstruct
    public void start() {
        try {
            String path = new ClassPathResource("prompt").getAbsolutePath();
            for (File file : FileUtil.ls(path)) {
                String content = FileUtil.readString(file, StandardCharsets.UTF_8);
                promptMap.put(FileUtil.mainName(file), content);
            }
        } catch (Exception e) {
            log.error("Prompt datasource init error......");
        }
    }

    public static String get(PromptConst promptEnum) {
        return promptMap.get(promptEnum.getType());
    }
}
