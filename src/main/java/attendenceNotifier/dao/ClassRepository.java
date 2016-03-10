package attendenceNotifier.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import attendenceNotifier.model.ClassObj;

@Repository
public interface ClassRepository  extends CrudRepository<ClassObj,Integer>{
	List<ClassObj> findAll();
}