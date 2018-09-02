package org.bookstore.store.rest;

import io.swagger.annotations.*;
import org.bookstore.store.rest.errors.BadRequestAlertException;
import org.bookstore.store.service.AuthorService;
import org.bookstore.store.service.dto.AuthorDTO;
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
import static javax.ws.rs.core.Response.noContent;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.status;

/**
 * REST controller for managing Author.
 */
@ApplicationScoped
@Path("authors")
@Api(value = "authors", description = "Operations for authors.")
public class AuthorResource {

    private final Logger log = LoggerFactory.getLogger(AuthorResource.class);

    private static final String ENTITY_NAME = "author";

    private final AuthorService authorService;

    @Inject
    public AuthorResource(AuthorService authorService) {
        this.authorService = authorService;
    }

    /**
     * POST  /authors : Create a new author.
     *
     * @param authorDTO the authorDTO to create
     * @return the Response with status 201 (Created) and with body the new authorDTO, or with status 400 (Bad Request) if the author has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Create an author", response = AuthorDTO.class)
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "The author is created"),
        @ApiResponse(code = 400, message = "Invalid input"),
        @ApiResponse(code = 415, message = "Format is not JSon")
    })
    public Response createAuthor(@ApiParam(value = "Author to be created", required = true) @Valid AuthorDTO authorDTO, @Context UriInfo uriInfo) throws URISyntaxException {
        log.debug("REST request to save Author : {}", authorDTO);
        if (authorDTO.getId() != null) {
            throw new BadRequestAlertException("A new author cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AuthorDTO result = authorService.save(authorDTO);
        URI createdURI = uriInfo.getBaseUriBuilder().path(String.valueOf(result.getId())).build();
        return Response.created(createdURI).entity(result).build();
    }

    /**
     * PUT  /authors : Updates an existing author.
     *
     * @param authorDTO the authorDTO to update
     * @return the Response with status 200 (OK) and with body the updated authorDTO,
     * or with status 400 (Bad Request) if the authorDTO is not valid,
     * or with status 500 (Internal Server Error) if the authorDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PUT
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Update an author", response = AuthorDTO.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "The author is updated"),
        @ApiResponse(code = 400, message = "Invalid input")
    })
    public Response updateAuthor(@ApiParam(value = "Author to be updated", required = true) @Valid AuthorDTO authorDTO) throws URISyntaxException {
        log.debug("REST request to update Author : {}", authorDTO);
        if (authorDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AuthorDTO result = authorService.save(authorDTO);
        return Response.ok(result).build();
    }

    /**
     * GET  /authors : get all the authors.
     *
     * @return the Response with status 200 (OK) and the list of authors in body
     */
    @GET
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Find all authors", response = AuthorDTO.class, responseContainer = "List")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "All authors found"),
        @ApiResponse(code = 404, message = "Authors not found")}
    )
    public Response getAllAuthors() {
        log.debug("REST request to get all Authors");
        List<AuthorDTO> result = authorService.findAll();
        return ok(result).build();
    }

    /**
     * GET  /authors/:id : get the "id" author.
     *
     * @param id the id of the authorDTO to retrieve
     * @return the Response with status 200 (OK) and with body the authorDTO, or with status 404 (Not Found)
     */
    @GET
    @Path("/{id : \\d+}")
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Find an author by the Id.", response = AuthorDTO.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Author found"),
        @ApiResponse(code = 400, message = "Invalid input"),
        @ApiResponse(code = 404, message = "Author not found")
    })
    public Response getAuthor(@PathParam("id") Long id) {
        log.debug("REST request to get Author : {}", id);
        Optional<AuthorDTO> authorDTO = authorService.findOne(id);
        return ofNullable(authorDTO)
            .map(Response::ok)
            .orElse(status(NOT_FOUND))
            .build();
    }

    /**
     * DELETE  /authors/:id : delete the "id" author.
     *
     * @param id the id of the authorDTO to delete
     * @return the Response with status 200 (OK)
     */
    @DELETE
    @Path("/{id : \\d+}")
    @ApiOperation(value = "Delete an author")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "Author has been deleted"),
        @ApiResponse(code = 400, message = "Invalid input")
    })
    public Response deleteAuthor(@PathParam("id") Long id) {
        log.debug("REST request to delete Author : {}", id);
        authorService.delete(id);
        return noContent().build();
    }
}
