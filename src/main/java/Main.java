import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		String t = "[1234, 5678, 91000]";
		String t2 = "[2234]";
		String[] tA = t.replace("[", "").replace("]", "").split(",");
		Arrays.stream(tA).forEach(s -> System.out.println(s.trim()));
		System.out.println("" + tA.toString());
		System.out.println("" + tA[0]);
		
		
	}
}
