package cz.spse.projektant_pata.c301;

//Only works as a counter in the console
public class TimerDaemon implements Runnable{
    int seconds = 0;

    public int getSeconds() {
        return seconds;
    }
    @Override
    public void run() {
        System.out.println("\uD83D\uDE08 Časový démon se probudil");
        try {
            while (true) {
                Thread.sleep(1000);
                System.out.println("⏱️ Time: " + ++seconds + "s");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
