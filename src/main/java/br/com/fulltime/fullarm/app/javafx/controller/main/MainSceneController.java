package br.com.fulltime.fullarm.app.javafx.controller.main;

import br.com.fulltime.fullarm.app.UserInputFormatter;
import br.com.fulltime.fullarm.app.UserInputValidator;
import br.com.fulltime.fullarm.app.javafx.SceneLoader;
import br.com.fulltime.fullarm.app.javafx.constants.ResultSceneControllerPath;
import br.com.fulltime.fullarm.app.javafx.controller.result.ResultSceneController;
import br.com.fulltime.fullarm.core.PackageUserInput;
import br.com.fulltime.fullarm.core.message.handler.MessageHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("prototype")
public class MainSceneController {
    private Stage stage;
    @FXML
    private TextField packageTextField;
    @Autowired
    private SceneLoader sceneLoader;
    @Autowired
    private UserInputValidator userInputValidator;
    @Autowired
    private UserInputFormatter userInputFormatter;
    @Autowired
    private MessageHandler messageHandler;

    public void send(ActionEvent event) {
        String userInput = packageTextField.getText();
        String spacelessString = userInput.replace(" ", "").toUpperCase();

        if (!userInputValidator.isValid(spacelessString)) {
            showAlert();
            return;
        }

        PackageUserInput packageUserInput = userInputFormatter.format(spacelessString);
        List<String> messageList = messageHandler.packageToMessageList(packageUserInput);

        String messageType = messageList.get(0);
        String path = ResultSceneControllerPath.getByValue(messageType).getPath();

        Parent root = sceneLoader.loadScene(path);

        ResultSceneController resultSceneController = sceneLoader.getLastLoader().getController();
        resultSceneController.displayResult(packageUserInput.getRawContent(), messageList);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/images/fullarm-logo.png"));
        alert.setTitle("Erro!");
        alert.setHeaderText("O pacote digitado não é válido! Por favor, tente novamente!");
        alert.showAndWait();
    }
}
