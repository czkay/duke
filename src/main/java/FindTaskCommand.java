import java.util.List;

/**
 * A Command to find tasks from the task list based on a keyword.
 */
public class FindTaskCommand extends Command {
    private String keyword;

    public FindTaskCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command to find tasks from the task list based on a keyword.
     *
     * @param tasks The task list.
     * @param ui The ui that handles user output.
     * @param storage The storage that handles saving and loading the task list.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        List<Task> matchedTasks = tasks.getMatchedTasks(keyword);
        if (matchedTasks.size() == 0) {
            return "There are no tasks in your list matching your keyword!";
        } else {
            StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
            for (int i = 1; i <= matchedTasks.size(); i++) {
                sb.append(String.format("%d. %s\n", i, matchedTasks.get(i - 1)));
            }
            return sb.toString().trim();
        }
    }

    /**
     * Returns a boolean value signalling whether the program should exit.
     *
     * @return A boolean value indicating whether the program should exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
