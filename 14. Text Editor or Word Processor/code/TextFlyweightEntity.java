public class TextFlyweightEntity {
    private final String font; // Intrinsic property
    private final String fontSize;
    private final String color;

    public TextFlyweightEntity(String font, String fontSize, String color) {
        this.font = font;
        this.fontSize = fontSize;
        this.color = color;
    }

    public void render(String content) { // Unique property as extrinsic state
        System.out.println("Rendering with font: " + font + ", size: " + fontSize + ", color: " + color + ", and content: " + content);
    }
}
