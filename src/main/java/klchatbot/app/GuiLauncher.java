package klchatbot.app;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import klchatbot.KLChatBot;
import klchatbot.gui.MainWindow;
import klchatbot.response.GuiResultFormatter;

/**
 * A GUI launcher for KLChatBot using FXML.
 */
public class GuiLauncher extends Application {

    private final KLChatBot bot = new KLChatBot(new GuiResultFormatter());

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(GuiLauncher.class.getResource("/view/MainWindow.fxml"));
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
