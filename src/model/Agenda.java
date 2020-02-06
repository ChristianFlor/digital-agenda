package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;


public class Agenda {
	public static String STUDENTS_PATH = "resources/students";
	public static String SUBJECTS_PATH = "resources/subjects";

	private List<Student> students;
	private Map<Integer, Subject> subjects;
	
	public Agenda() throws FileNotFoundException, IOException {
		students = new ArrayList<>();
		subjects = new HashMap<>();
		loadStudents();
	}

	/**
	 * 
	 * @param name
	 * @param lastName
	 * @param idCode
	 * @param program
	 * @param semester
	 * @throws IOException 
	 */
	public boolean registerStudent(String name, String lastName, String idCode, String program, int semester, String email, String profpic, String phoneNumber) throws IOException {
		boolean possible = true;
		for(int i =0; i < students.size() && possible; i++) {
			if(idCode.equals(students.get(i).getIdCode())) possible = true;
		}
		if(possible) {
			students.add(new Student(name, lastName, idCode, program, email, phoneNumber, profpic,semester));
			File f = new File("resources/students/"+idCode+".properties");
			FileWriter fw = new FileWriter(f);
			BufferedWriter bw = new BufferedWriter(fw); 
			bw.append("name="+name+"\n");
			bw.append("lastName="+lastName+"\n");
			bw.append("email="+email+"\n");
			bw.append("id="+idCode+"\n");
			bw.append("phoneNumber="+phoneNumber+"\n");
			bw.append("program="+program+"\n");
			bw.append("profpic="+profpic);
			bw.append("semester="+semester);
			bw.close();
		}
		return possible;
	}

	/**
	 * 
	 * @param idCode
	 */
	public boolean deleteStudent(int idCode) {
		// TODO - implement Agenda.deleteStudent
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param idCode
	 * @param nrc
	 * @throws IOException 
	 */
	public boolean addSubjectToStudent(int idCode, int nrc) throws IOException {
		boolean found = false;
		for(int i = 0; i < students.size() && !found;i++) {
			if(students.get(i).getIdCode().equals(idCode)) { found = true;
				students.get(i).addSubject(subjects.get(nrc));
				File f = new File("resources/students/"+idCode+".properties");
				FileWriter fw = new FileWriter(f);
				BufferedWriter bw = new BufferedWriter(fw); 
				bw.append("," + nrc);
				bw.close();
			}
		}
		return found;
	}

	/**
	 * 
	 * @param idCode
	 * @param nrc
	 */
	public boolean deleteSubjectToStudent(int idCode, int nrc) {
		// TODO - implement Agenda.deleteSubjectToStudent
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param name
	 */
	public List<Student> searchStudentByName(String name) {
		List<Student> list = new ArrayList<>();
		for(int i = 0; i < students.size();i++) {
			if(students.get(i).getName().equals(name)) {
				list.add(students.get(i));
			} 
		}
		return list;
	}

	/**
	 * 
	 * @param idCode
	 */
	public Student searchStudentByIdCode(String idCode) {
		boolean found = false;
		Student stu = null;
		for(int i = 0; i < students.size() && !found;i++) {
			if(students.get(i).getIdCode().equals(idCode)) { found = true;
				stu = students.get(i);
			}
		}
		return stu;
	}

	/**
	 * 
	 * @param lastName
	 */
	public List<Student> searchStudentByLastName(String lastName) {
		List<Student> list = new ArrayList<>();
		for(int i = 0; i < students.size();i++) {
			if(students.get(i).getLastName().equals(lastName)) {
				list.add(students.get(i));
			} 
		}
		return list;
	}

	/**
	 * 
	 * @param email
	 */
	public List<Student> searchStudentByEmail(String email) {
		List<Student> list = new ArrayList<>();
		for(int i = 0; i < students.size();i++) {
			if(students.get(i).getEmail().equals(email)) {
				list.add(students.get(i));
			} 
		}
		return list;
	}

	public void loadStudents() throws FileNotFoundException, IOException {
		File[] studs = new File(STUDENTS_PATH).listFiles();
		students.clear();
		for(File propStud : studs) {
			Properties p = new Properties();
			p.load(new FileInputStream(propStud.getPath()));

			String name = p.getProperty("name");
			String lName = p.getProperty("lastName");
			String email = p.getProperty("email");
			String id = p.getProperty("id");
			String pn = p.getProperty("phoneNumber");
			String prog = p.getProperty("program");
			int semester = Integer.parseInt(p.getProperty("semester"));
			String pp = p.getProperty("profpic");
			String[] subs = p.getProperty("subjects").split(",");
			students.add(new Student(name, lName, id, prog, email, pn, pp, semester));
			for (int i = 0; i < subs.length; i++) {
				int nrc=  Integer.parseInt(subs[i]);
				students.get(students.size()-1).addSubject(subjects.get(nrc));
			}
		}
	}

	public void loadSubjects() throws FileNotFoundException, IOException {
		File[] subjs = new File(SUBJECTS_PATH).listFiles();
		subjects.clear();
		for(File propStud : subjs) {
			Properties p = new Properties();
			p.load(new FileInputStream(propStud.getPath()));

			String name = p.getProperty("name");
			int nrc = Integer.parseInt(p.getProperty("nrc"));
			String faculty = p.getProperty("faculty");
			int credits = Integer.parseInt(p.getProperty("credits"));
			int students = Integer.parseInt(p.getProperty("students"));
			subjects.put(nrc, new Subject(name, nrc, faculty, credits, students));
		}
	}
	
	public List<Student> getStudents() {
		return students;
	}

}