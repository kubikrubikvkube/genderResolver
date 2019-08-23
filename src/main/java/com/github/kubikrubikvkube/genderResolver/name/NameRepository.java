package com.github.kubikrubikvkube.genderResolver.name;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface NameRepository extends CrudRepository<NameEntity, Long> {

}
