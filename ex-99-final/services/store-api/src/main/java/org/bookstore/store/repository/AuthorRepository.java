package org.bookstore.store.repository;

import org.bookstore.store.domain.Author;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Author entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

}
