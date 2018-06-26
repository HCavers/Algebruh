class Block{
	private char _symbol;
	
	public Block(char symbol){
		_symbol = symbol;
	}
	
	public int run(int value1, int value2){
		try{
			if(_symbol == '+'){
				return value1 + value2;
			}else if(_symbol == '-'){
				return value1 - value2;
			}else if(_symbol == '*'){
				return value1 * value2;
			}else{
				return value1 / value2;
			}
		}catch(Exception ex){
			return 0;
		}
	}
}