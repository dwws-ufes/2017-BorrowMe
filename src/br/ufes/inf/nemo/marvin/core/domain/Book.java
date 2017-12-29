package br.ufes.inf.nemo.marvin.core.domain;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
	@Size(max=3000)
	private String description;

	@Basic
	@Size(max = 50)
	private String author;
	
	@Basic
	@Size(max = 50)
	private String genre;
	
	@Temporal(TemporalType.DATE)
	private Date begin;

	@Temporal(TemporalType.DATE)
	private Date end;
	
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

	public void setAuthor(String string) {
		this.author = string;
	
	}
	
	public String getAuthor(){
		return this.author;
	}
	
	public void setGenre(String string){
		this.genre = string;
	}
	
	public String getGenre(){
		return this.genre;
	}
	
	public Date getBegin() {
		return begin;
	}

	public void setBegin(Date begin) {
		this.begin = begin;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

}
