package com.dj.atm.core.model;

import com.dj.atm.core.converter.CustomLongDeserializer;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

import java.io.Serializable;

/**
 * Represents an entity in the system.
 */
public abstract class Entity implements Serializable {
    private Long id;

    public Long getId() {
        return id;
    }

    @JsonDeserialize(using = CustomLongDeserializer.class)
    public void setId(Long id) {
        this.id = id;
    }
}
