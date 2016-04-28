package JavaMediaPlayer;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import java.io.File;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/**
 * Dropdowns class adds drop down menus to the top of the player screen.
 *
 * 
 */
public class DropDowns {

    private Menu fileMenu = new Menu("File");
    private Menu viewMenu = new Menu("View");
    private Menu aboutMenu = new Menu("About");
    private MenuItem urlItem = new MenuItem("Open URL"); // not currently implemented
    private MenuItem fileItem = new MenuItem("Open File");
    private MenuItem enlargeScreen = new MenuItem("Enlarge Screen");
    private MenuItem aboutItem = new MenuItem("About Medien");
    private FileChooser fileChooser = new FileChooser();
    public MenuBar menuBar = new MenuBar();
    public HBox dropDowns = new HBox();

    public DropDowns() {
        urlItem.setStyle("-fx-text-fill: #000000");
        fileItem.setStyle("-fx-text-fill: #000000");
        enlargeScreen.setStyle("-fx-text-fill: #000000");
        aboutItem.setStyle("-fx-text-fill: #000000");

        fileMenu.getItems().addAll(urlItem, fileItem);
        viewMenu.getItems().addAll(enlargeScreen);
        aboutMenu.getItems().addAll(aboutItem);
        menuBar.getMenus().addAll(fileMenu, viewMenu, aboutMenu);
        menuBar.setStyle("-fx-background-color: #383838");

        fileItem.setOnAction(e -> {
            File file = fileChooser.showOpenDialog(new Stage());
            JavaMediaPlayer.changeMedia(file.toURI().toString());
        });

        urlItem.setOnAction(e -> {
            URLAction();
        });
        
        enlargeScreen.setOnAction(e -> {
            JavaMediaPlayer.bindScreen();
        });
        
        aboutItem.setOnAction(e -> {
            Pane aboutPane = new Pane();
            ArrayList<Label> text = new ArrayList<>();
            Label aboutTitleLabel = new Label("Medien Player\n");
            Label aboutLabel = new Label("\n\nVersion 1.0 All rights reserved\n");
            aboutTitleLabel.setStyle("-fx-font-weight: bold");
            aboutTitleLabel.setPadding(new Insets(20,20,20,20));
            aboutLabel.setPadding(new Insets(20, 20, 20, 20));
            text.add(aboutTitleLabel);
            text.add(aboutLabel);
            
            aboutPane.getChildren().addAll(text);

            Stage aboutPrompt = new Stage();
            aboutPrompt.setTitle("About");
            aboutPrompt.setScene(new Scene(aboutPane, 250, 150));
            aboutPrompt.show();
            aboutPrompt.setResizable(false);
        });
    }

    private void URLAction() {
        VBox pane = new VBox();
        pane.setPadding(new Insets(20, 20, 20, 20));
        pane.setSpacing(10);

        Button okBt = new Button("OK");

        TextField text = new TextField();
        pane.getChildren().addAll(text, okBt);

        Stage stage = new Stage();
        stage.setTitle("Open URL");
        stage.setScene(new Scene(pane, 300, 100));
        stage.setResizable(false);
        stage.show();

        okBt.setOnAction(e -> {
            JavaMediaPlayer.changeMedia(text.getText());
            stage.close();
        });
    }
    
    
}
