
package com.github.kubikrubikvkube.genderResolver.name;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class NameEntity {
    @Id
    private Long id;

    private String name;

    private String sex;
}
