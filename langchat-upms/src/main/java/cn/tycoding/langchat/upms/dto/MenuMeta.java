package cn.tycoding.langchat.upms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 封装前端路由所需的路径名称、图标格式
 *
 * @author tycoding
 * @since 2024/4/15
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
public class MenuMeta implements Serializable {
    private static final long serialVersionUID = 547891924677981054L;

    /**
     * 标题
     */
    private String title;

    /**
     * 图标名称
     */
    private String icon;
}
