package attendenceNotifier.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import attendenceNotifier.model.User;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {

}
