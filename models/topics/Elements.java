package models.topics;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// import model.Domain;
import model.Level;
import model.Variable;
import main.mdp;

public class Elements extends TopicsBin {

	Variable varElt;
	Level levElt;

	ArrayList<String> listRef = new ArrayList<String>() {
		private static final long serialVersionUID = 1L;
		{

		}
	};

	public void fTopicsVoid(String fg) {
		listRef.add(fg);
	}

	public static Variable Elt(Integer idx) {

		List<String> query = new ArrayList<String>();
		String titleStr = "";
		String contentStr = "";
		Array typeArr = null;
		String characteristicsStr = "";
		int dimInt = 0;
		String extensivityStr = "";
		String continuityStr = "";
		int idLevelInt = 1;
		LocalDate savedDate = LocalDate.of(2018, 2, 2);
		String domainStr = "Tous domaines";
		Variable elt = new Variable(idx, titleStr, domainStr, typeArr, characteristicsStr, dimInt, extensivityStr,
				continuityStr, contentStr, idLevelInt, savedDate);

		try {

			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/physvar";
			String user = "postgres";
			String passwd = mdp.mdp;
			Connection conn = DriverManager.getConnection(url, user, passwd);
			Statement state = conn.createStatement();
			query.add("SELECT * FROM variables v WHERE v.id = " + idx);
			ResultSet resVar = state.executeQuery(query.get(idx - 1));
			ResultSetMetaData resVa = resVar.getMetaData();
			for (int j = 1; j <= resVa.getColumnCount(); j++) {
				resVa.getColumnName(idx).toUpperCase();
			}
			titleStr = resVar.getString("title");
			contentStr = resVar.getString("content");
			typeArr = resVar.getArray("arrtype");
			characteristicsStr = resVar.getString("idcharacteristics");
			dimInt = resVar.getInt("dim");
			extensivityStr = resVar.getString("idextensivity");
			continuityStr = resVar.getString("idcontinuity");
			idLevelInt = resVar.getInt("idlevel");
			elt.setId(idx);
			elt.setTitle(titleStr);
			elt.setDomain(domainStr);
			elt.setType(typeArr);
			elt.setCharacteristics(characteristicsStr);
			elt.setDim(dimInt);
			elt.setExtensivity(extensivityStr);
			elt.setContinuity(continuityStr);
			elt.setContent(contentStr);
			elt.setIdLevelInt(idLevelInt);
			elt.setSaved(savedDate);
			resVar.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return elt;
	}

	public static String fTopicsString() {
		String fTopicsReturn = "SELECT * FROM topics WHERE idlevel = 3;";
		return fTopicsReturn;
	}

	public static ArrayList<Variable> Element() {

		List<String> query = new ArrayList<String>();
		ArrayList<Variable> listVar = new ArrayList<Variable>();

		int vlid = 1;

		String titleStr;
		String contentStr;

		Array typeArr;
		String characteristicsStr;
		int dimInt;
		String extensivityStr;
		String continuityStr;
		int idLevelInt;
		LocalDate savedDate;
		String domainStr = "Tous domaines";

		try {

			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/physvar";
			String user = "postgres";
			String passwd = main.mdp.mdp;
			Connection conn = DriverManager.getConnection(url, user, passwd);
			Statement state = conn.createStatement();

			for (int i = vlid; i <= 5; i++) {

				// query.add((vid == 1) ? "SELECT * FROM variables v INNER JOIN vardom vd ON
				// v.id = vd.idvar"
				// : (vid == 2) ? "SELECT * FROM variables v INNER JOIN vardom vd ON v.id =
				// vd.idvar"
				// : "SELECT * FROM variables v INNER JOIN vardom vd, varlevel vl ON v.id =
				// vd.idvar + AND vl.idvar = "
				// + vlid);

				query.add("SELECT * FROM variables v WHERE v.id = " + i);
				ResultSet resVar = state.executeQuery(query.get(i - 1));
				ResultSetMetaData resVa = resVar.getMetaData();
				for (int j = 1; j <= resVa.getColumnCount(); j++) {
					resVa.getColumnName(i).toUpperCase();
				}
				while (resVar.next()) {
					titleStr = resVar.getString("title");
					contentStr = resVar.getString("content");
					typeArr = resVar.getArray("arrtype");
					// typeArrb = resVar.getString("arrtype");
					// ArrayList<String> typeArr = new ArrayList<String>();
					// Collections.addAll(typeArr, typeArrb);
					characteristicsStr = resVar.getString("idcharacteristics");
					dimInt = resVar.getInt("dim");
					extensivityStr = resVar.getString("idextensivity");
					continuityStr = resVar.getString("idcontinuity");
					idLevelInt = resVar.getInt("idlevel");
					savedDate = LocalDate.of(2018, 2, 2);
					listVar.add(new Variable(i, titleStr, domainStr, typeArr, characteristicsStr, dimInt,
							extensivityStr, continuityStr, contentStr, idLevelInt, savedDate));
				}
				resVar.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listVar;

	}

}
