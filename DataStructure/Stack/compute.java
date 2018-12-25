package Stack;

import javax.swing.JOptionPane;

public class compute {
	public static double operation(double num1, double num2, String operator)
	{
		switch(operator)
		{
		case "+":
			return num1+num2;
		case "-":
			return num1-num2;
		case "*":
			return num1*num2;
		case "/":
			if(num2 == 0) return 0;
			return num1/num2;
		default:
			return 0;
		}
	}
	public static void main(String args[])
	{
		Stack<Double> operands = new Stack<Double>();
		Stack<String> operators = new Stack<String>();
		double operand;
		String operator = null;
		double temp = 0;
		String result = "";
		while(true) //���� �Է�
		{
			while(true) //�ǿ�����
			{
				try
				{
					operand = (double)Integer.parseInt(JOptionPane.showInputDialog("���ڸ� �Է��ϼ���."));
					operands.push(operand);
					System.out.print("operands : ");
					operands.print();
					System.out.print("operators : ");
					operators.print();
					result += operand;
					break;
				}
				catch(Exception e)
				{
					System.out.println("<Error>���ڸ� �Է��ϼ���.");
					continue;
				}
			}
			while(true) //������
			{
				operator = JOptionPane.showInputDialog("�����ڸ� �Է��ϼ���.(+,-,*,/,=)");
				if(!operator.equals("+") && !operator.equals("-") && !operator.equals("*") && !operator.equals("/") && !operator.equals("="))
				{
					System.out.println("<Error>������(+,-,*,/,=)�� �Է��ϼ���.");
					continue;
				}
				result += operator;
				switch(operator)
				{
				case "+":
					while(operators.top() != null)
					{
						temp = operands.pop();
						operands.push(operation(operands.pop(),temp,operators.pop()));
					}
					operators.push(operator);
					break;
				case "-":
					while(operators.top() != null)
					{
						temp = operands.pop();
						operands.push(operation(operands.pop(),temp,operators.pop()));
					}
					operators.push(operator);
					break;
				case "*":
					while(operators.top() != null && (operators.top().equals("*") || operators.top().equals("/")))
					{
						temp = operands.pop();
						operands.push(operation(operands.pop(),temp,operators.pop()));
					}
					operators.push(operator);
					break;
				case "/":
					while(operators.top() != null && (operators.top().equals("*") || operators.top().equals("/")))
					{
						temp = operands.pop();
						operands.push(operation(operands.pop(),temp,operators.pop()));
					}
					operators.push(operator);
					break;
				case "=":
					while(operators.top() != null)
					{
						temp = operands.pop();
						operands.push(operation(operands.pop(),temp,operators.pop()));
					}
					operators.push(operator);
					break;
				}
				System.out.print("operands : ");
				operands.print();
				System.out.print("operators : ");
				operators.print();
				break;
			}
			if(operators.top().equals("="))
			{
				operators.pop();
				break;
			}
		}
		System.out.println(result+""+operands.pop());
	}
}