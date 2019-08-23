package com.github.kubikrubikvkube.genderResolver.name;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.Table;
import java.util.Collection;


@Service
@Table(name = "public.russian_names")
public interface NameRepository extends Repository<NameEntity, Long> {

    @Query(value = "SELECT * FROM public.russian_names", nativeQuery = true)
    Collection<NameEntity> findAll();

    @Query(value = "SELECT sex FROM public.russian_names WHERE name ILIKE ?1", nativeQuery = true)
    String findSexByName(String name);
}
