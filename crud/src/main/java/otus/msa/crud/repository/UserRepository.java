package otus.msa.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import otus.msa.crud.model.User;

public interface UserRepository extends JpaRepository<User,Long> {
}
