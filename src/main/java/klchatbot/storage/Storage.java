package klchatbot.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;

import klchatbot.task.FixedDuration;
import klchatbot.task.Deadline;
import klchatbot.task.Event;
import klchatbot.task.Task;
import klchatbot.task.TaskList;
import klchatbot.task.Todo;

public class Storage {
    private static final Path DATA_DIR = Path.of("data");
    private static final Path DATA_FILE = DATA_DIR.resolve("tasks");
    private static final Path TEMP_FILE = DATA_DIR.resolve("tasks.tmp");

    /**
     * Loads tasks from the data file.
     * Handles the case where the file or directory doesn't exist.
     * Handles corrupted data by skipping invalid lines.
     * Cleans up any leftover temporary files from crashed saves.
     *
     * @return a list of tasks loaded from the file, or an empty list if file doesn't exist
     */
    public static TaskList loadTasks() {
        cleanupTempFile();

        TaskList tasks = new TaskList();

        if (!Files.exists(DATA_FILE)) {
            return tasks;
        }

        try (BufferedReader reader = Files.newBufferedReader(DATA_FILE, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }

                try {
                    Task task = parseTaskFromLine(line);
                    if (task != null) {
                        tasks.add(task);
                    }
                } catch (Exception e) {
                    System.err.println("Warning: Skipping corrupted task data: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Warning: Failed to load tasks from file: " + e.getMessage());
        }

        return tasks;
    }

    /**
     * Saves all tasks to the data file using atomic file operations.
     * Writes to a temporary file first, then atomically renames it to replace the original.
     * This ensures data integrity in case of crashes during writing.
     * Creates the data directory if it doesn't exist.
     *
     * @param tasks the list of tasks to save
     */
    public static void saveTasks(TaskList tasks) {
        assert tasks != null : "tasks must not be null";
        try {
            Files.createDirectories(DATA_DIR);

            try (BufferedWriter writer = Files.newBufferedWriter(TEMP_FILE, StandardCharsets.UTF_8)) {
                for (Task task : tasks) {
                    writer.write(taskToFileLine(task));
                    writer.newLine();
                }
            }

            moveTempToData();
        } catch (IOException e) {
            System.err.println("Error: Failed to save tasks to file: " + e.getMessage());
            cleanupTempFile();
        }
    }

    private static void cleanupTempFile() {
        try {
            if (Files.deleteIfExists(TEMP_FILE)) {
                System.err.println("Info: Cleaned up leftover temporary file: " + TEMP_FILE);
            }
        } catch (IOException e) {
            System.err.println("Warning: Could not delete leftover temporary file: " + TEMP_FILE);
        }
    }

    private static void moveTempToData() throws IOException {
        try {
            Files.move(
                    TEMP_FILE,
                    DATA_FILE,
                    StandardCopyOption.REPLACE_EXISTING,
                    StandardCopyOption.ATOMIC_MOVE
            );
        } catch (AtomicMoveNotSupportedException e) {
            Files.move(TEMP_FILE, DATA_FILE, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    private static Task parseTaskFromLine(String line) {
        assert line != null : "line must not be null";
        String[] parts = line.split("\\|", -1);

        if (parts.length < 3) {
            throw new IllegalArgumentException("Invalid task format");
        }

        String type = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();

        Task task;

        switch (type) {
            case "T":
                task = new Todo(description);
                break;
            case "D":
                if (parts.length < 4) {
                    throw new IllegalArgumentException("Invalid Deadline format");
                }
                String by = parts[3].trim();
                LocalDateTime byDateTime = LocalDateTime.parse(by);
                task = new Deadline(description, byDateTime);
                break;
            case "E":
                if (parts.length < 5) {
                    throw new IllegalArgumentException("Invalid Event format");
                }
                String from = parts[3].trim();
                String to = parts[4].trim();
                LocalDateTime fromDateTime = LocalDateTime.parse(from);
                LocalDateTime toDateTime = LocalDateTime.parse(to);
                task = new Event(description, fromDateTime, toDateTime);
                break;
            case "F":
                if (parts.length < 4) {
                    throw new IllegalArgumentException("Invalid FixedDuration format");
                }
                int durationMinutes = Integer.parseInt(parts[3].trim());
                task = new FixedDuration(description, durationMinutes);
                break;
            default:
                throw new IllegalArgumentException("Unknown task type: " + type);
        }

        if (isDone) {
            task.markDone();
        }

        return task;
    }

    private static String taskToFileLine(Task task) {
        assert task != null : "task must not be null";
        String type = task.getTypeIcon();
        int isDone = task.isDone() ? 1 : 0;

        StringBuilder line = new StringBuilder();
        line.append(type).append("|").append(isDone).append("|").append(task.getDescription());

        if (task instanceof Deadline deadline) {
            line.append("|").append(deadline.getBy().toString());
        } else if (task instanceof Event event) {
            line.append("|").append(event.getFrom().toString()).append("|").append(event.getTo().toString());
        } else if (task instanceof FixedDuration fixedDuration) {
            line.append("|").append(fixedDuration.getDurationMinutes());
        }

        return line.toString();
    }
}
