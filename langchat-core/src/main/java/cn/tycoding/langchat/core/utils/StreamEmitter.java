package cn.tycoding.langchat.core.utils;


import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * @author tycoding
 * @since 2023/12/30
 */
public class StreamEmitter {

    private final SseEmitter emitter;

    public StreamEmitter() {
        emitter = new SseEmitter();
    }

    public StreamEmitter(Long timeout) {
        emitter = new SseEmitter(timeout);
    }

    public SseEmitter get() {
        return emitter;
    }

    public void send(Object obj) {
        try {
            emitter.send(obj);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void complete() {
        try {
            emitter.complete();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
