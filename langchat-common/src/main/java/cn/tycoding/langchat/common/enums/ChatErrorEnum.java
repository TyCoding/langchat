package cn.tycoding.langchat.common.enums;

import lombok.AllArgsConstructor;

/**
 * @author GB
 * @desc
 * @since 2024-08-21
 */
@AllArgsConstructor
public enum ChatErrorEnum {
    API_KEY_IS_NULL(1000, "模型 %s %s api key 为空，请检查配置"),
    BASE_URL_IS_NULL(1003, "模型 %s %s base url 为空，请检查配置"),
    SECRET_KEY_IS_NULL(1005, "模型 %s %s base secret Key 为空，请检查配置"),
    ;

    /**
     * 错误码
     */
    private int errorCode;
    /**
     * 错误描述，用于展示给用户
     */
    private String errorDesc;

    public int getErrorCode() {
        return this.errorCode;
    }

    public String getErrorDesc(String modelName, String type) {
        return this.errorDesc.formatted(modelName, type);
    }

}
