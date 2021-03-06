package main;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import constantes.mdp;
import model.Cadre;
import model.Domain;
import model.ListBox;
import model.Subvariable;
import model.Topic;
import model.Variable;

public class Connect {
	public static int idMaxi(int iElt) {
		int idMax = 0;
		String EltStr = "";
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
		case 3:
			EltStr = "subvariables";
			break;
		default:
			EltStr = "topic";
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

	public static ArrayList<Cadre> initCadre() {
		int idMCad = 1;
		String titleStr;
		String contentStr;
		ArrayList<Cadre> cadreObj = new ArrayList<Cadre>();
		List<String> query = new ArrayList<String>();
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/physvar";
			String user = "postgres";
			String passwd = mdp.mdp;
			Connection conn = DriverManager.getConnection(url, user, passwd);
			Statement state = conn.createStatement();
			for (int iCad = 1; iCad <= idMCad; iCad++) {
				query.add("SELECT * FROM cadre WHERE id = " + iCad);
				ResultSet resCad = state.executeQuery(query.get(iCad - 1));
				ResultSetMetaData resDo = resCad.getMetaData();
				for (int j = 1; j <= resDo.getColumnCount(); j++) {
					resDo.getColumnName(j).toUpperCase();
				}
				while (resCad.next()) {
					iCad = resCad.getInt("id");
					titleStr = resCad.getString("title");
					contentStr = resCad.getString("content");
					cadreObj.add(new Cadre(iCad, titleStr, contentStr));
				}
				resCad.close();
			}
			state.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cadreObj;
	}

	public static ArrayList<Domain> initDomain() {
		int idMDom = idMaxi(0);
		String titleStr;
		String contentStr;
		int iCad = 1;
		int iTyp = 1;
		String titleCadStr;
		String contentCadStr;
		ArrayList<Domain> domainObj = new ArrayList<Domain>();
		ArrayList<Cadre> cadreObj = new ArrayList<Cadre>();
		List<String> query = new ArrayList<String>();
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/physvar";
			String user = "postgres";
			String passwd = mdp.mdp;
			Connection conn = DriverManager.getConnection(url, user, passwd);
			Statement state = conn.createStatement();
			for (int iDom = 1; iDom <= idMDom; iDom++) {
				query.add("SELECT * FROM domain WHERE id = " + iDom);
				ResultSet resDom = state.executeQuery(query.get(iDom - 1));
				ResultSetMetaData resDo = resDom.getMetaData();
				for (int j = 1; j <= resDo.getColumnCount(); j++) {
					resDo.getColumnName(j).toUpperCase();
				}
				while (resDom.next()) {
					iCad = resDom.getInt("idcadre");
					iTyp = resDom.getInt("idtype");
					titleStr = resDom.getString("title");
					contentStr = resDom.getString("content");
					domainObj.add(new Domain(iDom, iCad, iTyp, titleStr, contentStr));
				}
				resDom.close();

				ResultSet resCad = state.executeQuery("SELECT * FROM cadre WHERE id = " + iCad);
				ResultSetMetaData resCa = resCad.getMetaData();
				for (int j = 1; j <= resCa.getColumnCount(); j++) {
					resCa.getColumnName(j).toUpperCase();
				}
				while (resCad.next()) {
					iCad = resCad.getInt("id");
					titleCadStr = resCad.getString("title");
					contentCadStr = resCad.getString("content");
					cadreObj.add(new Cadre(iCad, titleCadStr, contentCadStr));
				}
				resCad.close();
			}
			state.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return domainObj;
	}

	public static ArrayList<Integer> cvtArr(Array jj) {
		ArrayList<Integer> arrA = new ArrayList<Integer>();
		String uu = jj.toString();
		int uuM = uu.length();
		int jv = 0;
		String uvj = "";
		String uujv = "";
		if (uuM > 1) {
			while (jv < uuM - 1) {
				jv++;
				uvj = "";
				uujv = uu.substring(jv, jv + 1);
				while (!(uujv.equals(",")) && !(uujv.equals("}"))) {
					uvj += uujv;
					jv++;
					uujv = uu.substring(jv, jv + 1);
				}
				arrA.add(Integer.parseInt(uvj));
			}
		}
		return arrA;
	}

	public static ArrayList<Topic> initTopic() {
		int idDomain;
		int idType;
		int idMTop = idMaxi(1);
		String titleStr;
		String contentStr;
		Array varAx;
		ArrayList<Topic> topicObj = new ArrayList<Topic>();
		List<String> query = new ArrayList<String>();
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/physvar";
			String user = "postgres";
			String passwd = mdp.mdp;
			Connection conn = DriverManager.getConnection(url, user, passwd);
			Statement state = conn.createStatement();
			for (int iTop = 1; iTop <= idMTop; iTop++) {
				ArrayList<Integer> varA = new ArrayList<Integer>();
				query.add("SELECT * FROM topic t WHERE t.id = " + iTop);
				ResultSet resTop = state.executeQuery(query.get(iTop - 1));
				ResultSetMetaData resTo = resTop.getMetaData();
				for (int j = 1; j <= resTo.getColumnCount(); j++) {
					resTo.getColumnName(j).toUpperCase();
				}
				while (resTop.next()) {
					titleStr = resTop.getString("title");
					contentStr = resTop.getString("content");
					varAx = resTop.getArray("arrvar");
					varA = cvtArr(varAx);
					idType = resTop.getInt("idtype");
					idDomain = resTop.getInt("iddomain");
					topicObj.add(new Topic(iTop, idDomain, titleStr, varA, contentStr, idType));
				}
				resTop.close();
			}
			state.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return topicObj;
	}

	public static ArrayList<Variable> initVariables() {
		String exStr = "";
		String idElt = "1";
		String levStr = "";
		List<String> query = new ArrayList<String>();
		ArrayList<Variable> variablesObj = new ArrayList<Variable>();
		ArrayList<String> levels = new ArrayList<String>();
		ArrayList<String> extensivities = new ArrayList<String>();
		String titleStr = "";
		String contentStr = "";
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
				ArrayList<Integer> typA = new ArrayList<Integer>();
				for (int j = 1; j <= resVa.getColumnCount(); j++) {
					resVa.getColumnName(j).toUpperCase();
				}
				while (resVar.next()) {
					titleStr = resVar.getString("title");
					contentStr = resVar.getString("content");
					levStr = resVar.getString("idlevel");
					arrType = resVar.getArray("arrtype");
					typA = cvtArr(arrType);
					idCharacteristics = resVar.getInt("idcharacteristics");
					dimInt = resVar.getInt("dim");
					idExtensivity = resVar.getInt("idextensivity");
					idContinuity = resVar.getInt("idcontinuity");
					idLevel = resVar.getInt("idlevel");
					variablesObj.add(new Variable(i, dimInt, idExtensivity, idContinuity, idCharacteristics, titleStr,
							contentStr, idLevel, typA));
				}
				resVar.close();
			}
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

	public static ArrayList<Subvariable> initSubVariables() {
		String exStr = "";
		String idElt = "1";
		String levStr = "";
		List<String> query = new ArrayList<String>();
		ArrayList<Subvariable> subVariablesObj = new ArrayList<Subvariable>();
		ArrayList<String> levels = new ArrayList<String>();
		ArrayList<String> extensivities = new ArrayList<String>();
		String titleStr = "";
		String contentStr = "";
		int idCharacteristics;
		Array arrType;
		int dimInt;
		int idExtensivity;
		int idContinuity;
		int idLevel;
		int idMVar = idMaxi(3);
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/physvar";
			String user = "postgres";
			String passwd = mdp.mdp;
			Connection conn = DriverManager.getConnection(url, user, passwd);
			Statement state = conn.createStatement();
			for (int i = 1; i <= idMVar; i++) {
				query.add("SELECT * FROM subvariables sv WHERE sv.id = " + i);
				ResultSet resSubVar = state.executeQuery(query.get(i - 1));
				ResultSetMetaData resSubVa = resSubVar.getMetaData();
				for (int j = 1; j <= resSubVa.getColumnCount(); j++) {
					resSubVa.getColumnName(j).toUpperCase();
				}
				while (resSubVar.next()) {
					titleStr = resSubVar.getString("title");
					contentStr = resSubVar.getString("content");
					levStr = resSubVar.getString("idlevel");
					arrType = resSubVar.getArray("arrtype");
					idCharacteristics = resSubVar.getInt("idcharacteristics");
					dimInt = resSubVar.getInt("dim");
					idExtensivity = resSubVar.getInt("idextensivity");
					idContinuity = resSubVar.getInt("idcontinuity");
					idLevel = resSubVar.getInt("idlevel");
					subVariablesObj.add(new Subvariable(i, dimInt, idExtensivity, idContinuity, idCharacteristics,
							titleStr, contentStr, idLevel, arrType));
				}
				resSubVar.close();
			}
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
		return subVariablesObj;
	}

	public static Hashtable<String, ArrayList<String>> cadTotString() {
		Hashtable<String, ArrayList<String>> htCad = new Hashtable<String, ArrayList<String>>();
		try {
			String colX;
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/physvar";
			String user = "postgres";
			String passwd = mdp.mdp;
			Connection conn = DriverManager.getConnection(url, user, passwd);
			Statement state = conn.createStatement();
			String query = "SELECT * FROM cadre";
			ResultSet resCad = state.executeQuery(query);
			ResultSetMetaData resCa = resCad.getMetaData();
			int jMaxi = resCa.getColumnCount();
			ArrayList<String> colCad = new ArrayList<String>();
			for (int jIni = 1; jIni <= jMaxi; jIni++) {
				colCad.add(resCa.getColumnName(jIni));
			}
			resCad.close();
			state.close();
			Statement statex = conn.createStatement();
			for (int j = 0; j < jMaxi; j++) {
				ArrayList<String> htCadX = new ArrayList<String>();
				colX = colCad.get(j);
				ResultSet resCadx = statex.executeQuery(query);
				ResultSetMetaData resCax = resCadx.getMetaData();
				while (resCadx.next()) {
					if (resCax.getColumnName(j + 1).equals(colX)) {
						htCadX.add(resCadx.getString(colX));
					}
				}
				htCad.put(colX, htCadX);
				resCadx.close();
			}
			statex.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return htCad;
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

	public static int getIdByTitleTopic(String titlex) {
		Hashtable<String, ArrayList<String>> htTopx = topTotString();
		ArrayList<String> titles = htTopx.get("title");
		int idTop = 1 + titles.indexOf(titlex);
		return idTop;
	}

	public static int getIdByTitleVar(String titlex) {
		Hashtable<String, ArrayList<String>> htVarx = varTotString();
		ArrayList<String> titles = htVarx.get("title");
		int idVar = 1 + titles.indexOf(titlex);
		return idVar;
	}

	public static int getIdByTitleSubVar(String titlex) {
		Hashtable<String, ArrayList<String>> htSubVarx = subVarTotString();
		ArrayList<String> titles = htSubVarx.get("title");
		int idSubVar = 1 + titles.indexOf(titlex);
		return idSubVar;
	}

	public static Hashtable<String, ArrayList<String>> domString(int idDomain) {
		Hashtable<String, ArrayList<String>> hidDom = new Hashtable<String, ArrayList<String>>();
		try {
			String colX;
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/physvar";
			String user = "postgres";
			String passwd = mdp.mdp;
			Connection conn = DriverManager.getConnection(url, user, passwd);
			Statement state = conn.createStatement();
			String query = "SELECT * FROM domain WHERE id = " + idDomain;
			ResultSet resDom = state.executeQuery(query);
			ResultSetMetaData resDo = resDom.getMetaData();
			int jMaxi = resDo.getColumnCount();
			ArrayList<String> colDom = new ArrayList<String>();
			for (int jIni = 1; jIni <= jMaxi; jIni++) {
				colDom.add(resDo.getColumnName(jIni));
			}
			resDom.close();
			state.close();
			Statement statex = conn.createStatement();
			for (int j = 0; j < jMaxi; j++) {
				ArrayList<String> hidDomX = new ArrayList<String>();
				colX = colDom.get(j);
				ResultSet resDomx = statex.executeQuery(query);
				ResultSetMetaData resDox = resDomx.getMetaData();
				while (resDomx.next()) {
					if (resDox.getColumnName(j + 1).equals(colX)) {
						hidDomX.add(resDomx.getString(colX));
					}
				}
				hidDom.put(colX, hidDomX);
				resDomx.close();
			}
			statex.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hidDom;
	}

	public static Hashtable<String, ArrayList<String>> domTotString() {
		Hashtable<String, ArrayList<String>> htDom = new Hashtable<String, ArrayList<String>>();
		try {
			String colX;
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/physvar";
			String user = "postgres";
			String passwd = mdp.mdp;
			Connection conn = DriverManager.getConnection(url, user, passwd);
			Statement state = conn.createStatement();
			String query = "SELECT * FROM domain";
			ResultSet resDom = state.executeQuery(query);
			ResultSetMetaData resDo = resDom.getMetaData();
			int jMaxi = resDo.getColumnCount();
			ArrayList<String> colDom = new ArrayList<String>();
			for (int jIni = 1; jIni <= jMaxi; jIni++) {
				colDom.add(resDo.getColumnName(jIni));
			}
			resDom.close();
			state.close();
			Statement statex = conn.createStatement();
			for (int j = 0; j < jMaxi; j++) {
				ArrayList<String> htDomX = new ArrayList<String>();
				colX = colDom.get(j);
				ResultSet resDomx = statex.executeQuery(query);
				ResultSetMetaData resDox = resDomx.getMetaData();
				while (resDomx.next()) {
					if (resDox.getColumnName(j + 1).equals(colX)) {
						htDomX.add(resDomx.getString(colX));
					}
				}
				htDom.put(colX, htDomX);
				resDomx.close();
			}
			statex.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return htDom;
	}

	public static Hashtable<String, ArrayList<String>> topString(int idTopic) {
		Hashtable<String, ArrayList<String>> hidTop = new Hashtable<String, ArrayList<String>>();
		try {
			String colX;
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/physvar";
			String user = "postgres";
			String passwd = mdp.mdp;
			Connection conn = DriverManager.getConnection(url, user, passwd);
			Statement state = conn.createStatement();
			String query = "SELECT * FROM topic WHERE id = " + idTopic;
			ResultSet resTop = state.executeQuery(query);
			ResultSetMetaData resTo = resTop.getMetaData();
			int jMaxi = resTo.getColumnCount();
			ArrayList<String> colTop = new ArrayList<String>();
			for (int jIni = 1; jIni <= jMaxi; jIni++) {
				colTop.add(resTo.getColumnName(jIni));
			}
			resTop.close();
			state.close();
			Statement statex = conn.createStatement();
			for (int j = 0; j < jMaxi; j++) {
				ArrayList<String> hidTopX = new ArrayList<String>();
				colX = colTop.get(j);
				ResultSet resTopx = statex.executeQuery(query);
				ResultSetMetaData resTox = resTopx.getMetaData();
				while (resTopx.next()) {
					if (resTox.getColumnName(j + 1).equals(colX)) {
						hidTopX.add(resTopx.getString(colX));
					}
				}
				System.out.println(hidTopX);
				hidTop.put(colX, hidTopX);
				resTopx.close();
			}
			statex.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(hidTop);
		return hidTop;
	}

	public static Hashtable<String, ArrayList<String>> topTotString() {
		Hashtable<String, ArrayList<String>> htTop = new Hashtable<String, ArrayList<String>>();
		try {
			String colX;
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/physvar";
			String user = "postgres";
			String passwd = mdp.mdp;
			Connection conn = DriverManager.getConnection(url, user, passwd);
			Statement state = conn.createStatement();
			String query = "SELECT * FROM topic";
			ResultSet resTop = state.executeQuery(query);
			ResultSetMetaData resTo = resTop.getMetaData();
			int jMaxi = resTo.getColumnCount();
			ArrayList<String> colTop = new ArrayList<String>();
			for (int jIni = 1; jIni <= jMaxi; jIni++) {
				colTop.add(resTo.getColumnName(jIni));
			}
			resTop.close();
			state.close();
			Statement statex = conn.createStatement();
			for (int j = 0; j < jMaxi; j++) {
				ArrayList<String> htTopX = new ArrayList<String>();
				colX = colTop.get(j);
				ResultSet resTopx = statex.executeQuery(query);
				ResultSetMetaData resTox = resTopx.getMetaData();
				while (resTopx.next()) {
					if (resTox.getColumnName(j + 1).equals(colX)) {
						htTopX.add(resTopx.getString(colX));
					}
				}
				htTop.put(colX, htTopX);
				resTopx.close();
			}
			statex.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return htTop;
	}

	public static ArrayList<ListBox> topicITString(int iEltx) {
		ArrayList<ListBox> htTopic = new ArrayList<ListBox>();
		try {
			int idX;
			int eltX;
			String titleX;
			String contentX;
			ListBox topiX;
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/physvar";
			String user = "postgres";
			String passwd = mdp.mdp;
			Connection conn = DriverManager.getConnection(url, user, passwd);
			Statement state = conn.createStatement();
			String query = "SELECT * FROM topic";
			ResultSet resTopx = state.executeQuery(query);
			while (resTopx.next()) {
				idX = resTopx.getInt("id");
				eltX = 1;
				titleX = resTopx.getString("title");
				contentX = resTopx.getString("content");
				topiX = new ListBox(idX, eltX, titleX, contentX);
				htTopic.add(topiX);
			}
			resTopx.close();

			state.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return htTopic;
	}

	public static ArrayList<ListBox> characteristicsITString(int eltX) {
		ArrayList<ListBox> htChar = new ArrayList<ListBox>();
		try {
			int idX;
			String titleX;
			String contentX;
			ListBox chariX;
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/physvar";
			String user = "postgres";
			String passwd = mdp.mdp;
			Connection conn = DriverManager.getConnection(url, user, passwd);
			Statement state = conn.createStatement();
			String query = "SELECT * FROM characteristics WHERE elt = " + eltX;
			ResultSet resCharx = state.executeQuery(query);
			while (resCharx.next()) {
				idX = resCharx.getInt("id");
				titleX = resCharx.getString("title");
				contentX = resCharx.getString("content");
				chariX = new ListBox(idX, eltX, titleX, contentX);
				htChar.add(chariX);
			}
			resCharx.close();

			state.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return htChar;
	}

	public static ArrayList<ListBox> continuityITString(int eltX) {
		ArrayList<ListBox> htCont = new ArrayList<ListBox>();
		try {
			int idX;
			String titleX;
			String contentX;
			ListBox chariX;
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/physvar";
			String user = "postgres";
			String passwd = mdp.mdp;
			Connection conn = DriverManager.getConnection(url, user, passwd);
			Statement state = conn.createStatement();
			String query = "SELECT * FROM continuity WHERE elt = " + eltX;
			ResultSet resContx = state.executeQuery(query);
			while (resContx.next()) {
				idX = resContx.getInt("id");
				titleX = resContx.getString("title");
				contentX = resContx.getString("content");
				chariX = new ListBox(idX, eltX, titleX, contentX);
				htCont.add(chariX);
			}
			resContx.close();

			state.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return htCont;
	}

	public static ArrayList<ListBox> extensivityITString(int eltX) {
		ArrayList<ListBox> htExt = new ArrayList<ListBox>();
		try {
			int idX;
			String titleX;
			String contentX;
			ListBox chariX;
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/physvar";
			String user = "postgres";
			String passwd = mdp.mdp;
			Connection conn = DriverManager.getConnection(url, user, passwd);
			Statement state = conn.createStatement();
			String query = "SELECT * FROM extensivity WHERE elt = " + eltX;
			ResultSet resExtx = state.executeQuery(query);
			while (resExtx.next()) {
				idX = resExtx.getInt("id");
				titleX = resExtx.getString("title");
				contentX = resExtx.getString("content");
				chariX = new ListBox(idX, eltX, titleX, contentX);
				htExt.add(chariX);
			}
			resExtx.close();

			state.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return htExt;
	}

	public static ArrayList<ListBox> dimensionITString(int eltX) {
		ArrayList<ListBox> htDim = new ArrayList<ListBox>();
		try {
			int idX;
			String titleX;
			String contentX;
			ListBox chariX;
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/physvar";
			String user = "postgres";
			String passwd = mdp.mdp;
			Connection conn = DriverManager.getConnection(url, user, passwd);
			Statement state = conn.createStatement();
			String query = "SELECT * FROM dimension WHERE elt = " + eltX;
			ResultSet resDimx = state.executeQuery(query);
			while (resDimx.next()) {
				idX = resDimx.getInt("id");
				titleX = resDimx.getString("title");
				contentX = resDimx.getString("content");
				chariX = new ListBox(idX, eltX, titleX, contentX);
				htDim.add(chariX);
			}
			resDimx.close();

			state.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return htDim;
	}

	public static ArrayList<ListBox> typeITString(int eltX) {
		ArrayList<ListBox> htType = new ArrayList<ListBox>();
		try {
			int idX;
			String titleX;
			String contentX;
			ListBox chariX;
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/physvar";
			String user = "postgres";
			String passwd = mdp.mdp;
			Connection conn = DriverManager.getConnection(url, user, passwd);
			Statement state = conn.createStatement();
			String query = "SELECT * FROM typeelt WHERE elt = " + eltX;
			ResultSet resTypex = state.executeQuery(query);
			while (resTypex.next()) {
				idX = resTypex.getInt("id");
				titleX = resTypex.getString("title");
				contentX = resTypex.getString("content");
				chariX = new ListBox(idX, eltX, titleX, contentX);
				htType.add(chariX);
			}
			resTypex.close();

			state.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return htType;
	}

	public static Hashtable<String, ArrayList<String>> varString(int idVar) {
		Hashtable<String, ArrayList<String>> hidVar = new Hashtable<String, ArrayList<String>>();
		try {
			String colX;
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/physvar";
			String user = "postgres";
			String passwd = mdp.mdp;
			Connection conn = DriverManager.getConnection(url, user, passwd);
			Statement state = conn.createStatement();
			String query = "SELECT * FROM variables WHERE id = " + idVar;
			ResultSet resVar = state.executeQuery(query);
			ResultSetMetaData resTo = resVar.getMetaData();
			int jMaxi = resTo.getColumnCount();
			ArrayList<String> colVar = new ArrayList<String>();
			for (int jIni = 1; jIni <= jMaxi; jIni++) {
				colVar.add(resTo.getColumnName(jIni));
			}
			resVar.close();
			state.close();
			Statement statex = conn.createStatement();
			for (int j = 0; j < jMaxi; j++) {
				ArrayList<String> hidVarX = new ArrayList<String>();
				colX = colVar.get(j);
				ResultSet resVarx = statex.executeQuery(query);
				ResultSetMetaData resVax = resVarx.getMetaData();
				while (resVarx.next()) {
					if (resVax.getColumnName(j + 1).equals(colX)) {
						hidVarX.add(resVarx.getString(colX));
					}
				}
				hidVar.put(colX, hidVarX);
				resVarx.close();
			}
			statex.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hidVar;
	}

	public static Hashtable<String, ArrayList<String>> varTotString() {
		Hashtable<String, ArrayList<String>> htVar = new Hashtable<String, ArrayList<String>>();
		try {
			String colX;
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/physvar";
			String user = "postgres";
			String passwd = mdp.mdp;
			Connection conn = DriverManager.getConnection(url, user, passwd);
			Statement state = conn.createStatement();
			String query = "SELECT * FROM variables";
			ResultSet resVar = state.executeQuery(query);
			ResultSetMetaData resVa = resVar.getMetaData();
			int jMaxi = resVa.getColumnCount();
			ArrayList<String> colVar = new ArrayList<String>();
			for (int jIni = 1; jIni <= jMaxi; jIni++) {
				colVar.add(resVa.getColumnName(jIni));
			}
			resVar.close();
			state.close();
			Statement statex = conn.createStatement();
			for (int j = 0; j < jMaxi; j++) {
				ArrayList<String> htVarX = new ArrayList<String>();
				colX = colVar.get(j);
				ResultSet resVarx = statex.executeQuery(query);
				ResultSetMetaData resVax = resVarx.getMetaData();
				while (resVarx.next()) {
					if (resVax.getColumnName(j + 1).equals(colX)) {
						htVarX.add(resVarx.getString(colX));
					}
				}
				htVar.put(colX, htVarX);
				resVarx.close();
			}
			statex.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return htVar;
	}

	public static Hashtable<String, ArrayList<String>> subVarTotString() {
		Hashtable<String, ArrayList<String>> htSubVar = new Hashtable<String, ArrayList<String>>();
		try {
			String colX;
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/physvar";
			String user = "postgres";
			String passwd = mdp.mdp;
			Connection conn = DriverManager.getConnection(url, user, passwd);
			Statement state = conn.createStatement();
			String query = "SELECT * FROM subvariables";
			ResultSet resSubVar = state.executeQuery(query);
			ResultSetMetaData resSubVa = resSubVar.getMetaData();
			int jMaxi = resSubVa.getColumnCount();
			ArrayList<String> colSubVar = new ArrayList<String>();
			for (int jIni = 1; jIni <= jMaxi; jIni++) {
				colSubVar.add(resSubVa.getColumnName(jIni));
			}
			resSubVar.close();
			state.close();
			Statement statex = conn.createStatement();
			for (int j = 0; j < jMaxi; j++) {
				ArrayList<String> htSubVarX = new ArrayList<String>();
				colX = colSubVar.get(j);
				ResultSet resSubVarx = statex.executeQuery(query);
				ResultSetMetaData resSubVax = resSubVarx.getMetaData();
				while (resSubVarx.next()) {
					if (resSubVax.getColumnName(j + 1).equals(colX)) {
						htSubVarX.add(resSubVarx.getString(colX));
					}
				}
				htSubVar.put(colX, htSubVarX);
				resSubVarx.close();
			}
			statex.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return htSubVar;
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
			query.add("SELECT * FROM level WHERE id = " + idLev);
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

	public static void saveDomain(int idMaxPlus, int idcadre, int idtype, String titleStrx, String contentStrx) {
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/physvar";
			String user = "postgres";
			String passwd = mdp.mdp;
			Connection conn = DriverManager.getConnection(url, user, passwd);
			Statement state = conn.createStatement();
			String querySaveDomain = "INSERT INTO domain (id, idcadre, idtype, title, content) VALUES (" + idMaxPlus
					+ ", " + idcadre + ", " + idtype + ", '" + titleStrx + "', '" + contentStrx + "')";
			int resSDomain = state.executeUpdate(querySaveDomain);
			System.out.println(resSDomain);
			state.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void saveTopic(int idMaxPlus, int idDomain, String titleStr, String arrVarStr, String contentStr,
			int idType) {
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/physvar";
			String user = "postgres";
			String passwd = mdp.mdp;
			Connection conn = DriverManager.getConnection(url, user, passwd);
			Statement state = conn.createStatement();
			String querySaveTopic = "INSERT INTO topic (id, iddomain, title, arrvar, content, idtype) VALUES ("
					+ idMaxPlus + ", " + idDomain + ", '" + titleStr + "', '" + arrVarStr + "', '" + contentStr + "', "
					+ idType + ")";
			int resSTopic = state.executeUpdate(querySaveTopic);
			System.out.println(resSTopic);
			state.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void saveVariable(int idMaxPlus, int dim, int idextensivity, int idcontinuity, int idcharacteristics,
			String titleStr, String contentStr, int idlevel, String arrTypeStr) {
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/physvar";
			String user = "postgres";
			String passwd = mdp.mdp;
			Connection conn = DriverManager.getConnection(url, user, passwd);
			Statement state = conn.createStatement();
			String querySaveTopic = "INSERT INTO variables (id, dim, idextensivity, idcontinuity, idcharacteristics, title, content, idlevel, arrtype) VALUES ("
					+ idMaxPlus + ", " + dim + ", " + idextensivity + ", " + idcontinuity + "," + idcharacteristics
					+ ", '" + titleStr + "', '" + contentStr + "', " + idlevel + ", '" + arrTypeStr + "')";
			int resSVariable = state.executeUpdate(querySaveTopic);
			System.out.println(resSVariable);
			state.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
