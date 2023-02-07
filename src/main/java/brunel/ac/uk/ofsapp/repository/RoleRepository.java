package brunel.ac.uk.ofsapp.repository;

import brunel.ac.uk.ofsapp.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
