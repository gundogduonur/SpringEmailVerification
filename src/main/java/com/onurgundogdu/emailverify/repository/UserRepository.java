package com.onurgundogdu.emailverify.repository;

import com.onurgundogdu.emailverify.entity.AppUser;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface UserRepository {
    Optional<AppUser> findByEmail(String email);
}