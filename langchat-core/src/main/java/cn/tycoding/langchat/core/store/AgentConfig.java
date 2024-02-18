package cn.tycoding.langchat.core.store;

import lombok.Data;

/**
 * @author tycoding
 * @since 2023/10/31
 */
@Data
public class AgentConfig {
    /**
     * 模型密钥
     */
    private String apiKey;
    /**
     * 对话模型
     */
    private String chatModel;
    /**
     * 模型名称
     */
    private String modelName;
    /**
     * 温度
     */
    private Double temperature;

    private String tokenLimit;
}
