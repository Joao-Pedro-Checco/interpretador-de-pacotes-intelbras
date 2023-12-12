package br.com.fulltime.fullarm.app.javafx.controller.result.longframe.status;

import br.com.fulltime.fullarm.app.javafx.SceneLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("prototype")
public class StatusPackageResultSceneControllerImpl implements StatusPackageResultSceneController {
    @FXML
    private TextArea packageTextArea;
    @FXML
    private Label packageTypeLabel;
    @FXML
    private ListView<String> contentListView;
    @Autowired
    private SceneLoader sceneLoader;

    @Override
    public void displayResult(String rawPackage, List<String> message) {
        packageTextArea.setText(rawPackage);
        packageTypeLabel.setText(message.get(0));
        contentListView.getItems().addAll(message.subList(1, message.size()));
    }

    @Override
    public void returnToMainScene(ActionEvent event) {
        Parent root = sceneLoader.loadScene("/view/index.fxml");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
