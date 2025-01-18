import Interfaces.FileSystem;

// File is a FileSystem -> In other words FileSystem can be a File
public class File implements FileSystem {
    String fileName;

    public File(String name) {
        this.fileName = name;
    }

    public void ls() {
        System.out.println("File name " + fileName);
    }

}