package br.ufes.inf.nemo.marvin.core.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;

import br.ufes.inf.nemo.marvin.core.persistence.BookDAO;
import br.ufes.inf.nemo.marvin.core.application.ManageBooksService;
import br.ufes.inf.nemo.marvin.core.domain.Book;

@WebServlet(urlPatterns = { "/data/books" })
public class ListBooksInRdfServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ManageBooksService mb;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/xml");
		List<Book> books = mb.getDAO().retrieveAll();
		Model model = ModelFactory.createDefaultModel();
		String myNS	= "http://localhost:8080/BorrowMe-1.0/data/books/";
		String dboNS = "http://dbpedia.org/ontology/";
		String rdfsNS = "http://www.w3.org/2000/01/rdf-schema#";
		String biboNS = "http://purl.org/ontology/bibo/";
		model.setNsPrefix("dbo", dboNS);
		model.setNsPrefix("rdfs", rdfsNS);
		model.setNsPrefix("bibo", biboNS);
		Property description = ResourceFactory.createProperty(dboNS , "description");
		Property author = ResourceFactory.createProperty(dboNS , "author");
		Property genre = ResourceFactory.createProperty(dboNS,"genre");
		for (Book book : books) {
			model.createResource(myNS + book.getTitle().replace(" ", "_"))
			.addProperty(RDF.type, "bibo:Book")
			.addProperty(RDFS.label, book.getTitle())			
			.addProperty(description, book.getDescription())
			.addProperty(author, book.getAuthor())
			.addProperty(genre, book.getGenre())
		;
		}
		try (PrintWriter out = resp.getWriter()) {
			model.write(out, "RDF/XML");
		}
	}
}
