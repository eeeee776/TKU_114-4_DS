import java.util.ArrayList;
import java.util.List;

public class WildcardNumberTools {
    
    static double average(List<? extends Number> values) {
        if (values == null || values.isEmpty()) return 0.0;
        double sum = 0;
        for (Number n : values) {
            sum += n.doubleValue();
        }
        return sum / values.size();
    }

    static double maximum(List<? extends Number> values) {
        if (values == null || values.isEmpty()) return Double.NaN;
        double max = values.get(0).doubleValue();
        for (Number n : values) {
            if (n.doubleValue() > max) {
                max = n.doubleValue();
            }
        }
        return max;
    }

    static void addRange(List<? super Integer> target, int start, int end) {
        if (target == null || start > end) return;
        for (int i = start; i <= end; i++) {
            target.add(i);
        }
    }

    public static void main(String[] args) {
        List<Double> doubles = List.of(1.5, 2.5, 3.5);
        List<Integer> integers = new ArrayList<>();
        
        addRange(integers, 1, 5);
        
        System.out.println("Average of doubles: " + average(doubles));
        System.out.println("Maximum of integers: " + maximum(integers));
        System.out.println("Range added: " + integers);
    }
}