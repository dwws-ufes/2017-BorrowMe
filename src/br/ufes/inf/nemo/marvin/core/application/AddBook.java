package br.ufes.inf.nemo.marvin.core.application;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;

import br.ufes.inf.nemo.marvin.core.domain.Book;
import br.ufes.inf.nemo.marvin.core.persistence.BookDAO;


@Stateful
@LocalBean
@Model
public class AddBook {
	
	private static final Logger logger = Logger.getLogger(AddBook.class.getCanonicalName());

	@EJB
	private BookDAO bookDAO;

	private List<Book> books;

	private Book book = new Book();

	@Inject
	void loadPackages() {
		books = bookDAO.retrieveAll();
		logger.log(Level.INFO, "Loading books: {0} books loaded", books.size());

	}

	public List<Book> getBooks() {
		return books;
	}

	public Book getBook() {
		return book;
	}

	public String add() {

		bookDAO.save(book);
		books.add(book);

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Book \"" + book.getTitle() + "\" added successfully!"));
		book = new Book();
		return null;
	}
	
	public void suggestDescription() {
		String title = book.getTitle();
		String query =  "PREFIX dbo: <http://dbpedia.org/ontology/> "+
						"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "+
						"PREFIX bibo: <http://purl.org/ontology/bibo/> "+
						"SELECT ?book ?desc ?title"+
						"WHERE{"+
							"?book a bibo:Book. "+
							"?book rdfs:label ?title. "+
							"?book dbo:abstract ?desc. "+
							"FILTER(LANG(?title) = \"\" || LANGMATCHES(LANG(?title), \"en\")) "+
							"FILTER(LANG(?desc) = \"\" || LANGMATCHES(LANG(?desc), \"en\")) "+
							"FILTER(?title="+ "\"" + title + "\"" +"@en)"+
						"}";
		QueryExecution queryExecution = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query);
		ResultSet results = queryExecution.execSelect();
		
		if (results.hasNext()) {
			QuerySolution querySolution = results.next();
			Literal literal = querySolution.getLiteral("desc");
			book.setDescription("" + literal.getValue());
		}
		else book.setDescription("Not found");
	}
	
	public void suggestAuthor(){
		String title = book.getTitle();
		String query =  "PREFIX dbo: <http://dbpedia.org/ontology/> "+
						"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "+
						"PREFIX bibo: <http://purl.org/ontology/bibo/> "+
						"SELECT ?book ?title ?author ?name " +
						"WHERE{"+
						"?book rdfs:label ?title. "+
						"?book dbo:author ?author. "+
						"?author rdfs:label ?name. " +
						"FILTER(LANG(?title) = \"\" || LANGMATCHES(LANG(?title), \"en\")) "+
						"FILTER(LANG(?name) = \"\" || LANGMATCHES(LANG(?name), \"en\")) "+
						"FILTER(?title="+ "\"" + title + "\"" +"@en || ?title="+ "\"" + title + "\"" +"@pt)"+
						"}";
		QueryExecution queryExecution = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query);
		ResultSet results = queryExecution.execSelect();
		
		if (results.hasNext()) {
			QuerySolution querySolution = results.next();
			Literal literal = querySolution.getLiteral("name");
			book.setAuthor("" + literal.getValue());
		}
		else book.setAuthor("Not found");

	}
	
	public void suggestGenre(){
		String title = book.getTitle();
		String query =  "PREFIX dbo: <http://dbpedia.org/ontology/> "+
						"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "+
						"PREFIX bibo: <http://purl.org/ontology/bibo/> "+
						"SELECT ?book ?title ?genre ?g " +
						"WHERE{"+
						"?book rdfs:label ?title. "+
						"?book dbo:literaryGenre ?genre. " +
						"?genre rdfs:label ?g. "+
						"FILTER(LANG(?title) = \"\" || LANGMATCHES(LANG(?title), \"en\")) "+
						"FILTER(?title="+ "\"" + title + "\"" +"@en)"+
						"}";
		QueryExecution queryExecution = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query);
		ResultSet results = queryExecution.execSelect();
		
		if (results.hasNext()) {
			QuerySolution querySolution = results.next();
			Literal literal = querySolution.getLiteral("g");
			book.setGenre("" + literal.getValue());
		}
		else book.setGenre("Not found");

	}

}
