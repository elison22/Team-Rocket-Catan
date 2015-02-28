package client.base;

import java.util.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * Base class for overlay views
 */
@SuppressWarnings("serial")
public class OverlayView extends PanelView implements IOverlayView
{
	/**
	 * The frame window for the overlays
	 */
	private static JFrame window;
	
	/**
	 * Default glass pane component (displayed when no overlays are visible)
	 */
	private static Component defaultGlassPane;
	
	/**
	 * Stack of overlays that are currently displayed
	 */
	private static Deque<OverlayInfo> overlayStack;

    private String name;
	
	public static void setWindow(JFrame window)
	{
		OverlayView.window = window;
		defaultGlassPane = window.getGlassPane();
		overlayStack = new ArrayDeque<OverlayInfo>();
	}
	
	protected OverlayView(String name)
	{
		super();
        this.name = name;
	}
	
	/**
	 * Displays the overlay. The overlay is displayed on top of any other
	 * overlays that are already visible.
	 */
	public void showModal()
	{
		// Open the new overlay
		JPanel overlayPanel = new JPanel();
		overlayPanel.setLayout(new BorderLayout());
		overlayPanel.setOpaque(false);
		
		// Discard all mouse and keyboard events
		MouseAdapter mouseAdapter = new MouseAdapter() {};
		overlayPanel.addMouseListener(mouseAdapter);
		overlayPanel.addMouseMotionListener(mouseAdapter);
		overlayPanel.addMouseWheelListener(mouseAdapter);
		
		KeyAdapter keyAdapter = new KeyAdapter() {};
		overlayPanel.addKeyListener(keyAdapter);
		
		Dimension winSize = window.getContentPane().getSize();
		Dimension prefSize = this.getPreferredSize();
		
		int widthDiff = (int)(winSize.getWidth() - prefSize.getWidth());
		int heightDiff = (int)(winSize.getHeight() - prefSize.getHeight());
		
		overlayPanel.add(this, BorderLayout.CENTER);
		if(widthDiff / 2 > 0)
		{
			overlayPanel.add(Box.createRigidArea(new Dimension(widthDiff / 2, 0)),
							 BorderLayout.WEST);
			overlayPanel.add(Box.createRigidArea(new Dimension(widthDiff / 2, 0)),
							 BorderLayout.EAST);
		}
		if(heightDiff / 2 > 0)
		{
			overlayPanel.add(Box.createRigidArea(new Dimension(0,
															   heightDiff / 2)),
							 BorderLayout.NORTH);
			overlayPanel.add(Box.createRigidArea(new Dimension(0,
															   heightDiff / 2)),
							 BorderLayout.SOUTH);
		}
		
		if(overlayStack.size() > 0)
		{
			
			// Hide the currently-visible overlay
			overlayStack.peek().getOverlayPanel().setVisible(false);
		}
		
		window.setGlassPane(overlayPanel);
		overlayPanel.setVisible(true);
		overlayStack.push(new OverlayInfo(this, overlayPanel));
        printOverlays("SHOW");
	}
	
	/**
	 * Hides the top-most overlay
	 */
	public void closeModal()
	{
		
		assert overlayStack.size() > 0;
		assert window.getGlassPane() == overlayStack.peek().getOverlayPanel();
		
		if(overlayStack.size() > 0)
		{
			
			overlayStack.pop().getOverlayPanel().setVisible(false);
			
			if(overlayStack.size() > 0)
			{
				
				window.setGlassPane(overlayStack.peek().getOverlayPanel());
				overlayStack.peek().getOverlayPanel().setVisible(true);
			}
			else
			{
				window.setGlassPane(defaultGlassPane);
				window.getGlassPane().setVisible(false);
			}
		}
        printOverlays("CLOSE");
	}
	
	/**
	 * Is the overlay currently showing?
	 * 
	 * @return True if overlay is showing, false otherwise
	 */
	@Override
	public boolean isModalShowing()
	{
		
		for (OverlayInfo info : overlayStack)
		{
			
			if(info.getOverlayView() == this)
				return true;
		}
		
		return false;
	}
	
	public void showDialog(String message) {
		JOptionPane.showMessageDialog(window, message);
	}
	
	private static class OverlayInfo
	{
		private OverlayView overlayView;
		private JPanel overlayPanel;
		
		public OverlayInfo(OverlayView overlayView, JPanel overlayPanel)
		{
			
			setOverlayView(overlayView);
			setOverlayPanel(overlayPanel);
		}
		
		public OverlayView getOverlayView()
		{
			
			return overlayView;
		}
		
		public void setOverlayView(OverlayView overlayView)
		{
			
			this.overlayView = overlayView;
		}
		
		public JPanel getOverlayPanel()
		{
			
			return overlayPanel;
		}
		
		public void setOverlayPanel(JPanel overlayPanel)
		{
			
			this.overlayPanel = overlayPanel;
		}
	}

    private void printOverlays(String type) {
        System.out.println("************** "+type + " **************");
        for (OverlayInfo info : overlayStack) {
            if(info.getOverlayView().isModalShowing())
                System.out.println(info.getOverlayView().name);
        }
        System.out.println("*********************************");
    }

    public String getName() {
        return name;
    }

    public static int getOverlayCount() {
        return overlayStack.size();
    }

    public static void killView(String modelName) {
        OverlayInfo toKill = null;
        for (OverlayInfo info : overlayStack) {
            if(info.getOverlayView().getName().equalsIgnoreCase(modelName)) {
                toKill = info;
                break;
            }
        }
        if (toKill == null) return;
        overlayStack.remove(toKill);
        overlayStack.push(toKill);
        toKill.getOverlayView().closeModal();
    }

}

