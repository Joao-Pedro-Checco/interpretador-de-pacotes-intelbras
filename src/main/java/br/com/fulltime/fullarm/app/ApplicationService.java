package br.com.fulltime.fullarm.app;

import br.com.fulltime.fullarm.app.javafx.SceneLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {
    @Autowired
    private SceneLoader sceneLoader;

    public void start(Stage primaryStage) {
        Parent root = sceneLoader.loadScene("/view/index.fxml");
        Scene scene = new Scene(root);

        primaryStage.setTitle("Interpretador de Pacotes Intelbras");
        primaryStage.getIcons().add(new Image("/images/fullarm-logo.png"));
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
