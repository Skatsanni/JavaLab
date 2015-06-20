package Valentinaa.Kostyukhina.ivt.ch.makery.adress.view;
import java.io.File;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import org.controlsfx.dialog.Dialogs;
import Valentinaa.Kostyukhina.ivt.ch.makery.adress.VMainAppK;

public class VRootLayoutControllerK {
	private VMainAppK vmainAppk;

    public void setMainApp(VMainAppK mainApp) {
        this.vmainAppk = mainApp;
    }

   
    @FXML
    private void vhandleNewK() {
        vmainAppk.getPersonData().clear();
        vmainAppk.vsetPersonFilePathK(null);
    }

 
    @FXML
    private void vhandleOpenK() {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(vmainAppk.vgetPrimaryStageK());

        if (file != null) {
            vmainAppk.vloadPersonDataFromFileK(file);
        }
    }

    @FXML
    private void vhandleSaveK() {
        File personFile = vmainAppk.vgetPersonFilePathK();
        if (personFile != null) {
            vmainAppk.savePersonDataToFile(personFile);
        } else {
            vhandleSaveAsK();
        }
    }

    @FXML
    private void vhandleSaveAsK() {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showSaveDialog(vmainAppk.vgetPrimaryStageK());

        if (file != null) {
        	
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            vmainAppk.savePersonDataToFile(file);
        }
    }

    
    @FXML
    private void vhandleAboutK() {
        Dialogs.create()
            .title("AdressApp")
            .masthead("About")
            .message("Author: Kostyukhina Valentina")
            .showInformation();
    }

 
    @FXML
    private void vhandleExitK() {
        System.exit(0);
    }
    
    @FXML
    private void vhandleShowBirthdayStatisticsK() {
      vmainAppk.vshowBirthdayStatisticsK();
    }
}