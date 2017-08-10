package interview;

import java.nio.CharBuffer;

public class BigNumberAdd {

	public static void main(String[] args) {
		String value1 = "155";
		String value2 = "555";
		String result = add(value1, value2);

		String result2 = add2(value1, value2);
		System.out.println("the scan result is " + result);
		System.out.println("the scan result2 is " + result2);
	}

	public static String add2(String strNumber1, String strNumber2) {
		int str1length = strNumber1.length();
		int str2Length = strNumber2.length();
		int max = Math.max(str1length, str2Length);
		char result[] = new char[max + 1];
		for (int i = 0; i < max; ++i) {
			int lastIndex1 = str1length - 1 - i;
			int lastIndex2 = str2Length - 1 - i;
			int resultIndex = result.length - 1 - i;
			int value;
			if (result[resultIndex] == 0) {
				value = 0;
			} else {
				value = result[resultIndex] - '0';
			}

			if (strNumber1.charAt(lastIndex1) >= '0' && strNumber1.charAt(lastIndex1) <= '9') {
				if (lastIndex1 >= 0) {
					value += strNumber1.charAt(lastIndex1) - '0';
				}
			} else {
				throw new RuntimeException("error");
			}
			if (strNumber2.charAt(lastIndex1) >= '0' && strNumber1.charAt(lastIndex1) <= '9') {
				if (lastIndex2 >= 0) {
					value += strNumber2.charAt(lastIndex2) - '0';
				}
			} else {
				throw new RuntimeException("error");
			}
			int overFlow = value / 10;
			value = value % 10;
			result[resultIndex] = (char) ((char) value + (char) '0');
			result[resultIndex - 1] = (char) ((char) overFlow + (char) '0');
		}
		if (result[0] == '0') {
			return new String(result, 1, result.length - 1);
		} else {
			return new String(result);
		}
	}

	public static String add(String strNumber1, String strNumber2) {
		int max = Math.max(strNumber1.length(), strNumber2.length());
		int overFlowValue = 0;
		char result[] = new char[max + 1];
		for (int i = 0; i < max; ++i) {
			int str1length = strNumber1.length();
			int str2Length = strNumber2.length();
			int index1 = i - (max - str1length);
			int index2 = i - (max - str2Length);
			int intValue1 = 0;
			int intValue2 = 0;
			if (index1 >= 0) {
				intValue1 = strNumber1.charAt(index1) - '0';
			}
			if (index2 >= 0) {
				intValue2 = strNumber2.charAt(index2) - '0';
			}
			int overFlow = intValue1 + intValue2;
			for (int j = i; j >= 0; --j) {
				if (overFlow == 0) {
					break;
				}
				if (result[j + 1] == 0) {
					result[j + 1] = '0';
				}
				int preValue = result[j + 1] - '0';
				preValue = preValue + overFlow;
				overFlow = preValue / 10;
				preValue = preValue % 10;
				result[j + 1] = (char) ((char) preValue + '0');
			}
			if (overFlow != 0) {
				overFlowValue = overFlowValue + overFlow;
			}
		}
		if (overFlowValue != 0) {
			result[0] = (char) ((char) overFlowValue + '0');
			return String.valueOf(result, 0, result.length);
		} else {
			return String.valueOf(result, 1, result.length - 1);
		}
	}
}
