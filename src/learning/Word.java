package learning;

import java.io.Serializable;

public class Word implements Serializable,Cloneable {

	private static final long serialVersionUID = 3106469852981860292L;
	private boolean isNewWord = false;
	private String word;
	private String pro; //Pronunciation
	private String meaning;
	private boolean hasLearned;
	
	/**
	 * 
	 * @return mark new word
	 */
	public boolean isNewWord() {
		return isNewWord;
	}
	
	/**
	 * 
	 * @param whether or not it is new word
	 */
	public void setNewWord(boolean isNewWord) {
		this.isNewWord = isNewWord;
	}
	
	/**
	 * 
	 * @return word
	 */
	public String getWord() {
		return word;
	}
	
	public void setWord(String word) {
		this.word = word;
	}
	
	public String getPro() {
		return pro;
	}
	
	public void setPro(String pro) {
		this.pro = pro;
	}
	
	public String getMeaning() {
		return meaning;
	}
	
	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}
	
	public Word(String word, String pro, String meaning) {
		super();
		this.word = word;
		this.pro = pro;
		this.meaning = meaning;
	}
	
	public Word(){
		super();
	}
	
	/**
	 * override toString method
	 * */
	@Override
	public String toString() {
		return "Word [word=" + word + ", pro=" + pro + ", meaning=" + meaning;
	}
	
	public boolean isHasLearned() {
		return hasLearned;
	}
	
	/**
	 * @param hasLearned 要设置的 hasLearned
	 */
	public void setHasLearned(boolean hasLearned) {
		this.hasLearned = hasLearned;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (hasLearned ? 1231 : 1237);
		result = prime * result + (isNewWord ? 1231 : 1237);
		result = prime * result + ((meaning == null) ? 0 : meaning.hashCode());
		result = prime * result + ((pro == null) ? 0 : pro.hashCode());
		result = prime * result + ((word == null) ? 0 : word.hashCode());
		return result;
	}

	/* 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Word other = (Word) obj;
		if (hasLearned != other.hasLearned) {
			return false;
		}
		if (isNewWord != other.isNewWord) {
			return false;
		}
		if (meaning == null) {
			if (other.meaning != null) {
				return false;
			}
		} else if (!meaning.equals(other.meaning)) {
			return false;
		}
		if (pro == null) {
			if (other.pro != null) {
				return false;
			}
		} else if (!pro.equals(other.pro)) {
			return false;
		}
		if (word == null) {
			if (other.word != null) {
				return false;
			}
		} else if (!word.equals(other.word)) {
			return false;
		}
		return true;
	}

}
