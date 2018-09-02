package org.bookstore.toprated.service.mapper;

import org.bookstore.toprated.domain.Book;
import org.bookstore.toprated.service.dto.BookDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity Book and its DTO BookDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BookMapper extends EntityMapper<BookDTO, Book> {



    default Book fromId(Long id) {
        if (id == null) {
            return null;
        }
        Book book = new Book();
        book.setId(id);
        return book;
    }
}
