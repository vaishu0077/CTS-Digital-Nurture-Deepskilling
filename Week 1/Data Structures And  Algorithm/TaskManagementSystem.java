class Task {
    private String taskId;
    private String taskName;
    private String status;

    public Task(String taskId, String taskName, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
    }

    public String getTaskId() {
        return taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Task ID: " + taskId +
               ", Task Name: " + taskName +
               ", Status: " + status;
    }
}

class TaskNode {
    Task task;
    TaskNode next;

    public TaskNode(Task task) {
        this.task = task;
        this.next = null;
    }
}

class TaskLinkedList {
    private TaskNode head;

    public void addTask(Task task) {
        TaskNode newNode = new TaskNode(task);

        if (head == null) {
            head = newNode;
            System.out.println("Task added successfully.");
            return;
        }

        TaskNode current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
        System.out.println("Task added successfully.");
    }

    public Task searchTask(String taskId) {
        TaskNode current = head;

        while (current != null) {
            if (current.task.getTaskId().equals(taskId)) {
                return current.task;
            }
            current = current.next;
        }

        return null;
    }

    public void traverseTasks() {
        if (head == null) {
            System.out.println("No tasks available.");
            return;
        }

        TaskNode current = head;
        System.out.println("Task List:");
        while (current != null) {
            System.out.println(current.task);
            current = current.next;
        }
    }

    public boolean deleteTask(String taskId) {
        if (head == null) {
            System.out.println("Task list is empty.");
            return false;
        }

        if (head.task.getTaskId().equals(taskId)) {
            head = head.next;
            System.out.println("Task deleted successfully.");
            return true;
        }

        TaskNode current = head;
        while (current.next != null) {
            if (current.next.task.getTaskId().equals(taskId)) {
                current.next = current.next.next;
                System.out.println("Task deleted successfully.");
                return true;
            }
            current = current.next;
        }

        System.out.println("Task not found.");
        return false;
    }
}

public class TaskManagementSystem {
    public static void main(String[] args) {
        TaskLinkedList taskList = new TaskLinkedList();

        taskList.addTask(new Task("T101", "Prepare report", "Pending"));
        taskList.addTask(new Task("T102", "Fix bugs", "In Progress"));
        taskList.addTask(new Task("T103", "Deploy app", "Completed"));

        taskList.traverseTasks();

        System.out.println();
        Task found = taskList.searchTask("T102");
        if (found != null) {
            System.out.println("Search Result:");
            System.out.println(found);
        } else {
            System.out.println("Task not found.");
        }

        System.out.println();
        taskList.deleteTask("T102");
        taskList.traverseTasks();
    }
}