package br.com.fulltime.fullarm.app.javafx.constants;

public enum ResultSceneControllerPath {
    AUTHENTICATION("Autenticação", "/view/authenticationResultScene.fxml"),
    COMMAND("Comando", "/view/commandResultScene.fxml"),
    EVENT("Evento", "/view/eventResultScene.fxml"),
    PARTIAL_STATUS("Status Parcial", "/view/statusPackageResultScene.fxml"),
    FULL_STATUS("Status Completo", "/view/statusPackageResultScene.fxml"),
    SHORT_FRAME("", "/view/shortFramePackageResultScene.fxml");

    private final String identifier;
    private final String path;

    ResultSceneControllerPath(String identifier, String path) {
        this.identifier = identifier;
        this.path = path;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getPath() {
        return path;
    }

    public static ResultSceneControllerPath getByValue(String identifier) {
        for (ResultSceneControllerPath rscp : values()) {
            if (identifier.equals(rscp.getIdentifier())) return rscp;
        }

        return ResultSceneControllerPath.SHORT_FRAME;
    }
}
