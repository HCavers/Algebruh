class Tokenizer{
	private static String _input;
	private static char[] _output;
	
	public static void setInput(String input){
		_input = input;
	}

	public static char[] getOutput(){
		return _output;
	}
	
	public static void run(){
		String temp = trimInput();
		if(checkCharSet(temp) == false){
			return;
		}
		if(checkBrackets(temp) == false){
			return;
		}
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
		for(int i = 0; i < 20; i++){
			if(c == charSet[i]){
				return true;
			}
		}
		return false;
	}
	
	private static boolean checkBrackets(String input){
		int count = 0;
		for(int i = 0; i < input.length(); i++){
			if(input.charAt(i) == '('){
				count++;
			}else if(input.charAt(i) == ')'){
				count--;
			}
		}
		if(count != 0){
			System.out.println("Error: Brackets are used incorrectly");
			return false;
		}else{
			return true;
		}
	}
	
}