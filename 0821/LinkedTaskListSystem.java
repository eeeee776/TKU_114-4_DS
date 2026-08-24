class Task {
    String id;
    String name;
    public Task(String id, String name) { this.id = id; this.name = name; }
    @Override public String toString() { return "[" + id + "]" + name; }
}

class TaskNode {
    Task task;
    TaskNode next;
    public TaskNode(Task task) { this.task = task; }
}

class TaskLinkedList {
    private TaskNode head;
    private int size;

    private boolean containsId(String id) {
        return findById(id) != null;
    }

    public void addFirst(Task task) {
        if (containsId(task.id)) return;
        TaskNode newNode = new TaskNode(task);
        newNode.next = head;
        head = newNode;
        size++;
    }

    public void addLast(Task task) {
        if (containsId(task.id)) return;
        TaskNode newNode = new TaskNode(task);
        if (head == null) {
            head = newNode;
        } else {
            TaskNode curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = newNode;
        }
        size++;
    }

    public Task findById(String id) {
        TaskNode curr = head;
        while (curr != null) {
            if (curr.task.id.equals(id)) return curr.task;
            curr = curr.next;
        }
        return null;
    }

    public boolean removeById(String id) {
        if (head == null) return false;
        
        // 如果要刪除的是 head
        if (head.task.id.equals(id)) {
            head = head.next;
            size--;
            return true;
        }
        
        // 尋找目標的前一個節點
        TaskNode curr = head;
        while (curr.next != null) {
            if (curr.next.task.id.equals(id)) {
                curr.next = curr.next.next;
                size--;
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    public boolean insertAfter(String existingId, Task task) {
        if (containsId(task.id)) return false;
        
        TaskNode curr = head;
        while (curr != null) {
            if (curr.task.id.equals(existingId)) {
                TaskNode newNode = new TaskNode(task);
                newNode.next = curr.next;
                curr.next = newNode;
                size++;
                return true;
            }
            curr = curr.next;
        }
        return false; // 找不到 existingId
    }

    public int size() { return size; }

    public void printAll() {
        TaskNode curr = head;
        System.out.print("List (" + size + "): ");
        while (curr != null) {
            System.out.print(curr.task + " -> ");
            curr = curr.next;
        }
        System.out.println("null");
    }
}

public class LinkedTaskListSystem {
    public static void main(String[] args) {
        TaskLinkedList list = new TaskLinkedList();
        list.addLast(new Task("T1", "備份資料"));
        list.addFirst(new Task("T0", "開機檢查"));
        list.addLast(new Task("T1", "重複任務")); // 會被拒絕
        list.printAll();

        list.insertAfter("T0", new Task("T0.5", "啟動服務"));
        list.printAll();

        list.removeById("T0"); // 刪除 head
        list.removeById("T1"); // 刪除 tail
        list.removeById("T99"); // 刪除不存在
        list.printAll();
    }
}