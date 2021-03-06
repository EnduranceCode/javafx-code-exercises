package exercisetwo.firstscreen.body;

import exercisetwo.firstscreen.model.BodyOutput;
import exercisetwo.firstscreen.utils.ExerciseUtils;
import exercisetwo.secondscreen.SecondScreenView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

public class BodyPresenter implements Initializable {
    @FXML
    private Text bodyText;
    @FXML
    private CheckBox bodyCheckbox;
    @FXML
    private Button bodyPrintButton;
    @FXML
    private Button bodyNextButton;
    @FXML
    private Button bodyToggleOutputButton;

    @Inject
    BodyOutput bodyOutput;

    private String checkboxButtonLabel;
    private String labelButtonLabel;

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        checkboxButtonLabel = resourceBundle.getString("checkbox-button-label");
        labelButtonLabel = resourceBundle.getString("label-button-label");

        bodyText.setText(bodyOutput.getBodyOutputContent());
        bodyCheckbox.setText(bodyOutput.getBodyOutputContent());

        if (Boolean.TRUE.equals(bodyOutput.isActiveLabel())) {
            bodyText.setVisible(Boolean.TRUE);
            bodyCheckbox.setVisible(Boolean.FALSE);
        } else {
            bodyText.setVisible(Boolean.FALSE);
            bodyCheckbox.setVisible(Boolean.TRUE);
        }

        bodyToggleOutputButton.setText(bodyOutput.getBodyToggleOutputButtonLabel());
    }

    public void setBodyOutputContent(String text) {
        bodyOutput.setBodyOutputContent(text);
        bodyText.setText(text);
        bodyCheckbox.setText(text);
    }

    @FXML
    protected void showInformationPopUp() {
        Alert alertPopUp = new Alert(Alert.AlertType.INFORMATION);
        alertPopUp.setTitle("Print Button Pop Up Window");
        alertPopUp.setHeaderText("The Print Button has been clicked!");
        alertPopUp.setContentText("This Pop Up window appears when the Print button is clicked. Press the OK button to close this Pop Up window");
        alertPopUp.showAndWait();
    }

    @FXML
    protected void showSecondScreen() {

        Stage currentStage = (Stage) bodyNextButton.getScene().getWindow();
        currentStage.setTitle("JavaFX Exercise One - Second Screen");

        SecondScreenView secondScreenView = new SecondScreenView();
        Scene secondScene = new Scene(secondScreenView.getView());
        currentStage.setScene(secondScene);
    }

    @FXML
    protected void toggleBodyOutputTextContainer() {
        if (bodyText.getText().isEmpty()) {
            ExerciseUtils.showEmptyTextFieldPopUpWarning();
            return;
        }

        if (Boolean.TRUE.equals(bodyOutput.isActiveLabel())) {
            bodyOutput.setBodyToggleOutputButtonLabel(labelButtonLabel);
            bodyToggleOutputButton.setText(labelButtonLabel);
            bodyText.setVisible(Boolean.FALSE);
            bodyCheckbox.setVisible(Boolean.TRUE);
            bodyOutput.setActiveLabel(Boolean.FALSE);
            bodyCheckbox.setSelected(Boolean.FALSE);
        } else {
            bodyOutput.setBodyToggleOutputButtonLabel(checkboxButtonLabel);
            bodyToggleOutputButton.setText(checkboxButtonLabel);
            bodyCheckbox.setVisible(Boolean.FALSE);
            bodyText.setVisible(Boolean.TRUE);
            bodyOutput.setActiveLabel(Boolean.TRUE);
        }
    }
}
