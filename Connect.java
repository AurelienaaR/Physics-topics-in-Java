package main;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Topic;
// import model.Domain;
import model.Variable;
import models.topics.Domains;

public class Connect {

	public static ArrayList<Variable> connect() {

		String domStr = "";
		String caStr = "";
		String exStr = "";
		String idElt = "1";
		String levStr = "";

		List<String> query = new ArrayList<String>();
		ArrayList<Variable> variablesObj = new ArrayList<Variable>();
		ArrayList<String> levels = new ArrayList<String>();
		ArrayList<String> cadres = new ArrayList<String>();
		ArrayList<String> extensivities = new ArrayList<String>();

		String titleStr;
		String contentStr;
		String characteristicsStr;
		Array typeArr;
		int dimInt;
		String extensivityStr;
		String continuityStr;
		int idLevelInt;
		LocalDate savedDate;

		try {

			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/physvar";
			String user = "postgres";
			String passwd = mdp.mdp;
			Connection conn = DriverManager.getConnection(url, user, passwd);
			Statement state = conn.createStatement();

			for (int i = 1; i <= 15; i++) {

				query.add("SELECT * FROM variables v WHERE v.id = " + i);
				ResultSet resVar = state.executeQuery(query.get(i - 1));
				ResultSetMetaData resVa = resVar.getMetaData();
				for (int j = 1; j <= resVa.getColumnCount(); j++) {
					resVa.getColumnName(j).toUpperCase();
				}
				while (resVar.next()) {
					titleStr = resVar.getString("title");
					contentStr = resVar.getString("content");
					levStr = resVar.getString("idlevel");
					typeArr = resVar.getArray("arrtype");
					characteristicsStr = resVar.getString("idcharacteristics");
					dimInt = resVar.getInt("dim");
					extensivityStr = resVar.getString("idextensivity");
					continuityStr = resVar.getString("idcontinuity");
					idLevelInt = resVar.getInt("idlevel");
					savedDate = LocalDate.of(2018, 2, 2);

					String queryy = Domains.fTopicsString();
					queryy += i;

					Statement stateDom = conn.createStatement();
					ResultSet resDomains = stateDom.executeQuery(queryy);
					ResultSetMetaData resDom = resDomains.getMetaData();

					for (int q = 1; q <= resDom.getColumnCount(); q++) {
						resDom.getColumnName(q).toUpperCase();
					}
					while (resDomains.next()) {
						for (int op = 1; op <= resDom.getColumnCount(); op++) {
							domStr = resDomains.getObject(op).toString();
						}
					}
					resDomains.close();
					stateDom.close();
					variablesObj.add(new Variable(i, titleStr, domStr, typeArr, characteristicsStr, dimInt,
							extensivityStr, continuityStr, contentStr, idLevelInt, savedDate));
				}

				resVar.close();
			}

			ResultSet resCadre = state.executeQuery("SELECT content FROM cadre");
			ResultSetMetaData resCa = resCadre.getMetaData();
			for (int ji = 1; ji <= resCa.getColumnCount(); ji++) {
				resCa.getColumnName(ji).toUpperCase();
			}
			while (resCadre.next()) {
				for (int k = 1; k <= resCa.getColumnCount(); k++) {
					caStr = resCadre.getObject(k).toString();
					cadres.add(caStr);
				}
			}
			resCadre.close();

			ResultSet resExtensivity = state.executeQuery("SELECT content FROM extensivity");
			ResultSetMetaData resEx = resExtensivity.getMetaData();
			for (int l = 1; l <= resEx.getColumnCount(); l++) {
				resEx.getColumnName(l).toUpperCase();
			}
			while (resExtensivity.next()) {
				for (int m = 1; m <= resEx.getColumnCount(); m++) {
					exStr = resExtensivity.getObject(m).toString();
					extensivities.add(exStr);
				}
			}
			resExtensivity.close();

			ResultSet resLevels = state.executeQuery("SELECT title FROM variables where id = " + idElt);
			ResultSetMetaData resLev = resLevels.getMetaData();
			for (int n = 1; n <= resLev.getColumnCount(); n++) {
				resLev.getColumnName(n).toUpperCase();
			}
			while (resLevels.next()) {
				for (int o = 1; o <= resLev.getColumnCount(); o++) {
					levStr = resLevels.getObject(o).toString();
					levels.add(levStr);
				}
			}
			resExtensivity.close();
			state.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return variablesObj;
	}

	public static ArrayList<Topic> topic() {

		String caStr = "";
		String exStr = "";
		String idElt = "1";
		String levStr = "";

		List<String> query = new ArrayList<String>();
		ArrayList<Topic> topicObj = new ArrayList<Topic>();
		ArrayList<String> levels = new ArrayList<String>();
		ArrayList<String> cadres = new ArrayList<String>();
		ArrayList<String> extensivities = new ArrayList<String>();

		String titleStr;
		String domxStr;
		String contentStr;
		int iddomain;
		Array varArr;
		int idType;

		try {

			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/physvar";
			String user = "postgres";
			String passwd = mdp.mdp;
			Connection conn = DriverManager.getConnection(url, user, passwd);
			Statement state = conn.createStatement();

			for (int iTop = 1; iTop <= 10; iTop++) {
				query.add("SELECT * FROM topic t WHERE t.id = " + iTop);
				ResultSet resTop = state.executeQuery(query.get(iTop - 1));
				ResultSetMetaData resTo = resTop.getMetaData();
				for (int j = 1; j <= resTo.getColumnCount(); j++) {
					resTo.getColumnName(j).toUpperCase();
				}
				while (resTop.next()) {
					titleStr = resTop.getString("title");
					contentStr = resTop.getString("content");
					varArr = resTop.getArray("arrvar");
					idType = resTop.getInt("idtype");
					iddomain = resTop.getInt("iddomain");

					String queryy = Domains.fTopicsString();
					queryy += iddomain;

					Statement stateDom = conn.createStatement();
					ResultSet resDomains = stateDom.executeQuery(queryy);
					ResultSetMetaData resDom = resDomains.getMetaData();

					for (int q = 1; q <= resDom.getColumnCount(); q++) {
						resDom.getColumnName(q).toUpperCase();
					}

					while (resDomains.next()) {
						for (int op = 1; op <= resDom.getColumnCount(); op++) {
							domxStr = resDomains.getObject(op).toString();
							System.out.println(domxStr);
						}
					}

					resDomains.close();
					stateDom.close();
					topicObj.add(new Topic(iTop, iddomain, titleStr, varArr, contentStr, idType));
				}

				resTop.close();
			}

			ResultSet resCadre = state.executeQuery("SELECT content FROM cadre");
			ResultSetMetaData resCa = resCadre.getMetaData();
			for (int ji = 1; ji <= resCa.getColumnCount(); ji++) {
				resCa.getColumnName(ji).toUpperCase();
			}
			while (resCadre.next()) {
				for (int k = 1; k <= resCa.getColumnCount(); k++) {
					caStr = resCadre.getObject(k).toString();
					cadres.add(caStr);
				}
			}
			resCadre.close();

			ResultSet resExtensivity = state.executeQuery("SELECT content FROM extensivity");
			ResultSetMetaData resEx = resExtensivity.getMetaData();
			for (int l = 1; l <= resEx.getColumnCount(); l++) {
				resEx.getColumnName(l).toUpperCase();
			}
			while (resExtensivity.next()) {
				for (int m = 1; m <= resEx.getColumnCount(); m++) {
					exStr = resExtensivity.getObject(m).toString();
					extensivities.add(exStr);
				}
			}
			resExtensivity.close();

			ResultSet resLevels = state.executeQuery("SELECT title FROM variables where id = " + idElt);
			ResultSetMetaData resLev = resLevels.getMetaData();
			for (int n = 1; n <= resLev.getColumnCount(); n++) {
				resLev.getColumnName(n).toUpperCase();
			}
			while (resLevels.next()) {
				for (int o = 1; o <= resLev.getColumnCount(); o++) {
					levStr = resLevels.getObject(o).toString();
					levels.add(levStr);
				}
			}
			resExtensivity.close();

			state.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return topicObj;
	}

}
