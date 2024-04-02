package cn.tycoding.langchat.biz.dto;

import cn.tycoding.langchat.biz.entity.SysUser;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author tycoding
 * @since 2024/2/8
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserInfo extends SysUser implements Serializable {

    private static final long serialVersionUID = 5211809047293198579L;
}
