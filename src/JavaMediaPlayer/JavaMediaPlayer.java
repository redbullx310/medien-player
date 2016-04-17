
package JavaMediaPlayer;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.*;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * JavaMediaPlayer takes advantage of the JavaFX Media library to efficiently 
 * play media files from the users hard drive or stream media from a URL source 
 * via HTTP streaming. Supported video file types are .mp4 and .flv file types. 
 * Certain audio files can also be played. CSS is used to add some styling to the
 * player 
 * @author shane
 * @version 1.0
 */


public class JavaMediaPlayer extends Application {
    
    private Image initImage = new Image("file:images/medienEmblem.png", 700, 750, true, true);
    private ImageView imgView = new ImageView(initImage);
    private static Media media;
    private static MediaPlayer player;
    private static MediaView view;
    private static ControlPanel ctrlPanel;
    private Pane viewPane = new Pane();
    private DropDowns drops = new DropDowns();
    
    private static DoubleProperty width;
    private static DoubleProperty height;
    
    private static BorderPane pane = new BorderPane();
    
    @Override
    public void start(Stage primaryStage) {
        pane.setTop(drops.menuBar); 
        pane.setCenter(imgView); 
                
        Scene scene = new Scene(pane, 750, 700);
        scene.getStylesheets().add("file:MediaPlayerStyle.css");
        
        primaryStage.setTitle("Medien Player");
        primaryStage.setScene(scene);
        //primaryStage.setResizable(false); // Uncomment to disable screen resizing
        primaryStage.show(); // Display the Media Player
    }
    
    public static void main(String[] args) {
        Application.launch(args); // All JavaFX applications must have this statement in the main method
    }
    
    /*
      Makes the screen bigger.
    */
    public static void bindScreen() {
        width = view.fitWidthProperty();
        height = view.fitHeightProperty(); 
        width.bind(Bindings.selectDouble(view.sceneProperty(), "width"));
        height.bind(Bindings.selectDouble(view.sceneProperty(), "height"));
    }
    
    /**
     * Change the media. New Media, MediaPlayer, ControlPanel objects are
     * created each time the media is played. Each of these objects
     * are essentially bound to one media source and cannot be changed, so new
     * objects are created for a new media source. Due to Java garbage collection, 
     * this should not decrease efficiency. Hopefully.
     * 
     * @param source Source of the new media file
     */
    public static void changeMedia(String source) {
        boolean initialized = false;

        if(!initialized) {
            view = new MediaView();
            initialized = true;
        }
        
        try {
            media = new Media(source);
        } catch (Exception e) {
            Pane errorPane = new Pane();

            Label errorLbl = new Label("Media file type unsupported\n\n"
                    + "Valid types are\n"
                    + "\tVideo: MP4 and FLV\n"
                    + "\tAudo: MP3, AIFF, WAV, M4A");
            errorLbl.setPadding(new Insets(20, 20, 20, 20));
            errorPane.getChildren().add(errorLbl);
            
            Stage errorPrompt = new Stage();
            errorPrompt.setTitle("Invalid file type");
            errorPrompt.setScene(new Scene(errorPane, 250, 150));
            errorPrompt.show();
            errorPrompt.setResizable(false);
            System.out.println("Invalid file or file type not supported");
            return;
        }
        
        player = new MediaPlayer(media);
        view.setMediaPlayer(player);
        ctrlPanel = new ControlPanel(player);
        pane.setCenter(view);
        pane.setBottom(ctrlPanel.ctrlPane);
    }
    
}
