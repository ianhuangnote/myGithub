package lambda;

/**
 * Created by user on 2/9/18.
 */
public class EfficientLambdaInAnomymousClass {
    public static void main(String[] args) {
        new EfficientLambdaInAnomymousClass().test();
    }
    
    private void test() {
        Runnable r1 = () -> System.out.println("r1: " + this.getClass());
        
        Runnable r2 = new Runnable(){
            public void run(){
                System.out.println("r2: " + this.getClass());
            }
        };
        
        new Thread(r1).start();
        new Thread(r2).start();
    }
}
