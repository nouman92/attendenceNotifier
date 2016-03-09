package attendenceNotifier.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the students database table.
 * 
 */
@Entity
@Table(name="students")
@NamedQuery(name="Student.findAll", query="SELECT s FROM Student s")
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="class")
	private String class_;

	private String name;

	@Column(name="roll_no")
	private String rollNo;

	private String section;

	//bi-directional many-to-one association to Attendance
	@OneToMany(mappedBy="student", fetch=FetchType.EAGER)
	private List<Attendence> attendances;

	//bi-directional many-to-one association to Clas
	@ManyToOne
	@JoinColumn(name="class_id")
	private ClassObj clas;

	//bi-directional many-to-one association to Parent
	@ManyToOne
	@JoinColumn(name="parents_id")
	private Parent parent;

	public Student() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClass_() {
		return this.class_;
	}

	public void setClass_(String class_) {
		this.class_ = class_;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRollNo() {
		return this.rollNo;
	}

	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
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
		attendance.setStudent(this);

		return attendance;
	}

	public Attendence removeAttendance(Attendence attendance) {
		getAttendances().remove(attendance);
		attendance.setStudent(null);

		return attendance;
	}

	public ClassObj getClas() {
		return this.clas;
	}

	public void setClas(ClassObj clas) {
		this.clas = clas;
	}

	public Parent getParent() {
		return this.parent;
	}

	public void setParent(Parent parent) {
		this.parent = parent;
	}

}