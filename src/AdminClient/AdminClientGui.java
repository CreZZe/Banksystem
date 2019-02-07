/*
 * Java Utveckling 18
 */
package AdminClient;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sun.font.TextLabel;

/**
 *
 * @author KTH-CA
 */
public class AdminClientGui extends Application {
    Scene scene1,scene2,scene3,scene4,scene5;
    @Override
    public void start(Stage primaryStage) {
        //scene 1 
        Button submitBtn = new Button();
        submitBtn.setText("Submit");
        TextField pin= new TextField("Ange användarnamn");
        submitBtn.setOnAction(e->primaryStage.setScene(scene2));
        VBox vbox=new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);
        vbox.getChildren().add(pin);
        vbox.getChildren().add(submitBtn);
        scene1 = new Scene(vbox, 300, 250);
        //StackPane root = new StackPane();
//        root.getChildren().add(pin);
//        root.getChildren().add(btn);

        //Scene 2 kund konto lån
        Button kund = new Button();
        Button konto= new Button();
        Button lån  = new Button();
        kund.setText("Kunder");
        konto.setText("Konto");
        lån.setText("Lån");
        kund.setOnAction(e->primaryStage.setScene(scene3));
        konto.setOnAction(e->primaryStage.setScene(scene4));
        lån.setOnAction(e->primaryStage.setScene(scene5));
        VBox vbox1= new VBox();
        vbox1.setAlignment(Pos.CENTER);
        vbox1.setPadding(new Insets(10));
        vbox1.setSpacing(8);
        vbox1.getChildren().add(kund);
        vbox1.getChildren().add(konto);
        vbox1.getChildren().add(lån);
        scene2 = new Scene(vbox1, 300, 250);
        
        //Scene3 
        
        Label kontoNR= new Label("konto nr");
        Label överför= new Label("Överför");
        Label saldo = new Label();
        VBox vbox3 = new VBox();
        
        HBox hbox1= new HBox();
        HBox hbox2= new HBox();
        HBox hbox3= new HBox();
        
        vbox3.getChildren().addAll(hbox1,hbox2,hbox3);
        TextField kontoTextField = new TextField("Ange konto nr");
        TextField överförTextField = new TextField("Ange pengar");
        TextField ränta = new TextField("Ange ränta");
        Button hämta= new Button();
        Button plus= new Button();
        Button minus= new Button();
        Button godkänn= new Button();
        godkänn.setText("submit");
        plus.setText("+");
        plus.setOnAction(e->System.out.println("adda pengar till konton"));// adda pengar till konton
        minus.setText("-");
        minus.setOnAction(e->System.out.println("Ta Ut pengar"));// Ta ut pengar från konton
        hämta.setText("Hämta");
        hämta.setOnAction(e->{System.out.println("adda pengar till konton");
                              saldo.setText(kontoTextField.getText());
                              hbox1.getChildren().add(saldo);});
        
        hbox1.setPadding(new Insets(15, 12, 15, 12)); 
        hbox1.setSpacing(10);
        hbox2.setPadding(new Insets(15, 12, 15, 12)); 
        hbox2.setSpacing(10);
        hbox3.setPadding(new Insets(15, 12, 15, 12)); 
        hbox3.setSpacing(10);
        hbox1.getChildren().addAll(kontoNR,kontoTextField,hämta);
        hbox2.getChildren().addAll(överför,överförTextField,plus,minus);
        hbox3.getChildren().addAll(ränta,godkänn);
        
//        border1.setBottom(submit1);
        scene3 = new Scene(vbox3,380,180);
        
        primaryStage.setTitle("Admin Login");
        primaryStage.setScene(scene1);
        primaryStage.show();
    }
    
    
    
    
    
    
    
    
    
//    @Override
//    public void handle(ActionEvent e) {
//        if(e.getSource().equals(btn))
//            System.out.println(pin.getText());
//            //här motod för att sckicka inmatade pin till databasen för kontroll
//            //här anropa nästa scenen 
//        else
//            System.out.println("Fel");
//            
//    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    
    
}
