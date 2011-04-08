package edu.umd.cs.guitar.ripper.test.aut;

import org.eclipse.swt.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;

//Code based on example found at http://dev.eclipse.org/viewcvs/viewvc.cgi/org.eclipse.swt.snippets/src/org/eclipse/swt/snippets/Snippet143.java?view=co

public class SWTTrayApp {

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		Image image = new Image(display, 16, 16);
		final Tray tray = display.getSystemTray();
		if (tray == null) {
			System.out.println("The system tray is not available");
		} else {
			final TrayItem item = new TrayItem(tray, SWT.NONE);
			item.setToolTipText("SWT TrayItem");
			item.addListener(SWT.Selection, new Listener() {
				public void handleEvent(Event event) {
					System.out.println("selection");
				}
			});
			
			item.setImage(image);
		}
		shell.setBounds(50, 50, 300, 200);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		image.dispose();
		display.dispose();
	}
}