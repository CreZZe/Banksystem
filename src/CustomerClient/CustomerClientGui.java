/*
 * Java Utveckling 18
 */
package CustomerClient;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author KTH-CA
 */
public class CustomerClientGui extends Application {
    Scene scene1,scene2;
    @Override
    public void start(Stage primaryStage) {
         //scene 1 pin scene
        Button submitBtn = new Button();
        submitBtn.setText("Submit");
        TextField pin= new TextField("Ange din pin");
        submitBtn.setOnAction(e->primaryStage.setScene(scene2));//läsa kundens pin och visa scen2
        VBox vbox=new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);
        vbox.getChildren().add(pin);
        vbox.getChildren().add(submitBtn);
        scene1 = new Scene(vbox, 300, 250);
        
        //scene2
        
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene1);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
