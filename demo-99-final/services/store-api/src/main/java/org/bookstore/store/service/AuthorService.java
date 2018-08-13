package org.bookstore.store.service;

import org.bookstore.store.domain.Author;
import org.bookstore.store.repository.AuthorRepository;
import org.bookstore.store.service.dto.AuthorDTO;
import org.bookstore.store.service.mapper.AuthorMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static javax.transaction.Transactional.TxType.SUPPORTS;

/**
 * Service Implementation for managing Author.
 */
@Transactional
public class AuthorService {

    private final Logger log = LoggerFactory.getLogger(AuthorService.class);

    private final AuthorRepository authorRepository;

    private final AuthorMapper authorMapper;

    public AuthorService(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    /**
     * Save a author.
     *
     * @param authorDTO the entity to save
     * @return the persisted entity
     */
    public AuthorDTO save(AuthorDTO authorDTO) {
        log.debug("Request to save Author : {}", authorDTO);
        Author author = authorMapper.toEntity(authorDTO);
        author = authorRepository.save(author);
        AuthorDTO result = authorMapper.toDto(author);
        return result;
    }

    /**
     * Get all the authors.
     *
     * @return the list of entities
     */
    @Transactional(SUPPORTS)
    public List<AuthorDTO> findAll() {
        log.debug("Request to get all Authors");
        return null;
//        return authorRepository.findAll();
//            .map(authorMapper::toDtoStream);
    }


    /**
     * Get one author by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(SUPPORTS)
    public Optional<AuthorDTO> findOne(Long id) {
        log.debug("Request to get Author : {}", id);
        return authorRepository.findById(id)
            .map(authorMapper::toDto);
    }

    /**
     * Delete the author by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Author : {}", id);
        authorRepository.deleteById(id);
    }
}
