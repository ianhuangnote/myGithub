package innerClasses.anonymous;

/**
 * Created by user on 2/9/18.
 */
public class Main {
    public static void main(String[] args) {
        Animal a = new Animal() {
            
            @Override
            public void skill() {
                System.out.println("我會狩獵!");
                
            }
            
            @Override
            public void move() {
                System.out.println("我會跑步!");
                
            }
        };
        a.move();
        a.skill();
    }
}
