package edu.umd.cs.guitar.ripper.test;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import edu.umd.cs.guitar.ripper.SWTRipper;
import edu.umd.cs.guitar.ripper.SWTRipperConfiguration;
import edu.umd.cs.guitar.ripper.SWTRipperRunner;

public class IntegrationTest {

	public static void ripAndDiff(String filename) {
		SWTRipperConfiguration config = new SWTRipperConfiguration();
		config.setGuiFile("testoutput.xml");

		String fullName = "edu.umd.cs.guitar.ripper.test.aut." + filename;
		config.setMainClass(fullName);
		
		final SWTRipper swtRipper = new SWTRipper(config, Thread.currentThread());
		new SWTRipperRunner(swtRipper).start();
				
		String name = "expected/" + filename + ".xml";
		assertEquals(-1, diff(name, config.getGuiFile()));

	}

	/**
	 * Reads and compares two files for any differences, returns the line number
	 * if different, -1 if the files are the same.
	 */
	private static int diff(String expectedFile, String actualFile) {
		File expected = new File(expectedFile);
		File actual = new File(actualFile);
		BufferedReader expectedReader = null;
		BufferedReader actualReader = null;

		try {
			expectedReader = new BufferedReader(new FileReader(expected));
			actualReader = new BufferedReader(new FileReader(actual));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		int lineNumber = 1;
		String s1 = new String();
		String s2 = new String();

		boolean equal = true;
		try {
			while (expectedReader.ready()) {
				s1 = expectedReader.readLine();
				if (actualReader.ready()) {
					s2 = actualReader.readLine();
				} else {
					equal = false;
					break;
				}
				if (!s1.equals(s2)) {
					System.err.println("Failed at line " + lineNumber);
					System.err.println("Expected: " + s1);
					System.err.println("Actual: " + s2);
					equal = false;
					break;
				}
				lineNumber++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (equal) {
			return -1;
		} else {
			return lineNumber;
		}

	}

	@Test
	public void testBasicApp() {
		ripAndDiff("SWTBasicApp");
	}

	@Test
	public void testButtonApp() {
		ripAndDiff("SWTButtonApp");
	}

	@Test
	public void testCheckButtonApp() {
		ripAndDiff("SWTCheckButtonApp");
	}

	@Test
	public void testHelloWorld() {
		ripAndDiff("SWTHelloWorld");
	}

	@Test
	public void testLabelApp() {
		ripAndDiff("SWTLabelApp");
	}

	@Test
	public void testListApp() {
		ripAndDiff("SWTListApp");
	}

	@Test
	public void testMenuBarApp() {
		ripAndDiff("SWTMenuBarApp");
	}

	@Test
	public void testTwoWindowsApp() {
		ripAndDiff("SWTTwoWindowsApp");
	}

	@Test
	public void testWindowApp() {
		ripAndDiff("SWTWindowApp");
	}

}
