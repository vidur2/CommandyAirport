/*
 * Dr. Raymond Schenk  - Edited by Nikhil Bhagavati
 * CTAERN CLASS
 * Java Full Stack Program
 * MS-SQL Database
 * 15 October 2022
 */

package bhagavati.two;

//imports
import java.util.Optional;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

//Controller for the View
public class WidgetSceneController {

	// Fields of the class
	public Stage myStage;
	private int currentRecord;

//	Widget Fields
	@FXML
	private Label lblId;
	@FXML
	private TextField txtAirline;
	@FXML
	private TextField txtFlightNumber;
	@FXML
	private TextField txtSeats;
	@FXML
	private TextField txtCost;
	@FXML
	private TextField txtPilot;
	@FXML
	private TextField txtCopilot;
	@FXML
	private Button btnInsert;
	@FXML
	private Button btnEdit;
	@FXML
	private Button btnDelete;
	@FXML
	private Button btnConnect;
	@FXML
	private Button btnFirst;
	@FXML
	private Button btnLast;
	@FXML
	private Button btnFastBack;
	@FXML
	private Button btnFastForward;
	@FXML
	private Button btnBack;
	@FXML
	private Button btnForward;
	@FXML
	private Label lblMessages;
	@FXML
	private Button btnCancelAdd;
	@FXML
	private BorderPane myBorderPane;
	@FXML
	private MenuItem menuConnect;
	@FXML
	private MenuItem menuDisconnect;
	@FXML
	private MenuItem menuExit;

	// Database Object - THis is where this controller becomes a container class.
	WidgetRecordDb widgets = new WidgetRecordDb();

	// Event Listeners on Navigation Buttons
	@FXML
	public void btnFirstClicked(ActionEvent event) {
		jump(JumpCode.FIRST);
	}

	@FXML
	public void btnLastClicked(ActionEvent event) {
		jump(JumpCode.LAST);
	}

	@FXML
	public void btnFastBackClicked(ActionEvent event) {
		jump(JumpCode.FASTBACK);
	}

	@FXML
	public void btnFastForwardClicked(ActionEvent event) {
		jump(JumpCode.FASTFORWARD);
	}

	@FXML
	public void btnBackClicked(ActionEvent event) {
		jump(JumpCode.BACK);
	}

	@FXML
	public void btnForwardClicked(ActionEvent event) {
		jump(JumpCode.FORWARD);
	}

	// **************CRUD EVENT HANDLING
	// Data Manipulation Methods
	// Note: Read is handled automatically by the navigation and auto-display
	// methods
	// So we are concerned here with CUD (Create, Update, and DELETE

	@FXML
	public void btnNewClicked(ActionEvent event) {

		if (btnInsert.getText().equals("Insert")) {
			btnCancelAdd.setVisible(true);
			lblId.setText("---");

			btnDelete.setVisible(false);
			btnEdit.setVisible(false);

			disableControls();
			btnInsert.setDisable(false);

			// Clearing text fields
			txtAirline.setText("");
			txtFlightNumber.setText("");
			txtCost.setText("");
			txtSeats.setText("N");

			// Call update Message to set the help text message label field
			updateMessage("Enter new Widget, then press Commit Add.");
			btnInsert.setText("COMMIT");
		} else {
			btnCancelAdd.setVisible(false);
			updateMessage("Attempting Add...");
			btnInsert.setText("Insert");
			WidgetRecord w = pullRecord();
			updateMessage(widgets.addRecord(w));

			// Resetting disabled buttons
			enableControls();

			btnConnect.setDisable(false);

			btnDelete.setVisible(true);
			btnEdit.setVisible(true);

		}
	}

	@FXML
	public void btnCancelAddClicked(ActionEvent event) {
		btnConnect.setDisable(false);
		lblId.setDisable(false);
		btnInsert.setText("Insert");
		btnEdit.setDisable(false);
		btnDelete.setDisable(false);
		updateMessage("Add Widget Cancelled.");
		displayRecord(currentRecord);
		btnCancelAdd.setVisible(false);
		enableControls();

	}

	@FXML
	public void btnDeleteClicked(ActionEvent event) {

		btnConnect.setDisable(true);

		updateMessage("Attempting Delete.");
		WidgetRecord w = pullRecord();

		boolean result = verifyIntent(w);

		if (result) {
			updateMessage(widgets.deleteRecord(w));
			btnConnect.setDisable(false);
			jump(JumpCode.BACK);
		} else {
			updateMessage("Deletion Cancelled.");
		}
	}

	@FXML
	public void btnEditClicked(ActionEvent event) {
		WidgetRecord w = new WidgetRecord();
		updateMessage("Attempting Edit.");
		w = pullRecord();
		updateMessage(widgets.editRecord(w));
	}

	@FXML
	public void btnConnectClicked(ActionEvent event) {

		if (btnConnect.getText().equals("Connect")) {
			currentRecord = 0;
			btnCancelAdd.setVisible(false);
			boolean success = widgets.connectDb();
			if (success) {
				enableControls();
				btnConnect.setText("Disconnect");
				updateMessage("Successfully Connected.");
				String loaded = widgets.loadResults();
				appendMessage(loaded);
				// Call display method for current record (initially set to first record in the
				// ArrayList)
				displayRecord(currentRecord);
			} else {
				updateMessage("Error Connecting to Widget Database.");
			}
		} else {
			boolean success = widgets.disconnectDb();
			if (success) {
				btnConnect.setText("Connect");
				updateMessage("Successfully disonnected.");
				disableControls();
			} else {
				updateMessage("Error Disconnecting from Widget Database.");
			}
		}

	}

	// Initialization to set startup safety measures
	public void initialize() {
		btnCancelAdd.setVisible(false);
		disableControls();
	}

	// Delete safety lock method
	// Utility Methods
	private boolean verifyIntent(WidgetRecord w) {
		Stage stage = (Stage) myBorderPane.getScene().getWindow();
		Alert.AlertType type = Alert.AlertType.CONFIRMATION;
		Alert alert = new Alert(type, "");
		alert.initModality((Modality.APPLICATION_MODAL));
		alert.initOwner(stage);
		alert.getDialogPane().setHeaderText("Conform Record Deletion!");
		alert.getDialogPane().setContentText("Record to Delete: " + w.getName() + ".");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			return true;
		} else {
			return false;
		}
	}
	// Methods for pushing and pulling record to and from the VIEW (UI)

	// Methods to display a given record and send feedback messages to user
	private void displayRecord(int recordNum) {
		if (widgets.getResults().size() > 0) {
			lblId.setText(String.valueOf(widgets.getResults().get(recordNum).getId()));
			txtAirline.setText(widgets.getResults().get(recordNum).getName());
			txtFlightNumber.setText(String.valueOf(widgets.getResults().get(recordNum).getSize()));
			txtCost.setText(String.valueOf(widgets.getResults().get(recordNum).getCost()));
			if (widgets.getResults().get(recordNum).isInStock()) {
				txtSeats.setText("Y");
			} else {
				txtSeats.setText("N");
			}

		} else {
			updateMessage("Database Has No Records");
		}

	}

	private WidgetRecord pullRecord() {

		WidgetRecord w = new WidgetRecord();

		if (lblId.getText().equals("---")) {
			w.setId(-9);
		} else {
			w.setId(Integer.parseInt(lblId.getText()));
		}
		w.setName(txtAirline.getText());
		w.setSize(txtFlightNumber.getText().toCharArray()[0]);
		w.setCost(Double.parseDouble(txtCost.getText()));
		w.setInStock(txtSeats.getText().toCharArray()[0] == 'Y');

		return w;
	}

	// Utility methods
	private void updateMessage(String msg) {
		lblMessages.setText(msg);

	}

	public void appendMessage(String msg) {
		String oldMessage = lblMessages.getText();
		String newMessage = oldMessage + msg;
		lblMessages.setText(newMessage);
	}

	// Navigation and bounds checking methods
	// Navigation Methods
	private void jump(JumpCode jumpCode) {

		switch (jumpCode) {

		case FIRST:
			currentRecord = 0;
			displayRecord(currentRecord);
			updateMessage(String.valueOf(currentRecord));
			break;
		case LAST:
			currentRecord = widgets.getResults().size() - 1;
			checkIndex(currentRecord);
			displayRecord(currentRecord);
			updateMessage(String.valueOf(currentRecord));
			break;
		case FASTBACK:
			currentRecord = currentRecord - 5;
			checkIndex(currentRecord);
			displayRecord(currentRecord);
			updateMessage(String.valueOf(currentRecord));
			break;
		case FASTFORWARD:
			currentRecord = currentRecord + 5;
			checkIndex(currentRecord);
			displayRecord(currentRecord);
			updateMessage(String.valueOf(currentRecord));
			break;
		case BACK:
			currentRecord--;
			checkIndex(currentRecord);
			displayRecord(currentRecord);
			updateMessage(String.valueOf(currentRecord));
			break;
		case FORWARD:
			currentRecord++;
			checkIndex(currentRecord);
			displayRecord(currentRecord);
			updateMessage(String.valueOf(currentRecord));
			break;
		}
	}

	private void jump(int recordNumber) {
		for (int i = 0, l = widgets.getResults().size(); i < l; i++) {
			if (widgets.getResults().get(i).getId() == recordNumber) {
				currentRecord = i;
				displayRecord(currentRecord);
				updateMessage(String.valueOf(currentRecord));
			}
		}
	}

	private void checkIndex(int indexNow) {
		if (indexNow < 0) {
			currentRecord = 0;
		}
		int maxRec = widgets.getResults().size() - 1;
		if (indexNow > maxRec) {
			currentRecord = maxRec;
		}

	}

	// Menu event methods
	// Menu Methods
	@FXML
	public void menuConnectClicked(ActionEvent event) {
		btnConnectClicked(event);
//		currentRecord=0;
//		btnCancelAdd.setVisible(false);
//		boolean success = widgets.connectDb();
//		if (success) {
//			enableControls();
//			updateMessage("Successfully Connected.");
//			String loaded = widgets.loadResults();
//			appendMessage(loaded);
//			//Call display method for current record (initially set to first record in the ArrayList)
//			displayRecord(currentRecord);
//		} else {
//			updateMessage("Error Connecting to Widget Database.");
//		}
	}

	@FXML
	public void menuDisconnectClicked(ActionEvent event) {
		btnConnectClicked(event);
//		boolean success = widgets.disconnectDb();
//		if (success) {
//			updateMessage("Successfully disonnected.");
//			disableControls();
//		} else {
//			updateMessage("Error Disconnecting from Widget Database.");
//		}
	}

	@FXML
	public void menuExitClicked(ActionEvent event) {
		Platform.exit();
		System.exit(0);
	}

	@FXML
	public void menuInsertClicked(ActionEvent event) {
		btnNewClicked(event);
	}

	@FXML
	public void menuDeleteClicked(ActionEvent event) {
		btnDeleteClicked(event);
	}

	@FXML
	public void menuEditClicked(ActionEvent event) {
		btnEditClicked(event);
	}

	public void menuAboutClicked(ActionEvent event) {
		Stage stage = (Stage) myBorderPane.getScene().getWindow();
		Alert.AlertType type = Alert.AlertType.INFORMATION;
		Alert alert = new Alert(type, "");
		alert.initModality((Modality.APPLICATION_MODAL));
		alert.initOwner(stage);
		alert.getDialogPane().setHeaderText("Widget Tracking Database 1.0");
		alert.getDialogPane().setContentText("A Scooby Educational Program,\nCopyright 2022\nHawkeye Consulting, LLC");
		alert.showAndWait();

	}

	// Method to disable controls during critical operations or when not connected
	// to database
	private void disableControls() {
		btnInsert.setDisable(true);
		btnDelete.setDisable(true);
		btnEdit.setDisable(true);
		btnFirst.setDisable(true);
		btnLast.setDisable(true);
		btnFastBack.setDisable(true);
		btnFastForward.setDisable(true);
		btnBack.setDisable(true);
		btnForward.setDisable(true);

	}

	private void enableControls() {
		btnInsert.setDisable(false);
		btnDelete.setDisable(false);
		btnEdit.setDisable(false);
		btnFirst.setDisable(false);
		btnLast.setDisable(false);
		btnFastBack.setDisable(false);
		btnFastForward.setDisable(false);
		btnBack.setDisable(false);
		btnForward.setDisable(false);

	}

}
