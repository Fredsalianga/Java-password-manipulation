# Java-password-manipulation
**This is a modified Java hash password manipulation from @David Levine** 
### My Version is a modified version done by me @Frederico Salianga

>In this code I have 4 classes alongside 1 interface the key reason for that is to have the ability to manipulate and reset password for multiple users.


### EcryptedPassword.java Class 
> This part helps with encrypting password for each user.



###ClearPasswordClassTest
> This step helps with testing the password reset with JUnit @Test

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ClearPasswordTest {

	@Test
	void test() {
		PasswordPair test = new ClearPassword("Fred", "Winter123&");
		
		
		assertTrue(test.Verify("Winter123&"));

	}
	
	@Test
	void testSetGeorge() {
		PasswordPair one = new ClearPassword("George", "Washington");
		 assertTrue(one.Verify("Washington"));
		  one.setPW("Washington", "Bush");
		  assertFalse(one.Verify("Washington"));
		  assertTrue(one.Verify("Bush"));
	}
	

}

### ClearPassword.Java

>Password reset


/**
 * 
 */

### ManipulatePassword.Java

>Password Manipulation for users

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JFileChooser;

/**
 * A program to manipulate passwords
 * 
 * @author david levine\
 * @version 24 September 2022
 *
 */
public class ManipulatePasswords {

	private static Scanner sc; // One Scanner for the class
	private static PasswordPair[] pwList; // The complete list of login-names/passwords
	private static int pwCount; // The number of login-names/password pairs

	public static void main(String[] args) throws FileNotFoundException {

		final JFileChooser jfc = new JFileChooser();
		int returnVal = jfc.showOpenDialog(null);
		if (returnVal != JFileChooser.APPROVE_OPTION) {
			System.out.println("No password file to manipulate.");
			System.exit(0); // Stop, but without an error status
		}

		Scanner input = new Scanner(jfc.getSelectedFile());
		pwList = new PasswordPair[100]; // Artificial limit - good enough for lab
		pwCount = 0;
		while (input.hasNextLine()) {
			String pair = input.nextLine(); // read the pair from the file
			pair = pair.substring(1, pair.length() - 1); // strip off the first and last characters
			String[] pieces = pair.split(","); // split the line at the comma
			pwList[pwCount] = createPasswordEntry(pieces); // make the next password pair
			pwCount++;
		}

//		for (int i=0; i<pwCount; i++) {
//			System.out.println(pwList[i]);
//		}

		sc = new Scanner(System.in);
		int cmd = 0;
		while (cmd != 4) {
			printMenu();
			System.out.print("Which command (1-4)? ");
			cmd = sc.nextInt();
			sc.nextLine(); // scan off the newline
			switch (cmd) {
			case 1:
				handleNewUser();
				break;
			case 2:
				handleVerifyPW();
				break;
			case 3:
				handleChangePW();
				break;
			case 4:
				break;
			default:
				System.out.println("Unrecognized command number.  Try again.");
			}
		}

		System.out.println("Saving password file.");
		returnVal = jfc.showSaveDialog(jfc);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			// Open the file to be written
			// (we should check whether to overwrite if it exists, but that's for another
			// day)
			PrintWriter output = new PrintWriter(jfc.getSelectedFile());

			// Write the data that you want
			for (int i = 0; i < pwCount; i++) {
				output.println(pwList[i]);
			}

			// Close the PrintWriter or the file won't exist on disk
			output.close();
		} else {
			System.out.println("Password file not saved.");
		}
	}
	
	/**
	 * Create a password entry 
	 * @param pieces - The data from which to create the entry.  
	 */
	private static PasswordPair createPasswordEntry(String[] pieces) {
		return new ClearPassword(pieces[0], pieces[1]);
	}

	/**
	 * Print the menu of commands
	 */
	private static void printMenu() {
		System.out.println("1. Create new user");
		System.out.println("2. Test user password");
		System.out.println("3. Change user password");
		System.out.println("4. Quit");
		System.out.println();
	}

	/**
	 * Perform the tasks to add a new user/password pair
	 */
	private static void handleNewUser() {
		System.out.print("What is the new user's login name? ");
		String name = sc.nextLine();
		if (findPair(name) == null) {
			System.out.print("What is the new user's password? ");
			String pw = sc.nextLine();
			pwList[pwCount] = new ClearPassword(name, pw); // make the next password pair
			pwCount++;
			System.out.println("User added.");
		} else {
			System.out.println("User already exists.");
		}
	}

	/**
	 * Perform the tasks to verify a password
	 */
	private static void handleVerifyPW() {
		System.out.print("What is the user's login name? ");
		String name = sc.nextLine();
		PasswordPair pair = findPair(name);
		if (pair == null) {
			System.out.println("No such user.");
		} else {
			System.out.print("What is the password to check? ");
			String pw = sc.nextLine();
			if (pair.Verify(pw)) {
				System.out.println("Password checks out.");
			} else {
				System.out.println("Incorrect password.");
			}
		}
	}

	/**
	 * Perform the tasks to change the password for a user
	 */
	private static void handleChangePW() {
		System.out.print("What is the user's login name? ");
		String name = sc.nextLine();
		PasswordPair pair = findPair(name);
		if (pair == null) {
			System.out.println("No such user.");
		} else {
			System.out.print("What is the current password? ");
			String currPw = sc.nextLine();
			if (pair.Verify(currPw)) {
				System.out.print("What is the new password? ");
				String newPw = sc.nextLine();
				pair.setPW(currPw, newPw);
			} else {
				System.out.println("Incorrect password.");
			}
		}

	}

	/**
	 * Return the user associated with the given name
	 * 
	 * @param name the name to search for
	 * @return the password pair whose name matches <code>name</code> if such a pair
	 *         exists and <code>null</code> otherwise
	 */
	private static PasswordPair findPair(String name) {
		for (int i = 0; i < pwCount; i++) {
			if (pwList[i].getUsername().equals(name)) {
				return pwList[i];
			}
		}
		return null;
	}

}

###PasswordPair.Java(Interface)


/**Public
 * 
 */
/**
 * 
 */
public interface PasswordPair {

	boolean Verify(String Password);

	/**
	 * @return
	 */
	String getUsername();

	/**
	 * @param username
	 */
	void setUsername(String username);

	/**
	 * @return
	 */
	String getPassword();

	void setPassword(String password);

	/**
	 * @param Username
	 * @param Password
	 * @return
	 */
	void setPW(String currentPassword, String newPassword);

	String toString();

}

>Users
<Idiot,password>
<TooCommon,123456>
<Insecure,Insecure>
<fredericosalianga,pT34v!2lfG>
<Woodbecker,654321>

