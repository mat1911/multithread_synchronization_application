package muczynski.mateusz;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;
import muczynski.mateusz.initializer.Initializer;
import muczynski.mateusz.window.WindowData;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
        Scene mainScene = new Scene(root, 1366, 768);

        WindowData.scene = mainScene;
        WindowData.stage = primaryStage;

        Initializer initializer = new Initializer();
        initializer.startAllProcesses();

        primaryStage.setScene(mainScene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
