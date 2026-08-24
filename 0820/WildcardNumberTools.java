import java.util.ArrayList;
import java.util.List;

public class WildcardNumberTools {

    // 接收各種 Number 陣列來計算平均值
    static double average(List<? extends Number> values) {
        if (values == null || values.isEmpty()) return 0.0;
        double sum = 0;
        for (Number num : values) {
            sum += num.doubleValue();
        }
        return sum / values.size();
    }

    // 接收各種 Number 陣列來找出最大值
    static double maximum(List<? extends Number> values) {
        if (values == null || values.isEmpty()) return Double.NaN;
        double max = Double.NEGATIVE_INFINITY;
        for (Number num : values) {
            if (num.doubleValue() > max) {
                max = num.doubleValue();
            }
        }
        return max;
    }

    // 將範圍內的整數寫入集合中
    static void addRange(List<? super Integer> target, int start, int end) {
        if (target == null || start > end) return;
        for (int i = start; i <= end; i++) {
            target.add(i); // 安全寫入 Integer
        }
    }

    public static void main(String[] args) {
        List<Double> doubles = List.of(3.5, 7.0, 1.5);
        System.out.println("Double 平均: " + average(doubles));
        System.out.println("Double 最大值: " + maximum(doubles));

        List<Integer> integers = new ArrayList<>();
        addRange(integers, 1, 5);
        System.out.println("Integer 寫入結果: " + integers);
        System.out.println("Integer 最大值: " + maximum(integers));
    }
}