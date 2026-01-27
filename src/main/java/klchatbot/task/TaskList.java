package klchatbot.task;

import java.util.ArrayList;
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

    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task remove(int index) {
        return tasks.remove(index);
    }

    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }
}
