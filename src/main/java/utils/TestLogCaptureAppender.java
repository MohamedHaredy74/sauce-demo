package utils;

import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;

@Plugin(name = "TestLogCaptureAppender", category = "Core", elementType = "appender", printObject = true)
public class TestLogCaptureAppender extends AbstractAppender {

    private static final ThreadLocal<StringBuilder> BUFFER = ThreadLocal.withInitial(StringBuilder::new);

    protected TestLogCaptureAppender(String name, Layout<? extends Serializable> layout) {
        super(name, null, layout, true, null);
    }

    @PluginFactory
    public static TestLogCaptureAppender createAppender(
            @PluginAttribute("name") String name,
            @PluginElement("Layout") Layout<? extends Serializable> layout) {
        return new TestLogCaptureAppender(name, layout);
    }

    @Override
    public void append(LogEvent event) {
        BUFFER.get().append(new String(getLayout().toByteArray(event), StandardCharsets.UTF_8));
    }

    public static String getLogs() {
        return BUFFER.get().toString();
    }

    public static void clear() {
        BUFFER.remove();
    }
}