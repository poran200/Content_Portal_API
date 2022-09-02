package com.gft.manager.repository;

import com.gft.manager.model.token.RefreshToken;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RefreshTokenRepository extends MongoRepository<RefreshToken, String> {

    @Override
    Optional<RefreshToken> findById(String id);

    Optional<RefreshToken> findByToken(String token);
    Optional<RefreshToken> findByUserId(String userId);

}
