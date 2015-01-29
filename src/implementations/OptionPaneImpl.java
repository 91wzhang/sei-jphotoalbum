package implementations;

import java.awt.Component;
import javax.swing.JOptionPane;
import interfaces.OptionPane;

/**
 * @author Dandan Lyu, Wei Zhang
 *
 */
public class OptionPaneImpl implements OptionPane {

	/**
	 * 
	 */
	public OptionPaneImpl() {
		
	}

	/* (non-Javadoc)
	 * @see interfaces.OptionPane#showMessageDialog(java.awt.Component, java.lang.Object, java.lang.String, int)
	 */
	@Override
	public void showMessageDialog(Component parentComponent, Object message,
			String title, int messageType) {
		JOptionPane.showMessageDialog(parentComponent, message, title, messageType);
	}

}
