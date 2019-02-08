package AdminClient.View;

import AdminClient.Controller.AdminController;
import AdminClient.Models.Account;
import AdminClient.Models.Customer;
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
    Scene scene1, scene2, scene3, scene4, scene5, scene6;

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
        Button skapaKonto = new Button();
        Button lån = new Button();
        kund.setText("Kontoöversikt");
        konto.setText("Kundkonto");
        skapaKonto.setText("Skapa konto");
        lån.setText("Lån");
        kund.setOnAction(e -> primaryStage.setScene(scene3));
        konto.setOnAction(e -> primaryStage.setScene(scene4));
        skapaKonto.setOnAction(e -> primaryStage.setScene(scene6));
        lån.setOnAction(e -> primaryStage.setScene(scene5));
        VBox vbox1 = new VBox();
        vbox1.setAlignment(Pos.CENTER);
        vbox1.setPadding(new Insets(10));
        vbox1.setSpacing(8);

        vbox1.getChildren().add(kund);
        vbox1.getChildren().add(konto);
        vbox1.getChildren().add(skapaKonto);
        vbox1.getChildren().add(lån);

        scene2 = new Scene(vbox1, 300, 250);

        //Scene3 
        Label kontoNR = new Label("konto nr");
        Label överför = new Label("Överför");
        Label ränta = new Label("Ränta");
        TextField kontoTextField = new TextField("Ange konto nr");
        TextField överförTextField = new TextField("Ange pengar");
        TextField räntaField = new TextField("Ange ränta");
        Button hämta = new Button();
        Button plus = new Button();
        Button minus = new Button();
        Button godkänn = new Button();
        godkänn.setText("ändra");
//        Button submit1= new Button();
//        submit1.setText("Submit");
        plus.setText("+");
        plus.setOnAction(e -> System.out.println("adda pengar till konton"));// adda pengar till konton
        minus.setText("-");
        minus.setOnAction(e -> System.out.println("Ta Ut pengar"));// Ta ut pengar från konton
        hämta.setText("Hämta");
        Label saldolabel = new Label();
        Button deleteAccount = new Button("delete");

        hämta.setOnAction(e -> {
            System.out.println("visa saldo ");
            //saldolabel.setText("");

            account = ar.getAccountById(Integer.parseInt(kontoTextField.getText()));
            System.out.println("detta " + account.getName());
            if (account.getName() != null) // if correct
            {
                saldolabel.setText(Double.toString(account.getBalance()));
                System.out.println("TRUE!!");
                //primaryStage.setScene(scene2);
            }

        }); //visa saldot på konto

        deleteAccount.setOnAction(e -> {

            //funkar bra
            account = ar.getAccountById(Integer.parseInt(kontoTextField.getText()));

            if (account.getName() != null) {
                System.out.println(account.getCustomer().getSSN());
                System.out.println("detta " + account.getName());
                ar.deleteCustomerAccount(account.getCustomer().getSSN(), account.getName());
            }
        }); //visa saldot på konton

        plus.setOnAction(e -> {
            System.out.println("deposit ");

            System.out.println("amount " + överförTextField.getText());
            ar.deposit(Integer.parseInt(kontoTextField.getText()), Double.parseDouble(överförTextField.getText()));
            if (account.getName() != null) // if correct
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
            if (account.getName() != null) // if correct
            {
                saldolabel.setText(Double.toString(account.getBalance()));
                System.out.println("TRUE!!");
                //primaryStage.setScene(scene2);
            }

        }); //visa saldot på konton

        godkänn.setOnAction(e -> {
            System.out.println("ändra ränta ");
            //saldolabel.setText("");

            account = ar.getAccountById(Integer.parseInt(kontoTextField.getText()));
            System.out.println(Integer.parseInt(kontoTextField.getText()) + " och "
                    + Double.parseDouble(räntaField.getText()));
            System.out.println("detta " + account.getName());
            if (account.getName() != null) // if correct
            {
                //saldolabel.setText(Double.toString(account.getBalance()));
                System.out.println("TRUE!!");
                //primaryStage.setScene(scene2);
                System.out.println(Integer.parseInt(kontoTextField.getText()) + " och "
                        + Double.parseDouble(räntaField.getText()));
                ar.changeInterest(Integer.parseInt(kontoTextField.getText()), Double.parseDouble(räntaField.getText()));
            }

        }); //visa saldot på konto

        HBox hbox1 = new HBox();

        hbox1.setPadding(new Insets(15, 12, 15, 12));
        hbox1.setSpacing(10);
        HBox hbox2 = new HBox();
        HBox hbox3 = new HBox();
        HBox hbox4 = new HBox();

        hbox4.setPadding(new Insets(15, 12, 15, 12));
        hbox4.setSpacing(10);
        hbox3.setPadding(new Insets(15, 12, 15, 12));
        hbox3.setSpacing(10);
        hbox3.getChildren().addAll(ränta, räntaField, godkänn);

        hbox2.setPadding(new Insets(15, 12, 15, 12));
        hbox2.setSpacing(10);
        hbox1.getChildren().addAll(kontoNR, kontoTextField, hämta, deleteAccount);
        hbox2.getChildren().addAll(överför, överförTextField, plus, minus);
        hbox4.getChildren().addAll(saldolabel);

        VBox vbox3 = new VBox();
        vbox3.getChildren().addAll(hbox1, hbox2, hbox3, hbox4);
//        border1.setBottom(submit1);
        scene3 = new Scene(vbox3, 350, 250);

        primaryStage.setTitle("Admin Login");
        primaryStage.setScene(scene1);
        primaryStage.show();

        Label ssnr = new Label("konto nr*");
        TextField ssnrField = new TextField("Ange ssnr*");
        Label firstname = new Label("Förnamn");
        TextField firstnameField = new TextField();
        Label efternamn = new Label("efternamn");
        TextField efternamnField = new TextField();
        Label phoneNumber = new Label("Telefonnummer");
        TextField phoneField = new TextField();
        Label email = new Label("Email");
        TextField emailField = new TextField();
        Label pinLabel = new Label("Pin");
        TextField pinField = new TextField();

        Button skapa = new Button("Skapa");
        Button delete = new Button("Delete*");
        Button update = new Button("Update");

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

        HBox knappBox = new HBox(15);
        knappBox.getChildren().addAll(skapa, delete, update);

        skapa.setOnAction(e -> {
            System.out.println("skapa ");
            //saldolabel.setText("");
            ar.addCustomer(ssnrField.getText(), firstnameField.getText(), efternamnField.getText(),
                    phoneField.getText(), emailField.getText(), Integer.parseInt(pinField.getText()), employee.getId());

//            if (account.getName()!= null) // if correct
//            {
//                //saldolabel.setText(Double.toString(account.getBalance()));
//                System.out.println("SKAPAT!!");
//                //primaryStage.setScene(scene2);
//            }
        }); //visa saldot på konton
        delete.setOnAction(e -> {
            System.out.println("delete ");
            //saldolabel.setText("");
            Customer toBeDeleted = ar.getCustomer(ssnrField.getText());
            System.out.println(toBeDeleted.getFirstname());

            if (toBeDeleted.getFirstname() != null) // if correct
            {
                //saldolabel.setText(Double.toString(account.getBalance()));
                System.out.println("DELETED!!");
                ar.deleteCustomer(ssnrField.getText());
                //primaryStage.setScene(scene2);
            }

        }); //ta bort konto

        update.setOnAction(e -> {
            System.out.println("update ");
            Customer toBeUpdated = ar.getCustomer(ssnrField.getText());
            System.out.println(toBeUpdated.getFirstname());
            if (firstnameField.getText().equalsIgnoreCase(""))
                firstnameField.setText(null);
            if (efternamnField.getText().equalsIgnoreCase(""))
                efternamnField.setText(null);
            if (phoneField.getText().equalsIgnoreCase(""))
                phoneField.setText(null);
            if (emailField.getText().equalsIgnoreCase(""))
                emailField.setText(null);
            if (pinField.getText().equalsIgnoreCase(""))
                pinField.setText("0");
            System.out.println("lll " + firstnameField.getText() + " " + phoneField.getText());
            if (toBeUpdated.getFirstname() != null) // if correct
            {
                int number = 0; // or any appllication default value
                try {//löser problematiken med felaktig input och det blir då 0 -> ingen ändring
                    number = Integer.parseInt(pinField.getText());
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }
                System.out.println("UPDATED!!");
                ar.UpdateCustomerInformation(ssnrField.getText(), firstnameField.getText(), efternamnField.getText(),
                        phoneField.getText(), emailField.getText(), number);
            }

        }); //updatera konto

        vbox2.getChildren().addAll(ssnrBox, firstnameBox, efternamnBox, phoneBox, emailBox, pinBox, knappBox);
//        BorderPane border2 = new BorderPane();
//        border1.setTop(hbox1);
//        border1.setCenter(hbox2);
//        border1.setBottom(saldolabel);
////        border1.setBottom(submit1);
        scene4 = new Scene(vbox2, 350, 250);

        Label ssn = new Label("PersonNr");
        TextField ssnField = new TextField("Ange ssn");
//        Label employeeName = new Label("employeename");
//        TextField employeeNameField = new TextField("Ange employeename");
        Label accountName = new Label("accountname");
        TextField accountNameField = new TextField("Ange accountname");
        Label categoryName = new Label("category");
        TextField categoryNameField = new TextField("Ange categoryname");

        Button skapa2 = new Button("Skapa");

        VBox vbox10 = new VBox();
        vbox10.setPadding(new Insets(15, 12, 15, 12));
        vbox10.setSpacing(10);

        HBox ssnBox = new HBox(15);
        ssnBox.getChildren().addAll(ssn, ssnField);

//        HBox employeenameBox = new HBox(15);
//        employeenameBox.getChildren().addAll(employeeName, employeeNameField);
        HBox accountnameBox = new HBox(15);
        accountnameBox.getChildren().addAll(accountName, accountNameField);

        HBox categorynameBox = new HBox(15);
        categorynameBox.getChildren().addAll(categoryName, categoryNameField);

        skapa2.setOnAction(e -> {
            System.out.println("skapa ");
            //saldolabel.setText("");
            System.out.println(ssnField.getText() + " " + employee.getFirstname() + " "
                    + accountNameField.getText() + " " + categoryNameField.getText());
            ar.createCustomerAccount(ssnField.getText(), employee.getFirstname(),
                    accountNameField.getText(), categoryNameField.getText());
//            if (account.getName()!= null) // if correct
//            {
//                //saldolabel.setText(Double.toString(account.getBalance()));
//                System.out.println("SKAPAT!!");
//                //primaryStage.setScene(scene2);
//            }

        });

        vbox10.getChildren().addAll(ssnBox, accountnameBox, categorynameBox, skapa2);
        scene6 = new Scene(vbox10, 350, 250);
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
