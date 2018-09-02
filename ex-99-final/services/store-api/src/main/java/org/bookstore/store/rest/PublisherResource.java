package org.bookstore.store.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.web.util.ResponseUtil;
import org.bookstore.store.service.PublisherService;
import org.bookstore.store.service.dto.PublisherDTO;
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
 * REST controller for managing Publisher.
 */
@RestController
@RequestMapping("/api")
public class PublisherResource {

    private final Logger log = LoggerFactory.getLogger(PublisherResource.class);

    private static final String ENTITY_NAME = "publisher";

    private final PublisherService publisherService;

    public PublisherResource(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    /**
     * POST  /publishers : Create a new publisher.
     *
     * @param publisherDTO the publisherDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new publisherDTO, or with status 400 (Bad Request) if the publisher has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/publishers")
    @Timed
    public ResponseEntity<PublisherDTO> createPublisher(@Valid @RequestBody PublisherDTO publisherDTO) throws URISyntaxException {
        log.debug("REST request to save Publisher : {}", publisherDTO);
        if (publisherDTO.getId() != null) {
            throw new BadRequestAlertException("A new publisher cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PublisherDTO result = publisherService.save(publisherDTO);
        return ResponseEntity.created(new URI("/api/publishers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /publishers : Updates an existing publisher.
     *
     * @param publisherDTO the publisherDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated publisherDTO,
     * or with status 400 (Bad Request) if the publisherDTO is not valid,
     * or with status 500 (Internal Server Error) if the publisherDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/publishers")
    @Timed
    public ResponseEntity<PublisherDTO> updatePublisher(@Valid @RequestBody PublisherDTO publisherDTO) throws URISyntaxException {
        log.debug("REST request to update Publisher : {}", publisherDTO);
        if (publisherDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PublisherDTO result = publisherService.save(publisherDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, publisherDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /publishers : get all the publishers.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of publishers in body
     */
    @GetMapping("/publishers")
    @Timed
    public List<PublisherDTO> getAllPublishers() {
        log.debug("REST request to get all Publishers");
        return publisherService.findAll();
    }

    /**
     * GET  /publishers/:id : get the "id" publisher.
     *
     * @param id the id of the publisherDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the publisherDTO, or with status 404 (Not Found)
     */
    @GetMapping("/publishers/{id}")
    @Timed
    public ResponseEntity<PublisherDTO> getPublisher(@PathVariable Long id) {
        log.debug("REST request to get Publisher : {}", id);
        Optional<PublisherDTO> publisherDTO = publisherService.findOne(id);
        return ResponseUtil.wrapOrNotFound(publisherDTO);
    }

    /**
     * DELETE  /publishers/:id : delete the "id" publisher.
     *
     * @param id the id of the publisherDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/publishers/{id}")
    @Timed
    public ResponseEntity<Void> deletePublisher(@PathVariable Long id) {
        log.debug("REST request to delete Publisher : {}", id);
        publisherService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/publishers?query=:query : search for the publisher corresponding
     * to the query.
     *
     * @param query the query of the publisher search
     * @return the result of the search
     */
    @GetMapping("/_search/publishers")
    @Timed
    public List<PublisherDTO> searchPublishers(@RequestParam String query) {
        log.debug("REST request to search Publishers for query {}", query);
        return publisherService.search(query);
    }

}
