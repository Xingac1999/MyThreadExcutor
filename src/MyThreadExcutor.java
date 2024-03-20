import refuse.RefuseStrategy;
import refuse.ThrowRunnablePolicy;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class MyThreadExcutor {

    private LinkedBlockingQueue<Runnable> tasks = new LinkedBlockingQueue<>(100);

    private RefuseStrategy refuseStrategy;

    private Integer coolPoolSize;

    public MyThreadExcutor(Integer coolPoolSize) {
        this(coolPoolSize,new ThrowRunnablePolicy());
    }

    public MyThreadExcutor(Integer coolPoolSize,RefuseStrategy refuseStrategy) {
        this.coolPoolSize = coolPoolSize;
        this.refuseStrategy = refuseStrategy;
    }

    public void start() {
        for (Integer i = 0; i < coolPoolSize; i++) {
            addWorker().start();
        }
    }

    private Worker addWorker() {
        return new Worker();
    }

    public boolean existTask() {
        return (tasks != null && tasks.size() != 0);
    }

    public Runnable getTask() {
        return tasks.remove();
    }

    public void execute(Runnable runnable) {
        int capacity = tasks.remainingCapacity();
        System.out.println("capacity :" + capacity);
        if (capacity == 0) {
            refuseStrategy.refuse(runnable);
            return;
        }
        tasks.add(runnable);
    }

    private final class Worker extends Thread {

        @Override
        public void run() {
            while (existTask()) {
                Runnable task = getTask();
                task.run();
            }
        }
    }
}
