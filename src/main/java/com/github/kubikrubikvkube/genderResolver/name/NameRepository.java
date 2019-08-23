package com.github.kubikrubikvkube.genderResolver.name;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface NameRepository extends CrudRepository<NameEntity, Long> {
    @Query(value = "SELECT * FROM public.russian_names", nativeQuery = true)
    Collection<NameEntity> foo();
}
