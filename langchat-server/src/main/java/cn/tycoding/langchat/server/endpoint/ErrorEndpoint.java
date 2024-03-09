package cn.tycoding.langchat.server.endpoint;

import cn.tycoding.langchat.common.utils.R;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tycoding
 * @since 2024/1/5
 */
@RestController
public class ErrorEndpoint implements ErrorController {

    @RequestMapping("/error")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public R handleError() {
        return R.fail(HttpStatus.NOT_FOUND);
    }
}
