package com.jencys.apicreateuser.data.repository;

import com.jencys.apicreateuser.data.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, String> {
}
