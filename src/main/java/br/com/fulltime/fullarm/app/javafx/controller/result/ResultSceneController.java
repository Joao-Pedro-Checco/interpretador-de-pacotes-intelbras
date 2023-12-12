package br.com.fulltime.fullarm.app.javafx.controller.result;

import javafx.event.ActionEvent;

import java.util.List;

public interface ResultSceneController {
    void displayResult(String rawPackage, List<String> message);

    void returnToMainScene(ActionEvent event);
}
