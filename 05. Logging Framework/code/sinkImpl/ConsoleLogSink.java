package sinkImpl;

import Interfaces.LogSink;

public class ConsoleLogSink implements LogSink {
    @Override
    public void write(String message) {
        System.out.println("Console: " + message);
    }
}
