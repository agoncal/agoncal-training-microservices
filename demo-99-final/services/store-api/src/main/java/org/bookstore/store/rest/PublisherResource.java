package org.bookstore.store.rest;

import io.swagger.annotations.*;
import org.bookstore.store.rest.errors.BadRequestAlertException;
import org.bookstore.store.service.PublisherService;
import org.bookstore.store.service.dto.PublisherDTO;
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
 * REST controller for managing Publisher.
 */
@ApplicationScoped
@Path("publishers")
@Api(value = "publishers", description = "Operations for publishers.")
public class PublisherResource {

    private final Logger log = LoggerFactory.getLogger(PublisherResource.class);

    private static final String ENTITY_NAME = "publisher";

    private final PublisherService publisherService;

    @Inject
    public PublisherResource(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    /**
     * POST  /publishers : Create a new publisher.
     *
     * @param publisherDTO the publisherDTO to create
     * @return the Response with status 201 (Created) and with body the new publisherDTO, or with status 400 (Bad Request) if the publisher has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Create a publisher", response = PublisherDTO.class)
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "The publisher is created"),
        @ApiResponse(code = 400, message = "Invalid input"),
        @ApiResponse(code = 415, message = "Format is not JSon")
    })
    public Response createPublisher(@ApiParam(value = "Publisher to be created", required = true) @Valid PublisherDTO publisherDTO, @Context UriInfo uriInfo) throws URISyntaxException {
        log.debug("REST request to save Publisher : {}", publisherDTO);
        if (publisherDTO.getId() != null) {
            throw new BadRequestAlertException("A new publisher cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PublisherDTO result = publisherService.save(publisherDTO);
        URI createdURI = uriInfo.getBaseUriBuilder().path(String.valueOf(result.getId())).build();
        return Response.created(createdURI).entity(result).build();
    }

    /**
     * PUT  /publishers : Updates an existing publisher.
     *
     * @param publisherDTO the publisherDTO to update
     * @return the Response with status 200 (OK) and with body the updated publisherDTO,
     * or with status 400 (Bad Request) if the publisherDTO is not valid,
     * or with status 500 (Internal Server Error) if the publisherDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PUT
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Update a publisher", response = PublisherDTO.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "The publisher is updated"),
        @ApiResponse(code = 400, message = "Invalid input")
    })
    public Response updatePublisher(@ApiParam(value = "Publisher to be updated", required = true)@Valid PublisherDTO publisherDTO) throws URISyntaxException {
        log.debug("REST request to update Publisher : {}", publisherDTO);
        if (publisherDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PublisherDTO result = publisherService.save(publisherDTO);
        return Response.ok(result).build();
    }

    /**
     * GET  /publishers : get all the publishers.
     *
     * @return the Response with status 200 (OK) and the list of publishers in body
     */
    @GET
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Find all authors", response = PublisherDTO.class, responseContainer = "List")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "All publishers found"),
        @ApiResponse(code = 404, message = "Publishers not found")}
    )
    public Response getAllPublishers() {
        log.debug("REST request to get all Publishers");
        List<PublisherDTO> result = publisherService.findAll();
        return ok(result).build();
    }

    /**
     * GET  /publishers/:id : get the "id" publisher.
     *
     * @param id the id of the publisherDTO to retrieve
     * @return the Response with status 200 (OK) and with body the publisherDTO, or with status 404 (Not Found)
     */
    @GET
    @Path("/{id : \\d+}")
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Find a publisher by the Id.", response = PublisherDTO.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Publisher found"),
        @ApiResponse(code = 400, message = "Invalid input"),
        @ApiResponse(code = 404, message = "Publisher not found")
    })
    public Response getPublisher(@PathParam("id") Long id) {
        log.debug("REST request to get Publisher : {}", id);
        Optional<PublisherDTO> publisherDTO = publisherService.findOne(id);
        return ofNullable(publisherDTO)
            .map(Response::ok)
            .orElse(status(NOT_FOUND))
            .build();
    }

    /**
     * DELETE  /publishers/:id : delete the "id" publisher.
     *
     * @param id the id of the publisherDTO to delete
     * @return the Response with status 200 (OK)
     */
    @DELETE
    @Path("/{id : \\d+}")
    @ApiOperation(value = "Delete a publisher")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "Publisher has been deleted"),
        @ApiResponse(code = 400, message = "Invalid input")
    })
    public Response deletePublisher(@PathParam("id") Long id) {
        log.debug("REST request to delete Publisher : {}", id);
        publisherService.delete(id);
        return noContent().build();
    }
}
