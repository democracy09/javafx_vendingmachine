package util;

import javafx.scene.control.Alert;

public class AppUtil {

    public static void Alert(String msg, String header){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(msg);
        alert.setHeaderText(header);
        alert.setTitle("Notice");

        alert.show();

    }

}
