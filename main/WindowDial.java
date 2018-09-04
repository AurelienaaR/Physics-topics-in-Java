package main;

import javax.swing.JPanel;

public class WindowDial extends JPanel {

	private static final long serialVersionUID = 1L;
	public WindowDialInfo wInfo = new WindowDialInfo();
	public boolean sendData;

	public WindowDialInfo showWindowDial() {
		this.sendData = false;
		this.setVisible(true);
		return this.wInfo;
	}

}
