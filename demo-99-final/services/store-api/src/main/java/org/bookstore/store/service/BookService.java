package org.bookstore.store.service;

import org.bookstore.store.domain.Book;
import org.bookstore.store.repository.BookRepository;
import org.bookstore.store.service.dto.BookDTO;
import org.bookstore.store.service.mapper.BookMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static javax.transaction.Transactional.TxType.SUPPORTS;

/**
 * Service Implementation for managing Book.
 */
@Transactional
public class BookService {

    private final Logger log = LoggerFactory.getLogger(BookService.class);

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    @Inject
    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    /**
     * Save a book
     *
     * @param bookDTO the entity to save
     * @return the persisted entity
     */
    public BookDTO save(BookDTO bookDTO) {
        log.debug("Request to save Book : {}", bookDTO);
        Book book = bookMapper.toEntity(bookDTO);
        book = bookRepository.save(book);
        BookDTO result = bookMapper.toDto(book);
        return result;
    }

    /**
     * Get all the books.
     *
     * @return the list of entities
     */
    @Transactional(SUPPORTS)
    public List<BookDTO> findAll() {
        log.debug("Request to get all Books");
        return bookRepository.findAll().stream()
            .map(bookMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the Book with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
//    public Page<BookDTO> findAllWithEagerRelationships(Pageable pageable) {
//        return bookRepository.findAllWithEagerRelationships(pageable).map(bookMapper::toDto);
//    }


    /**
     * Get one book by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(SUPPORTS)
    public Optional<BookDTO> findOne(Long id) {
        log.debug("Request to get Book : {}", id);
        return bookRepository.findById(id)
            .map(bookMapper::toDto);
    }

    /**
     * Delete the book by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Book : {}", id);
        bookRepository.deleteById(id);
    }

    /**
     * Search for the book corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Transactional(SUPPORTS)
    public List<BookDTO> search(String query) {
        log.debug("Request to search for a page of Books for query {}", query);
        return null;
//
//        return bookRepository.search(query)
//            .map(bookMapper::toDto);

//        return bookRepository.search(query).stream()
//            .map(bookMapper::toDto)
//            .collect(Collectors.toCollection(LinkedList::new));
    }
}
