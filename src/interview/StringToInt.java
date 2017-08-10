package interview;

public class StringToInt {

	public static String i = "-1001213121";
	public static int count = 100000000;

	public static void main(String[] args) {
		print(new String2Int1());
		print(new String2Int2());
		print(new String2Int3());
	}

	public static interface String2Int {
		int string2int(String str);
	}

	private static void print(String2Int str2Int) {
		long startTime = System.currentTimeMillis();
		int intValue = 0;
		for (int j = 0; j < count; ++j) {
			intValue = str2Int.string2int(i);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("1the time is " + (endTime - startTime) + ", strValue = " + intValue);
	}

	public static class String2Int1 implements String2Int {
		@Override
		public int string2int(String str) {
			int value = 0;
			int pow10 = 1;
			for (int j = str.length() - 1; j >= 0; --j) {
				char charValue = str.charAt(j);
				if (charValue == '-' && j == 0) {
					value = -value;
					break;
				} else if (charValue == '+' && j == 0) {
					break;
				}
				if (charValue < '0' || charValue > '9') {
					throw new RuntimeException("error");
				}
				value = value + (charValue - '0') * pow10;
				pow10 = pow10 << 1 + pow10 << 3;
			}
			return value;
		}
	}

	public static class String2Int2 implements String2Int {
		@Override
		public int string2int(String str) {
			int result = 0;
			boolean negative = false;
			int i = 0, len = str.length();
			int digit;
			char firstChar = str.charAt(0);
			if (firstChar == '-') {
				negative = true;
				i++;
			} else if (firstChar == '+') {
				i++;
			}
			while (i < len) {
				char charValue = str.charAt(i++);
				if (charValue < '0' || charValue > '9') {
					throw new RuntimeException("error");
				}
				digit = charValue - '0';
				result *= 10;
				result += digit;
			}
			return negative ? -result : result;
		}
	}

	public static class String2Int3 implements String2Int {
		@Override
		public int string2int(String str) {
			return Integer.parseInt(str);
		}
	}

}
