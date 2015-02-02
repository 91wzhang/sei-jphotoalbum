package implementations;

import java.awt.Component;
import javax.swing.JOptionPane;
import interfaces.IOptionPane;

/**
 * @author Dandan Lyu, Wei Zhang
 *
 */
public class OptionPaneImpl implements IOptionPane {

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
