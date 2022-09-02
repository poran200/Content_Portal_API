package com.gft.manager.repository;

import com.gft.manager.model.Role;
import com.gft.manager.model.RoleName;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {
     Optional<Role> findByRole(RoleName roleName);
//     Page<?> getAllByAdminRoleIsTrue(Pageable pageable);
}
