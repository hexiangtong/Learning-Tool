package learning;

import java.io.Serializable;

public class Index implements Serializable,Cloneable{
	private static final long serialVersionUID = 4661374286912804784L;
	private long index = 0;
	private int maxIndex;
	private int size;
	public Index(int size){
		this.size = size;
		this.maxIndex = size - 1;
	}
	public int getIndex(){
		while(index < 0){
			index+=size;
		}
		while(index > maxIndex){
			index-=size;
		}
		return (int) index;
	}
	public int plusThenReturn(){
		index++;
		return this.getIndex();
	}
	
	public int minusThenReturn(){
		index--;
		return this.getIndex();
	}
}
