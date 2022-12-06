package com.horner.chooser;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.annotation.PostConstruct;
import javax.swing.ImageIcon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;

public class MyTrayIcon extends TrayIcon {

	private static Logger LOG = LoggerFactory.getLogger(MyTrayIcon.class);

	private static final String IMAGE_PATH = "images/cat-black-icon-16x16.png";
	private static final String TOOLTIP = "Chooser";

	private PopupMenu popup;
	private SystemTray tray;
	private ApplicationContext context;

	public MyTrayIcon(ApplicationContext context, int serverPort) {

		super(createImage(IMAGE_PATH, context, serverPort), getTooltip(serverPort));

		popup = new PopupMenu();
		tray = SystemTray.getSystemTray();
		try {
			setup();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.context = context;
	}

//    @PostConstruct
//    private void setup() throws AWTException{
//        // popup.add(itemAbout);
//        // here add the items to your popup menu. These extend MenuItem
//        // popup.addSeparator();
//        setPopupMenu(popup);
//        tray.add(this);
//    }
	@PostConstruct
	private void setup() throws AWTException {
		// Create a pop-up menu components
		MenuItem exitItem = new MenuItem("Exit");
		popup.add(exitItem);
		exitItem.addActionListener(new ActionListener() {
			private Logger LOG = LoggerFactory.getLogger(ChooserApplication.class);
			public void actionPerformed(ActionEvent e) {
				final int exitCode = 0;
				ExitCodeGenerator exitCodeGenerator = new ExitCodeGenerator() {

					@Override
					public int getExitCode() {
						return exitCode;
					}

				};

				tray.remove(MyTrayIcon.this);
				LOG.info("Exiting");
				SpringApplication.exit(context, exitCodeGenerator);
			}
		});
		// popup.addSeparator();
		setPopupMenu(popup);
		tray.add(this);
	}

	protected static Image createImage(String path, ApplicationContext context, int serverPort) {

		try {
			Resource r = context.getResource(IMAGE_PATH);
			URL imageURL = r.getURL();
			if (imageURL == null) {
				System.err.println("Failed Creating Image. Resource not found: " + path);
				return null;
			} else {
				return new ImageIcon(imageURL, getTooltip(serverPort)).getImage();
			}
		} catch (Exception e) {
			System.err.println("Failed Creating Image. Resource not found: " + path);
			e.printStackTrace();
			return null;
		}
	}
	
	protected static String getTooltip(int serverPort) {
		return "Chooser: "+serverPort;
	}
}
