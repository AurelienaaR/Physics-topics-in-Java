package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CharacteristicsListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
			System.out.println(e.getSource());
	}
}
