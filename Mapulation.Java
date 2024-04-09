### Main Class (MakePasswordFile)

> user passowrd making

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;

/**
 * Create a small password file
 * @author Frederico Salianga
 * @version 24 September 2022
 *
 */
public class MakePasswordFile {

	public static void main(String[] args) throws FileNotFoundException {
	
		// Fill an array with four simple pairs
		
		PasswordPair[] pws = new PasswordPair[4];
		pws[0] = new ClearPassword("Idiot","password");
		pws[1] = new ClearPassword("TooCommon","123456");
		pws[2] = new ClearPassword("Insecure","Insecure");
		pws[3] = new ClearPassword("fredericosalianga","pT34v!2lfG");
		
		// Present a dialog for the user to choose the data file to be written
		
		System.out.println("Choose where to store the file.");
		final JFileChooser fc = new JFileChooser();
		int returnVal = fc.showSaveDialog(fc);
		
		// Deal with the cancel button
		
		if (returnVal == JFileChooser.CANCEL_OPTION) {
			System.out.println("Operation cancelled.");
		}
		
		// Deal with the OK button
		
		
		else if (returnVal == JFileChooser.APPROVE_OPTION) {
			
			// Open the file to be written
			//    (we should check whether to overwrite if it exists, but that's for another day)
			PrintWriter output = new PrintWriter(fc.getSelectedFile());
			
			// Write the data that you want
			for (int i=0; i<pws.length; i++) {
				output.println(pws[i]);
			}
			
			
			
			// Close the PrintWriter or the file won't exist on disk
			output.close();
			System.out.println("Password file written.");
		}

		
		
	}


}
