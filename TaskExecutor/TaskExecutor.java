import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskExecutor implements TaskObservable{
    ExecutorService executorService;
    List<TaskScheduler> taskSchedulers;
    public TaskExecutor(){
        executorService = Executors.newFixedThreadPool(10);
        taskSchedulers = new ArrayList<TaskScheduler>();
    }

    @Override
    public void subscribeForTaskCompletion(TaskScheduler taskScheduler) {
        taskSchedulers.add(taskScheduler);
    }

    public void execute(Task task){
        Runnable runnable = transform(task);
        //TODO Check how you can handle failures
        executorService.submit(runnable);
        System.out.println("aSYNCHRONOUS" + task.getTaskId());
        //for (TaskScheduler taskScheduler : taskSchedulers){
          //  taskScheduler.notifyTaskCompletion(task,0);
        //}
    }

    public Runnable transform(final Task task){
        return new Runnable() {
            @Override
            public void run() {
                task.action();
                for (TaskScheduler taskScheduler : taskSchedulers){
                  taskScheduler.notifyTaskCompletion(task,0);
                }
            }
        };
    }

    public void shutDown(){
        this.executorService.shutdown();
    }
}
