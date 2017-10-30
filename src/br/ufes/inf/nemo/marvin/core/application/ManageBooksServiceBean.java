package br.ufes.inf.nemo.marvin.core.application;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudException;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean;
import br.ufes.inf.nemo.jbutler.ejb.application.filters.Filter;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.MultiplePersistentObjectsFoundException;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.PersistentObjectNotFoundException;
import br.ufes.inf.nemo.marvin.core.domain.Book;
import br.ufes.inf.nemo.marvin.core.domain.User;
import br.ufes.inf.nemo.marvin.core.persistence.BookDAO;

@Stateless
@PermitAll
public class ManageBooksServiceBean extends CrudServiceBean<Book> implements ManageBooksService {

	@EJB
	private BookDAO bookDAO;
	
	@Override
	public BaseDAO<Book> getDAO() {
		// TODO Auto-generated method stub
		return bookDAO;
	}



}
