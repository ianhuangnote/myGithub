package lambda;

/**
 * Created by user on 2/9/18.
 */
public class ShorterExpression {
    private ShorterExpression() {
        ShorterExpressionInter1 s1 = this::printOnce;
        s1.doStringWork("哈囉");
    }
    
    public static void main(String[] args) {
        ShorterExpressionInter1 s1 = ShorterExpression::printTwice;
        s1.doStringWork("嗨");
        new ShorterExpression();
        
        ShorterExpressionInter2 s2 = Math::pow;
        s1.doStringWork(String.valueOf(s2.doComputeWork(2.5f, 2)));
    }
    
    private static void printTwice(String s) {
        System.out.print(s);
        System.out.println(s);
    }
    
    private void printOnce(String s) {
        System.out.println(s);
    }
}
