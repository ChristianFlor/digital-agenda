package model;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;

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
		//assertTrue();
	}

}
