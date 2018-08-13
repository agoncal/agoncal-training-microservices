package org.bookstore.store.service.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Publisher entity.
 */
public class PublisherDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(min = 2, max = 30)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PublisherDTO publisherDTO = (PublisherDTO) o;
        if (publisherDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), publisherDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PublisherDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
