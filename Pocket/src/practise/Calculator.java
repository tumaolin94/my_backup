package practise;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

public class Calculator {
	public static int calculator4(String input) {
		input = input.replaceAll(" ", "");
		if (input == null || input.length() == 0)
			return 0;

		int num = 0;
		Stack<Integer> operand = new Stack<>();
		Stack<Character> operator = new Stack<>();

		Map<Character, Integer> map = new HashMap<>();
		map.put('+', 1);
		map.put('-', 1);
		map.put('*', 2);
		map.put('/', 2);
		map.put('^', 3);

		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			System.out.println(c);
			if (Character.isDigit(c)) {
				num = num * 10 + (c - '0');
			} else {
				operand.push(num);
				num = 0;
				if (operator.size() == 0 || map.get(c) > map.get(operator.peek())) {
					operator.push(c);
				} else {
					while (!operator.isEmpty() && map.get(c) <= map.get(operator.peek())) {
						helper(operand, operator);
					}
					operator.push(c);
				}
			}
		}
		operand.push(num);
		while (!operator.isEmpty()) {
			helper(operand, operator);
		}
		return operand.pop();
	}
	
	public static void helper(Stack<Integer> operand, Stack<Character> operator) {
		int b = operand.pop();
		int a = operand.pop();
		
		int op = operator.pop();
		
		if (op == '+'){
			operand.push(a+b);
		}else if (op == '-'){
			operand.push(a-b);
		}else if (op == '*'){
			operand.push(a*b);
		}else if (op == '+'){
			operand.push(a/b);
		}else if (op == '^'){
			operand.push((int)Math.pow(a, b));
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(calculator4("1+2^3+1"));
	}

}
