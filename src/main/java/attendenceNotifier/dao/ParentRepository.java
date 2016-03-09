package attendenceNotifier.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import attendenceNotifier.model.Parent;

@Repository
public interface ParentRepository  extends CrudRepository<Parent,Integer>{

}
