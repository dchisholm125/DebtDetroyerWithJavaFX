package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.input.DataFormat;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.input.InputEvent;

import java.util.ArrayList;

public class Main extends Application {

    ArrayList<UserDebt> debts = new ArrayList<UserDebt>();

    @Override
    public void start(Stage stage) {
        Scene debtProfileScene =  new Scene(new Group());

        TableView table = new TableView();
        TableColumn debtNameCol = new TableColumn("Debt Name");
        TableColumn interestCostCol = new TableColumn("Monthly Interest Cost");
        TableColumn balanceCol = new TableColumn("Balance Remaining");
        TableColumn interestRateCol = new TableColumn("Interest Rate");
        TableColumn monthlyPymtCol = new TableColumn("Monthly Payment");

        final Label tableLabel = new Label("Debt Profile");
        tableLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        table.setEditable(false);

        table.getColumns().addAll(debtNameCol, interestCostCol, balanceCol, interestRateCol, monthlyPymtCol);

        final VBox tableBox = new VBox();
        tableBox.setSpacing(5);
        tableBox.setPadding(new Insets(10, 0, 0, 10));
        tableBox.getChildren().addAll(tableLabel, table);

        ((Group) debtProfileScene.getRoot()).getChildren().addAll(tableBox);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10); //gap between inline elements of the grid
        grid.setVgap(10); //gap between elements in a column of the grid
        grid.setPadding(new Insets(25, 25, 25, 25)); //padding between each element's content: top, right, bottom, left


        Text scenetitle = new Text("Let's Add your debts!");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1); //positioning within grid, starting at: column 0, row 0,
        // spanning to column 2, row 1

        Label debtNameLabel = new Label("Debt Name:");
        grid.add(debtNameLabel, 0, 1); //inline element starting at column 0 ending at column 1

        TextField debtNameField = new TextField();
        grid.add(debtNameField, 1, 1); //adds interactive text field like a search bar on screen

        Label balanceLabel = new Label("Debt Balance:");
        grid.add(balanceLabel, 0, 2);

        TextField balanceBox = new TextField();
        grid.add(balanceBox, 1, 2);

        Button btn = new Button("Add debt"); //button instantiation with String that displays to UI
        HBox hbBtn = new HBox(10); // HBox parent object is a pane, and can have children such as 'btn'
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT); //position within addDebtScene
        hbBtn.getChildren().add(btn);//add button to parent
        grid.add(hbBtn, 1, 4); //add parent to grid

        Button doneBtn = new Button("All done!"); //button instantiation with String that displays to UI
        HBox doneHbBtn = new HBox(10); //parent object is a pane, and can have children such as 'btn'
        doneHbBtn.setAlignment(Pos.BOTTOM_RIGHT); //position within addDebtScene
        doneHbBtn.getChildren().add(doneBtn);//add button to parent
        grid.add(doneHbBtn, 1, 5); //add parent to grid

        final Text btnActiontarget = new Text();
        grid.add(btnActiontarget, 1, 6);
        final Text doneBtnActiontarget = new Text();
        grid.add(doneBtnActiontarget, 1, 7);

        //event that happens when btn is pressed
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                btnActiontarget.setFill(Color.FIREBRICK);
                btnActiontarget.setText("Debt added.");
                String debtName = debtNameField.getCharacters().toString();
                double debtBalance = Double.parseDouble(balanceBox.getCharacters().toString());

                debts.add(new UserDebt(debtName, debtBalance));

                System.out.println(debtName + " + " + debtBalance + " = " + debts.get(0).name);
            }
        });


        //event that happens when doneBtn is pressed
        doneBtn.setOnAction(new EventHandler<ActionEvent>()  {

            @Override
            public void handle(ActionEvent e) {
                doneBtnActiontarget.setFill(Color.GREEN);
                doneBtnActiontarget.setText("Let's get destroyin'!");

                //move to next addDebtScene which is the table itself
            }
        });

        Scene addDebtScene = new Scene(grid, 500, 500, Color.LIGHTGRAY); //define the addDebtScene to be shown on the Stage object

        Scene currentScene = debtProfileScene;

        stage.setTitle("JavaFX Scene Graph Demo");
        stage.setScene(currentScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
