import java.util.ArrayList;
import java.util.List;

public class ExampleSublist {
	
	public static void main(String[] args) {
		List<String> l = new ArrayList();
		l.add("1");
		l.add("2");
		l.add("3");
		l.add("4");
		System.out.println(l);
		System.out.println("----------------------");
		System.out.println(l.subList(0, 0));
		System.out.println(l.subList(0, 1));
		System.out.println(l.subList(0, 2));
		
		l.subList(0, 1).clear();
		
		System.out.println(l.toString());
		
		l.subList(0, 0).clear();
		
		System.out.println(l.toString());
		
		l.subList(0, 2).clear();
		
		System.out.println(l.toString());
	}
}
