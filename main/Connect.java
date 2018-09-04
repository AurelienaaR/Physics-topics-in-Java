package main;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import constantes.mdp;
import model.Topic;
import model.Variable;

public class Connect {

	public static int idMaxi(int iElt) {
		int idMax = 0;
		String EltStr = "domain";
		String query;
		int idX = 0;
		switch (iElt) {
		case 0:
			EltStr = "domain";
			break;
		case 1:
			EltStr = "topic";
			break;
		case 2:
			EltStr = "variables";
			break;
		default:
			break;
		}
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/physvar";
			String user = "postgres";
			String passwd = mdp.mdp;
			Connection conn = DriverManager.getConnection(url, user, passwd);
			Statement state = conn.createStatement();
			query = "SELECT id FROM " + EltStr;
			ResultSet resId = state.executeQuery(query);
			while (resId.next()) {
				idX = resId.getInt("id");
				if (idX > idMax) {
					idMax = idX;
				}
			}
			resId.close();
			state.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return idMax;
	}

	public static ArrayList<Variable> connect() {
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
		int idCharacteristics;
		Array arrType;
		int dimInt;
		int idExtensivity;
		int idContinuity;
		int idLevel;
		int idMVar = idMaxi(2);
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/physvar";
			String user = "postgres";
			String passwd = mdp.mdp;
			Connection conn = DriverManager.getConnection(url, user, passwd);
			Statement state = conn.createStatement();
			for (int i = 1; i <= idMVar; i++) {
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
					arrType = resVar.getArray("arrtype");
					idCharacteristics = resVar.getInt("idcharacteristics");
					dimInt = resVar.getInt("dim");
					idExtensivity = resVar.getInt("idextensivity");
					idContinuity = resVar.getInt("idcontinuity");
					idLevel = resVar.getInt("idlevel");
					variablesObj.add(new Variable(i, dimInt, idExtensivity, idContinuity, idCharacteristics, titleStr,
							contentStr, idLevel, arrType));
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
		int iddomain;
		int idType;
		int idMTop = idMaxi(1);
		String caStr = "";
		String exStr = "";
		String idElt = "1";
		String levStr = "";
		String titleStr;
		String contentStr;
		Array varArr;
		ArrayList<Topic> topicObj = new ArrayList<Topic>();
		ArrayList<String> levels = new ArrayList<String>();
		ArrayList<String> cadres = new ArrayList<String>();
		ArrayList<String> extensivities = new ArrayList<String>();
		List<String> query = new ArrayList<String>();
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/physvar";
			String user = "postgres";
			String passwd = mdp.mdp;
			Connection conn = DriverManager.getConnection(url, user, passwd);
			Statement state = conn.createStatement();
			for (int iTop = 1; iTop <= idMTop; iTop++) {
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
			resLevels.close();
			state.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return topicObj;
	}

	public static ArrayList<String> domTot() {
		String[] domStrT = { "Tous domaines", "Tous domaines" };
		ArrayList<String> domTot = new ArrayList<String>();
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/physvar";
			String user = "postgres";
			String passwd = mdp.mdp;
			Connection conn = DriverManager.getConnection(url, user, passwd);
			Statement state = conn.createStatement();
			String query = "SELECT * FROM domain";
			ResultSet resTotD = state.executeQuery(query);
			ResultSetMetaData resToD = resTotD.getMetaData();
			for (int j = 1; j <= resToD.getColumnCount(); j++) {
				resToD.getColumnName(j).toUpperCase();
			}
			while (resTotD.next()) {
				domStrT[0] = resTotD.getString("title");
				domStrT[1] = resTotD.getString("content");
				domTot.add(domStrT[0]);
			}
			resTotD.close();
			state.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return domTot;
	}

	public static String[] domString(int idDomain) {
		List<String> query = new ArrayList<String>();
		String titleStr = "";
		String contentStr = "";
		String[] domStr = {};
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/physvar";
			String user = "postgres";
			String passwd = mdp.mdp;
			Connection conn = DriverManager.getConnection(url, user, passwd);
			Statement state = conn.createStatement();
			query.add("SELECT * FROM domain WHERE id = " + idDomain);
			ResultSet resDom = state.executeQuery(query.get(idDomain - 1));
			ResultSetMetaData resDo = resDom.getMetaData();
			for (int j = 1; j <= resDo.getColumnCount(); j++) {
				resDo.getColumnName(j).toUpperCase();
			}
			while (resDom.next()) {
				titleStr = resDom.getString("title");
				contentStr = resDom.getString("content");
				domStr[0] = titleStr;
				domStr[1] = contentStr;
			}
			resDom.close();
			state.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return domStr;
	}

	public static String[] extString(int idExt) {
		List<String> query = new ArrayList<String>();
		String titleStr = "";
		String contentStr = "";
		String[] extStr = {};
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/physvar";
			String user = "postgres";
			String passwd = mdp.mdp;
			Connection conn = DriverManager.getConnection(url, user, passwd);
			Statement state = conn.createStatement();
			query.add("SELECT * FROM extensivity WHERE id = " + idExt);
			ResultSet resExt = state.executeQuery(query.get(idExt - 1));
			while (resExt.next()) {
				titleStr = resExt.getString("title");
				contentStr = resExt.getString("content");
				extStr[0] = titleStr;
				extStr[1] = contentStr;
			}
			resExt.close();
			state.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return extStr;
	}

	public static String[] contString(int idCont) {
		List<String> query = new ArrayList<String>();
		String titleStr = "";
		String contentStr = "";
		String[] contStr = {};
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/physvar";
			String user = "postgres";
			String passwd = mdp.mdp;
			Connection conn = DriverManager.getConnection(url, user, passwd);
			Statement state = conn.createStatement();
			query.add("SELECT * FROM continuity WHERE id = " + idCont);
			ResultSet resCont = state.executeQuery(query.get(idCont - 1));
			while (resCont.next()) {
				titleStr = resCont.getString("title");
				contentStr = resCont.getString("content");
				contStr[0] = titleStr;
				contStr[1] = contentStr;
			}
			resCont.close();
			state.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contStr;
	}

	public static String[] charString(int idChar) {
		List<String> query = new ArrayList<String>();
		String titleStr = "";
		String contentStr = "";
		String[] charStr = {};
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/physvar";
			String user = "postgres";
			String passwd = mdp.mdp;
			Connection conn = DriverManager.getConnection(url, user, passwd);
			Statement state = conn.createStatement();
			query.add("SELECT * FROM characteristics WHERE id = " + idChar);
			ResultSet resChar = state.executeQuery(query.get(idChar - 1));
			while (resChar.next()) {
				titleStr = resChar.getString("title");
				contentStr = resChar.getString("content");
				charStr[0] = titleStr;
				charStr[1] = contentStr;
			}
			resChar.close();
			state.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return charStr;
	}

	public static String[] levString(int idLev) {
		List<String> query = new ArrayList<String>();
		String titleStr = "";
		String contentStr = "";
		String[] levStr = {};
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/physvar";
			String user = "postgres";
			String passwd = mdp.mdp;
			Connection conn = DriverManager.getConnection(url, user, passwd);
			Statement state = conn.createStatement();
			query.add("SELECT * FROM characteristics WHERE id = " + idLev);
			ResultSet resLev = state.executeQuery(query.get(idLev - 1));
			while (resLev.next()) {
				titleStr = resLev.getString("title");
				contentStr = resLev.getString("content");
				levStr[0] = titleStr;
				levStr[1] = contentStr;
			}
			resLev.close();
			state.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return levStr;
	}

	public static void saveDomain(int idMaxPlus, String titleStr, String contentStr) {
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/physvar";
			String user = "postgres";
			String passwd = mdp.mdp;
			Connection conn = DriverManager.getConnection(url, user, passwd);
			Statement state = conn.createStatement();
			String querySaveDomain = "INSERT INTO domain ('id', 'title', 'content') VALUES (" + idMaxPlus + ", "
					+ titleStr + ", " + contentStr + ");";
			ResultSet resSDomain = state.executeQuery(querySaveDomain);
			resSDomain.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void saveTopic(int idMaxPlus, int idDomain, String titleStr, Array arrVar, String contentStr,
			int idType) {
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/physvar";
			String user = "postgres";
			String passwd = mdp.mdp;
			Connection conn = DriverManager.getConnection(url, user, passwd);
			Statement state = conn.createStatement();
			String querySaveTopic = "INSERT INTO topic ('id', 'iddomain', 'title', 'arrvar', 'content', 'idtype') VALUES ("
					+ idMaxPlus + ", " + idDomain + ", " + titleStr + ", " + arrVar + ", " + contentStr + ", " + idType
					+ ");";
			ResultSet resSTopic = state.executeQuery(querySaveTopic);
			resSTopic.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void saveVariable(int idMaxPlus, int dim, int idextensivity, int idcontinuity, int idcharacteristics,
			String titleStr, String contentStr, int idlevel, Array arrtype) {
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/physvar";
			String user = "postgres";
			String passwd = mdp.mdp;
			Connection conn = DriverManager.getConnection(url, user, passwd);
			Statement state = conn.createStatement();
			String querySaveTopic = "INSERT ('id', 'dim', 'idextensivity', 'idcontinuity', 'idcharacteristics', 'title', 'content', 'idlevel', 'arrtype') INTO variables VALUES ("
					+ idMaxPlus + ", " + dim + ", " + idextensivity + ", " + idcontinuity + "," + idcharacteristics
					+ ", " + titleStr + ", " + contentStr + ", " + idlevel + ", " + arrtype + ");";
			ResultSet resSTopic = state.executeQuery(querySaveTopic);
			resSTopic.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
