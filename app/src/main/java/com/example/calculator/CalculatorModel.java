package com.example.calculator;
import java.io.IOException;
import java.util.Stack;
import java.math.*;

public class CalculatorModel {

    private StringBuilder inputStr = new StringBuilder();

    boolean is_operand(char x)
    {
        if ((x >= '0' && x <= '9') || x == '.')
            return true;
        else
            return false;
    }

    int get_priority(char x)
    {
        switch (x)
        {
            case '(':
                return 1;
            case ')':
                return 2;
            case '+':
                return 3;
            case '-':
                return 3;
            case '*':
                return 4;
            case '/':
                return 4;
            case '^':
                return 5;
            default:
                return -1;
        }
    }

    public String to_postfix(String infix) {
        Stack<Character> stack = new Stack<>();
        StringBuilder postfix = new StringBuilder();
        for (int i = 0; i < infix.length(); i++)
        {

            char x = infix.charAt(i);

            if (is_operand(x))
            {
                postfix.append(x);
                if (i == infix.length() - 1 || !is_operand(infix.charAt(i + 1)))
                    postfix.append(" ");
            }

            if (x == '(')
                stack.push(x);

            if (x == ')')
            {
                while (stack.peek() != '(')
                {
                    postfix.append(stack.pop());
                }
                stack.pop();
            }

            if (!is_operand(x) && x != '(' && x != ')')
            {
                while (!stack.isEmpty() && get_priority(stack.peek()) >= get_priority(x))
                {
                    postfix.append(stack.pop());
                }
                stack.push(x);
            }
        }
        while (!stack.isEmpty())
        {
            postfix.append(stack.pop());
        }
        return postfix.toString();
    }

    double do_operation(double a, double b, char op)
    {
        switch (op)
        {
            case '+':
                return b + a;
            case '-':
                return b - a;
            case '*':
                return b * a;
            case '/':
                return b / a;
            case '^':
                return Math.pow(b, a);
            default:
                return -1;
        }
    }

    double calculate(String postfix)
    {
        Stack <Double> stack = new Stack<>();
        StringBuilder num = new StringBuilder();
        for (int i = 0; i < postfix.length(); i++)
        {
            char x = postfix.charAt(i);

            if (is_operand(x))
                num.append(x);

            if (x == ' ')
            {
                stack.push(Double.parseDouble(num.toString()));
                num.setLength(0);
            }

            if (!is_operand(x) && x != ' ')
            {
                double a = stack.pop();
                double b = stack.pop();
                stack.push(do_operation(a, b, x));
            }
        }
        double result = stack.pop();
        return result;
    }

    public void onPressed(int buttonId) {

        switch (buttonId) {
            case R.id.zero:
                inputStr.append("0");
                break;
            case R.id.one:
                inputStr.append("1");
                break;
            case R.id.two:
                inputStr.append("2");
                break;
            case R.id.three:
                inputStr.append("3");
                break;
            case R.id.four:
                inputStr.append("4");
                break;
            case R.id.five:
                inputStr.append("5");
                break;
            case R.id.six:
                inputStr.append("6");
                break;
            case R.id.seven:
                inputStr.append("7");
                break;
            case R.id.eight:
                inputStr.append("8");
                break;
            case R.id.nine:
                inputStr.append("9");
                break;
            case R.id.dot:
                inputStr.append(".");
                break;
            case R.id.plus:
                inputStr.append("+");
                break;
            case R.id.minus:
                inputStr.append("-");
                break;
            case R.id.multiply:
                inputStr.append("*");
                break;
            case R.id.division:
                inputStr.append("/");
                break;
            case R.id.left_bracket:
                inputStr.append("(");
                break;
            case R.id.right_bracket:
                inputStr.append(")");
                break;
            case R.id.power:
                inputStr.append("^");
                break;
            case R.id.erase:
                if (inputStr.length() != 0)
                    inputStr.setLength(inputStr.length() - 1);
                break;
            case R.id.clear:
                inputStr.setLength(0);
                break;
            case R.id.equals:
                try{
                    double result = calculate(to_postfix(inputStr.toString()));
                    inputStr.setLength(0);
                    inputStr.append(result);
                }
                catch (Exception e) {
                    inputStr.setLength(0);
                    inputStr.append("ERROR");
                }
                break;
        }
    }

    public String getInputText() {
        return inputStr.toString();
    }
}
