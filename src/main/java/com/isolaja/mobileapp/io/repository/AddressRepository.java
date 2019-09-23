package com.isolaja.mobileapp.io.repository;

import com.isolaja.mobileapp.io.entity.AddressEntity;
import com.isolaja.mobileapp.io.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends CrudRepository<AddressEntity, Long> {
    List<AddressEntity> findAllByUserDetails(UserEntity userEntity);
}
