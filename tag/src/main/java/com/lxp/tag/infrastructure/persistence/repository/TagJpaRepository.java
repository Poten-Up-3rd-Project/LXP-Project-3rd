package com.lxp.tag.infrastructure.persistence.repository;

import com.lxp.tag.infrastructure.persistence.entity.TagJpaEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TagJpaRepository extends JpaRepository<TagJpaEntity, Long> {
    Optional<TagJpaEntity> findByName(String name);

    @Query("select t.name from TagJpaEntity t where t.id = :id")
    Optional<String> findNameOnlyById(@Param("id") Long id);

    @Query("select t.id from TagJpaEntity t where t.name = :name")
    Optional<Long> findIdOnlyByName(@Param("name") String name);

    List<TagJpaEntity> findByIdIn(List<Long> ids);
}
