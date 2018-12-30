package mypage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dataBase.ConnectionDB;
import motherj.noticeModule.Notice;

public class EndSettlementDAO {
	
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private Connection conn;
    
	public EndSettlementDAO() {}
	
//	private  gkooId;
//	private LocalDate date;
//	private int transactionMoney;
//	private int depositMoney;
//	private String itemName;
//	private ItemFoto itemFoto;
//	private int purchasePrice;
//	private int shippingPrice;
//	private int settleAmount;
	public List<EndSettlement> getSettlementListFromLocal() {
		List<EndSettlement> settlementList = new ArrayList<EndSettlement>();

		EndSettlement element_1 = new EndSettlement("112+116",
				LocalDate.of(2018, 9, 11),
				0,
				939000,
				"합포장 6kg",
				"Foto",
				0,
				45000,
				749000);
		
		EndSettlement element_2 = new EndSettlement("112+116",
				LocalDate.of(2018, 9, 11),
				0,
				939000,
				"합포장 6kg",
				"Foto",
				0,
				45000,
				749000);
		
		settlementList.add(element_1);//settleAmount
		settlementList.add(element_2);//settleAmount
		return settlementList;
	}
	
	public List<EndSettlement> getSettlementListFromDB() throws SQLException{
		ConnectionDB.connectSQL();
		Connection conn = ConnectionDB.getConnectInstance();
		
		List<EndSettlement> settlementList = null;
		try {
			this.statement = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			resultSet = this.statement
			         .executeQuery("select * from settlement");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			settlementList = writeResultSettlementListSet(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		
		return settlementList;
	}
	
	private List<EndSettlement> writeResultSettlementListSet(ResultSet rs) throws SQLException {
		List<EndSettlement> settlementList =  new ArrayList<EndSettlement>();
		
		//EndSettlement settlement = new EndSettlement();
		while (rs.next()) {
			EndSettlement settlement = new EndSettlement();
			
			Date date = rs.getDate("settlement_date");
			LocalDate localDate = 
					LocalDate.parse( new SimpleDateFormat("yyyy-MM-dd").format(date) );
			settlement.setDate(localDate);
			
			settlement.setGkooId(rs.getString("item_id"));
			
			settlement.setTransactionMoney(rs.getInt("transactionMoney"));
			
			settlement.setDepositMoney(rs.getInt("depositMoney"));
			
			settlement.setItemName(rs.getString("item_name"));
			
			/*상품아이디와 상품url을 같게 설정함*/
			settlement.setItemImageUrl(rs.getString("item_id"));
			
			settlement.setPurchasePrice(rs.getInt("purchasePrice"));
			
			settlement.setShippingPrice(rs.getInt("shippingPrice"));
			
			settlement.setSettleAmount(rs.getInt("settleAmount"));
			
			settlementList.add(settlement);
			
		}
        return settlementList;
    }
	
	private EndSettlement writeResultSettlementContentSet(ResultSet rs) throws SQLException {
		EndSettlement settlement = new EndSettlement();
		while (rs.next()) {
			Date date = rs.getDate("settlement_date");
			LocalDate localDate = 
					LocalDate.parse( new SimpleDateFormat("yyyy-MM-dd").format(date) );
			settlement.setDate(localDate);
			settlement.setGkooId(rs.getString("item_id"));
			
			settlement.setTransactionMoney(rs.getInt("transactionMoney"));
			
			settlement.setDepositMoney(rs.getInt("depositMoney"));
			
			settlement.setItemName(rs.getString("item_name"));

			settlement.setItemImageUrl(rs.getString("itemFotoUrl"));
			
			settlement.setPurchasePrice(rs.getInt("purchasePrice"));
			
			settlement.setShippingPrice(rs.getInt("shippingPrice"));
			
			settlement.setSettleAmount(rs.getInt("settleAmount"));
			
		}
        return settlement;
    }
	
	private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {

        }
    }
}
