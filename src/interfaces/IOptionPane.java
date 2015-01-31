package interfaces;

import java.awt.Component;

/**
 * @author Dandan Lyu; Wei Zhang
 *
 */
public interface OptionPane {
	void showMessageDialog(
			Component parentComponent,
            Object message,
            String title,
            int messageType
    );
}
