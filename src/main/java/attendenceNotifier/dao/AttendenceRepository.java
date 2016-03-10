package attendenceNotifier.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import attendenceNotifier.model.Attendence;
import attendenceNotifier.model.ClassObj;
@Repository
public interface AttendenceRepository extends CrudRepository<Attendence,Integer>{
	List<Attendence> findByClasAndDate(ClassObj clas, Date date);
}