package com.github.kubikrubikvkube.genderResolver.name;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.Table;
import java.util.Collection;


/**
 * This is repository for H2 in-memory names DB
 */
@Service
@Table(name = "public.russian_names")
public interface NameRepository extends Repository<NameEntity, Long> {

    /**
     * Find all entries
     *
     * @return all entries
     */
    @Query(value = "SELECT * FROM public.russian_names", nativeQuery = true)
    Collection<NameEntity> findAll();

    /**
     * Find gender info using name (case insensitive)
     * @param name name, e.x. "Василиса"
     * @return resolved gender, e.x. "Ж"
     */
    @Query(value = "SELECT sex FROM public.russian_names WHERE name ILIKE ?1 LIMIT 1", nativeQuery = true)
    String findSexByName(String name);
}
