package tests;

import static org.junit.Assert.*;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.JList;

import fi.iki.jka.JPhotoCollection;
import fi.iki.jka.JPhotoFrame;
import fi.iki.jka.JPhotoList;
import fi.iki.jka.JPhotoMenu;
import fi.iki.jka.JPhotoShow;
import interfaces.OptionPane;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class JPhotoFrameTest {		
	@Mock
	private OptionPane mockOptionPane;
	@Mock
	private JPhotoCollection mockPhotoCollection;
	@Mock
	private JPhotoShow mockPhotoShow;
	
	private JPhotoFrame frame;
	
	@Before
	public void setUp() {
		doReturn("testCollection").when(mockPhotoCollection).getTitle();				
				
		try {
			frame = spy(new JPhotoFrame(null, mockPhotoCollection) {
					@Override
					protected void init(String frameName, JPhotoCollection photos) {
						this.photos = photos;
						this.list = new JPhotoList(photos, 1);
					}
			});
		} catch (Exception e) {
			fail("Failed to initiate frame for testing.");
		}						
		
		doReturn(mockPhotoShow)
		.when(frame)
		.getNewJPhotoShow(any(JPhotoCollection.class), any(int.class), any(JList.class));
	}

	@Test
	public void testViewSlideShowWithoutPhoto() {
		frame.setOptionPane(mockOptionPane);
		doReturn(0).when(mockPhotoCollection).getSize();
		ActionEvent event = new ActionEvent(frame, 0, JPhotoMenu.A_SLIDESHOW);		
		frame.actionPerformed(event);
		verify(mockOptionPane).showMessageDialog(any(Component.class), any(Object.class), any(String.class), any(int.class));
		
	}
	
	@Test
	public void testViewSlideShowWithPhoto() {
		doReturn(1).when(mockPhotoCollection).getSize();		
		ActionEvent event = new ActionEvent(frame, 0, JPhotoMenu.A_SLIDESHOW);
		frame.actionPerformed(event);
		verify(mockPhotoShow).setVisible(true);
	}

}
