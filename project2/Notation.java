/*
	 * @author David Sawma
	 * CMSC 204 23393
	 */
	
public class Notation {
	/**
	 * Convert the Postfix expression to the Infix expression
	 * @param postfix  the postfix expression in string format
	 * @return the infix expression in string format
	 * @throws InvalidNotationFormatException - if the postfix expression format is invalid
	 */
	public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException {
		MyStack<String> Stack=new MyStack<String>();
		
		char[] ch=postfix.toCharArray();
		
		try {
			for(char c: ch) {
				if(c ==' ') {
					continue;
				}
				else if(Character.isDigit(c)) {
					Stack.push(Character.toString(c));
				}
				else if(c =='+'||c=='-'||c=='/'||c=='*' || c == '%') {
					if(Stack.size()<2) {
						throw new InvalidNotationFormatException();
					}
					else {
						String bottom= Stack.pop();
						String top= Stack.pop();
						String infix="("+ top+ c+ bottom+")";
						Stack.push(infix);
					}
				}
			}
			if(Stack.size()>1) {
				throw new InvalidNotationFormatException();
			}
		}
		catch(StackOverflowException e) {
			e.printStackTrace();
		}
		catch(StackUnderflowException e) {
			e.printStackTrace();
		}
		return Stack.toString();
	}
	
	/**
	 * Convert an infix expression into a postfix expression
	 * @param infix, the infix expression in string format
	 * @return the postfix expression in string format
	 * @throws InvalidNotationFormatException  if the infix expression format is invalid
	 */
	public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException {
		
		MyStack<String> Stack=new MyStack<String>();
		MyQueue<String> Queue=new MyQueue<String>();

		char[] ch= infix.toCharArray();
		try {
			for(char c: ch) {
				if(c ==' ') {
					continue;
				}
				else if(Character.isDigit(c)) {
					Queue.enqueue(Character.toString(c));
				}
				else if(c =='(') {
					
					Stack.push(Character.toString(c));
				}
				
				else if(c=='+') {
					if(!Stack.isEmpty()) {
						while((Stack.top().equals("+")||Stack.top().equals("-")||Stack.top().equals("*")||Stack.top().equals("/") || Stack.top().equals("%"))) {
							Queue.enqueue(Stack.pop());
						}
					}
					Stack.push(Character.toString(c));
				}
				else if(c=='-') {
					if(!Stack.isEmpty()) {
						while(Stack.top().equals("+")||Stack.top().equals("-")||Stack.top().equals("*")||Stack.top().equals("/") || Stack.top().equals("%")) {
							Queue.enqueue(Stack.pop());
						}
					}
					Stack.push(Character.toString(c));
				}
				else if(c =='/') {
					if(!Stack.isEmpty()) {
						while(Stack.top().equals("*")||Stack.top().equals("/") || Stack.top().equals("%")) {
							Queue.enqueue(Stack.pop());
						}
					}
					Stack.push(Character.toString(c));
				}
				else if(c =='*') {
					if(!Stack.isEmpty()) {
						while(Stack.top().equals("*")||Stack.top().equals("/") || Stack.top().equals("%")) {
							Queue.enqueue(Stack.pop());
						}
					}
					Stack.push(Character.toString(c));
				}
				else if(c =='%') {
					if(!Stack.isEmpty()) {
						while(Stack.top().equals("*")||Stack.top().equals("/") || Stack.top().equals("%") ) {
							Queue.enqueue(Stack.pop());
						}
					}
					Stack.push(Character.toString(c));
				}
				else if(c ==')') {
					while(!Stack.isEmpty()&&!Stack.top().equals("(")) {
						Queue.enqueue(Stack.pop());
					}
					if(Stack.isEmpty()) {
						throw new InvalidNotationFormatException();
					}
					
					else if (Stack.top().equals("(")){
						Stack.pop();
					}
				}
			}
			while(!Stack.isEmpty()&&!Stack.top().equals("(")) {
				Queue.enqueue(Stack.pop());
			}
		}
		catch(QueueOverflowException e) {
			e.printStackTrace();
		}
		catch(StackOverflowException e) {
			e.printStackTrace();
		}
		catch(StackUnderflowException e) {
			e.printStackTrace();
		}
		return Queue.toString();
	}
	
	/**
	 * Evaluates a postfix expression from a string to a double
	 * @param postfixExpr, the postfix expression in String format
	 * @return the evaluation of the postfix expression as a double
	 * @throws InvalidNotationFormatException, if the postfix expression format is invalid
	 */
	public static double evaluatePostfixExpression(String postfixExpr) throws InvalidNotationFormatException {
		double result=0;
		MyStack<String> Stack=new MyStack<String>();
		char[] ch=postfixExpr.toCharArray();
		
		try {
			for(char c: ch) {
				if(c ==' ') {
					continue;
				}
				else if(Character.isDigit(c)) {
					Stack.push(Character.toString(c));
				}
				else if (c =='+'||c=='-'||c=='/'||c=='*' || c == '%') {
					if(Stack.size()<2) {
						throw new InvalidNotationFormatException();
					}
					else {
						
						String bottom=Stack.pop();
						String top=Stack.pop();
						double topD = Double.parseDouble(top);
					    double bottomD = Double.parseDouble(bottom);
						switch (c) {
					      case '+':
					         result = (topD + bottomD);
					         break;
					      case '-':
					        result = (topD-bottomD);
					        break;
					      case '*':
					    	  result = (topD*bottomD);
					    	  break;
					      case '/':
					        result = (topD/bottomD);
					        break;
					      case '%':
					    	  result = (topD%bottomD);
					    	  break;
					      
					    }
						Stack.push(Double.toString(result));
						
					}
				}
			}
			if(Stack.size()>1) {
				throw new InvalidNotationFormatException();
			}
		}
		catch(StackOverflowException e) {
			e.printStackTrace();
		}
		catch(StackUnderflowException e) {
			e.printStackTrace();
		}
		
					
		return result;
	}
	

}
