import java.util.ArrayDeque;
import java.util.Deque;

public class TextEditorHistory {
    private Deque<String> undoStack = new ArrayDeque<>();
    private Deque<String> redoStack = new ArrayDeque<>();

    void type(String text) {
        undoStack.push(text);
        redoStack.clear();
        System.out.println("輸入: " + text);
        printState();
    }

    void undo() {
        if (undoStack.isEmpty()) {
            System.out.println("Undo 失敗: 沒有可復原的操作");
            return;
        }
        String text = undoStack.pop();
        redoStack.push(text);
        System.out.println("Undo: 復原了 '" + text + "'");
        printState();
    }

    void redo() {
        if (redoStack.isEmpty()) {
            System.out.println("Redo 失敗: 沒有可重做的操作");
            return;
        }
        String text = redoStack.pop();
        undoStack.push(text);
        System.out.println("Redo: 重做了 '" + text + "'");
        printState();
    }

    void printState() {
        System.out.println("  [Undo Stack]: " + undoStack);
        System.out.println("  [Redo Stack]: " + redoStack + "\n");
    }

    public static void main(String[] args) {
        TextEditorHistory editor = new TextEditorHistory();
        editor.type("Hello");
        editor.type("World");
        editor.undo();
        editor.type("Java"); 
        editor.undo();
        editor.undo();
        editor.redo();
        editor.redo();
        editor.redo(); 
    }
}