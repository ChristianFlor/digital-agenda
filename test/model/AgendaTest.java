package model;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.junit.jupiter.api.Test;

import model.Agenda;

public class AgendaTest {
	private Agenda agenda;
	
	private void setupStage1() {
		agenda = null;
	}
	
	private void setupStage2() {
		try {
			agenda = new Agenda();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void CreateAgendaAndLoadInfoTest() {
		setupStage1();
		try {
			agenda = new Agenda();
		} catch (FileNotFoundException e) {
			fail();
		} catch (IOException e) {
			fail();
		}
		assertNotNull("The students were not properly initialized", agenda.getStudents());
		assertNotNull("The subjects were not properly initialized", agenda.getSubjects());
		
		List<Student> students = agenda.getStudents();
		for(Student st : students) {
			String id = st.getIdCode();
			Properties p = new Properties();
			try {
				FileInputStream is = new FileInputStream(Agenda.STUDENTS_PATH+"/"+id+".properties");
				p.load(is);
				is.close();
			} catch (FileNotFoundException e) {
				fail();
			} catch (IOException e) {
				fail();
			}
			
			assertEquals(st.getName(), p.getProperty("name"), "Properties did not match");
			assertEquals(st.getEmail(), p.getProperty("email"), "Properties did not match");
			assertEquals(st.getIdCode(), p.getProperty("id"), "Properties did not match");
			assertEquals(st.getPhoneNumber(), p.getProperty("phoneNumber"), "Properties did not match");
			assertEquals(st.getProgram(), p.getProperty("program"), "Properties did not match");
			assertEquals(st.getProfpic(), p.getProperty("profpic"), "Properties did not match");
		}
		
		Map<Integer, Subject> subs = agenda.getSubjects();
		Iterator<Integer> nrcs = subs.keySet().iterator();
		for(int n = nrcs.next(); nrcs.hasNext(); n = nrcs.next()) {
			Properties p = new Properties();
			try {
				FileInputStream is = new FileInputStream(Agenda.SUBJECTS_PATH+"/"+n+".properties");
				p.load(is);
				is.close();
			} catch (FileNotFoundException e) {
				fail();
			} catch (IOException e) {
				fail();
			}
			
			Subject sub = subs.get(n);
			assertEquals(sub.getName(), p.getProperty("name"), "Properties did not match");
			assertEquals(sub.getNrc()+"", p.getProperty("nrc"), "Properties did not match");
			assertEquals(sub.getFaculty(), p.getProperty("faculty"), "Properties did not match");
			assertEquals(sub.getCredits()+"", p.getProperty("credits"), "Properties did not match");
			assertEquals(sub.getStudents()+"", p.getProperty("students"), "Properties did not match");
		}
	}
	
	@Test
	public void registerStudentTest() {
		setupStage2();
		List<Student> sts = agenda.getStudents();
		int originalSize = sts.size();
		String name = "Sonic";
		String lname = "TheHedgehog";
		String code = "A00000000";
		String prog = "Ingenieria de sistemas";
		int sem = 6;
		String email = "speedysonic@example.com";
		String profpic = "resources/avatars/avatar1.png";
		String pn = "3111111111";
		
		try {
			agenda.registerStudent(name, lname, code, prog, sem, email, profpic, pn);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		assertEquals(originalSize, sts.size() - 1, "There should be one more student");
		Student st = sts.get(sts.size()-1); //get last added
		Properties p = new Properties();
		try {
			FileInputStream is = new FileInputStream(Agenda.STUDENTS_PATH+"/"+code+".properties");
			p.load(is);
			is.close();
		} catch (FileNotFoundException e) {
			fail();
		} catch (IOException e) {
			fail();
		}
		
		assertEquals(name, st.getName(), "There is something fishy with either the constructor of Student class or the method that registers students.");
		assertEquals(st.getName(), p.getProperty("name"), "Properties did not match");
		assertEquals(lname, st.getLname(), "There is something fishy with either the constructor of Student class or the method that registers students.");
		assertEquals(st.getLname(), p.getProperty("lastName"), "Properties did not match");
		assertEquals(email, st.getEmail(), "There is something fishy with either the constructor of Student class or the method that registers students.");
		assertEquals(st.getEmail(), p.getProperty("email"), "Properties did not match");
		assertEquals(code, st.getIdCode(), "There is something fishy with either the constructor of Student class or the method that registers students.");
		assertEquals(st.getIdCode(), p.getProperty("id"), "Properties did not match");
		assertEquals(pn, st.getPhoneNumber(), "There is something fishy with either the constructor of Student class or the method that registers students.");
		assertEquals(st.getPhoneNumber(), p.getProperty("phoneNumber"), "Properties did not match");
		assertEquals(prog, st.getProgram(), "There is something fishy with either the constructor of Student class or the method that registers students.");
		assertEquals(st.getProgram(), p.getProperty("program"), "Properties did not match");
		assertEquals(profpic, st.getProfpic(), "There is something fishy with either the constructor of Student class or the method that registers students.");
		assertEquals(st.getProfpic(), p.getProperty("profpic"), "Properties did not match");
		
		originalSize = sts.size();
		assertEquals(originalSize, sts.size(), "The student should not have been registered because he is in the system already");
		try {
			agenda.registerStudent(name, lname, code, prog, sem, email, profpic, pn);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		File deleteMeImJustATestFile = new File(Agenda.STUDENTS_PATH+"/"+code+".properties");
		deleteMeImJustATestFile.delete();
	}
	
	@Test
	public void deleteStudentTest() {
		setupStage2();
		List<Student> sts = agenda.getStudents();
		
		//add a fake student to do the test and keep data safe
		
		String name = "Sonic";
		String lname = "TheHedgehog";
		String code = "A00000000";
		String prog = "Ingenieria de sistemas";
		int sem = 6;
		String email = "speedysonic@example.com";
		String profpic = "resources/avatars/avatar1.png";
		String pn = "3111111111";
		
		try {
			agenda.registerStudent(name, lname, code, prog, sem, email, profpic, pn);
		} catch (IOException e) {
			e.printStackTrace();
		}
		int originalSize = sts.size();
		
		//fake student added
		
		assertTrue(agenda.deleteStudent(code), "It should return TRUE as the student and the respective file were recently created so they could be deleted");
		assertEquals(originalSize, sts.size()+1, "If the student was deleted, then the size should have decreased by one");
		originalSize = sts.size();
		assertFalse(agenda.deleteStudent("I am a nonsense String"), "There is not a student with that id so the method should have not deleted anything");
		assertEquals(originalSize, sts.size(), "As there was nothing to delete, the size should have not changed");
		
	}

}
