import Interfaces.LogSink;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public enum Logger {
    INSTANCE;

    private final List<LogSink> sinks = new ArrayList<>();
    private LogLevel currentLogLevel = LogLevel.DEBUG; // Default log level

    public void addSink(LogSink sink) {
        sinks.add(sink);
    }

    public void setLogLevel(LogLevel level) {
        this.currentLogLevel = level;
    }

    public void log(LogLevel level, String message) {
        if (level.getPriority() >= currentLogLevel.getPriority()) {
            String logMessage = "[" + level + "] " + LocalDateTime.now() + ": " + message;
            for (LogSink sink : sinks) {
                sink.write(logMessage);
            }
        }
    }
}
