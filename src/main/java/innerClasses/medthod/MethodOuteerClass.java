package innerClasses.medthod;

/**
 * Created by user on 2/9/18.
 */
public class MethodOuteerClass {
    public static void main(String[] args) {
        new MethodOuteerClass().method1();
    }
    
    public void method1() {
        class MyInnerClass1 {
            void foo() {
                System.out.println("Hi~");
            }
        }
        MyInnerClass1 ic = new MyInnerClass1();
        ic.foo();
        class MyInnerClass2 {
            void foo() {
                System.out.println("Hi~");
            }
        }
    }
    
    public void method2() {
        class MyInnerClassA {
            void foo() {
                System.out.println("Hi2~");
            }
        }
        MyInnerClassA ic = new MyInnerClassA();
        ic.foo();
        
    }
}
