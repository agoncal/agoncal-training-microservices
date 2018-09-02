package org.bookstore.store.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.web.util.ResponseUtil;
import org.bookstore.store.service.AuthorService;
import org.bookstore.store.service.dto.AuthorDTO;
import org.bookstore.store.web.rest.errors.BadRequestAlertException;
import org.bookstore.store.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing Author.
 */
@RestController
@RequestMapping("/api")
public class AuthorResource {

    private final Logger log = LoggerFactory.getLogger(AuthorResource.class);

    private static final String ENTITY_NAME = "author";

    private final AuthorService authorService;

    public AuthorResource(AuthorService authorService) {
        this.authorService = authorService;
    }

    /**
     * POST  /authors : Create a new author.
     *
     * @param authorDTO the authorDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new authorDTO, or with status 400 (Bad Request) if the author has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/authors")
    @Timed
    public ResponseEntity<AuthorDTO> createAuthor(@Valid @RequestBody AuthorDTO authorDTO) throws URISyntaxException {
        log.debug("REST request to save Author : {}", authorDTO);
        if (authorDTO.getId() != null) {
            throw new BadRequestAlertException("A new author cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AuthorDTO result = authorService.save(authorDTO);
        return ResponseEntity.created(new URI("/api/authors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /authors : Updates an existing author.
     *
     * @param authorDTO the authorDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated authorDTO,
     * or with status 400 (Bad Request) if the authorDTO is not valid,
     * or with status 500 (Internal Server Error) if the authorDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/authors")
    @Timed
    public ResponseEntity<AuthorDTO> updateAuthor(@Valid @RequestBody AuthorDTO authorDTO) throws URISyntaxException {
        log.debug("REST request to update Author : {}", authorDTO);
        if (authorDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AuthorDTO result = authorService.save(authorDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, authorDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /authors : get all the authors.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of authors in body
     */
    @GetMapping("/authors")
    @Timed
    public List<AuthorDTO> getAllAuthors() {
        log.debug("REST request to get all Authors");
        return authorService.findAll();
    }

    /**
     * GET  /authors/:id : get the "id" author.
     *
     * @param id the id of the authorDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the authorDTO, or with status 404 (Not Found)
     */
    @GetMapping("/authors/{id}")
    @Timed
    public ResponseEntity<AuthorDTO> getAuthor(@PathVariable Long id) {
        log.debug("REST request to get Author : {}", id);
        Optional<AuthorDTO> authorDTO = authorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(authorDTO);
    }

    /**
     * DELETE  /authors/:id : delete the "id" author.
     *
     * @param id the id of the authorDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/authors/{id}")
    @Timed
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        log.debug("REST request to delete Author : {}", id);
        authorService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/authors?query=:query : search for the author corresponding
     * to the query.
     *
     * @param query the query of the author search
     * @return the result of the search
     */
    @GetMapping("/_search/authors")
    @Timed
    public List<AuthorDTO> searchAuthors(@RequestParam String query) {
        log.debug("REST request to search Authors for query {}", query);
        return authorService.search(query);
    }

}
