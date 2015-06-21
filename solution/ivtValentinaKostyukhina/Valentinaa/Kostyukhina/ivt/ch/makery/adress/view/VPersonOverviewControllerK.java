package Valentinaa.Kostyukhina.ivt.ch.makery.adress.view;

import org.controlsfx.dialog.Dialogs;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import Valentinaa.Kostyukhina.ivt.ch.makery.adress.VMainAppK;
import Valentinaa.Kostyukhina.ivt.ch.makery.adress.model.VPersonK;
import Valentinaa.Kostyukhina.ivt.ch.makery.adress.util.VDateUtilK;

public class VPersonOverviewControllerK {
    @FXML
    private TableView<VPersonK> personTable;
    @FXML
    private TableColumn<VPersonK, String> firstNameColumn;
    @FXML
    private TableColumn<VPersonK, String> secondNameColumn;
    
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;

    private VMainAppK vmainAppk;

    public VPersonOverviewControllerK() {
    }

    @FXML
    private void initialize() {
    	
        firstNameColumn.setCellValueFactory(
        		cellData -> cellData.getValue().firstNameProperty());
        secondNameColumn.setCellValueFactory(
        		cellData -> cellData.getValue().lastNameProperty());        
       
        vshowPersonDetailsK(null);
        
		personTable .getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> vshowPersonDetailsK(newValue));
    }

   
    public void setMainApp(VMainAppK mainApp) {
        this.vmainAppk = mainApp;        
        personTable.setItems(mainApp.getPersonData());
    }
 
    private void vshowPersonDetailsK(VPersonK person) {
    	if (person != null) {
    		
    		firstNameLabel.setText(person.getFirstName());
    		lastNameLabel.setText(person.getLastName());
    		streetLabel.setText(person.getStreet());
    		postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
    		cityLabel.setText(person.getCity());
    		birthdayLabel.setText(VDateUtilK.vformatK(person.getBirthday()));
    		
    	} else {
    		
    		firstNameLabel.setText("");
    		lastNameLabel.setText("");
    		streetLabel.setText("");
    		postalCodeLabel.setText("");
    		cityLabel.setText("");
    		birthdayLabel.setText("");
    	}
    }
    
    @FXML
    private void vhandleDeletePersonK() {
        int vselectedIndexk = personTable.getSelectionModel().getSelectedIndex();
        if (vselectedIndexk >= 0) {
            personTable.getItems().remove(vselectedIndexk);
        } else {
        
            Dialogs.create().title("No Selection")
                .masthead("No Person Selected")
                .message("Please select a person in the table.")
                .showWarning();
        }
    }
    
    @FXML
	private void vhandleNewPersonK() {
		VPersonK tempPerson = new VPersonK();
		boolean okClicked = vmainAppk.showPersonEditDialog(tempPerson);
		if (okClicked) {
			vmainAppk.getPersonData().add(tempPerson);
		}
	}

	@FXML
	private void vhandleEditPersonK() {
		VPersonK selectedPerson = personTable.getSelectionModel()
				.getSelectedItem();
		if (selectedPerson != null) {
			boolean okClicked = vmainAppk.showPersonEditDialog(selectedPerson);
			if (okClicked) {
				vshowPersonDetailsK(selectedPerson);
			}

		} else {
		
			Dialogs.create().title("No Selection")
					.masthead("No Person Selected")
					.message("Please select a person in the table.")
					.showWarning();
		}
	}
}