package interview;

public class IntToString {
	public static int i = -100121312;
	public static int count = 10000000;

	public static void main(String[] args) {
		print(new Int2String1());
		print(new Int2String2());
		print(new Int2String3());
		print(new Int2String4());
		print(new Int2String5());
		print(new Int2String6());
		print(new Int2String7());
	}

	private static void print(Int2String int2String) {
		long startTime = System.currentTimeMillis();
		String strValue = "";
		for (int j = 0; j < count; ++j) {
			strValue = int2String.int2String(i);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("1the time is " + (endTime - startTime) + ", strValue = " + strValue);
	}

	private static interface Int2String {
		String int2String(int intValue);
	}

	/**
	 *  直接先求得长度，然后从左向右求得各位的大小
	 * @author wa505
	 *
	 */
	private static class Int2String1 implements Int2String {
		public String int2String(int intValue) {
			if (intValue == 0) {
				return "0";
			}
			int length = 0;
			boolean sign = false;
			if (intValue < 0) {
				intValue = -intValue;
				sign = true;
				length++;
			}
			int k = 1;
			while (true) {
				if (intValue >= k) {
					length++;
					k = k * 10;
				} else {
					break;
				}
			}

			char[] holder = new char[length];
			int start = 0;
			if (sign) {
				holder[0] = '-';
				start = 1;
			}

			for (; start < length; ++start) {
				int count = (length - start - 1);
				int pow10 = 1;
				for (int i = 0; i < count; i++) {
					pow10 = pow10 * 10;
				}
				holder[start] = (char) ((intValue / Math.pow(10, count)) + '0');
				intValue = intValue % pow10;
			}
			return new String(holder);
		}
	}
	
	/**
	 * 直接从右向左， 或者说低位到高位进行逐个插入。最后插入符号位
	 * @author wa505
	 *
	 */
	private static class Int2String2 implements Int2String {
		public String int2String(int intValue) {
			char[] str = new char[0];
			boolean sign = false;
			if (intValue == 0) {
				return "0";
			}
			if (intValue < 0) {
				sign = true;
				intValue = -intValue;
			}
			while (true) {
				int leftValue = intValue % 10;
				char additionalChar = (char) (leftValue + '0');
				char[] old = str;
				str = new char[str.length + 1];
				str[0] = additionalChar;
				for (int i = 1; i < str.length; ++i) {
					str[i] = old[i - 1];
				}
				intValue = intValue / 10;
				if (intValue == 0) {
					break;
				}
			}
			if (sign) {
				char[] old = str;
				str = new char[str.length + 1];
				str[0] = '-';
				for (int i = 1; i < str.length; ++i) {
					str[i] = old[i - 1];
				}
			}
			return new String(str);
		}
	}

	/**
	 * 先斤算长度，计算长度时与10的倍数比较。再从右向左进行运算各位的值。
	 * @author wa505
	 *
	 */
	private static class Int2String3 implements Int2String {
		public String int2String(int intValue) {
			if (intValue == 0) {
				return "0";
			}
			int length = 0;
			boolean sign = false;
			if (intValue < 0) {
				intValue = -intValue;
				sign = true;
				length++;
			}
			int k = 1;
			while (true) {
				if (intValue >= k) {
					length++;
					k = k <<1 + k<<3;
				} else {
					break;
				}
			}
			char[] holder = new char[length];
			int start = 0;
			if (sign) {
				holder[0] = '-';
				start = 1;
			}
			for (int i = length - 1; i >= start; --i) {
				holder[i] = (char) ((intValue % 10) + '0');
				intValue = intValue / 10;
			}
			return new String(holder);
		}
	}
	
	/**
	 * 先斤算长度，再从右向左进行运算各位的值。计算长度采用除法求模。
	 * @author wa505
	 *
	 */
	private static class Int2String4 implements Int2String {
		public String int2String(int intValue) {
			int length = 0;
			boolean sign = false;
			if (intValue < 0) {
				sign = true;
				intValue = -intValue;
			}
			int k = intValue;
			while (k >= 0) {
				length++;
				k = k / 10;
				if (k == 0) {
					break;
				}
			}
			char[] charArray;
			if (sign) {
				length = length + 1;
				charArray = new char[length];
				charArray[0] = '-';
			} else {
				charArray = new char[length];
			}
			int m = intValue;
			int index = length - 1;
			while (true) {
				int leftValue = m % 10;
				charArray[index] = (char) (leftValue + '0');
				index--;
				m = m / 10;
				if (m == 0) {
					break;
				}
			}
			return new String(charArray);
		}
	}

	private static class Int2String5 implements Int2String {
		public String int2String(int intValue) {
			int position = 10;
			char[] buf = new char[11];
			if (intValue < 0) {
				intValue = -intValue;
				buf[0] = '-';
			}
			while (true) {
				int leftValue = intValue % 10;
				buf[position] = (char) (leftValue + '0');
				intValue = intValue / 10;
				if (intValue == 0) {
					break;
				} else {
					position--;
				}
			}
			return new String(buf, position, buf.length - position);
		}
	}
	
	public static char[] buf = new char[11];
	private static class Int2String7 implements Int2String {
		public synchronized String int2String(int intValue) {
			int position = 10;
			if (intValue < 0) {
				intValue = -intValue;
				buf[0] = '-';
			}
			while (true) {
				int leftValue = intValue % 10;
				buf[position] = (char) (leftValue + '0');
				intValue = (intValue >>1)/5;
				if (intValue == 0) {
					break;
				} else {
					position--;
				}
			}
			return new String(buf, position, buf.length - position);
		}
	}

	private static class Int2String6 implements Int2String {
		public String int2String(int intValue) {
			return String.valueOf(intValue);
		}
	}
	
  
}
