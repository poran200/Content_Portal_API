package com.gft.manager.repository;

import com.gft.manager.model.token.EmailVerificationToken;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface EmailVerificationTokenRepository extends MongoRepository<EmailVerificationToken, String> {

    Optional<EmailVerificationToken> findByToken(String token);
}
