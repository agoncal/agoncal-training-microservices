package org.bookstore.inventory.domain;


import org.bookstore.inventory.domain.enumeration.Warehouse;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Book.
 */
@Entity
@Table(name = "inv_book")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 15)
    @Column(name = "isbn", length = 15, nullable = false)
    private String isbn;

    @NotNull
    @Size(min = 2, max = 300)
    @Column(name = "title", length = 300, nullable = false)
    private String title;

    @NotNull
    @Min(value = 0)
    @Column(name = "nb_of_copies", nullable = false)
    private Integer nbOfCopies;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "warehouse", nullable = false)
    private Warehouse warehouse;

    @Column(name = "location")
    private String location;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public Book isbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public Book title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getNbOfCopies() {
        return nbOfCopies;
    }

    public Book nbOfCopies(Integer nbOfCopies) {
        this.nbOfCopies = nbOfCopies;
        return this;
    }

    public void setNbOfCopies(Integer nbOfCopies) {
        this.nbOfCopies = nbOfCopies;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public Book warehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
        return this;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public String getLocation() {
        return location;
    }

    public Book location(String location) {
        this.location = location;
        return this;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Book book = (Book) o;
        if (book.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), book.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Book{" +
            "id=" + getId() +
            ", isbn='" + getIsbn() + "'" +
            ", title='" + getTitle() + "'" +
            ", nbOfCopies=" + getNbOfCopies() +
            ", warehouse='" + getWarehouse() + "'" +
            ", location='" + getLocation() + "'" +
            "}";
    }
}
