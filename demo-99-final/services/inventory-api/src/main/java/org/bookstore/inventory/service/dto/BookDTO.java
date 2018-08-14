package org.bookstore.inventory.service.dto;

import org.bookstore.inventory.domain.enumeration.Warehouse;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Book entity.
 */
public class BookDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 15)
    private String isbn;

    @NotNull
    @Size(min = 2, max = 200)
    private String title;

    @NotNull
    @Min(value = 0)
    private Integer nbOfCopies;

    @NotNull
    private Warehouse warehouse;

    private String location;

    private String row;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getNbOfCopies() {
        return nbOfCopies;
    }

    public void setNbOfCopies(Integer nbOfCopies) {
        this.nbOfCopies = nbOfCopies;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BookDTO bookDTO = (BookDTO) o;
        if (bookDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), bookDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BookDTO{" +
            "id=" + getId() +
            ", isbn='" + getIsbn() + "'" +
            ", title='" + getTitle() + "'" +
            ", nbOfCopies=" + getNbOfCopies() +
            ", warehouse='" + getWarehouse() + "'" +
            ", location='" + getLocation() + "'" +
            ", row='" + getRow() + "'" +
            "}";
    }
}
