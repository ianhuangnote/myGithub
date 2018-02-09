package lambda;

import java.util.ArrayList;
import java.util.List;

public class ExampleLambda {
	
	public static void main(String[] args) {
		new ExampleLambda().test();
	}
	
	private void test() {
		List<AccSec> data = this.genData();
		data.forEach(d -> System.out.println("** " + d.getLockout() + ", " + d.getPassExpired()));
		System.out.println("---------------------");
		this.calculate(data);
		System.out.println("\n\n");
		List<AccSec> data2 = this.genData2();
		data2.forEach(d -> System.out.println("** " + d.getLockout() + ", " + d.getPassExpired()));
		System.out.println("---------------------");
		this.calculate(data2);
		System.out.println("\n\n");
		List<AccSec> data3 = this.genData3();
		data3.forEach(d -> System.out.println("** " + d.getLockout() + ", " + d.getPassExpired()));
		System.out.println("---------------------");
		this.calculate(data3);
		
	}
	
	private void calculate(List<AccSec> data) {
		AccSec mergedProfile = new AccSec();
		
		int lockoutSum = data.stream().mapToInt(AccSec::getLockout).sum();
		mergedProfile.setLockout((lockoutSum == 0) ? 0 : data.stream().mapToInt(AccSec::getLockout).filter(n -> n > 0).min().getAsInt());
		
		
		int passExpiredSum = data.stream().mapToInt(AccSec::getPassExpired).sum();
		mergedProfile.setPassExpired((passExpiredSum == 0) ? 0 : data.stream().mapToInt(AccSec::getPassExpired).filter(n -> n > 0).min().getAsInt());

		System.out.println(">> " + mergedProfile.getLockout() + ", " + mergedProfile.getPassExpired());
	}
	
	private List<AccSec> genData() {
		AccSec as00 = new AccSec();
		as00.setLockout(0);
		as00.setPassExpired(0);
		
		AccSec as110 = new AccSec();
		as110.setLockout(1);
		as110.setPassExpired(10);
		
		List<AccSec> result = new ArrayList<>();
		result.add(as00);
		result.add(as110);
		return result;
	}
	
	private List<AccSec> genData2() {
		AccSec as00 = new AccSec();
		as00.setLockout(0);
		as00.setPassExpired(8);
		
		AccSec as110 = new AccSec();
		as110.setLockout(0);
		as110.setPassExpired(1);
		
		List<AccSec> result = new ArrayList<>();
		result.add(as00);
		result.add(as110);
		return result;
	}
	
	private List<AccSec> genData3() {
		AccSec as00 = new AccSec();
		as00.setLockout(9);
		as00.setPassExpired(0);
		
		AccSec as110 = new AccSec();
		as110.setLockout(2);
		as110.setPassExpired(0);
		
		List<AccSec> result = new ArrayList<>();
		result.add(as00);
		result.add(as110);
		return result;
	}
	
	class AccSec {
		int lockout;
		int passExpired;
		
		int getLockout() {
			return lockout;
		}
		
		void setLockout(int lockout) {
			this.lockout = lockout;
		}
		
		int getPassExpired() {
			return passExpired;
		}
		
		void setPassExpired(int passExpired) {
			this.passExpired = passExpired;
		}
	}
}
