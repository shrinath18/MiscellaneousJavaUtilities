import java.util.List;

public class TaskExecutionService {
    TaskScheduler taskScheduler;
    public TaskExecutionService(){
        taskScheduler = new TaskScheduler();
    }
    public void submit(List<Task> tasks){
        taskScheduler.scheduleAndExecute(tasks);
        System.out.println("Completed the execution!!!");
    }
}
