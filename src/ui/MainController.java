package ui;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

//import com.gluonhq.charm.glisten.control.CardPane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.*;

public class MainController {

	@FXML
	private Button btnConfirm;
	@FXML
	private TextField nameStudent;

	@FXML
	private TextField phoneStudent;

	@FXML
	private TextField emailStudent;

	@FXML
	private TextField codeStudent;

	@FXML
	private TextField programStudent;


	@FXML
	private TextField nameSubject;


	@FXML
	private TextField nrcSubject;

	@FXML
	private TextField txtCredits;
	@FXML
	private TextField txtFaculty;

	@FXML
	private Text avarageSubjects;

	@FXML
	private Text avarageCredits;

	@FXML
	private Text moreMatriculated;

	@FXML
	private Text lessMatriculated;
	@FXML
	private ComboBox<String> optionsSearch;

	@FXML
	private TextField search;

	@FXML
	private ImageView foundStudentImg;

	@FXML
	private Label dName;

	@FXML
	private Label dScore;

	@FXML
	private Label dNick;

	@FXML
	private Label dProgram;

	@FXML
	private VBox vBoxList;

	@FXML
	private ImageView photoStudent;

	@FXML
	private ImageView btnPrevious;

	@FXML
	private ImageView btnNext;

	@FXML
	private ListView<String> subjectList;


	@FXML
	private TableView<Student> foundStudent;

	@FXML
	private TableColumn<Student, String> nameColumn;

	@FXML
	private TableColumn<Student, String> lastColumn;

	@FXML
	private TableColumn<Student, String> codeColumn;

	@FXML
	private TableColumn<Student, String> emailColumn;

	@FXML
	private TableColumn<Student, String> numberColumn;

	@FXML
	private TableColumn<Subject,String> subNameColumn;

	@FXML
	private TableColumn<Subject,Integer> nrcColumn;

	@FXML
	private TableColumn<Subject,String> facultyColumn;

	@FXML
	private TableColumn<Subject,Integer> creditsColumn;

	@FXML
	private TableColumn<Subject,Integer> studentsColumn;

	@FXML
	private TextField foundName;


	@FXML
	private TextField foundEmail;

	@FXML
	private TextField foundCode;

	@FXML
	private TextField foundProgram;

	@FXML
	private TableView<Subject> subjectsTable;

	private Agenda agenda;
	private int actualPosition;

	@FXML
	public void initialize() throws FileNotFoundException, IOException {
		agenda = new Agenda();
		actualPosition = 0;
		if(agenda.getStudents().get(0)!=null) {
			showInformation(0);

		}
		optionsSearch.getItems().addAll("Name", "idCode","Email", "LastName");
		initializeTables();
	}

	private void initializeTables() {

		nameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
		lastColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("lastname"));
		codeColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("idCode"));
		emailColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));
		numberColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("phoneNumber"));

		foundStudent.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if(event.getClickCount()==2) {
					foundName.setText(foundStudent.getSelectionModel().getSelectedItem().getName());
					foundCode.setText(foundStudent.getSelectionModel().getSelectedItem().getIdCode());
					foundProgram.setText(foundStudent.getSelectionModel().getSelectedItem().getProgram());
					foundEmail.setText(foundStudent.getSelectionModel().getSelectedItem().getEmail());
					Image image = new Image(new File(foundStudent.getSelectionModel().getSelectedItem().getProfpic()).toURI().toString());
					foundStudentImg.setImage(image);
				}
			}

		});

		subNameColumn.setCellValueFactory(new PropertyValueFactory<Subject, String>("name"));
		nrcColumn.setCellValueFactory(new PropertyValueFactory<Subject, Integer>("nrc"));
		facultyColumn.setCellValueFactory(new PropertyValueFactory<Subject, String>("faculty"));
		creditsColumn.setCellValueFactory(new PropertyValueFactory<Subject, Integer>("credits"));
		studentsColumn.setCellValueFactory(new PropertyValueFactory<Subject, Integer>("students"));
	}

	@FXML
	public void calculate(ActionEvent event) {
		avarageSubjects.setText(""+agenda.calculateAverageSubjects());
		avarageCredits.setText(""+agenda.calculateAverageCredits());
		moreMatriculated.setText(agenda.mostMatriculated().getName());
		lessMatriculated.setText(agenda.lessMatriculated().getName());

	}



	@FXML
	public void nextStudent(MouseEvent event) {
		actualPosition++;

		if(actualPosition>=agenda.getStudents().size() ||agenda.getStudents().get(actualPosition)==null ) {
			actualPosition = 0;
		}
		showInformation(actualPosition);
		nameSubject.clear();
		nrcSubject.clear();
		txtFaculty.clear();
		txtCredits.clear();
	}

	@FXML
	public void previousStudent(MouseEvent event) {
		actualPosition--;

		if(actualPosition<0 ||agenda.getStudents().get(actualPosition)==null ) {
			actualPosition = agenda.getStudents().size()-1;
		}
		showInformation(actualPosition);
		nameSubject.clear();
		nrcSubject.clear();
		txtFaculty.clear();
		txtCredits.clear();
	}
	private void showInformation(int position) {
		Student next = agenda.getStudents().get(position);
		nameStudent.setText(next.getName() );
		emailStudent.setText(next.getEmail());
		codeStudent.setText(next.getIdCode());
		programStudent.setText(next.getProgram());
		phoneStudent.setText(next.getPhoneNumber());

		Image image = new Image(new File(next.getProfpic()).toURI().toString());
		photoStudent.setImage(image);
		subjectList.getItems().clear();

		for (int i = 0; i<next.getSubjects().size(); i++) {
			if(next.getSubjects().get(i)!=null)
			subjectList.getItems().add(next.getSubjects().get(i).getNrc()+" "+next.getSubjects().get(i).getName());
		}
		subjectList.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent click) {

				if (click.getClickCount() == 2) {
					int nrc = Integer.parseInt(subjectList.getSelectionModel().getSelectedItem().substring(0,5));
					Subject sub = agenda.getSubjects().get(nrc);
					nameSubject.setText(sub.getName());
					nrcSubject.setText(""+sub.getNrc());
					txtFaculty.setText(sub.getFaculty());
					txtCredits.setText(""+sub.getCredits());
				}
			}
		});
	}
	@FXML
	public void showTable(ActionEvent event) {
		subjectsTable.getItems().clear();
		List<Subject> subs = agenda.converToList();
		Collections.sort(subs);
		for (int i = 0; i < subs.size(); i++) {
			subjectsTable.getItems().add(subs.get(i));
		}

	}

	@FXML
	public void aboutProgram(ActionEvent event) {

	}

	@FXML
	public void addSubject(ActionEvent event) throws IOException {
		String name = nameSubject.getText();
		int nrc = Integer.parseInt(nrcSubject.getText());
		String faculty = txtFaculty.getText();;
		int credits = Integer.parseInt(txtCredits.getText());
		agenda.addSubjectToStudent(codeStudent.getText(), name, nrc, faculty, credits, 0);

		subjectList.getItems().add(nrc+" "+name);
	}

	@FXML
	public void delete(ActionEvent event) {
		if(!foundName.getText().isEmpty()) {
			agenda.deleteStudent(foundCode.getText());

			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setHeaderText(null);
			alert.setTitle("Confirmation");
			alert.setContentText("the student has been deleted");
			alert.showAndWait();
		}else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("first select a student");
			alert.showAndWait();
		}
	}

	@FXML
	public void exit(ActionEvent event) {

	}

	@FXML
	public void registerStudent(ActionEvent event) {
		nameStudent.clear();
		emailStudent.clear();
		codeStudent.clear();
		programStudent.clear();
		phoneStudent.clear();
		Image img = new Image(new File("src/uiImg/icons/agregar-usuario.png").toURI().toString());
		photoStudent.setImage(img);

		subjectList.getItems().clear();
		subjectList.getItems().clear();
		btnConfirm.setVisible(true);
		nameSubject.clear();
		nrcSubject.clear();
		txtFaculty.clear();
		txtCredits.clear();

	}
	@FXML
	public void ConfirmRegistration(ActionEvent event) throws IOException {
		String name = nameStudent.getText();
		String email = emailStudent.getText();
		String program = programStudent.getText();
		String code = codeStudent.getText();
		String phone = phoneStudent.getText();
		String profpic = photoStudent.getImage().impl_getUrl();
		boolean verification = name!="" && email!="" && program!=""&&code!=""&&phone!="";
		if(verification) {
			String[] fullName = name.split(" ");
			agenda.registerStudent(fullName[0], fullName[1], code, program, 0, email, profpic, phone);

			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setHeaderText(null);
			alert.setTitle("Confirmation");
			alert.setContentText("a new student has registered");
			alert.showAndWait();
		}

	}

	@FXML
	public void save(ActionEvent event) {

	}

	@FXML
	public void search(ActionEvent event) {

		String option = optionsSearch.getValue();
		String value = search.getText();
		List<Student> founds = null;

		if(option.equalsIgnoreCase("name")) {
			founds = agenda.searchStudentByName(value);

			ObservableList<Student> info = FXCollections.observableArrayList( founds );
			foundStudent.setItems(info);

		}else if(option.equalsIgnoreCase("idCode")) {

			Student found = agenda.searchStudentByIdCode(value);
			if(found!=null) {
				foundStudent.getItems().add(found);
			}
		}else if(option.equalsIgnoreCase("email")) {
			Student found = agenda.searchStudentByEmail(value);
			if(found!=null) {
				foundStudent.getItems().add(found);
			}
		} else if(option.equalsIgnoreCase("lastName")) {
			founds = agenda.searchStudentByLastName(value);
			
			ObservableList<Student> info = FXCollections.observableArrayList( founds );
			foundStudent.setItems(info);
		}
	}
	@FXML
    void deleteSubject(ActionEvent event) throws NumberFormatException, FileNotFoundException, IOException {
		if(!nrcSubject.getText().isEmpty()) {
			agenda.deleteSubjectToStudent(codeStudent.getText(), Integer.parseInt(nrcSubject.getText()));
			subjectList.getItems().remove(nrcSubject.getText()+" "+nameSubject.getText());
		}else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("first select a subject");
			alert.showAndWait();
		}
    }

	@FXML
	public void uploadImage(ActionEvent event) {

	}
	@FXML
	public void sortEmail(ActionEvent event) {

	}

	@FXML
	public void sortIdCode(ActionEvent event) {

	}

	@FXML
	public void sortLastName(ActionEvent event) {

	}

	@FXML
	public void sortName(ActionEvent event) {

	}

	@FXML
	public void sortPhoneNumber(ActionEvent event) {

	}
}
