package ua.com.nc.nctrainingproject.persistance.dao.postgre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.com.nc.nctrainingproject.models.Author;
import ua.com.nc.nctrainingproject.models.Book;
import ua.com.nc.nctrainingproject.persistance.dao.AbstractDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.BookQuery;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.FilterCriterionQuery;
import ua.com.nc.nctrainingproject.persistance.mappers.BookRowMapper;

import java.util.List;

@Repository
public class BookPostgreDAO extends AbstractDAO<Book> {

	private final AuthorBookPostgreDAO authorBookPostgreDAO;
	private final GenrePostgreDAO genrePostgreDAO;

	@Autowired
	public BookPostgreDAO(JdbcTemplate jdbcTemplate, AuthorBookPostgreDAO authorBookPostgreDAO,
						  GenrePostgreDAO genrePostgreDAO) {
		super(jdbcTemplate);
		this.authorBookPostgreDAO = authorBookPostgreDAO;
		this.genrePostgreDAO = genrePostgreDAO;
	}

	public Book getBookById(int bookId) {
		return super.getEntityById(BookQuery.GET_BOOK, new BookRowMapper(), bookId);
	}

	public List<Book> getAllBooks() {
		return super.getAllEntities(BookQuery.GET_ALL, new BookRowMapper());
	}

	public void deleteBookById(int bookId) {
		super.deleteEntityById(BookQuery.DELETE_BOOK_BY_ID, bookId);
	}

	public void createBook(Book book) {
		jdbcTemplate.update(BookQuery.CREATE_BOOK, book.getHeader(), book.getOverview(), book.getFileId(),
				book.getStatus(), genrePostgreDAO.getIdByGenre(book.getGenre()), book.getPhoto());

		for (Author author : book.getAuthors()) {
			authorBookPostgreDAO.createAuthorBookConnection(book.getId(), author.getId());
		}
	}

	public void updateBookById(int id, Book book) {
		Object[] params = new Object[]{book.getHeader(), book.getOverview(), book.getFileId(), book.getStatus(),
				genrePostgreDAO.getIdByGenre(book.getGenre()), book.getPhoto(), id};
		super.updateEntityById(id, params, BookQuery.UPDATE_BOOK);
	}

	public List<Book> filterBooks(FilterCriterionQuery filterCriterionQuery) {
		String query = filterCriterionQuery.makeQuery();
		Object[] args = filterCriterionQuery.makeArrayArgsStream();
		List<Book> books = jdbcTemplate.query(query, args, new BookRowMapper());
		for (Book book : books) {
			book.setAuthors(authorBookPostgreDAO.getAuthorsByBookId(book.getId()));
		}
		return books;
	}
}

