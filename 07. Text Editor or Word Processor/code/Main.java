import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<TextFlyweightEntity> textFlyweights = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            // Reuse shared state while providing unique state dynamically
            TextFlyweightEntity textFlyweight = TextFlyweightFactory.getTextFlyweight("Arial", "12px", "Black");
            textFlyweight.render("Character " + i);
            textFlyweights.add(textFlyweight);
        }

        System.out.println("\nTotal Text Flyweights created: " + TextFlyweightFactory.getTextFlyweightCount());
    }
}
