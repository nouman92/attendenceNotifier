package attendenceNotifier.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import attendenceNotifier.model.Attendence;
@Repository
public interface AttendenceRepository extends CrudRepository<Attendence,Integer>{

}
