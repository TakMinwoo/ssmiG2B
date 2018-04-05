package Controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Model.BidAnalysisVO;

public class BidExcel {
	public boolean xlsxWriter(List<BidAnalysisVO> list, String saveDir) {
		// ��ũ�� ����
		XSSFWorkbook workbook = new XSSFWorkbook();

		// ��ũ��Ʈ ����
		XSSFSheet sheet = workbook.createSheet();

		// �� ����
		XSSFRow row = sheet.createRow(0);

		// �� ����
		XSSFCell cell;

		// ��� ���� ����
		cell = row.createCell(0);
		cell.setCellValue("�����ȣ");
		cell = row.createCell(1);
		cell.setCellValue("����");
		cell = row.createCell(2);
		cell.setCellValue("�з�");
		cell = row.createCell(3);
		cell.setCellValue("�����");
		cell = row.createCell(4);
		cell.setCellValue("������");
		cell = row.createCell(5);
		cell.setCellValue("������");
		cell = row.createCell(6);
		cell.setCellValue("�����");
		cell = row.createCell(7);
		cell.setCellValue("����������");
		cell = row.createCell(8);
		cell.setCellValue("����������");
		cell = row.createCell(9);
		cell.setCellValue("��ǰ�ĺ���ȣ");
		cell = row.createCell(10);
		cell.setCellValue("��������");
		cell = row.createCell(11);
		cell.setCellValue("���ʱݾ�");
		cell = row.createCell(12);
		cell.setCellValue("�����ݾ�");
		cell = row.createCell(13);
		cell.setCellValue("�������");

		// ����Ʈ�� size��ŭ row�� ����
		BidAnalysisVO bVo;
		for (int rowIdx = 0; rowIdx < list.size(); rowIdx++) {
			bVo = list.get(rowIdx);

			// �� ����
			row = sheet.createRow(rowIdx + 1);
			cell = row.createCell(0);
			cell.setCellValue(bVo.getBidNum());
			cell = row.createCell(1);
			cell.setCellValue(bVo.getBidTrial());
			cell = row.createCell(2);
			cell.setCellValue(bVo.getBidClass());
			cell = row.createCell(3);
			cell.setCellValue(bVo.getBidName());
			cell = row.createCell(4);
			cell.setCellValue(bVo.getBidAgency());
			cell = row.createCell(5);
			cell.setCellValue(bVo.getBidDemand());
			cell = row.createCell(6);
			cell.setCellValue(bVo.getBidMethod());
			cell = row.createCell(7);
			cell.setCellValue(bVo.getBidStart());
			cell = row.createCell(8);
			cell.setCellValue(bVo.getBidEnd());
			cell = row.createCell(9);
			cell.setCellValue(bVo.getItemDetailNum());
			cell = row.createCell(10);
			cell.setCellValue(bVo.getPriceEstimate());
			cell = row.createCell(11);
			cell.setCellValue(bVo.getBaseAmount());
			cell = row.createCell(12);
			cell.setCellValue(bVo.getBidAmount());
			cell = row.createCell(13);
			cell.setCellValue(bVo.getWinResult());
		}

		// �Էµ� ���� ���Ϸ� ����
		String strReport = "Bid_" + System.currentTimeMillis() + ".xlsx";
		File file = new File(saveDir + "\\" + strReport);
		FileOutputStream fos = null;

		boolean saveSuccess;
		saveSuccess = false;
		try {
			fos = new FileOutputStream(file);
			if (fos != null) {
				workbook.write(fos);
				saveSuccess = true;
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("xlsxWriter FileNotFoundException");

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("xlsxWriter IOException");
		} finally {
			try {
				if (workbook != null) {
					workbook.close();
				}
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("xlsxWriter IOException finally IOException");
			}
		}
		return saveSuccess;
	}

}
