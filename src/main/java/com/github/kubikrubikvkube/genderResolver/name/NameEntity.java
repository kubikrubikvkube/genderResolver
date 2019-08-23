
package com.github.kubikrubikvkube.genderResolver.name;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "names")
public class NameEntity {
    @Id
    private Long id;

    private String name;

    private String sex;
}
