package cn.tycoding.langchat.biz.dto;

import cn.tycoding.langchat.biz.entity.LcUser;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author tycoding
 * @since 2024/2/8
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LcUserInfo extends LcUser implements Serializable {

    private static final long serialVersionUID = 5211809047293198579L;
}
