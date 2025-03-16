public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            // Reuse shared state while providing unique state dynamically
            TextFlyweightEntity textFlyweight = TextFlyweightFactory.getTextFlyweight("Arial", "12px", "Black");
            textFlyweight.render("Character " + i);
        }

        System.out.println("\nTotal Text Flyweights created: " + TextFlyweightFactory.getTextFlyweightCount());
    }
}
