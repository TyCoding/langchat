package cn.tycoding.langchat.aigc.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author tycoding
 * @since 2024/4/24
 */
@Data
public class MsgChartDto implements Serializable {
    private static final long serialVersionUID = 8074252311773257985L;

    private String date;
    private String tokens;
}
