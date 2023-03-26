package com.helloworld.respository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.helloworld.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{
    Address findById(long id);
}
