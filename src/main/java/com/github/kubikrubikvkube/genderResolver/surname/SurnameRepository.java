package com.github.kubikrubikvkube.genderResolver.surname;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurnameRepository extends CrudRepository<SurnameEntity, Long> {

}
