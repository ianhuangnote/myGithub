package innerClasses.staticClass;

/**
 * Created by user on 2/9/18.
 */
public class OutterClass {
    static class MyStaticInner {
        //non-static
        public void fooA() {
            System.out.println("Hello!");
        }
        
        //static
        public static void fooB() {
            System.out.println("Hi~");
        }
    }
}
