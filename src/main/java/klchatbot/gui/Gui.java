package klchatbot.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import klchatbot.KLChatBot;
import klchatbot.response.GuiResultFormatter;

import java.io.IOException;

/**
 * A GUI launcher for KLChatBot using FXML.
 */
public class Gui extends Application {

    private final KLChatBot bot = new KLChatBot(new GuiResultFormatter());

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setChatbot(bot);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
