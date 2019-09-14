import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Stores the tasks of the user.
 */
public class TaskList implements Serializable {
    private List<Task> taskList;
    private List<Reminder> reminders = new ArrayList<>();

    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Returns the number of tasks present in the task list.
     *
     * @return The number of tasks present in the task list.
     */
    int size() {
        return taskList.size();
    }

    /**
     * Returns the task present in the specific index of the task list.
     *
     * @param index The target index.
     * @return The task present in the specific index of the task list.
     */
    Task get(int index) {
        return taskList.get(index);
    }

    /**
     * Marks as complete the task present in the specific index of the task list.
     *
     * @param index The target index.
     */
    void markTaskAsDone(int index) throws OutOfBoundsDeletionException {
        try {
            taskList.get(index).markAsDone();
        } catch (IndexOutOfBoundsException e) {
            throw new OutOfBoundsDeletionException("No task with index number " + (index + 1) + " on your task list!");
        }
    }

    /**
     * Deletes the task present in the specific index of the task list and returns it.
     *
     * @param index The target index.
     * @return The deleted task.
     * @throws OutOfBoundsDeletionException If the target index is not present in the task list.
     */
    Task deleteTask(int index) throws OutOfBoundsDeletionException {
        try {
            return taskList.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new OutOfBoundsDeletionException("No task with index number " + (index + 1) + " on your task list!");
        }
    }

    /**
     * Adds a task to the task list.
     *
     * @param newTask The new task to be added.
     */
    void addTask(Task newTask) {
        taskList.add(newTask);
    }

    /**
     * Gets all tasks that have descriptions containing the keyword the user has inputted.
     *
     * @param keyword The word or phrase that the user has inputted.
     * @return The list of matched tasks.
     */
    List<Task> getMatchedTasks(String keyword) {
        List<Task> matchedTasks = new ArrayList<>();
        for (Task task: taskList) {
            if (task.toString().contains(keyword)) {
                matchedTasks.add(task);
            }
        }
        return matchedTasks;
    }

    /**
     * Gets reminders of the user's upcoming tasks for the user.
     *
     * @return A message containing all reminders for the user's upcoming tasks.
     */
    String getReminders() {
        StringBuilder sb = new StringBuilder();
        if (reminders.isEmpty()) {
            sb.append("There are no reminders right now!");
        } else {
            sb.append("Here are your upcoming tasks: ");
            for (Reminder reminder : reminders) {
                sb.append("\n" + reminder.toString());
            }
        }
        return sb.toString();
    }

    /**
     * Creates reminders of the user's upcoming tasks.
     */
    void createReminders() {
        for (Task task : taskList) {
            Optional<Reminder> potentialReminder = Reminder.createReminderIfValid(task);
            potentialReminder.ifPresent(reminder -> reminders.add(reminder));
        }
    }

}
