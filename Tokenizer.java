class Tokenizer{
	private static String _input;
	private static char[] _output;
	
	public static void setInput(String input){
		_input = input;
	}

	public static char[] getOutput(){
		return _output;
	}
	
	public static boolean tokenize(){
		String temp = trimInput();
		if(checkCharSet(temp) == false){
			return false;
		}
		if(checkBrackets(temp) == false){
			return false;
		}
		if(checkFormatt(temp) == false){
			return false;
		}
		setOutput(temp);
		return true;
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
			if(emptyBrackets(input)){
				return true;
			}else{
				return false;
			}
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
	
	private static boolean checkFormatt(String input){
		if(checkEqualsNum(input) == false){
			return false;
		}
		int equalIndex = getEqualsNum(input);
		if(checkRight(input, equalIndex) == false){
			return false;
		}
		String temp = trimBrackets(input);
		equalIndex = getEqualsNum(temp);
		if(checkLeft(temp, equalIndex) == false){
			return false;
		}
		return true;
	}
	
	private static String trimBrackets(String input){
		String result = "";
		for(int i = 0; i < input.length(); i++){
			if(input.charAt(i) != '(' ){
				if(input.charAt(i) != ')'){
					result += Character.toString(input.charAt(i));
				}
			}
		}
		return result;
	}
	
	private static boolean checkRight(String input, int equalsIndex){
		char[] charSet = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
		int index = equalsIndex + 1;
		for(int i = index; i < input.length(); i++){
			boolean check = false;
			for(int j = 0; j < charSet.length; j++){
				if(input.charAt(i) == charSet[j]){
					check = true;
				}
			}
			if(check == false){
				System.out.println("Error: Incorrect format only numbers are allowed after the '=' sign");
				return false;
			}else{
				check = false;
			}
		}
		return true;
	}
	
	private static boolean checkLeft(String input, int equalsIndex){
		char[] letterCharSet = {'a', 'b', 'c'};
		char[] symbolCharSet = {'(', ')', '+', '*', '-', '/'};
		char type = getFirstType(input);
		int letterNum = 0;
		int symbolNum = 0;
		int aCount = 0;
		int bCount = 0;
		int cCount = 0;
		for(int i = 0; i < equalsIndex; i++){
			boolean check = false;
			int size = 0;
			if(type == 'l'){
				size = letterCharSet.length;
			}else{
				size = symbolCharSet.length;
			}
			for(int j = 0; j < size; j++){
				if(type == 'l'){
					if(input.charAt(i) == 'a'){
						check = true;
						aCount++;
						letterNum++;
						j = size;
					}else if(input.charAt(i) == 'b'){
						check = true;
						bCount++;
						letterNum++;
						j = size;
					}else if(input.charAt(i) == 'c'){
						check = true;
						cCount++;
						letterNum++;
						j = size;
					}
				}else{
					if(input.charAt(i) == symbolCharSet[j]){
						check = true;
						symbolNum++;
					}
				}
			}
			if(check == false){
				System.out.println("Error: Incorrect format");
				return false;
			}else{
				check = false;
				if(type == 'l'){
					type = 's';
				}else{
					type = 'l';
				}
			}
		}
		int difference = letterNum - symbolNum;
		if(difference < 0){
			difference = difference * -1;
		}
		if(difference == 1 && aCount == 1 && bCount == 1 && cCount == 1){
			return true;
		}else{
			System.out.println("Error: Incorrect format");
			return false;
		}
	}
	
	private static char getFirstType(String input){
		char first = input.charAt(0);
		if(first == 'a' || first == 'b' || first == 'c'){
			return 'l';
		}else{
			return 's';
		}
	}	
	
	private static boolean checkEqualsNum(String input){
		int count = 0;
		for(int i = 0; i < input.length(); i++){
			if(input.charAt(i) == '='){
				count++;
			}
		}
		if(count == 1){
			return true;
		}else{
			System.out.println("Error: Incorrect too many '=', please only use one");
			return false;
		}
	}
	
	private static int getEqualsNum(String input){
		for(int i = 0; i < input.length(); i++){
			if(input.charAt(i) == '='){
				return i;
			}
		}
		System.out.println("Error: No '=' found, one '=' is required");
		return -1;
	}	
}