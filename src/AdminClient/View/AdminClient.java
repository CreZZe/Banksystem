package AdminClient.View;

import AdminClient.Controller.AdminController;
import AdminClient.Models.Account;
import AdminClient.Models.Employee;
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
public class AdminClient extends Application {

    AdminController ar = new AdminController();
    Employee employee;
    Account account;
    Scene scene1, scene2, scene3, scene4, scene5;

    @Override
    public void start(Stage primaryStage) {
        //scene 1 
        Button submitBtn = new Button();
        submitBtn.setText("Submit");
        TextField pin = new TextField("Ange användarnamn");

        submitBtn.setOnAction(e -> {
            System.out.println(pin.getText());

            System.out.println("hej " + ar.getEmployee(pin.getText()));
            employee = ar.getEmployee(pin.getText());
            System.out.println("detta " + employee.getFirstname());
            if (employee.getFirstname() != null) // if correct
            {
                System.out.println("TRUE!!");
                primaryStage.setScene(scene2);
            }

        });

        VBox vbox = new VBox();
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
        Button konto = new Button();
        Button lån = new Button();
        kund.setText("Kunder");
        konto.setText("Konto");
        lån.setText("Lån");
        kund.setOnAction(e -> primaryStage.setScene(scene3));
        konto.setOnAction(e -> primaryStage.setScene(scene4));
        lån.setOnAction(e -> primaryStage.setScene(scene5));
        VBox vbox1 = new VBox();
        vbox1.setAlignment(Pos.CENTER);
        vbox1.setPadding(new Insets(10));
        vbox1.setSpacing(8);

        vbox1.getChildren().add(kund);
        vbox1.getChildren().add(konto);
        vbox1.getChildren().add(lån);
        scene2 = new Scene(vbox1, 300, 250);

        //Scene3 
        Label kontoNR = new Label("konto nr");
        Label överför = new Label("Överför");
        TextField kontoTextField = new TextField("Ange konto nr");
        TextField överförTextField = new TextField("Ange pengar");
        Button hämta = new Button();
        Button plus = new Button();
        Button minus = new Button();
//        Button submit1= new Button();
//        submit1.setText("Submit");
        plus.setText("+");
        plus.setOnAction(e -> System.out.println("adda pengar till konton"));// adda pengar till konton
        minus.setText("-");
        minus.setOnAction(e -> System.out.println("Ta Ut pengar"));// Ta ut pengar från konton
        hämta.setText("Hämta");
        Label saldolabel = new Label();

        hämta.setOnAction(e -> {
            System.out.println("visa saldo ");
            //saldolabel.setText("");

            account = ar.getAccountById(Integer.parseInt(kontoTextField.getText()));
            System.out.println("detta " + account.getName());
            if (account.getName()!= null) // if correct
            {
                saldolabel.setText(Double.toString(account.getBalance()));
                System.out.println("TRUE!!");
                //primaryStage.setScene(scene2);
            }

        }); //visa saldot på konton
        
        
        plus.setOnAction(e -> {
            System.out.println("deposit ");
            //saldolabel.setText("");

            //account = ar.getAccountById(Integer.parseInt(kontoTextField.getText()));
            //System.out.println("detta " + account.getName());
            System.out.println("amount " + överförTextField.getText());
            //ar.deposit(50000, 2);
            ar.deposit(Integer.parseInt(kontoTextField.getText()), Double.parseDouble(överförTextField.getText()));
            if (account.getName()!= null) // if correct
            {
                saldolabel.setText(Double.toString(account.getBalance()));
                System.out.println("TRUE!!");
                //primaryStage.setScene(scene2);
            }

        }); //visa saldot på konton
        
        minus.setOnAction(e -> {
            System.out.println("withdraw ");
            //saldolabel.setText("");

            //account = ar.getAccountById(Integer.parseInt(kontoTextField.getText()));
            //System.out.println("detta " + account.getName());
            System.out.println("amount " + överförTextField.getText());
            //ar.deposit(50000, 2);
            ar.withdraw(Integer.parseInt(kontoTextField.getText()), Double.parseDouble(överförTextField.getText()));
            if (account.getName()!= null) // if correct
            {
                saldolabel.setText(Double.toString(account.getBalance()));
                System.out.println("TRUE!!");
                //primaryStage.setScene(scene2);
            }

        }); //visa saldot på konton
        
        HBox hbox1 = new HBox();

        hbox1.setPadding(new Insets(15, 12, 15, 12));
        hbox1.setSpacing(10);
        HBox hbox2 = new HBox();
        hbox2.setPadding(new Insets(15, 12, 15, 12));
        hbox2.setSpacing(10);
        hbox1.getChildren().addAll(kontoNR, kontoTextField, hämta);
        hbox2.getChildren().addAll(överför, överförTextField, plus, minus);
        BorderPane border1 = new BorderPane();
        border1.setTop(hbox1);
        border1.setCenter(hbox2);
        border1.setBottom(saldolabel);
//        border1.setBottom(submit1);
        scene3 = new Scene(border1, 350, 250);

        primaryStage.setTitle("Admin Login");
        primaryStage.setScene(scene1);
        primaryStage.show();
        
        
        //Scene 4
        Label ssnr = new Label("konto nr");
        TextField ssnrField = new TextField("Ange ssnr");
        Label firstname = new Label("Förnamn");
        TextField firstnameField = new TextField("Ange förnamn");
        Label efternamn = new Label("konto nr");
        TextField efternamnField = new TextField("Ange efternamn");
        Label phoneNumber = new Label("Telefonnummer");
        TextField phoneField = new TextField("Ange telefonnummer");
        Label email = new Label("Email");
        TextField emailField = new TextField("Ange email");
        Label pinLabel = new Label("Pin");
        TextField pinField = new TextField("Ange pin");
        
        Button skapa = new Button("Skapa");
        
        VBox vbox2 = new VBox();
        vbox2.setPadding(new Insets(15, 12, 15, 12));
        vbox2.setSpacing(10);
        
        HBox ssnrBox = new HBox(15);
        ssnrBox.getChildren().addAll(ssnr, ssnrField);
        
        HBox firstnameBox = new HBox(15);
        firstnameBox.getChildren().addAll(firstname, firstnameField);
        
        HBox efternamnBox = new HBox(15);
        efternamnBox.getChildren().addAll(efternamn, efternamnField);
        
        HBox phoneBox = new HBox(15);
        phoneBox.getChildren().addAll(phoneNumber, phoneField);
        
        HBox emailBox = new HBox(15);
        emailBox.getChildren().addAll(email, emailField);
        
        HBox pinBox = new HBox(15);
        pinBox.getChildren().addAll(pinLabel, pinField);
        
        skapa.setOnAction(e -> {
            System.out.println("withdraw ");
            //saldolabel.setText("");
            ar.createCustomerAccount(STYLESHEET_MODENA, STYLESHEET_MODENA, STYLESHEET_MODENA, STYLESHEET_MODENA);
            //account = ar.getAccountById(Integer.parseInt(kontoTextField.getText()));
            //System.out.println("detta " + account.getName());
            System.out.println("amount " + överförTextField.getText());
            //ar.deposit(50000, 2);
            ar.withdraw(Integer.parseInt(kontoTextField.getText()), Double.parseDouble(överförTextField.getText()));
            if (account.getName()!= null) // if correct
            {
                saldolabel.setText(Double.toString(account.getBalance()));
                System.out.println("TRUE!!");
                //primaryStage.setScene(scene2);
            }

        }); //visa saldot på konton
        
        
        vbox2.getChildren().addAll(ssnrBox, firstnameBox, efternamnBox, phoneBox, emailBox, pinBox, skapa);
//        BorderPane border2 = new BorderPane();
//        border1.setTop(hbox1);
//        border1.setCenter(hbox2);
//        border1.setBottom(saldolabel);
////        border1.setBottom(submit1);
        scene4 = new Scene(vbox2, 350, 250);
        
        
        
        
        //Scene 5
        
        
        
        
        
        
        
        
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
