import java.util.ArrayList;
import java.util.List;

public class TaskDemo {
    public static void main(String...args){
        final Task task1 = new Task() {
            @Override
            public int getTaskId() {
                return 1;
            }

            @Override
            public List<Task> getDependencyList() {
                //Pass an empty list if there are no dependent tasks
                List<Task> dependentTasks = new ArrayList<Task>();
                return dependentTasks;
            }

            @Override
            public void action() {
                System.out.println("Task1");
            }
        };

        final Task task2 = new Task() {
            @Override
            public int getTaskId() {
                return 2;
            }

            @Override
            public List<Task> getDependencyList() {
                List<Task> dependentTasks = new ArrayList<Task>();
                //dependentTasks.add(task1);
                return dependentTasks;
            }

            @Override
            public void action() {

                try{
                    Thread.sleep(10000);
                }catch (InterruptedException e){

                }

                System.out.println("Task2");
            }
        };

       final Task task3 = new Task() {
            @Override
            public int getTaskId() {
                return 3;
            }

            @Override
            public List<Task> getDependencyList() {
                List<Task> dependentTasks = new ArrayList<Task>();
                dependentTasks.add(task1);
                dependentTasks.add(task2);
                return dependentTasks;
            }

            @Override
            public void action() {
                System.out.println("Task3");
            }
        };

       final Task task4 = new Task() {
            @Override
            public int getTaskId() {
                return 4;
            }

            @Override
            public List<Task> getDependencyList() {
                List<Task> dependentTasks = new ArrayList<Task>();
                dependentTasks.add(task3);
                return dependentTasks;
            }

            @Override
            public void action() {
                System.out.println("Task4");
            }
        };
        final Task task5 = new Task() {
            @Override
            public int getTaskId() {
                return 5;
            }

            @Override
            public List<Task> getDependencyList() {
                //Pass an empty list if there are no dependent tasks
                List<Task> dependentTasks = new ArrayList<Task>();
                dependentTasks.add(task1);
                //dependentTasks.add(task3);
                return dependentTasks;
            }

            @Override
            public void action() {
                System.out.println("Task5");
            }
        };

        final Task task6 = new Task() {
            @Override
            public int getTaskId() {
                return 6;
            }

            @Override
            public List<Task> getDependencyList() {
                //Pass an empty list if there are no dependent tasks
                List<Task> dependentTasks = new ArrayList<Task>();
                dependentTasks.add(task2);
                dependentTasks.add(task4);
                dependentTasks.add(task1);
                return dependentTasks;
            }

            @Override
            public void action() {

                System.out.println("Task6");
            }
        };
        final Task task7 = new Task() {
            @Override
            public int getTaskId() {
                return 7;
            }

            @Override
            public List<Task> getDependencyList() {
                //Pass an empty list if there are no dependent tasks
                List<Task> dependentTasks = new ArrayList<Task>();
                dependentTasks.add(task5);
                dependentTasks.add(task2);
                return dependentTasks;
            }

            @Override
            public void action() {
                System.out.println("Task7");
            }
        };
        List<Task> tasks = new ArrayList<Task>();
        tasks.add(task1);
        tasks.add(task7);
        tasks.add(task6);
        tasks.add(task2);
        tasks.add(task3);

        tasks.add(task4);
        tasks.add(task5);


        TaskExecutionService taskExecutionService = new TaskExecutionService();
        taskExecutionService.submit(tasks);
        System.out.println("Done!!!!");


    }
}
