package org.bookstore.store.rest;

import io.swagger.annotations.*;
import org.bookstore.store.rest.errors.BadRequestAlertException;
import org.bookstore.store.service.CategoryService;
import org.bookstore.store.service.dto.CategoryDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
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
 * REST controller for managing Category.
 */
@ApplicationScoped
@Path("categories")
@Api(value = "categories", description = "Operations for categories.")
public class CategoryResource {

    private final Logger log = LoggerFactory.getLogger(CategoryResource.class);

    private static final String ENTITY_NAME = "category";

    private final CategoryService categoryService;

    public CategoryResource(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * POST  /categories : Create a new category.
     *
     * @param categoryDTO the categoryDTO to create
     * @return the Response with status 201 (Created) and with body the new categoryDTO, or with status 400 (Bad Request) if the category has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Create a category", response = CategoryDTO.class)
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "The category is created"),
        @ApiResponse(code = 400, message = "Invalid input"),
        @ApiResponse(code = 415, message = "Format is not JSon")
    })
    public Response createCategory(@ApiParam(value = "Category to be created", required = true) @Valid CategoryDTO categoryDTO, @Context UriInfo uriInfo) throws URISyntaxException {
        log.debug("REST request to save Category : {}", categoryDTO);
        if (categoryDTO.getId() != null) {
            throw new BadRequestAlertException("A new category cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CategoryDTO result = categoryService.save(categoryDTO);
        URI createdURI = uriInfo.getBaseUriBuilder().path(String.valueOf(result.getId())).build();
        return Response.created(createdURI).entity(result).build();
    }

    /**
     * PUT  /categories : Updates an existing category.
     *
     * @param categoryDTO the categoryDTO to update
     * @return the Response with status 200 (OK) and with body the updated categoryDTO,
     * or with status 400 (Bad Request) if the categoryDTO is not valid,
     * or with status 500 (Internal Server Error) if the categoryDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PUT
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Update a category", response = CategoryDTO.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "The category is updated"),
        @ApiResponse(code = 400, message = "Invalid input")
    })
    public Response updateCategory(@ApiParam(value = "Category to be updated", required = true) @Valid CategoryDTO categoryDTO) throws URISyntaxException {
        log.debug("REST request to update Category : {}", categoryDTO);
        if (categoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CategoryDTO result = categoryService.save(categoryDTO);
        return Response.ok(result).build();
    }

    /**
     * GET  /categories : get all the categories.
     *
     * @return the Response with status 200 (OK) and the list of categories in body
     */
    @GET
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Find all authors", response = CategoryDTO.class, responseContainer = "List")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "All categories found"),
        @ApiResponse(code = 404, message = "Categories not found")}
    )
    public Response getAllCategories() {
        log.debug("REST request to get all Categories");
        List<CategoryDTO> result = categoryService.findAll();
        return ok(result).build();
    }

    /**
     * GET  /categories/:id : get the "id" category.
     *
     * @param id the id of the categoryDTO to retrieve
     * @return the Response with status 200 (OK) and with body the categoryDTO, or with status 404 (Not Found)
     */
    @GET
    @Path("/{id : \\d+}")
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Find a category by the Id.", response = CategoryDTO.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Category found"),
        @ApiResponse(code = 400, message = "Invalid input"),
        @ApiResponse(code = 404, message = "Category not found")
    })
    public Response getCategory(@PathParam("id") Long id) {
        log.debug("REST request to get Category : {}", id);
        Optional<CategoryDTO> categoryDTO = categoryService.findOne(id);
        return ofNullable(categoryDTO)
            .map(Response::ok)
            .orElse(status(NOT_FOUND))
            .build();
    }

    /**
     * DELETE  /categories/:id : delete the "id" category.
     *
     * @param id the id of the categoryDTO to delete
     * @return the Response with status 200 (OK)
     */
    @DELETE
    @Path("/{id : \\d+}")
    @ApiOperation(value = "Delete a category")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "Category has been deleted"),
        @ApiResponse(code = 400, message = "Invalid input")
    })
    public Response deleteCategory(@PathParam("id")  Long id) {
        log.debug("REST request to delete Category : {}", id);
        categoryService.delete(id);
        return noContent().build();
    }
}
