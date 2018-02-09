package innerClasses.regular;

/**
 * Created by user on 2/9/18.
 */
public class OutterClass2 {
    public static void main(String[] args) {
        OutterClass oc = new OutterClass();
        OutterClass.MyInnerClass3 ic = oc.new MyInnerClass3();
        ic.foo();
        
        OutterClass.MyInnerClass3 ic2 = new OutterClass().new MyInnerClass3();
        ic2.foo();
        
        new OutterClass().new MyInnerClass3().foo();
        
    }
}
