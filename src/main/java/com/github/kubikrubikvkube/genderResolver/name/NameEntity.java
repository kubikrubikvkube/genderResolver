package com.github.kubikrubikvkube.genderResolver.name;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * This is H2 database entity
 */
@Entity
public class NameEntity {
    /**
     * Database ID
     */
    @Id
    private Long id;

    /**
     * Name, e.x. "Василиса"
     */
    private String name;

    /**
     * Gender info, e.x. "М" or "Ж"
     */
    private String sex;
}
