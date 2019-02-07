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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author KTH-CA
 */
public class CustomerClientGui extends Application {
    Scene scene1,scene2,scene3,scene4,scene5;
    @Override
    public void start(Stage primaryStage) {
         //scene 1 pin scene
        Button submitBtn = new Button();
        submitBtn.setText("Submit");
        TextField personNr= new TextField("Ange personNr");
        TextField pin= new TextField("Ange din pin");
        submitBtn.setOnAction(e->primaryStage.setScene(scene2));//OM kundens pin & person nummer stämmer ändra till scen2
        VBox vbox=new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);
        vbox.getChildren().addAll(pin,personNr,submitBtn);
        scene1 = new Scene(vbox, 300, 250);
        
        //scene2 visa konton och lån för kunden
        TextField uttag1 = new TextField("Ange summa att ta ut");
        TextField uttag2 = new TextField("Ange summa att ta ut");
        Button taUt1 = new Button();
        taUt1.setText("Uttag");
        taUt1.setOnAction(e->System.out.println("ta ut"));//uppdatera saldo vid uttag
        Button taUt2 = new Button();
        taUt2.setText("Uttag");
        taUt2.setOnAction(e->System.out.println());//uppdatera saldo vid uttag
        Button ändra1=new Button();
        Button ändra2=new Button();
        ändra1.setText("Lån");
        ändra1.setOnAction(e->primaryStage.setScene(scene3));// anropa scene3
        ändra2.setText("Lån");
        ändra2.setOnAction(e->primaryStage.setScene(scene3));// anropa scene3
        Label kontoNr1 = new Label("konto nr");
        Label kontoNr2 = new Label("konto nr");
        Label kontoSaldo1= new Label("600");
        Label kontoSaldo2= new Label("400");
        Label lån1 = new Label("lånNR1");
        Label lån2 = new Label("lånNR2");
        Label lånSaldo1 = new Label("500");
        Label lånSaldo2 = new Label("8560");
        VBox vbox1 = new VBox ();
        vbox1.setPadding(new Insets(10));
        vbox1.setSpacing(10);
        HBox hbox = new HBox();
        HBox hbox1 = new HBox();
        HBox hbox2= new HBox();
        HBox hbox3 = new HBox();
        HBox hbox4= new HBox();
        HBox hbox5= new HBox();
        hbox.setSpacing(40);
        hbox1.setSpacing(40);
        hbox2.setSpacing(40);
        hbox3.setSpacing(40);
        hbox4.setSpacing(40);
        hbox5.setSpacing(40);
        hbox.getChildren().addAll(kontoNr1,kontoSaldo1);
        hbox1.getChildren().addAll(uttag1,taUt1);
        hbox2.getChildren().addAll(kontoNr2,kontoSaldo2);
        hbox3.getChildren().addAll(uttag2,taUt2);
        hbox4.getChildren().addAll(lån1,lånSaldo1,ändra1);
        hbox5.getChildren().addAll(lån2,lånSaldo2,ändra2);
        vbox1.getChildren().addAll(hbox,hbox1,hbox2,hbox3,hbox4,hbox5);
        scene2=new Scene(vbox1,400,400);
        
        //scene3 ändra elle skapa lån
        Button skapaLån=new Button();
        skapaLån.setText("skapa lån");
        skapaLån.setOnAction(e->primaryStage.setScene(scene4));
        Button ändraLån=new Button();
        ändraLån.setText("ändra Lån");
        ändraLån.setOnAction(e->primaryStage.setScene(scene5));
        VBox vbox6= new VBox();
        vbox6.getChildren().addAll(skapaLån,ändraLån);
        scene3 = new Scene(vbox6,300,250);
        
        //scene4 skapa lån
        
        TextField lånSumma= new TextField("ange summa ");
        TextField ränta= new TextField("ange räntesats");
        Button ansökLån= new Button();
        ansökLån.setText("ansök");
        ansökLån.setOnAction(e->System.out.println("skapa lån"));// skapa lån 
        VBox vbox7 = new VBox();
        vbox7.setPadding(new Insets(10));
        vbox7.setSpacing(10);
        vbox7.getChildren().addAll(lånSumma,ränta,ansökLån);
        scene4 = new Scene(vbox7,300,250);
        
        //scene5 ändra Lån
        
        TextField lånSumma1= new TextField("ange summa ");
        TextField ränta1= new TextField("ange räntesats");
        Button ändraLÅN= new Button();
        ändraLÅN.setText("ansök");
        ändraLÅN.setOnAction(e->System.out.println("ändra lån lån"));//uppdatera lån 
        VBox vbox8 = new VBox();
        vbox8.setPadding(new Insets(10));
        vbox8.setSpacing(10);
        vbox8.getChildren().addAll(lånSumma1,ränta1,ändraLån);
        scene5 = new Scene(vbox8,300,250);
        
        primaryStage.setTitle("KundBank");
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
