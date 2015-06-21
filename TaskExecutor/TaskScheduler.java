import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskScheduler implements TaskObserver{
    TaskExecutor taskExecutor;
    List<Task> completedTasks;
    List<Task> tasksToComplete;
    int noOfTasks;
    boolean isComplete = false;
    //Initialise the task executor
    public TaskScheduler(){
        taskExecutor = new TaskExecutor();
        completedTasks = Collections.synchronizedList(new ArrayList<Task>());
        //taskExecutor.subscribeForTaskCompletion(this);
    }
    public void scheduleAndExecute(List<Task> tasks){
        taskExecutor.subscribeForTaskCompletion(this);
        tasksToComplete = Collections.synchronizedList(tasks);
        System.out.print("Taskstocomplete!!"+tasksToComplete.size());
        noOfTasks = tasks.size();
        startTasks(tasks);
        //block till all the tasks are completed
        //while (tasksToComplete.size() != 0 && completedTasks.size()!= noOfTasks){
          //  System.out.println("completedtasks"+completedTasks.size());

        //}
        while(isComplete == false){
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){

            }
            //System.out.println("iscomplete"+isComplete);
        }
        System.out.println("Completed all tasks!!!");
        System.out.println("Shutting down executors");
        taskExecutor.shutDown();
    }

    @Override
    public synchronized void notifyTaskCompletion(Task task, int status) {
        //TODO Check for status and abort the remaining dependent tasks
        completedTasks.add(task);
        System.out.println("TaskCompleted:"+task.getTaskId());
        if(completedTasks.size() == noOfTasks)
            isComplete = true;
        for(int i = 0;i<tasksToComplete.size();i++){

            Task t = tasksToComplete.get(i);
            System.out.println("TaskId"+ t.getTaskId());
            System.out.println("notifyForTask"+task.getTaskId());
            //Check if task has not completed
            if(!completedTasks.contains(t)){
                //Check if there are no dependencies
                if(completedTasks.containsAll(t.getDependencyList())){
                    taskExecutor.execute(t);
                    System.out.println("Came here!!"+task.getTaskId());
                    tasksToComplete.remove(t);
                }
            }
            System.out.println("tas!!!!"+tasksToComplete.size());
        }
    }

    public void startTasks(List<Task> tasks){
        //find the first task to be completed,Other tasks will be run when notifyTaskCompletion occurs(Check if we can improve)
        for(Task t : tasks){
            if(t.getDependencyList().size() == 0){
                taskExecutor.execute(t);
                tasksToComplete.remove(t);
                return;
            }
        }
    }
}
