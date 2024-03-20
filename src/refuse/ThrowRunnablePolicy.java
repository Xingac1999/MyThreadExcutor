package refuse;

public class ThrowRunnablePolicy implements RefuseStrategy {
    @Override
    public void refuse(Runnable runnable) {
        System.out.println("throw the runnable");
        return;
    }
}
