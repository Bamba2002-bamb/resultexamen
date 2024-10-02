package ci.example.ui;

import java.net.URL;

public enum FXMLPage {
    HOME("/ci/example/ui/fxml/Home.fxml"),
    VIEW("/ci/example/ui/fxml/ViewResult.fxml");

    private final String location;

    FXMLPage(String location) {
        this.location = location;
    }

    public URL getPage() {
        return  getClass().getResource(location) ;
    }
}
