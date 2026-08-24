import java.util.ArrayDeque;
import java.util.Deque;

public class TextEditorHistory {
    private Deque<String> undoStack = new ArrayDeque<>();
    private Deque<String> redoStack = new ArrayDeque<>();

    public void type(String text) {
        undoStack.push(text);
        redoStack.clear(); // 新操作會清除重做紀錄
        printState("輸入: " + text);
    }

    public void undo() {
        if (undoStack.isEmpty()) {
            System.out.println("[Undo 失敗] 沒有可復原的操作。");
            return;
        }
        String text = undoStack.pop();
        redoStack.push(text);
        printState("Undo 復原: " + text);
    }

    public void redo() {
        if (redoStack.isEmpty()) {
            System.out.println("[Redo 失敗] 沒有可重做的操作。");
            return;
        }
        String text = redoStack.pop();
        undoStack.push(text);
        printState("Redo 重做: " + text);
    }

    private void printState(String action) {
        System.out.println(action);
        System.out.println("  [Undo Stack]: " + undoStack);
        System.out.println("  [Redo Stack]: " + redoStack + "\n");
    }

    public static void main(String[] args) {
        TextEditorHistory editor = new TextEditorHistory();
        editor.type("Hello");
        editor.type(" World");
        
        editor.undo(); // World 移至 redo
        editor.undo(); // Hello 移至 redo
        editor.undo(); // 測試空 Stack
        
        editor.redo(); // Hello 移回 undo
        editor.type(" Java"); // World 被清除
    }
}