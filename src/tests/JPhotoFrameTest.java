package tests;

import static org.junit.Assert.*;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.JList;
import javax.swing.JOptionPane;

import fi.iki.jka.JPhotoCollection;
import fi.iki.jka.JPhotoFrame;
import fi.iki.jka.JPhotoList;
import fi.iki.jka.JPhotoMenu;
import fi.iki.jka.JPhotoShow;
import interfaces.IOptionPane;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class JPhotoFrameTest {		
	@Mock
	private IOptionPane mockOptionPane;
	@Mock
	private JPhotoCollection mockPhotoCollection;
	@Mock
	private JPhotoShow mockPhotoShow;
	
	private JPhotoFrameForTest frame;
	
	private class JPhotoFrameForTest extends JPhotoFrame {

		private static final long serialVersionUID = 1L;

		public JPhotoFrameForTest(String frameName, JPhotoCollection photos)
				throws Exception {
			super(frameName, photos);
		}
		
		@Override
		protected void init(String frameName, JPhotoCollection photos) {
			this.photos = photos;
			this.list = new JPhotoList(photos, 1);
		}
		
		@Override
		protected JPhotoShow getNewJPhotoShow(JPhotoCollection photos, int interval, JList list) {
	    	return mockPhotoShow;
	    }
		
		public void setOptionPane(IOptionPane optionPane) {
	    	this.optionPane = optionPane;
	    }
		
		
	}
			
	@Before
	public void setUp() {
		doReturn("testCollection").when(mockPhotoCollection).getTitle();				
				
		try {
			frame = spy(new JPhotoFrameForTest(null, mockPhotoCollection));
		} catch (Exception e) {
			fail("Failed to initialise frame for testing.");
		}											
	}

	@Test
	public void testViewSlideShowWithoutPhoto() {
		frame.setOptionPane(mockOptionPane);
		doReturn(0)
			.when(mockPhotoCollection)
			.getSize();
		ActionEvent event = new ActionEvent(frame, 0, JPhotoMenu.A_SLIDESHOW);		
		frame.actionPerformed(event);
		verify(mockOptionPane).showMessageDialog(eq(frame), eq("No photos to show!"), eq(JPhotoFrame.APP_NAME), eq(JOptionPane.ERROR_MESSAGE));		
	}
	
	@Test
	public void testViewSlideShowWithPhoto() {
		doReturn(1)
			.when(mockPhotoCollection)
			.getSize();		
		ActionEvent event = new ActionEvent(frame, 0, JPhotoMenu.A_SLIDESHOW);
		frame.actionPerformed(event);
		verify(frame).getNewJPhotoShow(eq(mockPhotoCollection), eq(5000), any(JList.class));
		verify(mockPhotoShow).setVisible(true);
	}
	
	@Test
	public void testPreviewSlideShowWithoutPhoto() {
		frame.setOptionPane(mockOptionPane);
		doReturn(0)
			.when(mockPhotoCollection)
			.getSize();
		ActionEvent event = new ActionEvent(frame, 0, JPhotoMenu.A_PREVIEW);		
		frame.actionPerformed(event);
		verify(mockOptionPane).showMessageDialog(eq(frame), eq("No photos to show!"), eq(JPhotoFrame.APP_NAME), eq(JOptionPane.ERROR_MESSAGE));		
	}
	
	@Test
	public void testPreviewSlideShowWithPhoto() {
		doReturn(1)
			.when(mockPhotoCollection)
			.getSize();		
		ActionEvent event = new ActionEvent(frame, 0, JPhotoMenu.A_PREVIEW);
		frame.actionPerformed(event);
		verify(frame).getNewJPhotoShow(eq(mockPhotoCollection), eq(500), any(JList.class));
		verify(mockPhotoShow).setVisible(true);
	}

}
