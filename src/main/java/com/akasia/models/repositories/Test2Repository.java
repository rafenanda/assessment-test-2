package com.akasia.models.repositories;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.akasia.models.entities.Test2;

@Repository
public interface Test2Repository extends JpaRepository<Test2, String> {
    Optional<Test2> findById(String id);

    @Modifying
    @Query(value = "update Test2 t set t.a = case when t.a = true then false when t.a = false then true else t.a end where t.id = :id", nativeQuery = true)
    void invertA(@Param("id") int id);

    @Modifying
    @Query(value = "update Test2 t set t.b = case when t.b = true then false when t.b = false then true else t.b end where t.id = :id", nativeQuery = true)
    void invertB(@Param("id") int id);

    @Modifying
    @Query(value = "update Test2 t set t.c = case when t.c = true then false when t.c = false then true else t.c end where t.id = :id", nativeQuery = true)
    void invertC(@Param("id") int id);

    @Modifying
    @Query(value = "update Test2 t set t.d = case when t.d = true then false when t.d = false then true else t.d end where t.id = :id", nativeQuery = true)
    void invertD(@Param("id") int id);

    @Modifying
    @Query(value = "update Test2 t set t.e = case when t.e = true then false when t.e = false then true else t.e end where t.id = :id", nativeQuery = true)
    void invertE(@Param("id") int id);
}
