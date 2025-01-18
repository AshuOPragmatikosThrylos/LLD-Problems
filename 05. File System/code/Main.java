public class Main {

    public static void main(String args[]) {

        Directory directory1 = new Directory("directory1");

        File file1 = new File("file1");
        directory1.add(file1);

        Directory directory2 = new Directory("directory2");
        File file2 = new File("file2");
        directory2.add(file2);
        directory1.add(directory2);

        directory1.ls();

    }
}
