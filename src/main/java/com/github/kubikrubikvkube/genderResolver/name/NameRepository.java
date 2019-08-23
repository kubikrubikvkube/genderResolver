package com.github.kubikrubikvkube.genderResolver.name;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import javax.persistence.Table;
import java.util.Collection;

@org.springframework.stereotype.Repository
@Table(name = "russian_names", schema = "public")
public interface NameRepository extends Repository<NameEntity, Long> {

    @Query(value = "SELECT * FROM public.russian_names", nativeQuery = true)
    Collection<NameEntity> foo();
}
