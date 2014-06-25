/* Copyright 2014 Esri

All rights reserved under the copyright laws of the United States
and applicable international laws, treaties, and conventions.

You may freely redistribute and use this sample code, with or
without modification, provided you include the original copyright
notice and use restrictions.

See the use restrictions.*/
package com.esri.client.samples.licensing;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.esri.core.io.UserCredentials;
import com.esri.core.portal.LicenseInfo;
import com.esri.core.portal.Portal;
import com.esri.core.runtime.LicenseResult;
import com.esri.map.JMap;
import com.esri.map.MapOptions;
import com.esri.map.MapOptions.MapType;
import com.esri.runtime.ArcGISRuntime;

/**
 * This sample shows you how to license your application to use Standard level
 * functionality when working in an offline environment.
 * <ol>
 * <li>The first step is to set the Client ID. See http://developers.arcgis.com
 * for details on how to obtain a Client ID.</li>
 * <li>
 * You must then either apply a license code or authenticate using a saved
 * LicenseInfo which was obtained when connecting to an organization account on
 * ArcGIS Online or your Portal for ArcGIS.</li>
 * </ol>
 */
public class StandardOfflineLevelApp {

	private JMap map;

	// default constructor
	public StandardOfflineLevelApp() {
	}

	// ------------------------------------------------------------------------
	// Core functionality
	// ------------------------------------------------------------------------
	private void initLicense() throws Exception {
		// set the Client ID.
		// see http://developers.arcgis.com for details on how to obtain a
		// Client ID.
		ArcGISRuntime.setClientID("BZvWswyDjaWgHxiN");

		// fetch the license code when online.
		// this could be an organization account on ArcGIS Online or your Portal
		// for ArcGIS.
		/*UserCredentials credentials = new UserCredentials();
		credentials.setUserAccount("aitor.calero.carto", "aitor.calero.carto");
		Portal portal = new Portal("http://geocarto.maps.arcgis.com/",
				credentials);*/
		// LicenseInfo licenseInfo = portal.fetchPortalInfo().getLicenseInfo();
		// String licenseInfoJson = licenseInfo.toJson();

		// create LicenseInfo from JSON string created when online
		//String licenseInfoJson1 = "{\"licenseString\":\"example\"}";
		//LicenseInfo licenseInfo = LicenseInfo.fromJson(licenseInfoJson1);
		String licenciaOffLine = new String(
				"LmhvTXFIdXRLVUhMNm5IOWxXZkZrdFhKRC81N3VNaEpBY2EwaGhsN1k4WG1hazJVSUVDb3J3Vzk0VWpWMDRGRGZpKg==");
		// apply the license
		LicenseResult licenseResult = ArcGISRuntime.License
				.setLicense(licenciaOffLine);

		System.out.println("Input license is " + licenseResult);
		// System.out.println(licenseInfoJson);
	}

	/**
	 * Starting point of this application.
	 * 
	 * @param args
	 *            arguments to this application.
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					// instance of this application
					StandardOfflineLevelApp app = new StandardOfflineLevelApp();

					// setup license
					app.initLicense();

					// create the UI, including the map, for the application.
					JFrame appWindow = app.createWindow();
					appWindow.add(app.createUI());
					appWindow.setVisible(true);
				} catch (Exception e) {
					// on any error, display the stack trace.
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Creates and displays the UI, including the map, for this application.
	 */
	public JComponent createUI() throws Exception {

		// application content
		JPanel contentPane = new JPanel(new BorderLayout());

		// map options: topographic map, centered at lat-lon (0,0), zoom level 1
		MapOptions mapOptions = new MapOptions(MapType.TOPO, 0, 0, 1);

		// create the map using MapOptions
		map = new JMap(mapOptions);
		contentPane.add(map);

		return contentPane;
	}

	private JFrame createWindow() {
		JFrame window = new JFrame("Licensing - Standard Offline");
		window.setSize(1000, 600);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(new BorderLayout());
		window.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent windowEvent) {
				super.windowClosing(windowEvent);
				map.dispose();
			}
		});
		return window;
	}
}