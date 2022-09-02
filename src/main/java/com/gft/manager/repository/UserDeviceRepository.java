package com.gft.manager.repository;

import com.gft.manager.model.UserDevice;
import com.gft.manager.model.token.RefreshToken;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserDeviceRepository extends MongoRepository<UserDevice, String> {

    @Override
    Optional<UserDevice> findById(String id);

    Optional<UserDevice> findByRefreshToken(RefreshToken refreshToken);

    Optional<UserDevice> findByUserId(String userId);
}
