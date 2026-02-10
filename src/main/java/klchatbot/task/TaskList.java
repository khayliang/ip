package klchatbot.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Wrapper around the task list to centralize task operations.
 */
public class TaskList implements Iterable<Task> {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(Task... tasks) {
        assert tasks != null : "tasks must not be null";
        this.tasks = new ArrayList<>(Arrays.asList(tasks));
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int index) {
        assert index >= 0 && index < tasks.size() : "index out of bounds: " + index;
        return tasks.get(index);
    }

    public void add(Task task) {
        assert task != null : "task must not be null";
        tasks.add(task);
    }

    public Task remove(int index) {
        assert index >= 0 && index < tasks.size() : "index out of bounds: " + index;
        return tasks.remove(index);
    }

    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }
}
