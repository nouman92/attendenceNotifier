package attendenceNotifier.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the clases database table.
 * 
 */
@Entity
@Table(name="clases")
@NamedQuery(name="ClassObj.findAll", query="SELECT c FROM ClassObj c")
public class ClassObj implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String name;

	private String section;

	//bi-directional many-to-one association to Attendance
	@OneToMany(mappedBy="clas", fetch=FetchType.EAGER)
	private List<Attendence> attendances;

	//bi-directional many-to-one association to Student
	@OneToMany(mappedBy="clas", fetch=FetchType.EAGER)
	private List<Student> students;

	public ClassObj() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSection() {
		return this.section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public List<Attendence> getAttendances() {
		return this.attendances;
	}

	public void setAttendances(List<Attendence> attendances) {
		this.attendances = attendances;
	}

	public Attendence addAttendance(Attendence attendance) {
		getAttendances().add(attendance);
		attendance.setClas(this);

		return attendance;
	}

	public Attendence removeAttendance(Attendence attendance) {
		getAttendances().remove(attendance);
		attendance.setClas(null);

		return attendance;
	}

	public List<Student> getStudents() {
		return this.students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public Student addStudent(Student student) {
		getStudents().add(student);
		student.setClas(this);

		return student;
	}

	public Student removeStudent(Student student) {
		getStudents().remove(student);
		student.setClas(null);

		return student;
	}

}