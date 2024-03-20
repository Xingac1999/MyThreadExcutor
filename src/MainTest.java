public class MainTest {
    public static void main(String[] args) {
        MyThreadExcutor excutor = new MyThreadExcutor(5);

        for (int i = 0; i < 1000; i++) {
            int finalI = i;
//            Runnable runnable = () -> System.out.println(finalI + "  start " + Thread.currentThread());
//            runnable.run();
            excutor.execute(() -> System.out.println(finalI + "  start " + Thread.currentThread()));
        }

        excutor.start();

        for (int i = 1000; i < 2000; i++) {
            int finalI = i;
//            Runnable runnable = () -> System.out.println(finalI + "  start " + Thread.currentThread());
//            runnable.run();
            excutor.execute(() -> System.out.println(finalI + "  start " + Thread.currentThread()));
        }
    }
}
