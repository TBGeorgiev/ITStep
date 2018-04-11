import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class VideoDurations {
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		List<String> times = new ArrayList<>();
		
		String time = "";
		
		while (!(time = reader.readLine()).equals("End")) {
			times.add(time);
		}
		
		
		int hours = 0;
		int minutes = 0;
		int seconds = 0;
		
		for (int i = 0; i < times.size(); i++) {
			String[] split = times.get(i).split(":");
			int currentHours = 0;
			int currentMin = Integer.parseInt(split[0]);
			int currentSec = Integer.parseInt(split[1]);
			if (split.length > 2) {
				currentHours = Integer.parseInt(split[2]);
			}
			hours += currentHours;
			if (minutes + currentMin > 59) {
				hours++;
				minutes = (currentMin + minutes) - 60;
			} else {
				minutes += currentMin;
			}
			if (seconds + currentSec > 59) {
				minutes++;
				seconds = (currentSec + seconds) - 60;
			} else {
				seconds += currentSec;
			}
			
		}
		
		if (hours > 0) {
			System.out.printf("%d:%d:%02d",hours, minutes, seconds);
		} else {
			System.out.printf("%d:%02d", minutes, seconds);			
		}
		
	}

}
