package br.ufes.inf.nemo.marvin.core.persistence;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufes.inf.nemo.jbutler.ejb.application.filters.Filter;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.MultiplePersistentObjectsFoundException;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.PersistentObjectNotFoundException;
import br.ufes.inf.nemo.marvin.core.domain.Book;
@Stateless
public class BookJPADAO extends BaseJPADAO<Book> implements BookDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	protected EntityManager getEntityManager(){
		return entityManager;
	}
	
	@Override
	public Class<Book> getDomainClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long retrieveCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long retrieveFilteredCount(Filter<?> filter, String value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Book> retrieveAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> retrieveWithFilter(Filter<?> filter, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> retrieveSome(int[] interval) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> retrieveSomeWithFilter(Filter<?> filter, String value, int[] interval) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book retrieveById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book retrieveByUuid(String uuid)
			throws PersistentObjectNotFoundException, MultiplePersistentObjectsFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Book object) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Book object) {
		// TODO Auto-generated method stub

	}

	@Override
	public Book merge(Book object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book refresh(Book object) {
		// TODO Auto-generated method stub
		return null;
	}

}
