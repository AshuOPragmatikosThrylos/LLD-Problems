public class Main {
    public static void main(String[] args) {
        MyHashMap<String, Integer> map = new MyHashMap<>(4);

        // Test put
        map.put("apple", 10);
        map.put("banana", 20);
        map.put("orange", 30);
        map.put("grape", 40);

        // Test get
        System.out.println("apple: " + map.get("apple"));
        System.out.println("banana: " + map.get("banana"));
        System.out.println("grape: " + map.get("grape"));
        System.out.println("cherry: " + map.get("cherry"));

        // Test containsKey
        System.out.println("Contains 'orange'? " + map.containsKey("orange"));
        System.out.println("Contains 'kiwi'? " + map.containsKey("kiwi"));

        // Test remove
        System.out.println("Removed 'banana': " + map.remove("banana"));
        System.out.println("banana: " + map.get("banana"));

        // Test keySet
        System.out.println("Keys: " + map.keySet()); // should not include "banana"

        // Test size
        System.out.println("Size: " + map.size());

        // Test rehash by inserting more elements
        map.put("kiwi", 50);
        map.put("mango", 60);
        map.put("papaya", 70);
        map.put("peach", 80);

        System.out.println("After rehash:");
        System.out.println("Keys: " + map.keySet());
        System.out.println("Size: " + map.size());
    }
}