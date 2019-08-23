package com.github.kubikrubikvkube.genderResolver.surname;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SurnameEntity {
    @Id
    private Long id;

    private String surname;

    private String sex;
}
