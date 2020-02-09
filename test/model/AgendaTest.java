package model;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

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
				p.load(new FileInputStream(Agenda.STUDENTS_PATH+"/"+id+".properties"));
			} catch (FileNotFoundException e) {
				fail();
			} catch (IOException e) {
				fail();
			}
			
			assertEquals(st.getName(), p.getProperty("name"), "Properties did not match");
			assertEquals(st.getLastName(), p.getProperty("lastName"), "Properties did not match");
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
				p.load(new FileInputStream(Agenda.SUBJECTS_PATH+"/"+n+".properties"));
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

}
