
package JavaMediaPlayer;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import java.io.File;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/**
 * Dropdowns class adds drop down menus to the top of the player screen.
 * @author shane
 */


public class DropDowns {
    private Menu menu1 = new Menu("File");
    private Menu menu2 = new Menu("Edit");
    private MenuItem urlItem = new MenuItem("Open URL"); // not currently implemented
    private MenuItem fileItem = new MenuItem("Open File");
    private FileChooser fileChooser = new FileChooser();
    public MenuBar menuBar = new MenuBar();
    public HBox dropDowns = new HBox();
    
    public DropDowns() {
        urlItem.setStyle("-fx-text-fill: #000000");
        fileItem.setStyle("-fx-text-fill: #000000");
        
        menu1.getItems().addAll(urlItem, fileItem);
        menuBar.getMenus().addAll(menu1, menu2);
        menuBar.setStyle("-fx-background-color: #383838");
        
        fileItem.setOnAction(e -> {
            File file = fileChooser.showOpenDialog(new Stage());
            JavaMediaPlayer.changeMedia(file.toURI().toString());
        });
    }
}