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
		// 워크북 생성
		XSSFWorkbook workbook = new XSSFWorkbook();

		// 워크시트 생성
		XSSFSheet sheet = workbook.createSheet();

		// 행 생성
		XSSFRow row = sheet.createRow(0);

		// 셀 생성
		XSSFCell cell;

		// 헤더 정보 구성
		cell = row.createCell(0);
		cell.setCellValue("공고번호");
		cell = row.createCell(1);
		cell.setCellValue("차수");
		cell = row.createCell(2);
		cell.setCellValue("분류");
		cell = row.createCell(3);
		cell.setCellValue("공고명");
		cell = row.createCell(4);
		cell.setCellValue("공고기관");
		cell = row.createCell(5);
		cell.setCellValue("수요기관");
		cell = row.createCell(6);
		cell.setCellValue("계약방법");
		cell = row.createCell(7);
		cell.setCellValue("입찰개시일");
		cell = row.createCell(8);
		cell.setCellValue("입찰마감일");
		cell = row.createCell(9);
		cell.setCellValue("물품식별번호");
		cell = row.createCell(10);
		cell.setCellValue("추정가격");
		cell = row.createCell(11);
		cell.setCellValue("기초금액");
		cell = row.createCell(12);
		cell.setCellValue("입찰금액");
		cell = row.createCell(13);
		cell.setCellValue("낙찰결과");

		// 리스트의 size만큼 row를 생성
		BidAnalysisVO bVo;
		for (int rowIdx = 0; rowIdx < list.size(); rowIdx++) {
			bVo = list.get(rowIdx);

			// 행 생성
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

		// 입력된 내용 파일로 쓰기
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
