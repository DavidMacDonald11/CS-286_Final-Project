import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class HelpfulResultSet {
	private ResultSet rs;
	private ResultSetMetaData rsmd;
	private ArrayList<ArrayList<String>> data;
	
	public HelpfulResultSet(ResultSet rs) {
		this.rs = rs;
		
		try { 
			rsmd = rs.getMetaData(); 
			
		} catch(SQLException e) { 
			System.out.println(e.getMessage());
		} finally {
			fillData();
		}
	}
	
	public void closeStatement() { 
		try {
			rs.getStatement().close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	public ArrayList<ArrayList<String>> getData() {
		if(data == null) { fillData(); }
		return new ArrayList<ArrayList<String>>(data);
	}
	
	public void printData(String rowFormat, String colFormat, boolean dynamicSize) {
		ArrayList<ArrayList<String>> dataCopy = getData();
		
		for(ArrayList<String> row : dataCopy) {
			String toPrint = "";
			
			for(int i = 0; i < row.size(); i++) {
				if(dynamicSize) {
					String size;
					size = Integer.toString(dataCopy.get(0).get(i).length());
					
					toPrint += String.format(rowFormat.replace("%s", "%" + size + "s"), row.get(i));
				} else {
					toPrint += String.format(rowFormat, row.get(i));
				}
			}
			
			System.out.printf(colFormat, toPrint);
		}
	}
	
	public void printData(String rowFormat) { printData(rowFormat, "%s%n", true); }
	
	public void printData() { printData("%s  "); }
	
	private void fillData() {
		ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
		ArrayList<String> meta = new ArrayList<String>();
		
		data.add(meta);
		
		try {
			for(int i = 1; i <= rsmd.getColumnCount(); i++) {
				meta.add(rsmd.getColumnLabel(i));
			}
			
			ArrayList<String> temp;
			
			do {
				temp = new ArrayList<String>();
				data.add(temp);
				
				for(int i = 1; i <= rsmd.getColumnCount(); i++) {
					temp.add(rs.getString(i));
				}
			} while(rs.next());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		this.data = data;
	}
}
