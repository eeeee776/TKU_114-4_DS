import java.util.ArrayDeque;
import java.util.Deque;

public class BrowserBackStack {
    private Deque<String> history = new ArrayDeque<>();
    private String currentPage = null;

    public void visit(String url) {
        if (currentPage != null) {
            history.push(currentPage); // 等同 offerFirst
        }
        currentPage = url;
        System.out.println("造訪: " + url);
    }

    public void back() {
        String prevPage = history.pollFirst();
        if (prevPage == null) {
            System.out.println("返回失敗：已經是最早的網頁了。");
        } else {
            currentPage = prevPage;
            System.out.println("返回至: " + currentPage);
        }
    }

    public void current() {
        System.out.println("當前網頁: " + (currentPage == null ? "無" : currentPage));
    }

    public static void main(String[] args) {
        BrowserBackStack browser = new BrowserBackStack();
        
        browser.visit("google.com");
        browser.visit("github.com");
        browser.visit("stackoverflow.com");
        
        browser.current(); // stackoverflow.com
        browser.back();    // github.com
        browser.back();    // google.com
        browser.back();    // 返回失敗
        browser.current(); // google.com
    }
}