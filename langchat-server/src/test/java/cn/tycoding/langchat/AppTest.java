package cn.tycoding.langchat;

import cn.tycoding.langchat.core.service.LangDocService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author tycoding
 * @since 2024/4/4
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {

    @Autowired
    private LangDocService embedService;

    @Test
    public void t1() {
//        embedService.embedding();
        embedService.search();
    }
}
