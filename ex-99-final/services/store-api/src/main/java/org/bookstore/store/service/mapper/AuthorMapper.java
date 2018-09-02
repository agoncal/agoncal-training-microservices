package org.bookstore.store.service.mapper;

import org.bookstore.store.domain.Author;
import org.bookstore.store.service.dto.AuthorDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity Author and its DTO AuthorDTO.
 */
@Mapper(componentModel = "cdi", uses = {})
public interface AuthorMapper extends EntityMapper<AuthorDTO, Author> {


    @Mapping(target = "books", ignore = true)
    Author toEntity(AuthorDTO authorDTO);

    default Author fromId(Long id) {
        if (id == null) {
            return null;
        }
        Author author = new Author();
        author.setId(id);
        return author;
    }
}
