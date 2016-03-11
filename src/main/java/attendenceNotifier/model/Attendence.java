package attendenceNotifier.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the attendance database table.
 * 
 */
@Entity
@Table(name="attendance")
@NamedQuery(name="Attendence.findAll", query="SELECT a FROM Attendence a")
public class Attendence implements Serializable {
	private static final long serialVersionUID = 1L;
	private String class_;
	private String date;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String section;
	private String timeIn;
	private String timeOut;
	private ClassObj clas;
	private Student student;
	private boolean attendance;
	public boolean isAttendance() {
		return attendance;
	}


	public void setAttendance(boolean attendance) {
		this.attendance = attendance;
	}


	public Attendence() {
	}


	@Column(name="class")
	public String getClass_() {
		return this.class_;
	}

	public void setClass_(String class_) {
		this.class_ = class_;
	}


	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}


	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getSection() {
		return this.section;
	}

	public void setSection(String section) {
		this.section = section;
	}


	@Column(name="time_in")
	public String getTimeIn() {
		return this.timeIn;
	}

	public void setTimeIn(String timeIn) {
		this.timeIn = timeIn;
	}


	@Column(name="time_out")
	public String getTimeOut() {
		return this.timeOut;
	}

	public void setTimeOut(String timeOut) {
		this.timeOut = timeOut;
	}


	//bi-directional many-to-one association to Clas
	@ManyToOne
	@JoinColumn(name="class_id")
	public ClassObj getClas() {
		return this.clas;
	}

	public void setClas(ClassObj clas) {
		this.clas = clas;
	}


	//bi-directional many-to-one association to Student
	@ManyToOne
	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}