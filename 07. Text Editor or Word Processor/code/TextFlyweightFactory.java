import java.util.HashMap;
import java.util.Map;

public class TextFlyweightFactory {
    private static final Map<String, TextFlyweightEntity> textFlyweights = new HashMap<>();

    public static TextFlyweightEntity getTextFlyweight(String font, String fontSize, String color) {
        String key = font + ":" + fontSize + ":" + color;
        textFlyweights.putIfAbsent(key, new TextFlyweightEntity(font, fontSize, color));
        return textFlyweights.get(key);
    }

    public static int getTextFlyweightCount() {
        return textFlyweights.size();
    }
}

// Why static? 
// - Directly call `TextFlyweightFactory.getTextFlyweight()`; no factory instantiation needed  
// - Ensures a single `textFlyweights` map; avoids redundant factories and preserves object reuse