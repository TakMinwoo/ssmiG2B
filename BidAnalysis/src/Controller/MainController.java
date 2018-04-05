package Controller;

import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.net.URL;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.awt.*;
import java.awt.event.*;

import javax.xml.ws.Response;

import Model.BidAnalysisVO;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;

import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainController implements Initializable {

	@FXML
	private TableView<BidAnalysisVO> tableView = new TableView<>();
	@FXML
	private Button btnPlatformExit;
	@FXML
	private Button btnExcelExport;
	@FXML
	private TextField txtSaveFolderSearch;
	@FXML
	private Button btnSaveFolderSearch;

	@FXML
	private Button btnBidInsert;
	@FXML
	private Button btnBidAmountInsert;
	@FXML
	private Button btnBidResultInsert;

	@FXML
	private TextField txtBidNum;
	@FXML
	private TextField txtBidTrial;
	@FXML
	private CheckBox chbxBidClass;
	@FXML
	private TextField txtBidClass;
	@FXML
	private TextField txtBidName;
	@FXML
	private TextField txtBidAgency;
	@FXML
	private TextField txtBidDemand;
	@FXML
	private TextField txtItemDetailNum;

	@FXML
	private RadioButton rbConGeneral;
	@FXML
	private RadioButton rbConLimit;
	@FXML
	private RadioButton rbConNominate;
	@FXML
	private RadioButton rbConPrivate;
	@FXML
	private TextField txtConNominateCompany;
	@FXML
	private Button btnConNominateCompanySave;
	@FXML
	private TextArea txtConLimit;
	@FXML
	private Button btnConLimitSave;

	@FXML
	private TextField txtBidStart;

	@FXML
	private TextField txtBidEnd;

	@FXML
	private TextField txtPriceEstimate;
	@FXML
	private TextField txtBaseAmount;
	@FXML
	private TextField txtBidAmount;
	@FXML
	private TextField txtWinResult;

	@FXML
	private Button btnItemDetailNumGuide;
	@FXML
	private Button btnBidInsertSave;
	@FXML
	private Button btnBidInsertCancel;

	@FXML
	private TextField txtBidNumSearch;
	@FXML
	private Button btnBidNumSearch;
	@FXML
	private TextField txtBidNameSearch;
	@FXML
	private Button btnBidNameSearch;
	@FXML
	private TextField txtDetailItemNumSearch;

	@FXML
	private Button btnBidModifyCancel;
	@FXML
	private Button btnBidModifySave;

	private Stage primaryStage;

	ObservableList<BidAnalysisVO> data = FXCollections.observableArrayList();
	ObservableList<BidAnalysisVO> selectBid = null; // ���̺��� ������ ���� ����
	int selectedIndex; // ���̺��� ������ �л� ���� �ε��� ����
	String no; // ������ ���̺��� ������ �л��� ��ȣ ����

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		tableView.setEditable(false);
		btnExcelExport.setDisable(true);

		// ���̺� �� �÷��̸� ����
		TableColumn bidNo = new TableColumn("�����ȣ");
		bidNo.setMaxWidth(120);
		bidNo.setStyle("-fx-allignment: CENTER");
		bidNo.setCellValueFactory(new PropertyValueFactory<>("bidNum"));

		TableColumn bidTrial = new TableColumn("����");
		bidTrial.setMaxWidth(30);
		bidTrial.setStyle("-fx-allignment: CENTER");
		bidTrial.setCellValueFactory(new PropertyValueFactory<>("bidTrial"));

		TableColumn bidClass = new TableColumn("�з�");
		bidClass.setMaxWidth(30);
		bidClass.setStyle("-fx-allignment: CENTER");
		bidClass.setCellValueFactory(new PropertyValueFactory<>("bidClass"));

		TableColumn bidName = new TableColumn("�����");
		bidName.setMinWidth(250); // �ּ� ���� 250
		bidName.setMaxWidth(500); // �ִ� ���� 500
		bidName.setStyle("-fx-allignment: CENTER");
		bidName.setCellValueFactory(new PropertyValueFactory<>("bidName"));

		TableColumn bidAgency = new TableColumn("������");
		bidAgency.setMaxWidth(100);
		bidAgency.setStyle("-fx-allignment: CENTER");
		bidAgency.setCellValueFactory(new PropertyValueFactory<>("bidAgency"));

		TableColumn bidDemand = new TableColumn("������");
		bidDemand.setMaxWidth(100);
		bidDemand.setStyle("-fx-allignment: CENTER");
		bidDemand.setCellValueFactory(new PropertyValueFactory<>("bidDemand"));

		TableColumn bidMethod = new TableColumn("�����");
		bidMethod.setMaxWidth(80);
		bidMethod.setStyle("-fx-allignment: CENTER");
		bidMethod.setCellValueFactory(new PropertyValueFactory<>("bidMethod"));

		TableColumn bidStart = new TableColumn("���������Ͻ�");
		bidStart.setMaxWidth(200);
		bidStart.setStyle("-fx-allignment: CENTER");
		bidStart.setCellValueFactory(new PropertyValueFactory<>("bidStart"));

		TableColumn bidEnd = new TableColumn("���������Ͻ�");
		bidEnd.setMaxWidth(200);
		bidEnd.setStyle("-fx-allignment: CENTER");
		bidEnd.setCellValueFactory(new PropertyValueFactory<>("bidEnd"));

		TableColumn itemDetailNum = new TableColumn("��ǰ�ĺ���ȣ");
		itemDetailNum.setMaxWidth(220);
		itemDetailNum.setStyle("-fx-allignment: CENTER");
		itemDetailNum.setCellValueFactory(new PropertyValueFactory<>("itemDetailNum"));

		TableColumn priceEstimate = new TableColumn("��������");
		priceEstimate.setMaxWidth(200);
		priceEstimate.setStyle("-fx-allignment: CENTER");
		priceEstimate.setCellValueFactory(new PropertyValueFactory<>("priceEstimate"));

		TableColumn baseAmount = new TableColumn("���ʰ���");
		baseAmount.setMaxWidth(200);
		baseAmount.setStyle("-fx-allignment: CENTER");
		baseAmount.setCellValueFactory(new PropertyValueFactory<>("baseAmount"));

		TableColumn bidAmount = new TableColumn("��������");
		bidAmount.setMaxWidth(200);
		bidAmount.setStyle("-fx-allignment: CENTER");
		bidAmount.setCellValueFactory(new PropertyValueFactory<>("bidAmount"));

		TableColumn winResult = new TableColumn("�������");
		winResult.setMaxWidth(100);
		winResult.setStyle("-fx-allignment: CENTER");
		winResult.setCellValueFactory(new PropertyValueFactory<>("winResult"));

		// ���� ��� ��ư Ŭ���� �̺�Ʈ
		btnBidInsert.setOnAction(event -> handlerBtnBidInsertAction(event));

		// ���̺� �信 �÷��� ������
		tableView.getColumns().addAll(bidNo, bidTrial, bidClass, bidName, bidAgency, bidDemand, bidMethod, bidStart,
				bidEnd, itemDetailNum, priceEstimate, baseAmount, bidAmount, winResult);

		// ���� ��ü ����
		totalList();
		tableView.setItems(data);

		// ���̺�信�� ���콺Ŭ�������� �̺�Ʈ
		tableView.setOnMouseClicked(event -> handlerBidDetailAction(event));
		btnBidNumSearch.setOnAction(event -> hanlderBtnBidNumSearchAction(event));
		btnSaveFolderSearch.setOnAction(event -> handlerBtnSaveFoloderDirAction(event)); // ���� ���� ����
		btnExcelExport.setOnAction(event -> handleExcelExport(event)); // ���� ���� ����
	}

	// �ű� ���� ��� ���̾�α�â ����
	public void handlerBtnBidInsertAction(ActionEvent event) {

		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/View/formbidinsert.fxml")); // formbidinsert â ����

			Stage bidInsertDialog = new Stage(StageStyle.UTILITY);
			bidInsertDialog.initModality(Modality.WINDOW_MODAL);
			bidInsertDialog.setTitle("�ű� ���� ���");

			Parent parentInsert = (Parent) loader.load();

			BidAnalysisVO BidInsert = tableView.getSelectionModel().getSelectedItem();
			selectedIndex = tableView.getSelectionModel().getSelectedIndex();

			TextField insertBidNum = (TextField) parentInsert.lookup("#txtBidNum");
			TextField insertBidTrial = (TextField) parentInsert.lookup("#txtBidTrial");
			TextField insertBidClass = (TextField) parentInsert.lookup("#txtBidClass");
			TextField insertBidName = (TextField) parentInsert.lookup("#txtBidName");
			TextField insertBidAgency = (TextField) parentInsert.lookup("#txtBidAgency");
			TextField insertBidDemand = (TextField) parentInsert.lookup("#txtBidDemand");
			TextField insertBidMethod = (TextField) parentInsert.lookup("#txtBidMethod");
			TextField insertBidStart = (TextField) parentInsert.lookup("#txtBidStart");
			TextField insertBidEnd = (TextField) parentInsert.lookup("#txtBidEnd");
			TextField insertItemDetailNum = (TextField) parentInsert.lookup("#txtItemDetailNum");
			TextField insertPriceEstimate;
			insertPriceEstimate = (TextField) parentInsert.lookup("#txtPriceEstimate");
			TextField insertBaseAmount = (TextField) parentInsert.lookup("#txtBaseAmount");
			TextField insertBidAmount = (TextField) parentInsert.lookup("#txtBidAmount");
			TextField insertWinResult = (TextField) parentInsert.lookup("#txtWinResult");

			Button btnBidInsertSave = (Button) parentInsert.lookup("#btnBidInsertSave");
			Button btnBidInsertCancel = (Button) parentInsert.lookup("#btnBidInsertCancel");

			/*
			 * ����Ǿ� �ִ� ���� �ҷ����� ��(������ ��) editBidNum.setText(BidInsert.getBidNum());
			 * editBidTrial.setText(BidInsert.getBidTrial());
			 * editBidClass.setText(BidInsert.getBidClass());
			 * editBidName.setText(BidInsert.getBidName());
			 * editBidAgency.setText(BidInsert.getBidAgency());
			 * editBidDemand.setText(BidInsert.getBidDemand());
			 * editBidMethod.setText(BidInsert.getBidMethod());
			 * editBidStart.setText(BidInsert.getBidStart());
			 * editBidEnd.setText(BidInsert.getBidEnd());
			 * editPriceEstimate.setText(BidInsert.getPriceEstimate()+"");
			 */
			btnBidInsertSave.setOnAction(e -> {

				BidAnalysisVO bidAnlysisVo = null;
				BidAnalysisDAO bidAnalysisDao = null;
				data.removeAll(data);

				try {
					bidAnlysisVo = new BidAnalysisVO(insertBidNum.getText(), insertBidTrial.getText(),
							insertBidClass.getText(), insertBidName.getText(), insertBidAgency.getText(),
							insertBidDemand.getText(), insertBidMethod.getText(), insertBidStart.getText(),
							insertBidEnd.getText(), insertItemDetailNum.getText(),
							Integer.parseInt(insertPriceEstimate.getText().trim()),
							Integer.parseInt(insertBaseAmount.getText().trim()),
							Integer.parseInt(insertBidAmount.getText().trim()), insertWinResult.getText());

					bidInsertDialog.close();

					bidAnalysisDao = new BidAnalysisDAO();
					bidAnalysisDao.getNewBidAnnouncement(bidAnlysisVo);

					// data.add(bidAnlysisVo); // �����Ϳ� �־��־�� ��

					totalList();

				} catch (Exception btnBidInsertSaveE) {
					btnBidInsertSaveE.printStackTrace();
					System.out.println("btnBidInsertSaveE error");
				}

			});

			btnBidInsertCancel.setOnAction(e -> bidInsertDialog.close());

			// ���â�� ���� Scene ����
			Scene scene = new Scene(parentInsert);
			bidInsertDialog.setScene(scene);
			bidInsertDialog.setResizable(true);
			bidInsertDialog.show();

		} catch (IOException e) {// ���Ͽ� ���� �б� �Ǵ� ���� ���� ���� I/O ������ �߻��� ��
			System.out.println(e.toString() + " bidInsert����");
		}
	}

	public void totalList() {
		Object[][] totalData;

		BidAnalysisDAO badao = new BidAnalysisDAO();
		BidAnalysisVO bavo = new BidAnalysisVO();
		ArrayList<String> title;
		ArrayList<BidAnalysisVO> list;

		title = badao.getColumnName(); // DAO�� �ִ� DB���� ���� ���̺� �÷��� ����
		int columnCount = title.size();

		list = badao.getBidTotal(); // DAO�� �ִ� DB���� ���� ��ü ����Ʈ
		int rowCount = list.size();
		totalData = new Object[rowCount][columnCount];

		for (int index = 0; index < rowCount; index++) {
			bavo = list.get(index);
			data.add(bavo);
		}

	}

	// ���� ��â
	public void handlerBidDetailAction(MouseEvent event) {

		// ���콺 ������ ��ư ������ ����/���� context menu â �߰� ��
		if (event.getButton() == MouseButton.SECONDARY) {
			selectBid = tableView.getSelectionModel().getSelectedItems();
			no = selectBid.get(0).getBidNum();

			ContextMenu cm = new ContextMenu(); // context â ����
			javafx.scene.control.MenuItem mi1 = new javafx.scene.control.MenuItem("����"); // ���� �޴�
			cm.getItems().add(mi1); // context�޴��� �����̶�� �� �Է�
			mi1.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					handlerModifyAction(event);
				}
			});

			javafx.scene.control.MenuItem mi2 = new javafx.scene.control.MenuItem("����");// ���� �޴�
			cm.getItems().add(mi2); // context�޴��� ������� �� �Է�
			mi2.setOnAction(new EventHandler<ActionEvent>() { // ��������

				@Override
				public void handle(ActionEvent event) {
					handlerDeleteAction(event);
				}
			});

			tableView.setContextMenu(cm);

		}

		// if (event.getClickCount() != 2) {
		// try {
		// selectBid = tableView.getSelectionModel().getSelectedItems();
		// no =selectBid.get(0).getBidNum();
		//
		// } catch (Exception e) {
		// Alert alert = new Alert(AlertType.WARNING);
		// alert.setTitle("������");
		// alert.setHeaderText("������ ���� �����ϴ�.");
		// alert.setContentText("���� �߰��� �Ŀ� �����ϼ���.");
		// alert.showAndWait();
		// }
		// return;
		// }
		//
		// try {
		// Stage dialog = new Stage(StageStyle.UTILITY);
		// dialog.initModality(Modality.WINDOW_MODAL);
		//
		// Parent parent = FXMLLoader.load(getClass().getResource("/View/blank.fxml"));
		//
		// } catch (IOException e) {
		// System.out.println("handlerBidDetailAction IOException");
		// }

	}

	// ���� ����
	public void handlerDeleteAction(ActionEvent event) {
		BidAnalysisDAO bDao = null;
		bDao = new BidAnalysisDAO();

		try {
			bDao.getBidDelete(no);
			data.removeAll(data);

			// ���� ��ü ����
			totalList();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("handlerBtnDeleteAction Exception");
		}

	}

	public void handlerModifyAction(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/View/formbidmodify.fxml"));

			Stage dialog = new Stage(StageStyle.UTILITY);
			dialog.initModality(Modality.WINDOW_MODAL);

			dialog.setTitle("����");

			Parent parentModify = (Parent) loader.load();
			// ���â�� ���� Scene ����
			Scene scene = new Scene(parentModify);
			dialog.setScene(scene);
			dialog.setResizable(true);
			dialog.show();

			BidAnalysisVO bidModify = tableView.getSelectionModel().getSelectedItem();
			selectedIndex = tableView.getSelectionModel().getSelectedIndex();

			TextField modifyBidNum = (TextField) parentModify.lookup("#txtBidNum");
			TextField modifyBidTrial = (TextField) parentModify.lookup("#txtBidTrial");
			TextField modifyBidClass = (TextField) parentModify.lookup("#txtBidClass");
			TextField modifyBidName = (TextField) parentModify.lookup("#txtBidName");
			TextField modifyBidAgency = (TextField) parentModify.lookup("#txtBidAgency");
			TextField modifyBidDemand = (TextField) parentModify.lookup("#txtBidDemand");
			TextField modifyBidMethod = (TextField) parentModify.lookup("#txtBidMethod");
			TextField modifyBidStart = (TextField) parentModify.lookup("#txtBidStart");
			TextField modifyBidEnd = (TextField) parentModify.lookup("#txtBidEnd");
			TextField modifyItemDetailNum = (TextField) parentModify.lookup("#txtItemDetailNum");
			TextField modifyPriceEstimate;
			modifyPriceEstimate = (TextField) parentModify.lookup("#txtPriceEstimate");
			TextField modifyBaseAmount = (TextField) parentModify.lookup("#txtBaseAmount");
			TextField modifyBidAmount = (TextField) parentModify.lookup("#txtBidAmount");
			TextField modifyWinResult = (TextField) parentModify.lookup("#txtWinResult");

			modifyBidNum.setDisable(true);

			modifyBidNum.setText(bidModify.getBidNum());
			modifyBidTrial.setText(bidModify.getBidTrial());
			modifyBidClass.setText(bidModify.getBidClass());
			modifyBidName.setText(bidModify.getBidName());
			modifyBidAgency.setText(bidModify.getBidAgency());
			modifyBidDemand.setText(bidModify.getBidDemand());
			modifyBidMethod.setText(bidModify.getBidMethod());
			modifyBidStart.setText(bidModify.getBidStart());
			modifyBidEnd.setText(bidModify.getBidEnd());
			modifyItemDetailNum.setText(bidModify.getItemDetailNum());
			modifyPriceEstimate.setText(bidModify.getPriceEstimate() + "");
			modifyBaseAmount.setText(bidModify.getBaseAmount() + "");
			modifyBidAmount.setText(bidModify.getBidAmount() + "");
			modifyWinResult.setText(bidModify.getWinResult());

			Button btnBidModifySave = (Button) parentModify.lookup("#btnBidModifySave");
			Button btnBidModifyCancel = (Button) parentModify.lookup("#btnBidModifyCancel");

			btnBidModifySave.setOnAction(e -> {
				BidAnalysisVO bVo = null;
				BidAnalysisDAO bDao = null;

				TextField txtBidNum = (TextField) parentModify.lookup("#txtBidNum");
				TextField txtBidTrial = (TextField) parentModify.lookup("#txtBidTrial");
				TextField txtBidClass = (TextField) parentModify.lookup("#txtBidClass");
				TextField txtBidName = (TextField) parentModify.lookup("#txtBidName");
				TextField txtBidAgency = (TextField) parentModify.lookup("#txtBidAgency");
				TextField txtBidDemand = (TextField) parentModify.lookup("#txtBidDemand");
				TextField txtBidMethod = (TextField) parentModify.lookup("#txtBidMethod");
				TextField txtBidStart = (TextField) parentModify.lookup("#txtBidStart");
				TextField txtBidEnd = (TextField) parentModify.lookup("#txtBidEnd");
				TextField txtItemDetailNum = (TextField) parentModify.lookup("#txtItemDetailNum");
				TextField txtPriceEstimate;
				txtPriceEstimate = (TextField) parentModify.lookup("#txtPriceEstimate");
				TextField txtBaseAmount = (TextField) parentModify.lookup("#txtBaseAmount");
				TextField txtBidAmount = (TextField) parentModify.lookup("#txtBidAmount");
				TextField txtWinResult = (TextField) parentModify.lookup("#txtWinResult");

				data.remove(selectedIndex);
				try {
					bVo = new BidAnalysisVO(txtBidNum.getText(), txtBidTrial.getText(), txtBidClass.getText(),
							txtBidName.getText(), txtBidAgency.getText(), txtBidDemand.getText(),
							txtBidMethod.getText(), txtBidStart.getText(), txtBidEnd.getText(),
							txtItemDetailNum.getText(), Integer.parseInt(txtPriceEstimate.getText().trim()),
							Integer.parseInt(txtBaseAmount.getText().trim()),
							Integer.parseInt(txtBidAmount.getText().trim()), txtWinResult.getText());

					dialog.close();

					bDao = new BidAnalysisDAO();
					bDao.getBidModifyUpdate(bVo, bVo.getBidNum());

					data.removeAll(data);
					totalList();

				} catch (Exception bmse) {

					bmse.printStackTrace();
					System.out.println("handlerModifyAction btnBidModifySave Exception");

				}

			});

			btnBidModifyCancel.setOnAction(e -> {
				dialog.close();
			});

		} catch (IOException e) {
			System.out.println(e.toString());
			System.out.println("handlerModifyAction IOException");
		}

	}

	public void hanlderBtnBidNumSearchAction(ActionEvent event) {
		BidAnalysisVO bVo = new BidAnalysisVO();
		BidAnalysisDAO bDao = null;

		Object[][] totalData = null;

		String searchBidNum = "";
		boolean searchResult = false;

		try {

			searchBidNum = txtBidNumSearch.getText().trim();
			bDao = new BidAnalysisDAO();
			bVo = bDao.getBidNumberCheck(searchBidNum);

			if (searchBidNum.equals("")) {
				searchResult = true;
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("���� �˻�");
				alert.setHeaderText("�Է��Ͻ� �����ȣ�� �����ϴ�.");
				alert.setContentText("");
				alert.showAndWait();

			}

			if (!searchBidNum.equals("") && (bVo != null)) {
				ArrayList<String> title;
				ArrayList<BidAnalysisVO> list;

				title = bDao.getColumnName();
				int columnCount = title.size();

				list = bDao.getBidTotal();
				int rowCount = list.size();

				totalData = new Object[rowCount][columnCount];

				if (bVo.getBidNum().equals(searchBidNum)) {

					txtBidNumSearch.clear();
					data.removeAll(data);
					for (int index = 0; index < rowCount; index++) {
						bVo = list.get(index);
						if (bVo.getBidNum().equals(searchBidNum)) {
							data.add(bVo);
							searchResult = true;
						}
					}
				}
			}

		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("���� �˻� ����");
			alert.setHeaderText("���� �˻��� ������ �߻��Ͽ����ϴ�.");
			alert.setContentText("");
		}

	}

	public void handleExit(ActionEvent e) {
		Platform.exit();
	}

	public void handleExcelExport(ActionEvent e) {
		BidAnalysisDAO bDao = new BidAnalysisDAO();
		boolean saveSuccess;

		ArrayList<BidAnalysisVO> list;
		list = bDao.getBidTotal();
		BidExcel excelWriter = new BidExcel();

		// xlsx ���� ����
		saveSuccess = excelWriter.xlsxWriter(list, txtSaveFolderSearch.getText());
		if (saveSuccess) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("���� ���� ����");
			alert.setHeaderText("���� ���� ���� ���� ����");
			alert.setContentText("���� ���� ������ �����Ͽ����ϴ�.");
			alert.showAndWait();
		}
		txtSaveFolderSearch.clear();
		btnExcelExport.setDisable(true);

	}

	public void handlerBtnSaveFoloderDirAction(ActionEvent event) {
		final DirectoryChooser directoryChooser = new DirectoryChooser();
		final File selectedDirectory = directoryChooser.showDialog(primaryStage);

		if (selectedDirectory != null) {
			txtSaveFolderSearch.setText(selectedDirectory.getAbsolutePath());
			btnExcelExport.setDisable(false);
		}
	}

}
