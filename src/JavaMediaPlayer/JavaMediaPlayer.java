
package JavaMediaPlayer;

import java.io.File;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.*;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;

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
    
    private File mediaFile = new File("mediaFiles/familyguy.mp4"); // replace famguy.mp4 with your video file name
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
        try {
            media = new Media(mediaFile.toURI().toString()); // Media object holds the video file
            player = new MediaPlayer(media); // player object allows video manipulation and holds data about the video
            view = new MediaView(player); // MediaView object allows a player object to be presented on the screen
            bindScreen();
            ctrlPanel = new ControlPanel(player);
        } catch (Exception e) {
            //e.toString();
            System.out.println("Unsupported file type or something weird happened. Check mediaFile path");
        }
        
        viewPane.getChildren().add(view); // add the view MediaView object into the viewPane
        
        pane.setTop(drops.menuBar); 
        pane.setCenter(viewPane); 
        pane.setBottom(ctrlPanel.ctrlPane); 
                
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
      Makes the screen bigger. Huge pain in the butt and I plan to try to fix
      it somehow. But it works for now.
    */
    private void bindScreen() {
        width = view.fitWidthProperty();
        height = view.fitHeightProperty(); 
        width.bind(Bindings.selectDouble(view.sceneProperty(), "width"));
        height.bind(Bindings.selectDouble(view.sceneProperty(), "height"));
    }
    
    /**
     * Change the media. New Media, MediaPlayer, ControlPanel objects are
     * created each time the media is played. The reason, each of these objects
     * are essentially bound to one media source and cannot be changed, so new
     * objects are created for a new media source. Due to Java garbage collection, 
     * this should not decrease efficiency. Hopefully.
     * 
     * @param source Source of the new media file
     */
    public static void changeMedia(String source) {
        try {
            media = new Media(source);
        } catch (MediaException e) {
            // Plan on throwing a popup screen in the users face here explaining the error
            System.out.println("Invalid file or file type not supported");
        }
        
        player = new MediaPlayer(media);
        view.setMediaPlayer(player);
        
        ctrlPanel = new ControlPanel(player);
        pane.setBottom(ctrlPanel.ctrlPane);
    }
    
}
