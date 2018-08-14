package org.bookstore.toprated.rest;

import io.swagger.annotations.*;
import org.bookstore.toprated.rest.errors.BadRequestAlertException;
import org.bookstore.toprated.service.BookService;
import org.bookstore.toprated.service.dto.BookDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.*;

/**
 * REST controller for managing Book.
 */
@ApplicationScoped
@Path("toprated")
@Api(value = "toprated", description = "Operations for toprated books.")
public class BookResource {

    private final Logger log = LoggerFactory.getLogger(BookResource.class);

    private static final String ENTITY_NAME = "book";

    private final BookService bookService;

    @Inject
    public BookResource(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * POST  /books : Create a new book.
     *
     * @param bookDTO the bookDTO to create
     * @return the Response with status 201 (Created) and with body the new bookDTO, or with status 400 (Bad Request) if the book has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Create a toprated book", response = BookDTO.class)
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "The toprated book is created"),
        @ApiResponse(code = 400, message = "Invalid input"),
        @ApiResponse(code = 415, message = "Format is not JSon")
    })
    public Response createBook(@ApiParam(value = "Toprated book to be created", required = true) @Valid BookDTO bookDTO, @Context UriInfo uriInfo) throws URISyntaxException {
        log.debug("REST request to save Book : {}", bookDTO);
        if (bookDTO.getId() != null) {
            throw new BadRequestAlertException("A new book cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BookDTO result = bookService.save(bookDTO);
        URI createdURI = uriInfo.getBaseUriBuilder().path(String.valueOf(result.getId())).build();
        return Response.created(createdURI).entity(result).build();
    }

    /**
     * PUT  /books : Updates an existing book.
     *
     * @param bookDTO the bookDTO to update
     * @return the Response with status 200 (OK) and with body the updated bookDTO,
     * or with status 400 (Bad Request) if the bookDTO is not valid,
     * or with status 500 (Internal Server Error) if the bookDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PUT
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Update a toprated Book", response = BookDTO.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "The toprated book is updated"),
        @ApiResponse(code = 400, message = "Invalid input")
    })
    public Response updateBook(@ApiParam(value = "Book to be updated", required = true) @Valid BookDTO bookDTO) throws URISyntaxException {
        log.debug("REST request to update Book : {}", bookDTO);
        if (bookDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BookDTO result = bookService.save(bookDTO);
        return Response.ok(result).build();
    }

    /**
     * GET  /books : get all the books.
     *
     * @return the Response with status 200 (OK) and the list of books in body
     */
    @GET
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Find all toprated books", response = BookDTO.class, responseContainer = "List")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "All toprated books found"),
        @ApiResponse(code = 404, message = "Toprated books not found")}
    )
    public Response getAllBooks() {
        log.debug("REST request to get all Books");
        List<BookDTO> result = bookService.findAll();
        return ok(result).build();
    }

    /**
     * GET  /books/:id : get the "id" book.
     *
     * @param id the id of the bookDTO to retrieve
     * @return the Response with status 200 (OK) and with body the bookDTO, or with status 404 (Not Found)
     */
    @GET
    @Path("/{id : \\d+}")
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Find a toprated book by the Id.", response = BookDTO.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Toprated Book found"),
        @ApiResponse(code = 400, message = "Invalid input"),
        @ApiResponse(code = 404, message = "Toprated Book not found")
    })
    public Response getBook(@PathParam("id") Long id) {
        log.debug("REST request to get Book : {}", id);
        Optional<BookDTO> bookDTO = bookService.findOne(id);
        return ofNullable(bookDTO)
            .map(Response::ok)
            .orElse(status(NOT_FOUND))
            .build();
    }

    /**
     * DELETE  /books/:id : delete the "id" book.
     *
     * @param id the id of the bookDTO to delete
     * @return the Response with status 200 (OK)
     */
    @DELETE
    @Path("/{id : \\d+}")
    @ApiOperation(value = "Delete a toprated book")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "Toprated Book has been deleted"),
        @ApiResponse(code = 400, message = "Invalid input")
    })
    public Response deleteBook(@PathParam("id") Long id) {
        log.debug("REST request to delete Book : {}", id);
        bookService.delete(id);
        return noContent().build();
    }
}
