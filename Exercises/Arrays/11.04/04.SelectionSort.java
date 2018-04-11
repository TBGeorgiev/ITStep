import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SelectionSort {
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		List<Integer> list = Arrays.stream(reader.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
		
		boolean isFinal = true;
		
		while (isFinal) {
			isFinal = false;
			for (int i = 0; i < list.size() - 1; i++) {
				if (list.get(i) > list.get(i + 1)) {
					int temp = list.get(i + 1);
					list.set(i + 1, list.get(i));
					list.set(i, temp);
					isFinal = true;
				}
			}
		}
		
		System.out.println(Arrays.asList(list).toString());
		
	}

}
