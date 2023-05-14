package edu.neu.csye6200.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import edu.neu.csye6200.model.Parent;
import edu.neu.csye6200.model.Student;
import edu.neu.csye6200.model.Teacher;
import edu.neu.csye6200.model.Vaccine;
import java.time.LocalDate;

/**
 * @author tanyashah
 *
 */
public class StudentDaoImpl {

	private Connection connection = null;
	// private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	private Connection getConnection() throws Exception {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/daycare?" + "user=root&password=password");
			return connection;

		} catch (Exception e) {
			throw e;
		}
	}

	public int addParent(Parent parent) throws Exception {
		connection = getConnection();
		preparedStatement = connection.prepareStatement(
				"insert into daycare.Parent(parent_id, first_name, last_name, email, phone ) values (default, ?, ?, ?, ?)",
				Statement.RETURN_GENERATED_KEYS);
		// preparedStatement.setInt(1, parent.getParentId());
		System.out.println("parents --->" + parent);
		preparedStatement.setString(1, parent.getFirstName());
		preparedStatement.setString(2, parent.getLastName());
		preparedStatement.setString(3, parent.getEmail());
		preparedStatement.setLong(4, parent.getPhone().longValue());
		int updated = preparedStatement.executeUpdate();
		resultSet = preparedStatement.getGeneratedKeys();
		int parentId = 0;
		while (resultSet.next()) {
			parentId = resultSet.getInt(1);
			;
		}
		System.out.println("Parent : " + updated);
		return parentId;
	}

	public int addStudent(Student student) throws Exception {
//		System.out.println("stuuudent --->" + student);
		connection = getConnection();
		preparedStatement = connection.prepareStatement(
				" insert into daycare.Student(student_id, first_name, last_name, address, dob, age, registration_date,teacher_assigned, parent_id) values (default,?,?,?,?,?,?,?,?)",
				Statement.RETURN_GENERATED_KEYS);
		// preparedStatement.setInt(1, student.getStudentId());
		preparedStatement.setString(1, student.getFirstName());
		preparedStatement.setString(2, student.getLastName());
		preparedStatement.setString(3, student.getAddress());
		preparedStatement.setDate(4, Date.valueOf(student.getDob()));
		preparedStatement.setInt(5, student.getAge());
		preparedStatement.setDate(6, Date.valueOf(student.getRegistrationDate()));
		preparedStatement.setInt(7, 1);
		preparedStatement.setInt(8, student.getParentId());

		preparedStatement.executeUpdate();
		resultSet = preparedStatement.getGeneratedKeys();
		int studentId = 0;
		while (resultSet.next()) {
			studentId = resultSet.getInt(1);
			;
		}
		List<Vaccine> vaccines = student.getImmunizationRecord();
		for (Vaccine vaccine : vaccines)
			try {
				vaccine.setStudentId(studentId);
				addStudentVaccinationRecord(vaccine);
			} catch (Exception e) {
				e.printStackTrace();
			}
		return studentId;

	}

	public Student getStudentById(int studentId) throws Exception {
		connection = getConnection();
		preparedStatement = connection
				.prepareStatement(
						"SELECT s.*,  p.first_name as p_first_name, p.last_name as p_last_name , p.phone , p.email \n"
						+ "FROM daycare.student s \n"
						+ "INNER JOIN daycare.parent p\n"
						+ "ON s.parent_id =p.parent_id  WHERE student_id = ? and is_deleted =0 ORDER BY student_id ;");
		preparedStatement.setInt(1, studentId);
		resultSet = preparedStatement.executeQuery();
		return writeStudentResultSet(resultSet).get(0);
	}

	public Student getLatestStudent() throws Exception {
		connection = getConnection();
		preparedStatement = connection
				.prepareStatement(
"SELECT s.*,  p.first_name as p_first_name, p.last_name as p_last_name , p.phone , p.email \n"
+ "FROM daycare.student s \n"
+ "INNER JOIN daycare.parent p\n"
+ "ON s.parent_id =p.parent_id ORDER BY student_id  DESC LIMIT 1;");
//		preparedStatement.setInt(1, studentId);
		resultSet = preparedStatement.executeQuery();
		return writeStudentResultSet(resultSet).get(0);
	}

//	SELECT fields FROM table ORDER BY id DESC LIMIT 1;
	public List<Student> getAllStudents() throws Exception {
		connection = getConnection();
		preparedStatement = connection.prepareStatement(
				"SELECT s.*,  p.first_name as p_first_name, p.last_name as p_last_name , p.phone , p.email \n"
						+ "FROM daycare.student s\n" + "INNER JOIN daycare.parent p\n"
						+ "ON s.parent_id =p.parent_id ;");
		resultSet = preparedStatement.executeQuery();
		return writeStudentResultSet(resultSet);
	}

	public void addStudentVaccinationRecord(Vaccine vaccine) throws Exception {
//		System.out.println("vacccc---> " + vaccine);
//		System.out.println("vaccccsqsdqw---> " + vaccine.getStudentId());
		connection = getConnection();
		preparedStatement = connection.prepareStatement(
				" insert into daycare.Vaccine (vaccine_id, name, doses_taken, total_doses,  doses_taken_dates, last_shot_date, upcoming_shot_date, student_id, is_vaccinated) "
						+ "    values (default, ?, ?,?,?,?,?,?,?)");
		preparedStatement.setString(1, vaccine.getName());
		preparedStatement.setInt(2, vaccine.getDosestaken());
		preparedStatement.setInt(3, vaccine.getTotalDoses());
		preparedStatement.setString(4, vaccine.getVaccinationRecord().toString());
		preparedStatement.setDate(5, Date.valueOf(vaccine.getLastShotDate()));

		//preparedStatement.setDate(6, Date.valueOf(vaccine.getNextShotDate()));
                Date nextshotDate = Date.valueOf(LocalDate.now());
                preparedStatement.setDate(6, nextshotDate);
		preparedStatement.setInt(7, vaccine.getStudentId());
		preparedStatement.setBoolean(8, vaccine.isVaccinated());

		int updated = preparedStatement.executeUpdate();
		System.out.println("Vaccine : " + updated);
	}

	public List<Vaccine> getVaccinesByStudentId(int studentId) throws Exception {
		connection = getConnection();
		preparedStatement = connection.prepareStatement("select * from daycare.vaccine where student_id=?");
		preparedStatement.setInt(1, studentId);
		resultSet = preparedStatement.executeQuery();
		return writeVaccineResultSet(resultSet);
	}

	public void updateVaccineByStudentIdAndVaccineId(Vaccine vaccine) throws Exception {
		connection = getConnection();
		preparedStatement = connection.prepareStatement(
				"insert into daycare.Vaccine (vaccine_id, name, doses_taken, total_doses,  doses_taken_dates, last_shot_date, upcoming_shot_date, student_id, is_vaccinated) \"\n"
						+ "values (default, ?, ?,?,?,?,?,?,?)");
		preparedStatement.setDate(1, Date.valueOf(vaccine.getLastShotDate()));
		preparedStatement.setDate(2, Date.valueOf(vaccine.getNextShotDate()));
		preparedStatement.setBoolean(3, vaccine.isVaccinated());
		preparedStatement.setString(4, null); // TODO change this later
		preparedStatement.setInt(5, vaccine.getDosestaken() + 1); // TODO change this later
		preparedStatement.setInt(6, vaccine.getStudentId());
		preparedStatement.setInt(7, vaccine.getId());
		int result = preparedStatement.executeUpdate();
		System.out.println(result + "  vaccine updated");
	}

	public void deleteStudent(int studentId) throws Exception {
		connection = getConnection();
		preparedStatement = connection
				.prepareStatement(" update daycare.student set is_deleted = 1 where student_id = ? ");
		preparedStatement.setInt(1, studentId);
		int result = preparedStatement.executeUpdate();
		System.out.println(result + "  Student deleted");
	}

	public void updateStudent(Student student) throws Exception {
		connection = getConnection();
		preparedStatement = connection.prepareStatement(
				" update daycare.student set first_name = ?, last_name = ?, address = ? where student_id = ? ");
		preparedStatement.setString(1, student.getFirstName());
		preparedStatement.setString(2, student.getLastName());
		preparedStatement.setString(3, student.getAddress());
		preparedStatement.setInt(4, student.getStudentId());
		int result = preparedStatement.executeUpdate();
		System.out.println(result + "  Student updated");
	}

	private List<Student> writeStudentResultSet(ResultSet resultSet) throws SQLException {
		Student student = null;
		List<Student> students = new ArrayList<>();
		while (resultSet.next()) {
			Date regDate = resultSet.getDate("registration_date");
			Date dob = resultSet.getDate("dob");
			BigInteger ph = BigInteger.valueOf(resultSet.getLong("phone"));

//			int parentId, String firstName, String lastName,String email, BigInteger phone
			Parent p = new Parent(resultSet.getInt("parent_id"), resultSet.getString("p_first_name"),
					resultSet.getString("p_last_name"), resultSet.getString("email"), ph);
//			int studentId, LocalDate registrationDate, 
//			String address, int age, LocalDate dob, 
//			String firstName, String lastName,  Parent parent,
//			Teacher teacher_assigned
			student = new Student(resultSet.getInt("student_id"), regDate.toLocalDate(), resultSet.getString("address"),
					resultSet.getInt("age"), dob.toLocalDate(), resultSet.getString("first_name"),
					resultSet.getString("last_name"), p);
			students.add(student);
		}
		return students;
	}

	private List<Vaccine> writeVaccineResultSet(ResultSet resultSet) throws SQLException {
		Vaccine vaccine = null;
		List<Vaccine> vaccines = new ArrayList<>();
		while (resultSet.next()) {
			Date lastShotDate = resultSet.getDate("last_shot_date");
			Date upcomingShotDate = resultSet.getDate("upcoming_shot_date");
			// TODO change this to List<LocalDate>
			String dates = resultSet.getString("doses_taken_dates");
			vaccine = new Vaccine(resultSet.getInt("vaccine_id"), resultSet.getString("name"),
					resultSet.getInt("doses_taken"), resultSet.getInt("total_doses"), lastShotDate.toLocalDate(),
					upcomingShotDate.toLocalDate(), resultSet.getInt("student_id"),
					resultSet.getBoolean("is_vaccinated"), null);
			vaccines.add(vaccine);
		}
		return vaccines;

	}

	// update vaccine set doses_taken = doses_taken + 1 and last_shot_date =
	// '2021-01-12' and upcoming_shot_date = '2021-01-12' and is_vaccinated =
	// 'true'and doses_taken_dates = null where student_id = 100 and vaccine_id = 1;

}
