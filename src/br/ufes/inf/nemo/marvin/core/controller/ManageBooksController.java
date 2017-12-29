package br.ufes.inf.nemo.marvin.core.controller;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.application.filters.LikeFilter;
import br.ufes.inf.nemo.jbutler.ejb.controller.CrudController;
import br.ufes.inf.nemo.marvin.core.application.ManageBooksService;
import br.ufes.inf.nemo.marvin.core.domain.Book;

@Named
@SessionScoped
public class ManageBooksController extends CrudController<Book> {

	@EJB
	private ManageBooksService manageBooksService;
	
	@Override
	protected CrudService<Book> getCrudService(){
		return manageBooksService;
	}
	
	@Override
	protected Book createNewEntity() {
		return new Book();
	}

	@Override
	protected void initFilters() {
		addFilter(new LikeFilter("manageBooks.filter.byTitle", "title", getI18nMessage("msgsCore", "manageUsers.text.filter.byTitle")));
		addFilter(new LikeFilter("manageBooks.filter.byGenre", "genre", getI18nMessage("msgsCore", "manageUsers.text.filter.byGenre")));
	}
	
}
