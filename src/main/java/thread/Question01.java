package thread;

/**
 * Created by user on 2/9/18.
 */
public class Question01 {
    public static void main(String[] args) {
        new Question01().goInLambda();
        
    }
    
    public void go() {
        Runnable r = new Runnable() {
            public void run() {
                System.out.print("OOO");
            }
        };
        
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
    }
    
    public void goInLambda() {
        Runnable r = () -> System.out.print("000");
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
    }
    
    //Q: What is the result?
    //A: OOOOOO
}
