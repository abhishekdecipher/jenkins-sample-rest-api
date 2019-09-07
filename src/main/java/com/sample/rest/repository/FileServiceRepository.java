package com.sample.rest.repository;

import com.sample.rest.domain.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface FileServiceRepository extends JpaRepository<Asset, String> {

  @Override
  <S extends Asset> S save(S s);

  @Transactional
  @Modifying
  @Query(value = "update asset set location = ?2 where id =?1",
    nativeQuery = true)
  void update(String id, String location);


}
