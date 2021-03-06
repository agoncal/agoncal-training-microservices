package org.bookstore.toprated.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A Book.
 */
@Entity
@Table(name = "top_book")
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
    @Min(value = 1)
    @Max(value = 5)
    @Column(name = "rate", nullable = false)
    private Integer rate;

    @NotNull
    @Column(name = "last_rate_update", nullable = false)
    private Instant lastRateUpdate;

    @Column(name = "small_image_url")
    private String smallImageURL;

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

    public Integer getRate() {
        return rate;
    }

    public Book rate(Integer rate) {
        this.rate = rate;
        return this;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Instant getLastRateUpdate() {
        return lastRateUpdate;
    }

    public Book lastRateUpdate(Instant lastRateUpdate) {
        this.lastRateUpdate = lastRateUpdate;
        return this;
    }

    public void setLastRateUpdate(Instant lastRateUpdate) {
        this.lastRateUpdate = lastRateUpdate;
    }

    public String getSmallImageURL() {
        return smallImageURL;
    }

    public Book smallImageURL(String smallImageURL) {
        this.smallImageURL = smallImageURL;
        return this;
    }

    public void setSmallImageURL(String smallImageURL) {
        this.smallImageURL = smallImageURL;
    }

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
            ", rate=" + getRate() +
            ", lastRateUpdate='" + getLastRateUpdate() + "'" +
            ", smallImageURL='" + getSmallImageURL() + "'" +
            "}";
    }
}
