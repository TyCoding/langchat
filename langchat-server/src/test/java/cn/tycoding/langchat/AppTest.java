package cn.tycoding.langchat;

import cn.tycoding.langchat.common.properties.AuthProps;
import cn.tycoding.langchat.upms.utils.AuthUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author tycoding
 * @since 2024/4/4
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {

    @Autowired
    private AuthProps authProps;

    @Test
    public void t1() {
        System.out.println("-----");
    }

    public static void main(String[] args) {
        String encode = AuthUtil.encode("langchat-salt", "123456");
        System.out.println(encode);
    }
}
