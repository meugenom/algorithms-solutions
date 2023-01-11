package leetcode.MultiplyStrings;

import java.math.BigInteger;

/***
 * Given two non-negative integers num1 and num2 represented as strings, return
 * the product of num1 and num2, also represented as a string.
 * Note: You must not use any built-in BigInteger library or convert the inputs
 * to integer directly.
 * 
 * Example 1:
 * Input: num1 = "2", num2 = "3"
 * Output: "6"
 * 
 * Example 2:
 * Input: num1 = "123", num2 = "456"
 * Output: "56088"
 */

public class MultiplyStrings {
	public static String multiply(String num1, String num2) {

		// get int number from num1
		int right = num1.length() - 1;
		int num1Int = helperToInt(num1, right, 0, 1);

		// get int number from num2
		right = num2.length() - 1;
		int num2Int = helperToInt(num2, right, 0, 1);
		int num1num2 = num1Int * num2Int;

		// multiple int to string
		String res = "";
		return helperToString(num1num2, res);
	}

	private static int helperToInt(String s, int right, int sum, int k) {
		int n = s.charAt(right) - '0';
		sum = sum + n * k;
		if (right > 0) {
			k = k * 10;
			right = right - 1;
			return helperToInt(s, right, sum, k);
		} else {
			return sum;
		}
	}

	private static String helperToString(int multiple, String res) {
		int lastDigit = multiple % 10;
		StringBuilder tmp = new StringBuilder().append(lastDigit);
		res = tmp.toString() + res;

		if (multiple > 10) {
			multiple = multiple / 10;
			return helperToString(multiple, res);
		}
		return res;
	}

	public static void main(String[] args) {
		String num1 = "123456789";
		String num2 = "987654321";
		System.out.println(multiply(num1, num2));
	}
}
