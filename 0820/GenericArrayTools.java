public class GenericArrayTools {

    // 計算目標物件出現次數
    static <T> int countMatches(T[] data, T target) {
        if (data == null) return 0;
        int count = 0;
        for (T element : data) {
            // 處理 target 可能為 null 的比較
            if (target == null ? element == null : target.equals(element)) {
                count++;
            }
        }
        return count;
    }

    // 取得最後一筆資料
    static <T> T last(T[] data) {
        if (data == null || data.length == 0) return null;
        return data[data.length - 1];
    }

    // 交換陣列元素 (需處理邊界與不合法 index)
    static <T> void swap(T[] data, int first, int second) {
        if (data == null || data.length == 0) return;
        if (first < 0 || first >= data.length || second < 0 || second >= data.length) return;
        
        T temp = data[first];
        data[first] = data[second];
        data[second] = temp;
    }

    public static void main(String[] args) {
        String[] words = {"Apple", "Banana", "Apple", "Cherry"};
        
        System.out.println("Apple 出現次數: " + countMatches(words, "Apple"));
        System.out.println("最後一個元素: " + last(words));
        
        swap(words, 0, 3);
        System.out.println("交換後的第一個元素: " + words[0]);
    }
}