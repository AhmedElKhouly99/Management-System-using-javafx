/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package dbtest;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/**
 *
 * @author ahmed
 */
public class Form extends Application {

    Label title;

    Label id;
    Label fname;
    Label mname;
    Label lname;
    Label email;
    Label phone;

    TextField idIn;
    TextField fnameIn;
    TextField mnameIn;
    TextField lnameIn;
    TextField emailIn;
    TextField phoneIn;

    Button newBtn;
    Button updateBtn;
    Button deletbtn;
    Button firstBtn;
    Button lastBtn;
    Button nextBtn;
    Button previous;

    VBox rightPane;
    VBox leftPane;

    FlowPane btns;

    VBox main;
    HBox h;

    Scene s;

    boolean isNew = false;

    @Override
    public void init() {
        title = new Label("Person Details");
        title.setStyle("-fx-translate-y: 3;-fx-translate-x: 15;-fx-background-color: -fx-background;");

        id = new Label("ID");
        fname = new Label("First Name");
        mname = new Label("Middle Name");
        lname = new Label("last Name");
        email = new Label("Email");
        phone = new Label("Phone");

        leftPane = new VBox(id, fname, mname, lname, email, phone);
        leftPane.setMinWidth(200);
        leftPane.setPadding(new Insets(0, 20, 10, 20));

        idIn = new TextField();
        idIn.setEditable(false);
        fnameIn = new TextField();
        mnameIn = new TextField();
        lnameIn = new TextField();
        emailIn = new TextField();
        phoneIn = new TextField();

        rightPane = new VBox(idIn, fnameIn, mnameIn, lnameIn, emailIn, phoneIn);
        rightPane.setMinWidth(500);
        rightPane.setPadding(new Insets(0, 20, 10, 20));

        newBtn = new Button("New");
        updateBtn = new Button("Update");
        deletbtn = new Button("Delete");
        firstBtn = new Button("First");
        previous = new Button("Previous");
        nextBtn = new Button("Next");
        lastBtn = new Button("Last");

        btns = new FlowPane(newBtn, updateBtn, deletbtn, firstBtn, previous, nextBtn, lastBtn);
        btns.setAlignment(Pos.CENTER);
        btns.setPadding(new Insets(10));

        h = new HBox(leftPane, rightPane);

        main = new VBox(h, btns);

        main.setStyle("-fx-content-display: top");
        main.setStyle("-fx-border-insets: 20 15 15 15");
        main.setStyle("-fx-background-color: white");
        main.setStyle("-fx-border-color: black");
        main.setPadding(new Insets(25, 10, 25, 10));

        StackPane pane = new StackPane(main, title);
        pane.setAlignment(title, Pos.TOP_LEFT);
        pane.setMargin(main, new Insets(12, 12, 12, 12));
        s = new Scene(pane);
    }

    @Override
    public void start(Stage primaryStage) {
        newBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Person p = new Person(0, fnameIn.getText(), mnameIn.getText(), lnameIn.getText(), emailIn.getText(), phoneIn.getText());
                try {
                    Database.addPerson(p);
                } catch (SQLException e) {
                }
                idIn.setText("");
                fnameIn.setText("");
                mnameIn.setText("");
                lnameIn.setText("");
                emailIn.setText("");
                phoneIn.setText("");
            }
        });

        updateBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Person p = new Person(Integer.parseInt(idIn.getText()), fnameIn.getText(), mnameIn.getText(), lnameIn.getText(), emailIn.getText(), phoneIn.getText());
                try {
                    Database.editePerson(p);
                } catch (SQLException e) {
                }
                idIn.setText("");
                fnameIn.setText("");
                mnameIn.setText("");
                lnameIn.setText("");
                emailIn.setText("");
                phoneIn.setText("");
            }
        });

        firstBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Person p;
                try {
                    p = Database.firstPerson();
                    idIn.setText(p.id.toString());
                    fnameIn.setText(p.fname);
                    mnameIn.setText(p.mname);
                    lnameIn.setText(p.lname);
                    emailIn.setText(p.email);
                    phoneIn.setText(p.phone);
                } catch (SQLException ex) {
                    Logger.getLogger(Form.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        previous.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Person p;
                try {
                    p = Database.prePerson();
                    idIn.setText(p.id.toString());
                    fnameIn.setText(p.fname);
                    mnameIn.setText(p.mname);
                    lnameIn.setText(p.lname);
                    emailIn.setText(p.email);
                    phoneIn.setText(p.phone);
                } catch (SQLException ex) {
                    Logger.getLogger(Form.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        lastBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Person p;
                try {
                    p = Database.lastPerson();
                    idIn.setText(p.id.toString());
                    fnameIn.setText(p.fname);
                    mnameIn.setText(p.mname);
                    lnameIn.setText(p.lname);
                    emailIn.setText(p.email);
                    phoneIn.setText(p.phone);
                } catch (SQLException ex) {
                    Logger.getLogger(Form.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        nextBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Person p;
                try {
                    p = Database.nextPerson();
                    idIn.setText(p.id.toString());
                    fnameIn.setText(p.fname);
                    mnameIn.setText(p.mname);
                    lnameIn.setText(p.lname);
                    emailIn.setText(p.email);
                    phoneIn.setText(p.phone);
                } catch (SQLException ex) {
                    Logger.getLogger(Form.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        deletbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Database.deletePerson(Integer.parseInt(idIn.getText()));
                } catch (SQLException e) {
                }
                idIn.setText("");
                fnameIn.setText("");
                mnameIn.setText("");
                lnameIn.setText("");
                emailIn.setText("");
                phoneIn.setText("");
            }
        });

        primaryStage.setTitle("Contacts");
        primaryStage.setScene(s);
        primaryStage.show();
        
        primaryStage.setOnCloseRequest((event) -> {            
            Platform.exit();
            System.exit(0);
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
