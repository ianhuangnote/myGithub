package time;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * Created by user on 2/8/18.
 */
public class ExampleTimeManipulate {
    
    
    
    public static void main(String[] args) {
        TimeZone utcTZ = TimeZone.getTimeZone("UTC");
        Calendar utcCal = Calendar.getInstance(utcTZ);
        
        long lastLogin = 1517908880293L;
        long lastLogin2 = 1417906880293L;
        long currentTime = utcCal.getTimeInMillis();
        long duration = currentTime - lastLogin2;
        
        long day = TimeUnit.DAYS.convert(currentTime - lastLogin2, TimeUnit.MILLISECONDS);
    
        System.out.println("duration long=" + duration + ", day=" + day);
    }
}
