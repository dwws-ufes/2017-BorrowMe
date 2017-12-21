package br.ufes.inf.nemo.marvin.core.domain;

import javax.enterprise.context.SessionScoped;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

@Entity
public class Book extends PersistentObjectSupport implements Comparable<Book> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Basic
	@NotNull
	@Size(max = 50)
	private String title;
	
	@Basic
	@NotNull
	private String description;
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int compareTo(Book arg0) {
		// TODO Auto-generated method stub
		return title.compareTo(arg0.title);
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	@Override
	public String toString() {
		return title;
	}

}
