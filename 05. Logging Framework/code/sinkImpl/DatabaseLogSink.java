package sinkImpl;

import Interfaces.LogSink;

public class DatabaseLogSink implements LogSink {
    @Override
    public void write(String message) {
        System.out.println("Database: " + message); // Simulate database write
    }
}
