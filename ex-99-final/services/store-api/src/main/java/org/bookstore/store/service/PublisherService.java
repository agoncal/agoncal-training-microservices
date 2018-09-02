package org.bookstore.store.service;

import org.bookstore.store.domain.Publisher;
import org.bookstore.store.repository.PublisherRepository;
import org.bookstore.store.service.dto.PublisherDTO;
import org.bookstore.store.service.mapper.PublisherMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static javax.transaction.Transactional.TxType.SUPPORTS;

/**
 * Service Implementation for managing Publisher.
 */
@Transactional
public class PublisherService {

    private final Logger log = LoggerFactory.getLogger(PublisherService.class);

    private final PublisherRepository publisherRepository;

    private final PublisherMapper publisherMapper;

    public PublisherService(PublisherRepository publisherRepository, PublisherMapper publisherMapper) {
        this.publisherRepository = publisherRepository;
        this.publisherMapper = publisherMapper;
    }

    /**
     * Save a publisher.
     *
     * @param publisherDTO the entity to save
     * @return the persisted entity
     */
    public PublisherDTO save(PublisherDTO publisherDTO) {
        log.debug("Request to save Publisher : {}", publisherDTO);
        Publisher publisher = publisherMapper.toEntity(publisherDTO);
        publisher = publisherRepository.save(publisher);
        PublisherDTO result = publisherMapper.toDto(publisher);
        return result;
    }

    /**
     * Get all the publishers.
     *
     * @return the list of entities
     */
    @Transactional(SUPPORTS)
    public List<PublisherDTO> findAll() {
        log.debug("Request to get all Publishers");
        return null;
//
//        return publisherRepository.findAll()
//            .map(publisherMapper::toDto);
    }


    /**
     * Get one publisher by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(SUPPORTS)
    public Optional<PublisherDTO> findOne(Long id) {
        log.debug("Request to get Publisher : {}", id);
        return publisherRepository.findById(id)
            .map(publisherMapper::toDto);
    }

    /**
     * Delete the publisher by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Publisher : {}", id);
        publisherRepository.deleteById(id);
    }
}
