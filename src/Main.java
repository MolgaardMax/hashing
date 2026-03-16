public class Main {

    public static void main(String[] args) {

        Dictionary<String, Integer> dict = new DictionaryOpenAddressing<>();

        dict.put("A", 1);
        dict.put("B", 2);
        dict.put("C", 3);

        System.out.println(dict.get("A"));
        System.out.println(dict.get("B"));

        dict.remove("B");

        System.out.println(dict.get("B"));

        System.out.println("Size: " + dict.size());
    }
}