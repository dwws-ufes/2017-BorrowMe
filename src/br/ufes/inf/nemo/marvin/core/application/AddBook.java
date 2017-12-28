package br.ufes.inf.nemo.marvin.core.application;

import java.text.DateFormat;
import java.text.NumberFormat;
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
		System.out.println("entrou");
		books = bookDAO.retrieveAll();
		logger.log(Level.INFO, "Loading tour packages: {0} packages loaded", books.size());

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
		context.addMessage(null, new FacesMessage("Tour Package \"" + book.getTitle() + "\" added successfully!"));
		book = new Book();
		return null;
	}
	
	public void suggestDescription() {
		//book.setDescription("teste");
//		String name = book.getTitle();
//		if (name != null && name.length() > 3) {
//			String query = "PREFIX dbpedia-owl: <http://dbpedia.org/ontology/> " +
//					"PREFIX dbpprop: <http://dbpedia.org/property/> " +
//					"SELECT ?desc " +
//					"WHERE { " +
//						"?x a dbpedia-owl:Place ; " +
//						"dbpprop:name ?name ; " +
//						"dbpedia-owl:abstract ?desc . " +
//						"FILTER (lcase(str(?name)) = \"" + name.toLowerCase() + "\") " +
//						"FILTER (langMatches(lang(?desc), \"EN\")) " +
//					"}";
//			
//			QueryExecution queryExecution = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query);
//			ResultSet results = queryExecution.execSelect();
//			
//			if (results.hasNext()) {
//				QuerySolution querySolution = results.next();
//				Literal literal = querySolution.getLiteral("desc");
//				pack.setDescription("" + literal.getValue());
//			}
//		}
		String title = book.getTitle();
		String query = "PREFIX dbo: <http://dbpedia.org/ontology/>"+
				"SELECT ?book ?author ?nameauthor ?title"+
						"WHERE{"+
						"?book a dbo:Book;"+
						"rdfs:label ?title;"+


			"dbo:author ?author."+
			"?author rdfs:label ?nameauthor"+

			"FILTER(LANG(?title) = "+""+" || LANGMATCHES(LANG(?title), "+"en"+"))"+
			"FILTER(LANG(?nameauthor) = "+""+" || LANGMATCHES(LANG(?nameauthor), "+"en"+"))"+

			"FILTER(?title="+title+"@en)"+


			"}"+

			"LIMIT 10";
		QueryExecution queryExecution = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query);
		ResultSet results = queryExecution.execSelect();
		
		if (results.hasNext()) {
			QuerySolution querySolution = results.next();
			Literal literal = querySolution.getLiteral("authorname");
			book.setDescription("" + literal.getValue());
		}
	}

}
