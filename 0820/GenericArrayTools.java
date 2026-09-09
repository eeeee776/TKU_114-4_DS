public class GenericArrayTools {
    
    static <T> int countMatches(T[] data, T target) {
        if (data == null) return 0;
        int count = 0;
        for (T item : data) {
            if (target == null) {
                if (item == null) count++;
            } else {
                if (target.equals(item)) count++;
            }
        }
        return count;
    }

    static <T> T last(T[] data) {
        if (data == null || data.length == 0) return null;
        return data[data.length - 1];
    }

    static <T> void swap(T[] data, int first, int second) {
        if (data == null || first < 0 || second < 0 || first >= data.length || second >= data.length) return;
        T temp = data[first];
        data[first] = data[second];
        data[second] = temp;
    }

    public static void main(String[] args) {
        Integer[] numbers = {10, 20, 30, 20, 50};
        String[] words = {"A", "B"};

        System.out.println("Count 20: " + countMatches(numbers, 20));
        System.out.println("Last number: " + last(numbers));
        
        swap(words, 0, 1);
        System.out.println("Swapped words: " + words[0] + ", " + words[1]);
    }
}