import sinkImpl.ConsoleLogSink;
import sinkImpl.FileLogSink;
import sinkImpl.DatabaseLogSink;


public class Main {
    public static void main(String[] args) {
        Logger logger = Logger.INSTANCE;

        logger.addSink(new ConsoleLogSink());
        logger.addSink(new FileLogSink("bin/logs.txt"));
        logger.addSink(new DatabaseLogSink());

        logger.setLogLevel(LogLevel.INFO);

        logger.log(LogLevel.DEBUG, "This is a DEBUG message"); // Ignored due to log level
        logger.log(LogLevel.INFO, "This is an INFO message");
        logger.log(LogLevel.WARNING, "This is a WARNING message");
        logger.log(LogLevel.ERROR, "This is an ERROR message");
        logger.log(LogLevel.FATAL, "This is a FATAL message");
    }
}
