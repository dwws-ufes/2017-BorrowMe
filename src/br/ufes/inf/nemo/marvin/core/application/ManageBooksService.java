package br.ufes.inf.nemo.marvin.core.application;

import javax.ejb.Local;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.marvin.core.domain.Book;
import br.ufes.inf.nemo.marvin.core.persistence.BookDAO;

@Local
public interface ManageBooksService extends CrudService<Book> {

}
