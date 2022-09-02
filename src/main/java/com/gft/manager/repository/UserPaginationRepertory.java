package com.gft.manager.repository;

import com.gft.manager.model.RoleName;
import com.gft.manager.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPaginationRepertory extends PagingAndSortingRepository<User,Long> {
    Page<User> findAllByUsernameStartingWith(String username, Pageable pageable);
    Page<User>findAllByUsernameStartingWithOrEmailStartingWithOrFullNameStartingWith(String username, String email, String fullName, Pageable pageable);
    Page<User>findAllByUsernameStartingWithOrEmailStartingWithOrFullNameStartingWithAndRolesRole(String username, String email, String fullName, RoleName role, Pageable pageable);
    Page<User>findAllByRolesRole(RoleName roles_role, Pageable pageable);
}
