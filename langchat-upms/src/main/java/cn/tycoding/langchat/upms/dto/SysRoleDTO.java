package cn.tycoding.langchat.upms.dto;

import cn.tycoding.langchat.upms.entity.SysRole;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * SysRole DTO封装
 *
 * @author tycoding
 * @since 2024/4/15
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysRoleDTO extends SysRole {
    private static final long serialVersionUID = -5792577217091151552L;

    /**
     * 菜单ID集合
     */
    private List<Long> menuIds;
}
