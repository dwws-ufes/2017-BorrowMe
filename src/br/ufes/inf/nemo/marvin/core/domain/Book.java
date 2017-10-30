package br.ufes.inf.nemo.marvin.core.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;
import br.ufes.inf.nemo.marvin.people.domain.Person;

@Entity
public class Book extends PersistentObjectSupport implements Comparable<Book> {

	@Basic
	@NotNull
	@Size(max = 50)
	private String title;
	
	@Override
	public int compareTo(Book arg0) {
		// TODO Auto-generated method stub
		return title.compareTo(arg0.title);
	}

}
