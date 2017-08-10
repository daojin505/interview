package interview;

public class QuickSort {

	public static void main(String[] args) {
		int[] list = new int[] { 12, 1, 8, 9, 1, 2, 4, 5, 3, 7, 11, 2 };
		quickSort(list);
		for (int item : list) {
			System.out.println(item);
		}

	}

	static void quickSort(int[] list) {

		int pivoit = quickSortPartition(list, 0, list.length - 1);
	}

	static int quickSortPartition(int[] list, int low, int high) {
		int originLow = low;
		int orignHigh = high;
		int pivoit = list[low];
		while (high > low) {
			while (high > low && list[high] >= pivoit) {
				--high;
			}
			list[low] = list[high];
			while (high > low && list[low] <= pivoit) {
				++low;
			}
			list[high] = list[low];
		}
		list[low] = pivoit;
		if (low - 1 > originLow) {
			quickSortPartition(list, originLow, low - 1);
		}
		if (low + 1 < orignHigh) {
			quickSortPartition(list, low + 1, orignHigh);
		}
		return low;
	}
}
