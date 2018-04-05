package Controller;

import java.awt.event.KeyAdapter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.sun.javafx.scene.EnteredExitedHandler;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

public class LoginController implements Initializable {
	@FXML
	private TextField txtId;
	@FXML
	private PasswordField txtPassword;
	@FXML
	private Button btnCancel;
	@FXML
	private Button btnLogin;

	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnLogin.setOnAction(event -> handlerBtnLoginAction(event));
		btnCancel.setOnAction(event -> handlerBtnCancelAction(event));
	
	}

	public void handlerBtnCancelAction(ActionEvent event) {
		Platform.exit();
	}

	public void handlerBtnLoginAction(ActionEvent event) {

		Alert alert;
		if (txtId.getText().equals("") || txtPassword.getText().equals("")) {
			
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("Log_In Failed");
			alert.setHeaderText("���̵�� ��й�ȣ ���Է�");
			alert.setContentText("���̵�� ��й�ȣ �� �Էµ��� ���� �׸��� �ֽ��ϴ�.");
			alert.setResizable(false);
			alert.showAndWait();
			return;

		}

		if (txtId.getText().equals("1") && txtPassword.getText().equals("1")) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/main.fxml"));
				Parent mainView = (Parent) loader.load();
				Scene scene = new Scene(mainView);
				Stage mainMtage = new Stage();
				mainMtage.setTitle("Main Window");
				mainMtage.setScene(scene);
				Stage oldStage = (Stage) btnLogin.getScene().getWindow();
				oldStage.close();
				mainMtage.show();

			} catch (IOException e) {
				System.out.println("����" + e);
			} // end of try&catch
		} else {
			// ���̵�� �н����� Ȯ���϶�� â
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("Log_In Failed");
			alert.setHeaderText("���̵�� ��й�ȣ ����ġ");
			alert.setContentText("���̵�� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
			alert.showAndWait();
		} // end of if&else
	}// end of �α��ι�ư�׼�
	
}
