package sinkImpl;

import Interfaces.LogSink;

import java.io.FileWriter;
import java.io.IOException;

public class FileLogSink implements LogSink {
    private final String fileName;

    public FileLogSink(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void write(String message) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(message + System.lineSeparator());
        } catch (IOException e) {
            System.err.println("Failed to write to file: " + e.getMessage());
        }
    }
}
