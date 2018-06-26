import java.util.LinkedList;
import java.util.List;

class Equation{
	private int _result;
	private int _answer;
	private char[] _order;
	private List<Block> _blocksList;
	
	public Equation(){
		_blocksList = new LinkedList<Block>();
	}
	
	public void addBlock(Block block){
		_blocksList.add(block);
	}
	
	public void setAnswer(int answer){
		_answer = answer;
	}
	
	public void setOrder(char[] order){
		_order = order;
	}
	
	public boolean calculate(int a, int b, int c){
		int[] values = setValues(a, b, c);
		_result = values[0];
		for(int i = 0; i < _blocksList.size(); i++){
			_result = _blocksList.get(i).run(_result, values[i + 1]);
		}
		if(_result == _answer){
			return true;
		}else{
			return false;
		}
	}
	
	private int[] setValues(int a, int b, int c){
		int[] result = new int[3];
		for(int i = 0; i < result.length; i++){
			if(_order[i] == 'a'){
				result[i] = a;
			}else if(_order[i] == 'b'){
				result[i] = b;
			}else{
				result[i] = c;
			}
		}
		return result;
	}
}