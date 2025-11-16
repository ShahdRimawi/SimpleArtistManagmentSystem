package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Link.DoubleLinkedList;
import Link.NodeDouble;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Main extends Application {

	private BorderPane basicBorderPane, borderPane2, borderPane3, borderPane4, borderPane5, borderPane6;
	private BorderPane bf;
	// basic -->table view ,(, borderPane2-->Passenger Management Menu ,),3-->flight
	// ,,5__>stat
	// ,,4-->menu
	// 3-->Flight

//	private TableView<Major> tableView;
	private ArrayList<Passenger> all = new ArrayList<>();
	private DoubleLinkedList Flight = new DoubleLinkedList();
	// private NodeDouble currentFlight = null; // To track the current flight

	private VBox vbox1; // for buttons
//	private TableView<DoubleLinkedList> tableView;
	private int count = 20;
	private Button btTableView, btReadWrite, btFileChooser, btInsert, btDeleteSearchDisplaySave,
			btStatisticsAndReporting; // ready
	private BorderPane root = new BorderPane(); // basic
//	private TableView<Passenger> tableView = new TableView<>();

	@Override
	public void start(Stage primaryStage) {

//		root.setCenter(getBasicBorderPane());
		root.setLeft(getVBcox());
		root.setCenter(getBasicBorderPane());
		Scene scene = new Scene(root, 1000, 600);
		primaryStage.setTitle("Theird project");
		primaryStage.setScene(scene);

		primaryStage.show();

		btDeleteSearchDisplaySave.setOnAction(e -> { // Passenger Management Menu
			root.setCenter(getPassenger());
		});

		btFileChooser.setOnAction(e -> { // Flight Management Menu
			root.setCenter(getFlightBorder());
		});

		btReadWrite.setOnAction(e -> { // log text
			root.setCenter(getFileBorder());
		});
		btInsert.setOnAction(e -> { // Operation Menu
			root.setCenter(getOperationBorderPane());
		});
		btTableView.setOnAction(e -> { // table view
			root.setCenter(getBasicBorderPane());
		});
		btStatisticsAndReporting.setOnAction(e -> { // stat

			root.setCenter(getStatborderPane());
		});

	}

	private VBox getVBcox() {
		vbox1 = new VBox(20);
		
		btTableView = new Button("Table View");
		btTableView.setStyle("-fx-text-fill: white; -fx-background-color: purple;");
		btTableView.setFont(Font.font("Arial", 15));

		btDeleteSearchDisplaySave = new Button("Passenger Management Menu");
		btDeleteSearchDisplaySave.setStyle("-fx-text-fill: white; -fx-background-color: purple;");
		btDeleteSearchDisplaySave.setFont(Font.font("Arial", 15));

		btFileChooser = new Button("Flight Management Menu");
		btFileChooser.setStyle("-fx-text-fill: white; -fx-background-color: purple;");
		btFileChooser.setFont(Font.font("Arial", 15));

		btReadWrite = new Button("File chooser");
		btReadWrite.setStyle("-fx-text-fill: white; -fx-background-color: purple;");
		btReadWrite.setFont(Font.font("Arial", 15));

		btInsert = new Button("Operation Menu");
		btInsert.setStyle("-fx-text-fill: white; -fx-background-color: purple;");
		btInsert.setFont(Font.font("Arial", 15));

		btStatisticsAndReporting = new Button("Statistical Menu");
		btStatisticsAndReporting.setStyle("-fx-text-fill: white; -fx-background-color: purple;");
		btStatisticsAndReporting.setFont(Font.font("Arial", 15));

		vbox1.getChildren().addAll(btTableView, btDeleteSearchDisplaySave, btFileChooser, btReadWrite, btInsert,
				btStatisticsAndReporting);
		vbox1.setMinHeight(20);
		vbox1.setMinWidth(100);
		vbox1.setAlignment(Pos.CENTER);
		vbox1.setPadding(new Insets(0, 20, 0, 20));
		return vbox1;

	}

	private BorderPane getBasicBorderPane() { // for table view
		basicBorderPane = new BorderPane();

		TableView<Passenger> tableView = new TableView<>();
		TableColumn<Passenger, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn<Passenger, Integer> idColumn = new TableColumn<>("ID");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

		TableColumn<Passenger, String> flightIDColumn = new TableColumn<>("Flight ID");
		flightIDColumn.setCellValueFactory(new PropertyValueFactory<>("flightID"));

		// Add columns to TableView
		tableView.getColumns().addAll(nameColumn, idColumn, flightIDColumn);

		// Populate data
		ObservableList<Passenger> passengers = FXCollections.observableArrayList(all);
		tableView.setItems(passengers);
		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		// Set up layout
		VBox vbox = new VBox(tableView);
		basicBorderPane.setCenter(vbox);
		Color backgroundColor = new Color(0.033, 0.04, 0.8, 0.09);
		basicBorderPane.setBackground(new Background(new BackgroundFill(backgroundColor, CornerRadii.EMPTY, null)));

		return basicBorderPane;

	}

	private BorderPane getPassenger() {

		borderPane2 = new BorderPane();
		GridPane gp = new GridPane();
		gp.setHgap(10);
		gp.setVgap(10);
//		Add Passenger: Add a new passenger with a unique ID, name, flight ID, and status 
//		(e.g., Regular, VIP).
		TextField name = new TextField();
		name.setPromptText("name");
		gp.add(name, 0, 0);

		TextField tawjihi = new TextField();
		tawjihi.setPromptText("flight ID");
		gp.add(tawjihi, 1, 0);

		TextField placement = new TextField();
		placement.setPromptText("status ");
		gp.add(placement, 2, 0);

		Label lbinsert = new Label("");
		gp.add(lbinsert, 1, 1);

		Button insert = new Button("add");
		gp.add(insert, 0, 1);
		insert.setStyle("-fx-text-fill: white; -fx-background-color: purple;");
		insert.setOnAction(e -> {
//			Flight.addFlight("1", "kk", true); // Ensure the flight ID matches the input

			count++;
			NodeDouble flight = Flight.searchFlight(tawjihi.getText());

			if (flight != null) {
				Passenger p = new Passenger(name.getText(), count, tawjihi.getText(),
						Boolean.parseBoolean(placement.getText()));
				lbinsert.setText(name.getText() + " id: " + count + " ,Successfully added");
				if (Boolean.parseBoolean(placement.getText())) {
					flight.vipQueue.enqueue(p);
					all.add(p);
				} else {
					flight.regularQueue.enqueue(p);
					all.add(p);
				}
			} else {
				lbinsert.setText("Flight not found. Try again.");
			}

			// Clear the input fields
			name.setText("");
			tawjihi.setText("");
			placement.setText("");
		});

		TextField id = new TextField();
		id.setPromptText("Enter passenger's ID");
		gp.add(id, 0, 3);

		Label lbi = new Label("");
		gp.add(lbi, 1, 4);

		Button delete = new Button("remove");
		gp.add(delete, 0, 4);
		delete.setStyle("-fx-text-fill: white; -fx-background-color: purple;");
		delete.setOnAction(e -> {
			int i = Integer.parseInt(id.getText());
			boolean removed = false;

			for (NodeDouble n : Flight.all) {
				if (n.removePassenger(i)) {
					lbi.setText("Passenger with ID " + i + " removed successfully.");
					removed = true;
					break;
				}
			}

			if (!removed) {
				lbi.setText("Passenger with ID " + i + " not found.");
			}

			id.setText(""); // Clear the input field

//			if(pp!=null) {
//				for(NodeDouble n: Flight.all) {
//					n.
//				}
//			}

		});
		TextField name1 = new TextField();
		name1.setPromptText("updated name");
		gp.add(name1, 0, 6);

		TextField tawjihi1 = new TextField();
		tawjihi1.setPromptText("updated status");
		gp.add(tawjihi1, 1, 6);

		TextField placement1 = new TextField();
		placement1.setPromptText("updated FlightId");
		gp.add(placement1, 2, 6);

		TextField placement11 = new TextField();
		placement11.setPromptText("Enter the id");
		gp.add(placement11, 3, 6);

		Label lbinsert1 = new Label("");
		gp.add(lbinsert1, 1, 7);

		Button insert1 = new Button("update"); // The program shall be able to save the updated lists back to the files.
		gp.add(insert1, 0, 7);
		insert1.setStyle("-fx-text-fill: white; -fx-background-color: purple;");
		insert1.setOnAction(e -> {
			Passenger updatedPassenger = new Passenger(name1.getText(), Integer.parseInt(placement11.getText()),
					placement1.getText(), Boolean.parseBoolean(tawjihi1.getText()));

			boolean updated = false;
			for (NodeDouble n : Flight.all) {
				if (n.updatePassenger(Integer.parseInt(placement11.getText()), updatedPassenger)) {
					lbinsert1.setText("Passenger updated successfully.");
					updated = true;
					break;
				}
			}

			if (!updated) {
				lbinsert1.setText("Passenger update failed. ID not found.");
			}

			// Clear input fields
			name1.setText("");
			tawjihi1.setText("");
			placement1.setText("");
			placement11.setText("");
		});

		TextField id1 = new TextField();
		id1.setPromptText("Enter the id");
		gp.add(id1, 0, 9);

		Label lbin1 = new Label("");
		gp.add(lbin1, 1, 10);

		Button search1 = new Button("search");
		gp.add(search1, 0, 10);
		search1.setStyle("-fx-text-fill: white; -fx-background-color: purple;");
		search1.setOnAction(e -> {
//			for (NodeDouble n : Flight.all) {
//
//				lbin1.setText(" " + n.search(Integer.parseInt(id1.getText())));
//			}
			lbin1.setText(" " + Flight.searchPassenger(Integer.parseInt(id1.getText())));

		});

//		TextArea a = new TextArea();
//		gp.add(id1, 0, 12);
		Button search11 = new Button("print all");
		gp.add(search11, 0, 12);
		search11.setStyle("-fx-text-fill: white; -fx-background-color: purple;");
		search11.setOnAction(e -> {
			for (NodeDouble n : Flight.all) {
				n.printAllPassengers();
			}
		});

		TextField id13 = new TextField();
		id13.setPromptText("Enter the id");
		gp.add(id13, 0, 14);
		Button search113 = new Button("print");
		gp.add(search113, 0, 15);
		search113.setStyle("-fx-text-fill: white; -fx-background-color: purple;");
		search113.setOnAction(e -> {
			for (NodeDouble n : Flight.all) {
				n.printPassengerInfo(Integer.parseInt(id13.getText()));
			}
		});

		gp.setAlignment(Pos.CENTER);
		VBox vbox = new VBox(30, gp);
		vbox.setAlignment(Pos.TOP_CENTER);
		vbox.setPadding(new Insets(100, 0, 0, 0));
		borderPane2.setCenter(vbox);
		Color backgroundColor = new Color(0.033, 0.04, 0.8, 0.09);
		borderPane2.setBackground(new Background(new BackgroundFill(backgroundColor, CornerRadii.EMPTY, null)));
		return borderPane2;

	}

	private BorderPane getFlightBorder() {
		borderPane3 = new BorderPane();
		GridPane gp = new GridPane();
		gp.setHgap(10);
		gp.setVgap(10);

		TextField name = new TextField();
		name.setPromptText("destination");
		gp.add(name, 0, 0);

		TextField tawjihi = new TextField();
		tawjihi.setPromptText("flight ID");
		gp.add(tawjihi, 1, 0);

		TextField placement = new TextField();
		placement.setPromptText("status ");
		gp.add(placement, 2, 0);

		Label lbinsert = new Label("");
		gp.add(lbinsert, 1, 1);

		Button insert = new Button("Add Flight");
		gp.add(insert, 0, 1);
		insert.setStyle("-fx-text-fill: white; -fx-background-color: purple;");
		insert.setOnAction(e -> {
			Flight.addFlight(tawjihi.getText(), name.getText(), Boolean.parseBoolean(placement.getText())); // Ensure
																											// the
																											// flight ID
																											// matches
																											// the input
			lbinsert.setText("Adding succesfuly");
		});

		TextField id = new TextField();
		id.setPromptText("Enter Flight's ID");
		gp.add(id, 0, 3);

		Label lbi = new Label("");
		gp.add(lbi, 1, 4);

		Button delete = new Button("remove");
		gp.add(delete, 0, 4);
		delete.setStyle("-fx-text-fill: white; -fx-background-color: purple;");
		delete.setOnAction(e -> {
			Flight.removeFlight(id.getText());
			// in console
		});
//		 public void updateFlight(String flightID, String newDestination, Boolean newStatus) {

		TextField name1 = new TextField();
		name1.setPromptText("updated Destination");
		gp.add(name1, 0, 6);

		TextField tawjihi1 = new TextField();
		tawjihi1.setPromptText("updated status");
		gp.add(tawjihi1, 1, 6);

		TextField placement1 = new TextField();
		placement1.setPromptText("Enter the FlightId");
		gp.add(placement1, 2, 6);

//		TextField placement11 = new TextField();
//		placement11.setPromptText("");
//		gp.add(placement11, 3, 6);

		Label lbinsert1 = new Label("");
		gp.add(lbinsert1, 1, 7);

		Button insert1 = new Button("update"); // The program shall be able to save the updated lists back to the files.
		gp.add(insert1, 0, 7);
		insert1.setStyle("-fx-text-fill: white; -fx-background-color: purple;");
		insert1.setOnAction(e -> {
			Flight.updateFlight(placement1.getText(), name1.getText(), Boolean.parseBoolean(tawjihi1.getText()));
		});

		TextField id1 = new TextField();
		id1.setPromptText("Enter Flight id");
		gp.add(id1, 0, 9);

		Label lbin1 = new Label("");
		gp.add(lbin1, 1, 10);

		Button search1 = new Button("search");
		gp.add(search1, 0, 10);
		search1.setStyle("-fx-text-fill: white; -fx-background-color: purple;");
		search1.setOnAction(e -> {

			lbin1.setText("" + Flight.searchFlight(id1.getText()));
		});

		Button search11 = new Button("print all");
		gp.add(search11, 0, 12);
		search11.setStyle("-fx-text-fill: white; -fx-background-color: purple;");
		search11.setOnAction(e -> {
			Flight.displayFlights();
		});

		TextField id13 = new TextField();
		id13.setPromptText("Enter the id");
		gp.add(id13, 0, 14);
		Button search113 = new Button("print");
		gp.add(search113, 0, 15);
		search113.setStyle("-fx-text-fill: white; -fx-background-color: purple;");
		search113.setOnAction(e -> {
			Flight.displaySpecificFlight(id13.getText());
		});

		Button s1 = new Button("Display Active Flight");
		gp.add(s1, 0, 17);
		s1.setStyle("-fx-text-fill: white; -fx-background-color: purple;");
		s1.setOnAction(e -> {
			Flight.displayActiveFlights();
		});
		Button s2 = new Button("Display InActive Flight");
		gp.add(s2, 2, 17);
		s2.setStyle("-fx-text-fill: white; -fx-background-color: purple;");
		s2.setOnAction(e -> {
			Flight.displayInactiveFlights();
		});

		gp.setAlignment(Pos.CENTER);
		VBox vbox = new VBox(30, gp);
		vbox.setAlignment(Pos.TOP_CENTER);
		vbox.setPadding(new Insets(100, 0, 0, 0));
		borderPane3.setCenter(vbox);
		Color backgroundColor = new Color(0.033, 0.04, 0.8, 0.09);
		borderPane3.setBackground(new Background(new BackgroundFill(backgroundColor, CornerRadii.EMPTY, null)));
		return borderPane3;

	}

	private BorderPane getOperationBorderPane() {
		borderPane4 = new BorderPane();
		GridPane gp = new GridPane();
		gp.setHgap(10);
		gp.setVgap(10);

		TextField name = new TextField();
		name.setPromptText("passenger id");
		gp.add(name, 0, 0);

		Label lbinsert = new Label("");
		gp.add(lbinsert, 1, 1);

		Button insert = new Button("Check in");
		gp.add(insert, 0, 1);
		insert.setStyle("-fx-text-fill: white; -fx-background-color: purple;");
		insert.setOnAction(e -> {
			Passenger l = null;
			for (NodeDouble n : Flight.all) {

				l = Flight.searchPassenger(Integer.parseInt(name.getText()));
			}
			if (l != null) {
				Flight.checkInPassenger(l);
				lbinsert.setText("succesfuly");

			} else {
				lbinsert.setText("not found");
			}

		});

		TextField flightIDField = new TextField();
		flightIDField.setPromptText("Enter flight ID");
		gp.add(flightIDField, 0, 3);

		Label lbBoardStatus = new Label("");
		gp.add(lbBoardStatus, 1, 4);

		Button boardButton = new Button("Board Passenger");
		gp.add(boardButton, 0, 4);
		boardButton.setStyle("-fx-text-fill: white; -fx-background-color: purple;");
		boardButton.setOnAction(e -> {
			String flightID = flightIDField.getText();
			NodeDouble flight = Flight.searchFlight(flightID);

			if (flight != null) {
				Passenger passenger = null;
				if (!flight.vipQueue.isEmpty()) {
					passenger = flight.vipQueue.dequeue();
				} else if (!flight.regularQueue.isEmpty()) {
					passenger = flight.regularQueue.dequeue();
				}

				if (passenger != null) {
					flight.boardedPassengers.insertAtStart(passenger);
					lbBoardStatus.setText("Passenger " + passenger.getName() + " boarded successfully.");
				} else {
					lbBoardStatus.setText("No passengers to board.");
				}
			} else {
				lbBoardStatus.setText("Flight " + flightID + " not found.");
			}
		});

		TextField passengerIDField = new TextField();
		passengerIDField.setPromptText("Enter passenger ID");
		gp.add(passengerIDField, 0, 6);

		Label lbCancelStatus = new Label("");
		gp.add(lbCancelStatus, 1, 7);

		Button cancelButton = new Button("Cancel Passenger");
		gp.add(cancelButton, 0, 7);
		cancelButton.setStyle("-fx-text-fill: white; -fx-background-color: purple;");
		cancelButton.setOnAction(e -> {
			Passenger l = null;

			l = Flight.searchPassenger(Integer.parseInt(passengerIDField.getText()));
			if (l != null) {
				Flight.cancelPassenger(l);
				lbCancelStatus.setText(" succesfuly");

			} else {
				lbCancelStatus.setText("not found");
			}

//		    public void cancelPassenger(String flightID, Passenger passenger) {

		});
		Button undoButton = new Button("Undo");
		gp.add(undoButton, 1, 9);
		undoButton.setStyle("-fx-text-fill: white; -fx-background-color: purple;");
		undoButton.setOnAction(e -> {
			for (NodeDouble flight : Flight.all) {
				flight.undo(); // Call the undo operation for the flight
			}
		});

		Button redoButton = new Button("Redo");
		gp.add(redoButton, 2, 10);
		redoButton.setStyle("-fx-text-fill: white; -fx-background-color: purple;");
		redoButton.setOnAction(e -> {
			for (NodeDouble flight : Flight.all) {
				flight.redo(); // Call the redo operation for the flight
			}
		});

		gp.setAlignment(Pos.CENTER);
		VBox vbox = new VBox(30, gp);
		vbox.setAlignment(Pos.TOP_CENTER);
		vbox.setPadding(new Insets(100, 0, 0, 0));
		borderPane4.setCenter(vbox);
		Color backgroundColor = new Color(0.033, 0.04, 0.8, 0.09);
		borderPane4.setBackground(new Background(new BackgroundFill(backgroundColor, CornerRadii.EMPTY, null)));
		return borderPane4;

	}

	private BorderPane getStatborderPane() { // statistics board
		borderPane5 = new BorderPane();
		GridPane gp = new GridPane();
		gp.setHgap(10);
		gp.setVgap(10);

		Label lbCancelledVIPCount = new Label("Cancelled Passengers: " + Flight.getCanceled());
		Label lbBoardedVIPCount = new Label("Boarded Passengers: " + Flight.getB());

		Button count1 = new Button("Regular Count in Queue");

		gp.add(count1, 0, 2);
		count1.setStyle("-fx-text-fill: white; -fx-background-color: purple;");
		count1.setOnAction(e -> {
			for (NodeDouble flight : Flight.all) {
				System.out.println("" + flight.countQueueReg());
			}
		});
		Button count2 = new Button("Vip Count in Queue");
		gp.add(count2, 0, 6);
		count2.setStyle("-fx-text-fill: white; -fx-background-color: purple;");
		count2.setOnAction(e -> {
			for (NodeDouble flight : Flight.all) {
				System.out.println("" + flight.countQueueVip());
			}
		});

		gp.add(lbCancelledVIPCount, 0, 0);
		gp.add(lbBoardedVIPCount, 0, 4);

		gp.setAlignment(Pos.CENTER);
		VBox vbox = new VBox(30, gp);
		vbox.setAlignment(Pos.TOP_CENTER);
		vbox.setPadding(new Insets(100, 0, 0, 0));
		borderPane5.setCenter(vbox);
		Color backgroundColor = new Color(0.033, 0.04, 0.8, 0.09);
		borderPane5.setBackground(new Background(new BackgroundFill(backgroundColor, CornerRadii.EMPTY, null)));
		return borderPane5;

	}

	private BorderPane getFileBorder() {
		bf = new BorderPane();
		GridPane gp = new GridPane();

		// FileChooser to select the flight file
		FileChooser flightFileChooser = new FileChooser();
		flightFileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

		Label flightFileLabel = new Label("Select Flight Data File:");
		Button flightFileButton = new Button("Choose File");
		Label flightFileStatusLabel = new Label("");

		gp.add(flightFileLabel, 0, 0);
		gp.add(flightFileButton, 1, 0);
		gp.add(flightFileStatusLabel, 2, 0);

		flightFileButton.setOnAction(e -> {
			File flightFile = flightFileChooser.showOpenDialog(null);
			if (flightFile != null) {
				flightFileStatusLabel.setText("File selected: " + flightFile.getName());
				loadFlightData(flightFile); // Call method to process flight file
			} else {
				flightFileStatusLabel.setText("File selection cancelled.");
			}
		});

		// FileChooser to select the passenger file
		FileChooser passengerFileChooser = new FileChooser();
		passengerFileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

		Label passengerFileLabel = new Label("Select Passenger Data File:");
		Button passengerFileButton = new Button("Choose File");
		Label passengerFileStatusLabel = new Label("");

		gp.add(passengerFileLabel, 0, 1);
		gp.add(passengerFileButton, 1, 1);
		gp.add(passengerFileStatusLabel, 2, 1);

		passengerFileButton.setOnAction(e -> {
			File passengerFile = passengerFileChooser.showOpenDialog(null);
			if (passengerFile != null) {
				passengerFileStatusLabel.setText("File selected: " + passengerFile.getName());
				loadPassengerData(passengerFile); // Call method to process passenger file
			} else {
				passengerFileStatusLabel.setText("File selection cancelled.");
			}
		});

		gp.setAlignment(Pos.CENTER);
		VBox vbox = new VBox(30, gp);
		vbox.setAlignment(Pos.TOP_CENTER);
		vbox.setPadding(new Insets(100, 0, 0, 0));
		bf.setCenter(vbox);
		Color backgroundColor = new Color(0.033, 0.04, 0.8, 0.09);
		bf.setBackground(new Background(new BackgroundFill(backgroundColor, CornerRadii.EMPTY, null)));
		return bf;
	}

	private void loadFlightData(File file) {
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] data = line.split(","); // Keep the delimiter as comma
				if (data.length >= 3) { // Ensure there are enough fields
					try {
						String flightID = data[0].trim();
						String destination = data[1].trim();
						boolean status = Boolean.parseBoolean(data[2].trim());

						// Process flight data (e.g., store it in a flight list or a map)
						Flight.addFlight(flightID, destination, status);
					} catch (NumberFormatException e) {
						System.err.println("Error parsing number or boolean for flight data: " + line);
					}
				} else {
					System.err.println("Invalid data format in flight file, line skipped: " + line);
				}
			}
		} catch (IOException e) {
			System.err.println("Error reading flight data file: " + e.getMessage());
		}
	}

	private void loadPassengerData(File file) {
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] data = line.split(","); // Keep the delimiter as comma
				if (data.length >= 4) { // Ensure there are enough fields
					try {
						int passengerID = Integer.parseInt(data[0].trim());
						String name = data[1].trim();
						String flightID = data[2].trim();
						boolean status = Boolean.parseBoolean(data[3].trim());

						// Process passenger data (e.g., store it in a passenger list or map)
						NodeDouble flight = Flight.searchFlight(flightID);

						if (flight != null) {
							Passenger p = new Passenger(name, passengerID, flightID, status);

							if (status) {
								flight.vipQueue.enqueue(p);
								all.add(p);
							} else {
								flight.regularQueue.enqueue(p);
								all.add(p);
							}

						}
					} catch (NumberFormatException e) {
						System.err.println("Error parsing number for passenger data (ID): " + line);
					}
				}
			}
		} catch (IOException e) {
			System.err.println("Error reading passenger data file: " + e.getMessage());
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
