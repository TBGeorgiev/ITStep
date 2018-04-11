import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SelectionSort {
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		List<Integer> list = Arrays.stream(reader.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
		
		List<Integer> sortedList = new ArrayList<>();
		
		
		for (int i = 0; i < list.size(); i++) {
			int min = list.get(i);
			int indexToRemove = 0;
			for (int j = 1; j < list.size(); j++) {
				if (min >= list.get(j)) {
					min = list.get(j);
					indexToRemove = j;
				}
			}
			i = -1;
			list.remove(indexToRemove);
			sortedList.add(min);
		}
		
		System.out.println(Arrays.asList(sortedList).toString());
		
	}

}
