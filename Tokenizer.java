class Tokenizer{
	private static String _input;
	private static char[] _output;
	
	public static void setInput(String input){
		_input = input;
	}

	public static char[] getOutput(){
		return _output;
	}
	
	private String trimInput(){
		String tempString = "";
		for(int i = 0; i < _input.length(); i++){
			if(_input.charAt(i) != ' '){
				tempString += Character.toString(_input.charAt(i));
			}
		}
		return tempString;
	}
	
	private boolean checkCharSet(String input){
		char[] charSet = {'a', 'b', 'c', '+', '-', '/', '*', '=', '(', ')'};
		for(int i = 0; i < input.length(); i++){
			if(!(charSet.contains(input.charAt(i)))){
				System.out.println("Error: " + Character.toString(input.charAt(i)) + " is not a valid character.");
				return false;
			}
		}
		return true;
	}
}