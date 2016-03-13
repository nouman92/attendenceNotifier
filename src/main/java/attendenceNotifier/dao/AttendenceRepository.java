package attendenceNotifier.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import attendenceNotifier.model.Attendence;
import attendenceNotifier.model.ClassObj;
@Repository
public interface AttendenceRepository extends CrudRepository<Attendence,Integer>{
	
	@Query( value = "SELECT count(id) FROM attendance_system.attendance where class_id = ?  and date = ? " , nativeQuery = true )
	int findTotal(int classId,String date);

	List<Attendence> findByClasAndDate(ClassObj clas, Date date);
	@Query( value = "SELECT * FROM attendance_system.attendance where attendence = ? and class_id = ?  and date = ? " , nativeQuery = true )
	List<Attendence> findByAttendenceClasAndDate(boolean atendence,String classId,String date);
	
	@Query( value = "SELECT * FROM attendance_system.attendance where class_id = ?  and date = ? " , nativeQuery = true )
	List<Attendence> findByClasAndDate(String classId,String date);
	
	@Query( value = "SELECT * FROM attendance_system.attendance where class_id = ?  and date = ? and student_id = ? " , nativeQuery = true )
	Attendence findByClasAndDateAndStudent(String classId,String date,String Student);
	
	@Query( value = "SELECT * FROM attendance_system.attendance where class_id = ?  and date >= ? and date <= ? " , nativeQuery = true )
	List<Attendence> findByClasAndBetweenDates(String classId,String startDate,String endDate);
	
	@Query( value = "SELECT count(id) FROM attendance_system.attendance where attendence = ? and class_id = ?  and date = ? " , nativeQuery = true )
	int getAttendenceCount(boolean atendence,int classId,String date);
}				