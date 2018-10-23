package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Scanner;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class IO {
	
	public static File open() {

		JFileChooser chooser = new JFileChooser();
		int returnVal = chooser.showOpenDialog(null);

		if(returnVal == JFileChooser.APPROVE_OPTION) {

			File file = chooser.getSelectedFile();

			return file;
		}

		return null;
	}

	public static File open(String filePath) {
		return new File(filePath);
	}

	public static String openAsString() {

		JFileChooser chooser = new JFileChooser();
		int returnVal = chooser.showOpenDialog(null);

		if(returnVal == JFileChooser.APPROVE_OPTION) {

			File file = chooser.getSelectedFile();

			return readAsString(file);
		}

		return null;
	}

	public static String openAsString(String filePath) {
		
		try {
			
			String string = openLocalSourceAsString(filePath);
			
			if(string == null)
				return openURLAsString(filePath);
			
			return string;
		}
		
		catch(Exception exception) {
			
			try {
				
				String string = openLocalSourceAsStringInternally(filePath.substring(filePath.indexOf('/')));
				
				if(string == null)
					return openURLAsString(filePath);
				
				return string;
			}
			
			catch(Exception innerException) {
				return openURLAsString(filePath);
			}
		}
	}

	public static String openLocalSourceAsString(String filePath) throws IOException {

		if(filePath.length() == 0)
			return null;

		if(filePath.charAt(0) == '/')
			filePath = filePath.substring(1);

		return readAsString(filePath);
	}

	public static String openLocalSourceAsStringInternally(String filePath) {

		if(filePath.length() == 0)
			return null;

		if(filePath.charAt(0) == '/')
			filePath = filePath.substring(1);

		return readAsStringInternally(filePath);
	}
	
	private static String readAsString(String filePath) throws IOException {
		
		File file = new File(filePath);
		BufferedReader reader = null;
		
		String openedFile = "";
		
	    reader = new BufferedReader(new FileReader(file));
	    String text = null;

	    while ((text = reader.readLine()) != null)
			openedFile += text + "\n";
	    
	    if(openedFile.length() > 0)
	    	openedFile = openedFile.substring(0, openedFile.length() - 1);
	    
	    reader.close();

		return openedFile;
	}
	
	private static String readAsStringInternally(String filePath) {

		InputStream file = ClassLoader.getSystemResourceAsStream(filePath);

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(file);
		
		String openedFile = "";

		while(scanner.hasNextLine()) {
			
			openedFile += scanner.nextLine();
			
			if(scanner.hasNextLine())
				openedFile += "\n";
		}

		return openedFile;
	}
	
	private static String readAsString(File file) {

		Scanner scanner = null;

		try {
			scanner = new Scanner(file);
		}

		catch (FileNotFoundException e) {
			
		}
		
		String openedFile = "";

		while(scanner.hasNextLine()) {
			
			openedFile += scanner.nextLine();
			
			if(scanner.hasNextLine())
				openedFile += "\n";
		}

		return openedFile;
	}

	public static String openURLAsString(String URL) {
		
		try {
			
			doTrustToCertificates();
			
			URL url = new URL(URL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(connection.getInputStream()));

			StringBuilder stringBuilder = new StringBuilder();
			String line = "";

			boolean firstLine = true;
			
			while(true) {
				
				stringBuilder.append(line);
				
				line = reader.readLine();
				
				if(line != null) {
					
					if(!firstLine)
						stringBuilder.append("\n");
					
					firstLine = false;
				}
				
				else
					break;
			}

			reader.close();

			connection.disconnect();
			
			return stringBuilder.toString();
		}
		
		catch (Exception exception) {
			return null;
		}
	}

	public static void doTrustToCertificates() throws Exception {

		TrustManager[] trustAllCertificates = new TrustManager[]{

				new X509TrustManager() {

					public X509Certificate[] getAcceptedIssuers() {
						return null;
					}

					public void checkServerTrusted(
							X509Certificate[] certs,
							String authType)
									throws CertificateException {

						return;
					}

					public void checkClientTrusted(
							X509Certificate[] certs,
							String authType)
									throws CertificateException {

						return;
					}
				}
		};

		SSLContext sslContext = SSLContext.getInstance("SSL");
		sslContext.init(null, trustAllCertificates, new SecureRandom());

		HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());

		HostnameVerifier hostnameVerifier = new HostnameVerifier() {

			public boolean verify(String urlHostName, SSLSession session) {
				return true;
			}
		};

		HttpsURLConnection.setDefaultHostnameVerifier(hostnameVerifier);
	}

	public static ArrayList<String> openAsList() {

		JFileChooser chooser = new JFileChooser();
		int returnVal = chooser.showOpenDialog(null);

		if(returnVal == JFileChooser.APPROVE_OPTION) {

			File file = chooser.getSelectedFile();

			return readAsList(file);
		}

		return null;
	}

	public static ArrayList<String> openAsList(String filePath) {
		
		try {
			
			ArrayList<String> string = openLocalSourceAsList(filePath);
			
			if(string == null)
				return openURLAsList(filePath);
			
			return string;
		}
		
		catch(Exception exception) {
			
			try {
				
				ArrayList<String> string = openLocalSourceAsList(filePath.substring(filePath.indexOf('/')));
				
				if(string == null)
					return openURLAsList(filePath);
				
				return string;
			}
			
			catch(Exception innerException) {
				return openURLAsList(filePath);
			}
		}
	}

	public static ArrayList<String> openLocalSourceAsList(String filePath) {

		if(filePath.length() == 0)
			return null;

		if(filePath.charAt(0) == '/')
			filePath = filePath.substring(1);

		return readAsList(filePath);
	}

	@SuppressWarnings("resource")
	private static ArrayList<String> readAsList(String filePath) {

		InputStream file = ClassLoader.getSystemResourceAsStream(filePath);

		Scanner scanner = new Scanner(file);
		
		ArrayList<String> openedFile = new ArrayList<String>();

		while(scanner.hasNextLine())
			openedFile.add(scanner.nextLine());

		return openedFile;
	}
	
	private static ArrayList<String> readAsList(File file) {

		Scanner scanner = null;

		try {
			scanner = new Scanner(file);
		}

		catch (FileNotFoundException e) {
			
		}
		
		ArrayList<String> openedFile = new ArrayList<String>();

		while(scanner.hasNextLine())
			openedFile.add(scanner.nextLine());

		return openedFile;
	}

	public static ArrayList<String> openURLAsList(String URL) {
		
		String urlContent = openURLAsString(URL);
		int index = 0;
		
		ArrayList<String> list = new ArrayList<String>();
		
		while(urlContent.indexOf('\n', index) > -1) {
			
			list.add(urlContent.substring(index, urlContent.indexOf('\n', index)));
			
			index = urlContent.indexOf('\n', index) + 1;
		}
		
		if(urlContent.length() > 0)
			list.add(urlContent.substring(index));
		
		return list;
	}
	
	public static void save(String file, String filePath) {
		
		ArrayList<String> lines = new ArrayList<String>();
		lines.add(file);
		
		save(lines, filePath);
	}

	public static void save(ArrayList<String> lines, String filePath) {

		PrintWriter writer = null;

		try {
			writer = new PrintWriter(filePath, "UTF-8");
		}

		catch (FileNotFoundException e) {
			
		}

		catch (UnsupportedEncodingException e) {
			
		}

		for(int i = 0; i < lines.size(); i++) {
			
			if(i < lines.size() - 1)
				writer.println(lines.get(i));
			
			else
				writer.print(lines.get(i));
		}

		writer.close();
	}
	
	public static void saveAs(String file) {
		
		ArrayList<String> lines = new ArrayList<String>();
		lines.add(file);
		
		saveAs(lines, "");
	}
	
	public static void saveAs(String file, String fileExtension) {
		
		ArrayList<String> lines = new ArrayList<String>();
		lines.add(file);
		
		saveAs(lines, fileExtension);
	}
	
	public static void saveAs(ArrayList<String> lines) {
		saveAs(lines, "");
	}
	
	public static void saveAs(ArrayList<String> lines, String fileExtension) {

		JFileChooser fc = new JFileChooser();
		PrintWriter outputStream = null;

		int rval = fc.showSaveDialog(fc);

		if(rval == JFileChooser.APPROVE_OPTION) {

			File file = fc.getSelectedFile();

			try {
				
				fileExtension = fileExtension.trim();
				
				if(fileExtension.length() > 0) {
					
					if(file.toString().indexOf('.') == -1)
						file = new File(file + "." + fileExtension);
					
					else if(!file.toString().substring(file.toString().lastIndexOf('.') + 1).equals(fileExtension))
						file = new File(file + "." + fileExtension);
					
					else
						file = new File(file.toString());
				}
				
				else
					file = new File(file.toString());
				
				outputStream = new PrintWriter(new FileOutputStream(file));

				for(int i = 0; i < lines.size(); i++) {
					
					if(i < lines.size() - 1)
						outputStream.println(lines.get(i));
					
					else
						outputStream.print(lines.get(i));
				}
				
				outputStream.close();
			}

			catch(IOException a) {
				JOptionPane.showMessageDialog(null, "There was a problem.");
			}
		}
	}
}