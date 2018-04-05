package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.BidAnalysisVO;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class BidAnalysisDAO {

	// !(1) �ű� ���� ���� ���
	public BidAnalysisVO getNewBidAnnouncement(BidAnalysisVO bidAnlysisVo) throws Exception {
		// (1) ������ ó���� ���� sql��
		StringBuffer sql = new StringBuffer();
		sql.append("insert into bidannouncement2");
		sql.append(
				"(bidnum, bidtrial, bidclass, bidname, bidagency, biddemand, bidmethod, bidstart, bidend, itemdetailnum, price_estimate, baseamount, bidamount, winresult)");
		sql.append(" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

		Connection con = null;
		PreparedStatement pstmt = null;
		BidAnalysisVO baVo = bidAnlysisVo;

		try {
			// (2) DBUtil�̶�� Ŭ������ getConnection()�޼���� �����ͺ��̽��� ����
			con = DBUtil.getConnection();

			// (3) �Է¹��� ������ ó���ϱ� ���� SQL���� ����
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, baVo.getBidNum());
			pstmt.setString(2, baVo.getBidTrial());
			pstmt.setString(3, baVo.getBidClass());
			pstmt.setString(4, baVo.getBidName());
			pstmt.setString(5, baVo.getBidAgency());
			pstmt.setString(6, baVo.getBidDemand());
			pstmt.setString(7, baVo.getBidMethod());
			pstmt.setString(8, baVo.getBidStart());
			pstmt.setString(9, baVo.getBidEnd());
			pstmt.setString(10, baVo.getItemDetailNum());
			pstmt.setInt(11, baVo.getPriceEstimate());
			pstmt.setInt(12, baVo.getBaseAmount());
			pstmt.setInt(13, baVo.getBidAmount());
			pstmt.setString(14, baVo.getWinResult());
			

			// (4)SQL���� ���� �� ó�� ����� ������
			int i = pstmt.executeUpdate();

		} catch (SQLException e1) {
			System.out.println(" e1 = [" + e1 + "]");
		} catch (Exception e2) {
			System.out.println(" e2 = [" + e2 + "]");
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e3) {
				System.out.println(" e3 = [" + e3 + "]");
			} // end of SQLException
		} // end of try
		return bidAnlysisVo;
	} // end of !(1) �ű� ���� ����

	// ���� ���� ��ü ����Ʈ
	public ArrayList<BidAnalysisVO> getBidTotal() {
		ArrayList<BidAnalysisVO> list = new ArrayList<BidAnalysisVO>();
		StringBuffer sql = new StringBuffer();
		sql.append(
				"select bidnum, bidtrial, bidclass, bidname, bidagency, biddemand, bidmethod, bidstart, bidend, itemdetailnum, price_estimate, baseamount, bidamount, winresult");
		sql.append(" from bidannouncement2 order by bidnum desc");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BidAnalysisVO baVo = null;

		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {

				baVo = new BidAnalysisVO();

				baVo.setBidNum(rs.getString("bidnum"));
				baVo.setBidTrial(rs.getString("bidtrial"));
				baVo.setBidClass(rs.getString("bidclass"));
				baVo.setBidName(rs.getString("bidname"));
				baVo.setBidAgency(rs.getString("bidagency"));
				baVo.setBidDemand(rs.getString("biddemand"));
				baVo.setBidMethod(rs.getString("bidmethod"));
				baVo.setBidStart(rs.getString("bidstart"));
				baVo.setBidEnd(rs.getString("bidend"));
				baVo.setItemDetailNum(rs.getString("itemdetailnum"));
				baVo.setPriceEstimate(rs.getInt("price_estimate"));
				baVo.setBaseAmount(rs.getInt("baseamount"));
				baVo.setBidAmount(rs.getInt("bidamount"));
				baVo.setWinResult(rs.getString("winresult"));
				
				list.add(baVo);
			}
		} catch (SQLException se) {
			System.out.println("se3 : " + se);
		} catch (Exception e) {
			System.out.println("e10=[" + e + "]");
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				} // end if
			} catch (SQLException se) {
				System.out.println("se4 : " + se);
			}
		} // end try-catch-finally
		return list;
	}

	// �����ͺ��̽����� �л� ���̺��� �÷��� ����
	public ArrayList<String> getColumnName() {
		ArrayList<String> columnName = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		sql.append("select * from bidannouncement2");
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount();
			for (int i = 1; i <= cols; i++) {
				columnName.add(rsmd.getColumnName(i));
			}

		} catch (SQLException se) {
			System.out.println("se5 : " + se);
		} catch (Exception e) {
			System.out.println("e11=[" + e + "]");
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException se) {
				System.out.println("se6 : " + se);
			}
		}
		return columnName;
	}

	public void getBidDelete(String bidNum) throws Exception {

		// (2)������ ó���� ���� SQL ��
		StringBuffer sql = new StringBuffer();
		sql.append("delete from bidannouncement2 where bidnum = ?");
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			// (3)DBUtil�̶�� Ŭ������ getConnection()�޼���� �����ͺ��̽����� ����
			con = DBUtil.getConnection();

			// (5)SQL���� ���� �� ó�� ����� ����
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, bidNum);

			int i = pstmt.executeUpdate();

			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("���� ����");
				alert.setHeaderText("���� �Ϸ�");
				alert.setContentText("������ �����Ͽ����ϴ�.");
				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("���� ����");
				alert.setHeaderText("���� ����");
				alert.setContentText("������ �����Ͽ����ϴ�.");
				alert.showAndWait();
			}

		} catch (SQLException e) {
			System.out.println("getBidDelete SQLException error = [" + e + "]");
		} catch (Exception e) {
			System.out.println("getBidDelete Exception error = [" + e + "]");
		} finally {
			try {
				// (6) �����ͺ��̽����� ���ῡ ���Ǿ��� ������Ʈ�� ����
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (SQLException e) {
				System.out.println("getBidDelete finally SQLException error = [" + e + "]");
			}

		}

	}

	// ������ ���� ����
	public BidAnalysisVO getBidModifyUpdate(BidAnalysisVO bVo, String bidNum) throws Exception {
		// (2) ������ ó���� ���� SQL��

		StringBuffer sql = new StringBuffer();
		sql.append("update bidannouncement2 set ");
		sql.append(
				" bidtrial =?, bidclass = ?, bidname = ?, bidagency =?, biddemand = ?, bidmethod = ?, bidstart = ?, bidend = ?, itemdetailnum =?, price_estimate =?, baseamount=?, bidamount=?, winresult=?");
		sql.append(" where bidnum = ?");

		Connection con = null;
		PreparedStatement pstmt = null;
		BidAnalysisVO retval = null;

		try {
			// (3) DBUtil�̶�� Ŭ������ getConnection() �޼ҵ�� �����ͺ��̽��� ����
			con = DBUtil.getConnection();

			// (4) ������ �л� ������ �����ϱ� ���Ͽ� SQL������ ����
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, bVo.getBidTrial());
			pstmt.setString(2, bVo.getBidClass());
			pstmt.setString(3, bVo.getBidName());
			pstmt.setString(4, bVo.getBidAgency());
			pstmt.setString(5, bVo.getBidDemand());
			pstmt.setString(6, bVo.getBidMethod());
			pstmt.setString(7, bVo.getBidStart());
			pstmt.setString(8, bVo.getBidEnd());
			pstmt.setString(9, bVo.getItemDetailNum());
			pstmt.setInt(10, bVo.getPriceEstimate());
			pstmt.setInt(11, bVo.getBaseAmount());
			pstmt.setInt(12, bVo.getBidAmount());
			pstmt.setString(13, bVo.getWinResult());
			pstmt.setString(14, bVo.getBidNum());

			// (5) SQl���� ���� �� ó�� ����� ����
			int i = pstmt.executeUpdate();

			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("���� ����");
				alert.setHeaderText("���� ���� �Ϸ�");
				alert.setContentText("���� ���� ����");
				alert.showAndWait();
				retval = new BidAnalysisVO();

			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("���� ����");
				alert.setHeaderText("���� ���� ����");
				alert.setContentText("���� ������ �����Ͽ����ϴ�.");
				alert.showAndWait();
			}
		} catch (SQLException e) {
			System.out.println("getBidModifyUpdate SQLException");
		} catch (Exception e) {
			System.out.println("getBidModifyUpdate Exception");
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (SQLException e) {
				System.out.println("getBidModifyUpdate SQLException finally SQLException ");
			}

		}
		return retval;
	}
	
	

	// ���� ��ȣ�� �Է¹޾� ������ȸ
	public BidAnalysisVO getBidNumberCheck(String bidNum) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from bidannouncement2 where bidnum like ");
		sql.append("? order by bidnum desc");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BidAnalysisVO bVo = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, "%" + bidNum + "%");

			rs = pstmt.executeQuery();

			if (rs.next()) {
				bVo = new BidAnalysisVO();

				bVo.setBidNum(rs.getString("bidnum"));
				bVo.setBidTrial(rs.getString("bidtrial"));
				bVo.setBidClass(rs.getString("bidclass"));
				bVo.setBidName(rs.getString("bidname"));
				bVo.setBidAgency(rs.getString("bidagency"));
				bVo.setBidDemand(rs.getString("biddemand"));
				bVo.setBidMethod(rs.getString("bidmethod"));
				bVo.setBidStart(rs.getString("bidstart"));
				bVo.setBidEnd(rs.getString("bidend"));
				bVo.setItemDetailNum(rs.getString("itemdetailnum"));
				bVo.setPriceEstimate(rs.getInt("price_estimate"));
				bVo.setBaseAmount(rs.getInt("baseamount"));
				bVo.setBidAmount(rs.getInt("bidamount"));
				bVo.setWinResult(rs.getString("winresult"));
			}

		} catch (SQLException se) {
			System.out.println("se");
			System.out.println("getBidNumberCheck SQLException");
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("getBidNumberCheck Exception");
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException se) {
				System.out.println("getBidNumberCheck finally SQLException");
			}
		}
		return bVo;
	}

}
