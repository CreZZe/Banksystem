
package CustomerClient.View;

import CustomerClient.Controller.ClientController;
import CustomerClient.Models.Account;
import CustomerClient.Models.Transactions;
import java.util.List;
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

import javafx.scene.layout.StackPane;

import javafx.scene.layout.VBox;

import javafx.stage.Stage;



/**

 *

 * @author KTH-CA

 */

public class CustomerClient extends Application {

    ClientController cc = new ClientController();
    Scene scene1,scene2,scene3,scene4,scene5, scene6;
    String custSSN = null;
    List<Account> accounts;
    @Override

    public void start(Stage primaryStage) {
         //scene 1 pin scene
        Button submitBtn = new Button();
        submitBtn.setText("Submit");
        TextField personNr= new TextField("Ange personNr");
        TextField pin= new TextField("Ange din pin");
        
        VBox vbox1 = new VBox ();
        vbox1.setPadding(new Insets(10));
        vbox1.setSpacing(10);
        
        submitBtn.setOnAction(e -> {
            if (cc.checkPincode(personNr.getText(), Integer.parseInt(pin.getText()))) {
                custSSN = personNr.getText();
                accounts = cc.getAllAccountsBySSN(custSSN);
                
                for (Account a : accounts) {
                    HBox upper = new HBox(20);
                    HBox lower = new HBox(20);
                    
                    Label kontoNr = new Label("Konto: " + a.getId());
                    Label belopp = new Label("Belopp: " + a.getBalance());
                    Label kategori = new Label(a.getCategory().getName());
                    Button historik = new Button("Historik");
                    upper.getChildren().addAll(kategori, kontoNr, belopp, historik);
                    
                    if (!a.getCategory().getName().equalsIgnoreCase("lån")) {
                        TextField uttag = new TextField("Belopp");
                        Button uttagKnapp = new Button("Ta ut");
                        
                        uttagKnapp.setOnAction(ea -> {
                            cc.withdraw(a.getId(), Integer.parseInt(uttag.getText()));
                            belopp.setText("Belopp: " + a.getBalance());
                        });
                        
                        lower.getChildren().addAll(uttag, uttagKnapp);
                        vbox1.getChildren().addAll(upper, lower);
                    }
                    else
                        vbox1.getChildren().add(upper);
                    
                    
                    historik.setOnAction(ea -> {
                        List<Transactions> transactions = cc.getLatestTransactions(a.getId());
                        VBox transactionList = new VBox(10);
                        
                        for (Transactions t : transactions) {
                            HBox transaction = new HBox(5);
                            Label datum = new Label(t.getDate().toString());
                            Label summa = new Label(Double.toString(t.getAmount()));
                            transaction.getChildren().addAll(datum, summa);
                            transactionList.getChildren().add(transaction);
                        }
                        transactionList.setMinHeight(200);
                        transactionList.setMinWidth(200);
                        transactionList.setPadding(new Insets(10, 20, 10, 20));
                        
                        Button goBack = new Button("Tillbaka");
                        
                        goBack.setOnAction(eac -> {
                            primaryStage.setScene(scene2);
                        });
                        
                        transactionList.getChildren().add(goBack);
                        scene6 = new Scene(transactionList);
                        primaryStage.setScene(scene6);
                    });
                }
                
                primaryStage.setScene(scene2);
            }
            });//OM kundens pin & person nummer stämmer ändra till scen2
        
        //scene2 visa konton och lån för kunden
        

        
        VBox vbox=new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);
        vbox.getChildren().addAll(pin,personNr,submitBtn);
        scene1 = new Scene(vbox, 300, 250);
        
        
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