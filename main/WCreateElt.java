package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import listeners.CharacteristicsListener;
import listeners.ContinuityListener;
import listeners.DimListener;
import listeners.ExtensivityListener;
import listeners.TypeListener;
import model.Cadre;
import model.Domain;
import model.Subvariable;
import model.Topic;
import model.Variable;

public class WCreateElt extends JFrame implements ActionListener, ItemListener, ListSelectionListener {

	private static final long serialVersionUID = 1L;
	public static final String[] Elements = { "Domaine", "Thème", "Variables", "Type", "Caractéristiques",
			"Symbolisation" };
	public static final String[] Cadres = { "Cadre", "Domaine", "Thème" };
	private String environmentChanged = "Tous domaines";
	private Border environmentCenter = BorderFactory.createEmptyBorder(10, 10, 10, 10);
	private Border environmentContents = BorderFactory.createEmptyBorder(0, 0, 10, 0);
	private Border environmentList = BorderFactory.createLineBorder(Color.WHITE, 1);
	private Box boxButtons = Box.createVerticalBox();
	private Box boxListHidden = Box.createVerticalBox();
	private Box boxListVisible = Box.createVerticalBox();
	private Box boxListEnvironment = Box.createVerticalBox();
	private static JPanel contents;
	private static JPanel panelSouth;
	private static JPanel panelCenter;
	private JButton buttonAdd;
	private JButton buttonAddAll;
	private JButton buttonRemove;
	private JButton buttonRemoveAll;
	private JLabel labelListVisible;
	private JLabel labelListHidden;
	private JLabel labellistEnvironment;
	private JLabel labelSelectedVisible;
	private JLabel labelSelectedHidden;
	private JLabel labelSelectedVisiblelb;
	private JLabel labelSelectedHiddenlb;
	private JLabel labelSelectedEnvironment;
	private JLabel labelSelectedEnvironmentlb;
	private WindowDialInfo wInfo = new WindowDialInfo();
	public boolean sendData;
	private JList<String> listVisible;
	private JList<String> listHidden;
	public JComboBox<String> listEnvironment = new JComboBox<String>();
	public ArrayList<ArrayList<String>> environments = new ArrayList<ArrayList<String>>();
	public ArrayList<String> cadres = new ArrayList<String>();
	public ArrayList<String> domains = new ArrayList<String>();
	public ArrayList<String> topics = new ArrayList<String>();
	public ArrayList<String> variables = new ArrayList<String>();
	public ArrayList<Cadre> cadresObj = new ArrayList<Cadre>();
	public ArrayList<Domain> domainsObj = new ArrayList<Domain>();
	public ArrayList<Topic> topicsObj = new ArrayList<Topic>();
	public ArrayList<ArrayList<String>> subElements = new ArrayList<ArrayList<String>>();
	public ArrayList<ArrayList<?>> subElementsObj = new ArrayList<ArrayList<?>>();
	public ArrayList<ArrayList<?>> subElts = new ArrayList<ArrayList<?>>();
	public ArrayList<String> subElt = new ArrayList<String>();
	public ArrayList<Integer> subId = new ArrayList<Integer>();
	private DefaultListModel<String> listModelVisible = new DefaultListModel<String>();
	private DefaultListModel<String> listModelHidden = new DefaultListModel<String>();
	private DefaultComboBoxModel<String> listModelEnvironment = new DefaultComboBoxModel<String>();
	public static String[] typeTab[] = { { "Qualitatif", "Empirique", "Evaluation", "Prospectif", "Théorique" },
			{ "Scalaires discrets", "Scalaires continus", "Vecteurs", "Tenseurs, matrices", "Autre" },
			{ "Ordres de grandeur", "Mesures", "Théoriques" } };
	public static String[] extensivityTab[] = { { "Extensif", "Intensif", "Autre" },
			{ "Extensif", "Intensif", "Autre" }, { "Extensif", "Intensif", "Autre" } };
	public static String[] continuityTab[] = {
			{ "Système de N points", "Système ponctuel", "Système linéaire", "Système surfacique", "Système volumique",
					"Système stochastique", "Autre" },
			{ "Continu", "Discret", "Aléatoire", "Discontinu", "Autre" },
			{ "Continu", "Discret", "Aléatoire", "Discontinu" } };
	public static String[] characteristicsTab[] = {
			{ "Système fermé", "Système isolé", "Système ouvert", "Système implicite", "Autre" },
			{ "Ordres de grandeur", "Ordres de variation", "Valeurs précises", "Valeurs estimées", "Autre" },
			{ "Réel", "Complexe", "Entier", "Autre" } };
	public static String[] dimTab[] = {
			{ "Espace-temps", "Système d'états", "Système stationnaire", "Système variable", "Autre" },
			{ "-1 - questions", "0 - état", "1 - solutions - causes directes", "2 - solutions - champ d'évolutions" },
			{ "continues", "discretes", "ondes" }, { "fonctions ondes", "potentiel de jauge" } };
	public static String[] environnementTab = { "cadre", "domain", "topic" };
	public static String[] eltTab = { "domaine", "thème", "variables" };
	int dimOK;
	Object obj;
	String environmentOK = "";
	String contentOK = "";
	String titleOK = "";
	int idEnvironmentOK = 0;
	Array arrTypeOK;
	String typeOK = "";
	Array arrVarOK;
	String arrVarStrOK = "";

	public WCreateElt(int iEltx, final int idM, int levelElt) {
		cadres.add("Tous cadres");
		environments.add(cadres);
		domains.add("Tous domaines");
		environments.add(domains);
		topics.add("Tous thèmes");
		environments.add(topics);
		subElements.add(cadres);
		subElements.add(domains);
		subElements.add(topics);
		subElements.add(variables);
		subElementsObj.add(Connect.initDomain());
		subElementsObj.add(Connect.initTopic());
		subElementsObj.add(Connect.initVariables());
		subElts.add(subId);
		subElts.add(subElt);
		String envt = environnementTab[iEltx];
		String elt = eltTab[iEltx];
		JPanel panInt = new JPanel();
		panInt.setSize(1400, 360);
		// Type
		JPanel panType = new JPanel();
		panType.setPreferredSize(new Dimension(420, 160));
		panType.setBorder(BorderFactory.createTitledBorder("Type de " + elt));
		JComboBox<String> type = new JComboBox<String>();
		TypeListener typeListener = new TypeListener();
		for (int iTy = 0; iTy < typeTab[iEltx].length; iTy++) {
			type.addItem(typeTab[iEltx][iTy]);
		}
		type.addActionListener(typeListener);
		JLabel typeLabel = new JLabel("Type : ");
		panType.add(typeLabel);
		panType.add(type);
		// Extensivity
		JPanel panExtensivity = new JPanel();
		panExtensivity.setPreferredSize(new Dimension(220, 60));
		panExtensivity.setBorder(BorderFactory.createTitledBorder("Extensivité de " + elt));
		JComboBox<String> extensivity = new JComboBox<String>();
		ExtensivityListener extensivityListener = new ExtensivityListener();
		for (int iTy = 0; iTy < extensivityTab[iEltx].length; iTy++) {
			extensivity.addItem(extensivityTab[iEltx][iTy]);
		}
		extensivity.addActionListener(extensivityListener);
		JLabel extensivityLabel = new JLabel("Extensivité : ");
		panType.add(extensivityLabel);
		panType.add(extensivity);
		// Continuity
		JPanel panContinuity = new JPanel();
		panContinuity.setPreferredSize(new Dimension(220, 60));
		panContinuity.setBorder(BorderFactory.createTitledBorder("Continuité de " + elt));
		JComboBox<String> continuity = new JComboBox<String>();
		ContinuityListener continuityListener = new ContinuityListener();
		for (int iTy = 0; iTy < continuityTab[iEltx].length; iTy++) {
			continuity.addItem(continuityTab[iEltx][iTy]);
		}
		continuity.addActionListener(continuityListener);
		JLabel continuityLabel = new JLabel("Continuité : ");
		panType.add(continuityLabel);
		panType.add(continuity);
		// Characteristics
		JPanel panCharacteristics = new JPanel();
		panCharacteristics.setPreferredSize(new Dimension(220, 60));
		panCharacteristics.setBorder(BorderFactory.createTitledBorder("Caracteristiques de " + elt));
		JComboBox<String> characteristics = new JComboBox<String>();
		CharacteristicsListener characteristicsListener = new CharacteristicsListener();
		for (int iTy = 0; iTy < characteristicsTab[iEltx].length; iTy++) {
			characteristics.addItem(characteristicsTab[iEltx][iTy]);
		}
		characteristics.addActionListener(characteristicsListener);
		JLabel characteristicsLabel = new JLabel("Caracteristiques : ");
		panType.add(characteristicsLabel);
		panType.add(characteristics);
		// Dimension
		JPanel panDim = new JPanel();
		panDim.setBorder(BorderFactory.createTitledBorder("Dimension de " + elt));
		panDim.setPreferredSize(new Dimension(440, 90));
		ButtonGroup group = new ButtonGroup();
		JRadioButton dimOption[] = new JRadioButton[12];
		ButtonGroup bg = new ButtonGroup();
		DimListener dimListener = new DimListener();
		for (int iRb = 0; iRb < dimTab[iEltx].length; iRb++) {
			dimOption[iRb] = new JRadioButton(dimTab[iEltx][iRb]);
			dimOption[iRb].addActionListener(dimListener);
			group.add(dimOption[iRb]);
			bg.add(dimOption[iRb]);
			panDim.add(dimOption[iRb]);
		}
		dimOption[0].setSelected(true);
		JPanel panTitle = new JPanel();
		panTitle.setPreferredSize(new Dimension(520, 60));
		panTitle.setBorder(BorderFactory.createTitledBorder("Titre de " + elt));
		JLabel titleLabel = new JLabel("Titre : ");
		JTextField titleJ = new JTextField("");
		titleJ.setPreferredSize(new Dimension(290, 25));
		panTitle.add(titleLabel);
		panTitle.add(titleJ);
		JPanel content = new JPanel();
		content.add(panType);
		content.add(panDim);
		content.add(panTitle);
		JPanel panContentElt = new JPanel();
		panContentElt.setPreferredSize(new Dimension(520, 230));
		panContentElt.setBorder(BorderFactory.createTitledBorder("Contenu de " + elt));
		JLabel contentEltLabel = new JLabel("Contenu : ");
		JTextField contentEltJ = new JTextField("");
		contentEltJ.setPreferredSize(new Dimension(490, 150));
		panContentElt.add(contentEltLabel);
		panContentElt.add(contentEltJ);
		JPanel contentElt = new JPanel();
		contentElt.add(panContentElt);
		JPanel control = new JPanel();
		JButton okBouton = new JButton("Enregistrer");
		okBouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String titlex = getTitle();
				String contentx = getContent();
				WindowDialInfo wInfo = new WindowDialInfo(getEnvironment(iEltx), (String) type.getSelectedItem(),
						getDim(), titlex, contentx);
				wInfo.setVisible(false);
				switch (iEltx) {
				case 0:
					int idCadx = listEnvironment.getSelectedIndex();
					int idTypeDom = type.getSelectedIndex();
					int idXDom = idM + 1;
					ArrayList<Domain> listDom = new ArrayList<Domain>();
					if (titlex.matches("^[0-9.]+$") && idXDom > 0) {
						listDom.add(new Domain(idXDom, idCadx, idTypeDom, titlex, contentx));
						Connect.saveDomain(idXDom, idCadx, idTypeDom, titlex, contentx);
					}
					break;
				case 1:
					int idDomx = listEnvironment.getSelectedIndex();
					int idTypeTop = type.getSelectedIndex();
					ArrayList<Integer> arrVarx = new ArrayList<Integer>();
					int sizex = listModelVisible.getSize();
					for (int i = 0; i < sizex; i++) {
						arrVarx.add(Connect.getIdByTitleVar(listModelVisible.elementAt(i)));
					}
					int idXTop = idM + 1;
					ArrayList<Topic> listTop = new ArrayList<Topic>();
					if (titlex.matches("^[0-9.]+$") && idXTop > 0) {
						listTop.add(new Topic(idXTop, idDomx, titlex, (Array) arrVarx, contentx, idTypeTop));
						Connect.saveTopic(idXTop, idDomx, titlex, (Array) arrVarx, contentx, idTypeTop);
					}
					break;
				case 2:
					int idLevel = listEnvironment.getSelectedIndex();
					int idExtensivity = extensivity.getSelectedIndex();
					int idContinuity = continuity.getSelectedIndex();
					int idCharacteristics = characteristics.getSelectedIndex();
					int typeIni = type.getSelectedIndex();
					ArrayList<Integer> arrType = new ArrayList<Integer>();
					arrType.add(typeIni);
					int dimInt = 1; // (int) getDim();
					int idXVar = idM + 1;
					ArrayList<Variable> listVar = new ArrayList<Variable>();
					if (titlex.matches("^[0-9.]+$") && idXVar > 0) {
						listVar.add(new Variable(idXVar, dimInt, idExtensivity, idContinuity, idCharacteristics, titlex,
								contentx, idLevel, (Array) arrType));
						Connect.saveVariable(idXVar, dimInt, idExtensivity, idContinuity, idCharacteristics, titlex,
								contentx, idLevel, (Array) arrType);
					}
					break;
				default:
					break;
				}
			}

			public String getDim() {
				int iRbSel = 0;
				while (iRbSel < dimTab.length) {
					if (dimOption[iRbSel].isSelected()) {
						return dimOption[iRbSel].getText();
					}
					iRbSel++;
				}
				dimOK = iRbSel - 1;
				return dimOption[0].getText();
			}

			public String getEnvironment(int iEl) {
				idEnvironmentOK = listEnvironment.getSelectedIndex();
				environmentOK = listEnvironment.getItemAt(idEnvironmentOK);
				return (environmentOK.equals(null)) ? "Domaine" : environmentOK;
			}

			public String getTitle() {
				titleOK = titleJ.getText();
				return (titleOK.equals(null)) ? "Titre " : titleOK;
			}

			public String getContent() {
				contentOK = contentEltJ.getText();
				return (contentOK.equals(null)) ? "Contenu " : contentOK;
			}
		});

		JButton cancelBouton = new JButton("Annuler");
		cancelBouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switch (levelElt % 3) {
				case 2:
					WCreation(iEltx, levelElt - 1);
					break;
				case 3:
					WCreation(iEltx, levelElt - 1);
					break;
				default:
					WCreation(iEltx, 1);
					break;
				}
			}
		});

		String labelButton = "Niveau ";
		switch (levelElt % 3) {
		case 1:
			labelButton += "Théories et formulations";
			break;
		case 2:
			labelButton += "Résultats et analyses";
			break;
		default:
			labelButton += "Objets et variables étudiés";
			break;
		}
		JButton levelSupButton = new JButton(labelButton);
		levelSupButton.setVisible(false);
		if (iEltx == 1) {
			levelSupButton.setVisible(true);
			levelSupButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					WCreation(iEltx, levelElt + 1);
				}
			});
		}
		control.add(okBouton);
		control.add(cancelBouton);
		control.add(levelSupButton);
		panInt.add(content, BorderLayout.NORTH);
		panInt.add(contentElt, BorderLayout.CENTER);
		panInt.add(control, BorderLayout.WEST);
		panInt.setVisible(true);
		subElt.add(0, "Tous les sous-éléments");
		switch (iEltx) {
		case 0:
			ArrayList<Topic> topics = Connect.initTopic();
			if (topics.size() > 0) {
				for (Iterator<Topic> iter = topics.iterator(); iter.hasNext();) {
					Topic t = iter.next();
					subElt.add(t.getId(), t.getTitle());
				}
			}
			break;
		case 1:
			ArrayList<Variable> variables = Connect.initVariables();
			if (variables.size() > 0) {
				for (Iterator<Variable> iter = variables.iterator(); iter.hasNext();) {
					Variable v = iter.next();
					subElt.add(v.getId(), v.getTitle());
				}
			}
			break;
		case 2:
			ArrayList<Subvariable> subVariables = Connect.initSubVariables();
			if (subVariables.size() > 0) {
				for (Iterator<Subvariable> iter = subVariables.iterator(); iter.hasNext();) {
					Subvariable s = iter.next();
					subElt.add(s.getId(), s.getTitle());
				}
			}
			break;
		default:
			break;
		}
		JFrame globalW = new JFrame();
		globalW.setSize(1400, 750);
		globalW.add(panInt);
		Collections.sort(environments.get(iEltx));
		contents = new JPanel();
		contents.setBorder(environmentContents);
		contents.setLayout(new BorderLayout());
		setContentPane(contents);
		JLabel lblTitle = new JLabel("Variables", SwingConstants.CENTER);
		contents.add(lblTitle, BorderLayout.NORTH);
		panelCenter = new JPanel();
		panelCenter.setBorder(environmentCenter);
		labelListHidden = new JLabel("Hidden");
		labelListHidden.setAlignmentX(LEFT_ALIGNMENT);
		initHiddenModel();
		listHidden = new JList<>(listModelHidden);
		listHidden.setAlignmentX(LEFT_ALIGNMENT);
		listHidden.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listHidden.setBorder(environmentList);
		JScrollPane scrollListHidden = new JScrollPane(listHidden);
		scrollListHidden.setAlignmentX(LEFT_ALIGNMENT);
		scrollListHidden.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		setSpecificSize(scrollListHidden, new Dimension(200, 300));
		boxListHidden.add(labelListHidden);
		boxListHidden.add(scrollListHidden);
		panelCenter.add(boxListHidden);
		panelCenter.add(Box.createRigidArea(new Dimension(10, 1)));
		buttonAdd = new JButton("Add >");
		buttonAddAll = new JButton("Add All >>");
		buttonRemove = new JButton("< Remove");
		buttonRemoveAll = new JButton("<< Remove All");
		Dimension dimRef = buttonRemoveAll.getPreferredSize();
		setSpecificSize(buttonAdd, dimRef);
		setSpecificSize(buttonAddAll, dimRef);
		setSpecificSize(buttonRemove, dimRef);
		boxButtons.add(buttonAdd);
		boxButtons.add(Box.createRigidArea(new Dimension(1, 5)));
		boxButtons.add(buttonAddAll);
		boxButtons.add(Box.createRigidArea(new Dimension(1, 20)));
		boxButtons.add(buttonRemove);
		boxButtons.add(Box.createRigidArea(new Dimension(1, 5)));
		boxButtons.add(buttonRemoveAll);
		panelCenter.add(boxButtons);
		panelCenter.add(Box.createRigidArea(new Dimension(10, 1)));
		labelListVisible = new JLabel("Visible");
		labelListVisible.setAlignmentX(LEFT_ALIGNMENT);
		listVisible = new JList<>(listModelVisible);
		listVisible.setAlignmentX(LEFT_ALIGNMENT);
		listVisible.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listVisible.setBorder(environmentList);
		JScrollPane scrollListVisible = new JScrollPane(listVisible);
		scrollListVisible.setAlignmentX(LEFT_ALIGNMENT);
		scrollListVisible.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		setSpecificSize(scrollListVisible, new Dimension(200, 300));
		boxListVisible.add(labelListVisible);
		boxListVisible.add(scrollListVisible);
		panelCenter.add(boxListVisible);
		panelCenter.add(Box.createRigidArea(new Dimension(10, 1)));
		labellistEnvironment = new JLabel(envt);
		labellistEnvironment.setAlignmentX(LEFT_ALIGNMENT);
		initEnvironmentModel(iEltx);
		listEnvironment = new JComboBox<>(listModelEnvironment);
		listEnvironment.setAlignmentX(LEFT_ALIGNMENT);
		Dimension dimListView = listEnvironment.getPreferredSize();
		setSpecificSize(listEnvironment, new Dimension(200, dimListView.height));
		boxListEnvironment.add(labellistEnvironment);
		boxListEnvironment.add(listEnvironment);
		panelCenter.add(boxListEnvironment);
		contents.add(panelCenter, BorderLayout.CENTER);
		panelSouth = new JPanel();
		labelSelectedVisiblelb = new JLabel("Selected Visible");
		labelSelectedVisible = new JLabel();
		labelSelectedHiddenlb = new JLabel("Selected Hidden");
		labelSelectedHidden = new JLabel();
		labelSelectedEnvironmentlb = new JLabel("Selected " + envt);
		labelSelectedEnvironment = new JLabel();
		labelListHidden.setAlignmentX(LEFT_ALIGNMENT);
		panelSouth.add(labelSelectedHiddenlb);
		panelSouth.add(labelSelectedHidden);
		panelSouth.add(Box.createRigidArea(new Dimension(100, 1)));
		panelSouth.add(labelSelectedVisiblelb);
		panelSouth.add(labelSelectedVisible);
		panelSouth.add(Box.createRigidArea(new Dimension(100, 1)));
		panelSouth.add(labelSelectedEnvironmentlb);
		panelSouth.add(labelSelectedEnvironment);
		contents.add(panelCenter, BorderLayout.SOUTH);
		buttonAdd.addActionListener(this);
		buttonAddAll.addActionListener(this);
		buttonRemove.addActionListener(this);
		buttonRemoveAll.addActionListener(this);
		listVisible.addListSelectionListener(this);
		listHidden.addListSelectionListener(this);
		listEnvironment.addItemListener(this);
		globalW.setLocationRelativeTo(null);
		globalW.add(contents);
		globalW.setResizable(false);
		globalW.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == buttonAdd) {
			try {
				addItem();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return;
		}
		if (source == buttonAddAll) {
			addAllItems();
			return;
		}
		if (source == buttonRemove) {
			try {
				removeItem();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return;
		}
		if (source == buttonRemoveAll) {
			try {
				removeAllItems();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return;
		}
	}

	public void addItem() throws SQLException {
		int iSelected = listHidden.getSelectedIndex();
		if (iSelected == -1) {
			return;
		}
		String addedItem = listHidden.getSelectedValue();
		listModelHidden.removeElementAt(iSelected);
		displaySelectedItems();
		int size = listModelVisible.getSize();
		if (size == 0) {
			listModelVisible.addElement(addedItem);
			return;
		}
		for (int i = 0; i < size; i++) {
			String item = listModelVisible.elementAt(i);
			int compare = addedItem.compareToIgnoreCase(item);
			if (compare < 0) {
				listModelVisible.add(i, addedItem);
				return;
			}
		}
		listModelVisible.addElement(addedItem);
	}

	public void addAllItems() {
		listModelVisible.clear();
		if (environmentChanged.equals("Tous domaines")) {
			Collections.sort(subElt);
			subElt.forEach((String s) -> {
				listModelVisible.addElement(s);
			});
		}
		listModelHidden.clear();
	}

	private int changeEnvironment(int iElt) {
		int idDomX = 0;
		int iSelected = listEnvironment.getSelectedIndex();
		String domX;
		if (iSelected == -1) {
			return 1;
		}
		Object selectedEnvironment = listEnvironment.getSelectedItem();
		environmentChanged = (String) selectedEnvironment;
		ArrayList<Topic> inTop = Connect.initTopic();
		for (Topic topX : inTop) {
			domX = topX.getTitle();
			idDomX = topX.getId();
			if (environments.get(iElt).contains(domX)) {
				environments.get(iElt).add(domX);
			}
		}
		Collections.sort(environments.get(iElt));
		return idDomX; // iSelected;
	}

	private void displaySelectedItems() throws SQLException {
		String itemName;
		List<Integer> listType;
		int iLevPlus;
		ArrayList<Variable> listVar = Connect.initVariables();
		for (int k = 0; k < listVar.size(); k++) {
			Array Arrx = listVar.get(k).getArrType();
			Integer[] typex = (Integer[]) Arrx.getArray();
			listType = Arrays.asList(typex);
			for (int i = 0; i < typex.length; i++) {
				iLevPlus = listType.get(i);
				if (iLevPlus < listVar.size()) {
					// eltSup = listVar.get(iLevPlus);
				}
			}
		}
		int iSelected = listHidden.getSelectedIndex();
		if (iSelected == -1) {
			labelSelectedHidden.setText("");
		} else {
			itemName = listVisible.getSelectedValue();
			labelSelectedVisible.setText(itemName);
		}
	}

	private void initEnvironmentModel(int iE) {
		Hashtable<String, ArrayList<String>> inCadTot;
		Hashtable<String, ArrayList<String>> inDomTot;
		Hashtable<String, ArrayList<String>> inTopTot;
		Hashtable<String, ArrayList<String>> inVarTot;
		switch (iE) {
		case 0:
			inCadTot = Connect.cadTotString();
			for (String inCadX : inCadTot.get("title")) {
				environments.get(iE).add(inCadX);
				listModelEnvironment.addElement(inCadX);
			}
			break;
		case 1:
			inDomTot = Connect.domTotString();
			for (String inDomX : inDomTot.get("title")) {
				environments.get(iE).add(inDomX);
				listModelEnvironment.addElement(inDomX);
			}
			break;
		case 2:
			inTopTot = Connect.topTotString();
			for (String inTopX : inTopTot.get("title")) {
				environments.get(iE).add(inTopX);
				listModelEnvironment.addElement(inTopX);
			}
			break;
		default:
			inVarTot = Connect.varTotString();
			for (String inVarX : inVarTot.get("title")) {
				environments.get(iE).add(inVarX);
				listModelEnvironment.addElement(inVarX);
			}
			break;
		}
		Collections.sort(environments.get(iE));
		return;
	}

	private void initHiddenModel() {
		listModelHidden.clear();
		Collections.sort(subElt);
		subElt.forEach((String s) -> {
			listModelHidden.addElement(s);
		});
	}

	public void itemStateChanged(ItemEvent e, int iElt) {
		Object source = e.getSource();
		if (source == listEnvironment) {
			changeEnvironment(iElt);
			try {
				displaySelectedItems();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return;
		}
	}

	private void removeItem() throws SQLException {
		int iSelected = listVisible.getSelectedIndex();
		String removedItem = listVisible.getSelectedValue();
		if (iSelected == -1) {
			return;
		}
		listModelVisible.remove(iSelected);
		displaySelectedItems();
		int size = listModelHidden.getSize();
		if (size == 0) {
			listModelHidden.addElement(removedItem);
			return;
		}
		for (int i = 0; i < size; i++) {
			String item = listModelHidden.elementAt(i);
			int compare = removedItem.compareToIgnoreCase(item);
			if (compare < 0) {
				listModelHidden.add(i, removedItem);
				return;
			}
		}
		listModelHidden.addElement(removedItem);
	}

	private void removeAllItems() throws SQLException {
		listModelHidden.clear();
		initHiddenModel();
		listModelVisible.clear();
		displaySelectedItems();
	}

	private void setSpecificSize(JComponent component, Dimension dimension) {
		component.setMinimumSize(dimension);
		component.setPreferredSize(dimension);
		component.setMaximumSize(dimension);
	}

	public void valueChanged(ListSelectionEvent e) {
		Object source = e.getSource();
		if (source == listHidden) {
			try {
				displaySelectedItems();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (source == listVisible) {
			try {
				displaySelectedItems();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return;
	}

	public WindowDialInfo showWindowDial() {
		this.sendData = false;
		this.setVisible(true);
		return this.wInfo;
	}

	public static void WCreation(final int iEt, int level) {
		final int idM = 1 + Connect.idMaxi(iEt);
		WCreateElt wtop = new WCreateElt(iEt, idM, level);
		wtop.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub

	}
}
