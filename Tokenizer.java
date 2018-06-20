class Tokenizer{
	private static String _input;
	private static char[] _output;
	
	public static void setInput(String input){
		_input = input;
	}

	public static char[] getOutput(){
		return _output;
	}
	
	public static void tokenize(){
		String temp = trimInput();
		if(checkCharSet(temp) == false){
			return;
		}
		if(checkBrackets(temp) == false){
			return;
		}
		if(emptyBrackets(temp) == false){
			return;
		}
		setOutput(temp);
	}
	
	private static void setOutput(String input){
		char[] result = new char[input.length()];
		for(int i = 0; i < input.length(); i++){
			result[i] = input.charAt(i);
		}
		_output = result;
	}
	
	private static String trimInput(){
		String tempString = "";
		for(int i = 0; i < _input.length(); i++){
			if(_input.charAt(i) != ' '){
				tempString += Character.toString(_input.charAt(i));
			}
		}
		return tempString;
	}
	
	private static boolean checkCharSet(String input){
		for(int i = 0; i < input.length(); i++){
			if(charGood(input.charAt(i)) == false){
				System.out.println("Error: " + input.charAt(i) + " is not a valid character");
				return false;
			}
		}
		return true;
	}
	
	private static boolean charGood(char c){
		char[] charSet = {'a', 'b', 'c', '+', '-', '/', '*', '=', '(', ')', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
		for(int i = 0; i < charSet.length; i++){
			if(c == charSet[i]){
				return true;
			}
		}
		return false;
	}
	
	private static boolean checkBrackets(String input){
		char prevChar = ')';
		for(int i = 0; i < input.length(); i++){
			if(input.charAt(i) == ')' || input.charAt(i) == '('){
				if(bracketsOpposite(input.charAt(i), prevChar)){
					prevChar = input.charAt(i);
				}else{
					return false;
				}
			}
		}
		if(prevChar == '('){
			System.out.println("Error: Unclosed set of brackets");
			return false;
		}else{
			return true;
		}
	}
	
	private static boolean bracketsOpposite(char current, char previous){
		if(current == previous){
			System.out.println("Error: Unclosed set of brackets");
			return false;
		}else{
			return true;
		}		
	}
	
	private static boolean emptyBrackets(String input){
		int numBracketPairs = getBracketPairNum(input);
		for(int i = 1; i <= numBracketPairs; i++){
			int startBracketIndex = getBracketIndex(i, 'f', input);
			int lastBracketIndex = getBracketIndex(i, 'l', input);
			if((lastBracketIndex - startBracketIndex) != 4){
				System.out.println("Error: Incorrectly filled brackets");
				return false;
			}
		}
		return true;
	}
	
	private static int getBracketPairNum(String input){
		int count = 0;
		for(int i = 0; i < input.length(); i++){
			if(input.charAt(i) == '('){
				count++;
			}
		}
		return count;
	}
	
	private static int getBracketIndex(int pairNum, char type, String input){
		int count = 0;
		for(int i = 0; i < input.length(); i++){
			if(type == 'f' && input.charAt(i) == '(' || type == 'l' && input.charAt(i) == ')'){
				count++;
				if(count == pairNum){
					return i;
				}
			}
		}
		return -1;
	}
	
	
	
}