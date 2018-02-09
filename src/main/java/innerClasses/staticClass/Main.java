package innerClasses.staticClass;

/**
 * Created by user on 2/9/18.
 */
public class Main {
    public static void main(String[] args) {
        OutterClass.MyStaticInner ic = new OutterClass.MyStaticInner();
        ic.fooA();
        ic.fooB();
        OutterClass.MyStaticInner.fooB();
    }
}
