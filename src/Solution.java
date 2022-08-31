import java.util.LinkedHashMap;
import java.util.Map;

public class Solution {
	public static void main(String[] args) {
		LinkedHashMap<Integer, Integer> hm = new LinkedHashMap<>() {
			@Override
			protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
				return size() > 4;
			}
		};
	}
}