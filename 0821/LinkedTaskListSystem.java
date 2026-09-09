class Task {
    String id;
    String description;

    Task(String id, String description) {
        this.id = id;
        this.description = description;
    }
    
    @Override
    public String toString() {
        return "[" + id + "] " + description;
    }
}

class TaskNode {
    Task task;
    TaskNode next;

    TaskNode(Task task) {
        this.task = task;
    }
}

class TaskLinkedList {
    private TaskNode head;
    private int size;

    boolean addFirst(Task task) {
        if (findById(task.id) != null) return false;
        TaskNode node = new TaskNode(task);
        node.next = head;
        head = node;
        size++;
        return true;
    }

    boolean addLast(Task task) {
        if (findById(task.id) != null) return false;
        TaskNode node = new TaskNode(task);
        if (head == null) {
            head = node;
        } else {
            TaskNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = node;
        }
        size++;
        return true;
    }

    Task findById(String id) {
        TaskNode current = head;
        while (current != null) {
            if (current.task.id.equals(id)) return current.task;
            current = current.next;
        }
        return null;
    }

    boolean removeById(String id) {
        if (head == null) return false;
        if (head.task.id.equals(id)) {
            head = head.next;
            size--;
            return true;
        }
        TaskNode current = head;
        while (current.next != null) {
            if (current.next.task.id.equals(id)) {
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    boolean insertAfter(String existingId, Task task) {
        if (findById(task.id) != null) return false;
        TaskNode current = head;
        while (current != null) {
            if (current.task.id.equals(existingId)) {
                TaskNode node = new TaskNode(task);
                node.next = current.next;
                current.next = node;
                size++;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    int size() { return size; }

    void printAll() {
        System.out.print("Tasks (" + size + "): ");
        TaskNode current = head;
        while (current != null) {
            System.out.print(current.task + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
}

public class LinkedTaskListSystem {
    public static void main(String[] args) {
        TaskLinkedList list = new TaskLinkedList();
        list.addLast(new Task("T01", "Design"));
        list.addFirst(new Task("T02", "Plan"));
        list.addLast(new Task("T03", "Code"));
        
        System.out.println("重複加入 T02: " + list.addFirst(new Task("T02", "Test")));
        list.printAll();

        list.insertAfter("T01", new Task("T04", "Review"));
        list.printAll();

        System.out.println("刪除 T02 (Head): " + list.removeById("T02"));
        System.out.println("刪除 T04 (Middle): " + list.removeById("T04"));
        System.out.println("刪除 T03 (Tail): " + list.removeById("T03"));
        System.out.println("刪除不存在 T99: " + list.removeById("T99"));
        
        list.printAll();
    }
}