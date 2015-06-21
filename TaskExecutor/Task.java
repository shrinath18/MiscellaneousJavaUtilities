import java.util.List;

public interface Task {
    public int getTaskId();
    public List<Task> getDependencyList();
    public void action();
}
