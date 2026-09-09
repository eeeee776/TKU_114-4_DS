import java.util.ArrayDeque;
import java.util.Deque;

public class BrowserBackStack {
    public static void main(String[] args) {
        Deque<String> history = new ArrayDeque<>();
        
        System.out.println("Visit: google.com");
        history.push("google.com");
        
        System.out.println("Visit: github.com");
        history.push("github.com");
        
        System.out.println("Visit: stackoverflow.com");
        history.push("stackoverflow.com");
        
        System.out.println("Current: " + history.peek());
        
        System.out.println("Back to: " + history.poll());
        System.out.println("Current: " + history.peek());
        
        System.out.println("Back to: " + history.poll());
        System.out.println("Back to: " + history.poll());
        
        System.out.println("Back again (Empty check): " + history.poll());
        System.out.println("Current (Empty check): " + history.peek());
    }
}