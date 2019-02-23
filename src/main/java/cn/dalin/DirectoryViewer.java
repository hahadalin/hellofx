package cn.dalin;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Objects;

/**
 * @author hahadalin
 * @date 2019/2/23
 */
public class DirectoryViewer extends Application {
    @Override
    public void start(Stage primaryStage) {
        TreeView<String> treeView = new TreeView<>();
        BorderPane borderPane = new BorderPane();
        Button button = new Button("Load Folder");
        button.setOnAction(e -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            File choice = directoryChooser.showDialog(primaryStage);
            if (choice == null || !choice.isDirectory()) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setHeaderText("Could not open directory");
                alert.setContentText("The file is invalid.");

                alert.showAndWait();
            } else {
                treeView.setRoot(getNodesForDirectory(choice));
            }
        });
        borderPane.setTop(button);
        borderPane.setCenter(treeView);
        primaryStage.setScene(new Scene(borderPane, 600, 400));
        primaryStage.setTitle("Folder View");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    //Returns a TreeItem representation of the specified directory
    public TreeItem<String> getNodesForDirectory(File directory) {
        TreeItem<String> root = new TreeItem<>(directory.getName());
        for (File f : Objects.requireNonNull(directory.listFiles())) {
            System.out.println("Loading " + f.getName());
            if (f.isDirectory()) { //Then we call the function recursively
                root.getChildren().add(getNodesForDirectory(f));
            } else {
                root.getChildren().add(new TreeItem<>(f.getName()));
            }
        }
        return root;
    }
}
