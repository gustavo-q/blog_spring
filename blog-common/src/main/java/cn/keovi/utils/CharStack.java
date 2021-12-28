package cn.keovi.utils;


import org.apache.tomcat.util.digester.ArrayStack;

public class CharStack {


	private ArrayStack<Character> stack;


	public CharStack(){
		this.stack = new ArrayStack<Character>();
	}
	
	public CharStack(int size){
		this.stack = new ArrayStack<Character>(size);
	}


	public void push(char c){
		stack.push(c);
	}
	
	public void pushString(String str){
		char[] cs = str.toCharArray();
		for(char c : cs){
			this.push(c);
		}
	}
	
	public char pop(){
		return stack.pop();
	}
	
	public boolean isEmpty(){
		return stack.isEmpty();
	}
	
	public int size(){
		return stack.size();
	}
	
	public String popUntil(char... cs){
		StringBuilder sb = new StringBuilder();
		
		if(!isEmpty() && cs != null){
			char c = this.pop();
			boolean reachEnd = false;
			while(ArrayUtils.charNotInArray(c, cs)){
				sb.append(c);
				if(!isEmpty()){
					c = this.pop();
				}else{
					reachEnd = true;
					break;
				}
			}
			if(!reachEnd){
				this.stack.push(c);
			}
		}
		return sb.reverse().toString();
	}
	
	public String popFromLastUntil(char... cs){
		int size = this.size();
		int fromIndex = 0;
		for(int i = 0; i< size; i++){
			char c = this.stack.get(i);
			if(ArrayUtils.charInArray(c, cs)){
				fromIndex = i;
				break;
			}
		}
		StringBuilder sb = new StringBuilder();
		if(fromIndex != 0){
			//fromIndex++;
		}
		fromIndex++;
		for(int i = fromIndex, j = 0; i < size; i++){
			
			sb.append(this.stack.remove(i - j));
			j++;
		}
		return sb.toString();


	}	
}
