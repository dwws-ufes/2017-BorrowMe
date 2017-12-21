package br.ufes.inf.nemo.marvin.core.controller;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
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
		// TODO Auto-generated method stub
		
	}
	
}
