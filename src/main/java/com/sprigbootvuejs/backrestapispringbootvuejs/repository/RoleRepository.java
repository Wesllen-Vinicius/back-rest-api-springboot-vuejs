package com.sprigbootvuejs.backrestapispringbootvuejs.repository;

import java.util.Optional;

import com.sprigbootvuejs.backrestapispringbootvuejs.models.ERole;
import com.sprigbootvuejs.backrestapispringbootvuejs.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
