import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


//TODO: create a reset user data
public class User {
	String name;
	boolean masculine;
	boolean feminine;
	double chanceMixPatterns;
	int mixPatternsBalance;
	double chanceAccessorize; // this number could potentially change based on how they rate accessorized outfits
	int maxAccessories;
	String username;
	String password;
	ClothingPreference pref;
	Scanner scnr;
	ArrayList<String> patternTypes;
	
	public double chancePrefMatchAcc;
	
	public enum ClothingPreference {
		MASCULINE, FEMININE, BOTH
	}

	public User() {
		System.out.println("------------------------------------");
		System.out.println("USER SETUP");
		System.out.println("------------------------------------");
		scnr = new Scanner(System.in);
		askName();
		askUsername();
		askPassword();
		
		chancePrefMatchAcc = .9;
		
		ArrayList<Option> options = new ArrayList<Option>();
		options.add(new Option("Setup my account and get more accurate outfits!", 's'));
		options.add(new Option("Quick setup", 'q'));
		Choice choice = new Choice("Hi " + name +", would you like to continue setting up your account or do quick setup?", options);
		char choiceChar = choice.performChoice();
		
		switch(choiceChar) {
		case 's':
			setup();
			System.out.println("You're all done setting up! Change any of these preferences in settings");
			break;
		case 'q':
			masculine = true;
			feminine = true;
			pref = ClothingPreference.BOTH;
			chanceMixPatterns = .3;
			chanceAccessorize = .8;
			maxAccessories = 2;
			mixPatternsBalance = 0;
			System.out.println("That's okay! You can always finish setting up your account later in settings, found on the home screen");
			break;
		}	
		patternTypes = new ArrayList<String>();
		saveUserInfo(username);
	}
	
	
	public User(String username, Closet closet) {
		
			//TODO: save chances to userinfo
			chancePrefMatchAcc = .9;
			scnr = new Scanner(System.in);
			this.username = username;
			patternTypes = new ArrayList<String>();
			importUserInfo(username);
			//saveUserInfo(username);
	}
	
	public String clothingPrefToString() {
		if (pref == ClothingPreference.FEMININE)
			return "feminine";
		if (pref == ClothingPreference.MASCULINE)
			return "masculine";
		else
			return "both";
	}
	
	public double getChanceMixPatterns() {
		return chanceMixPatterns;
	}
	
	public double adjustMixPatterns(double adjust) {
		chanceMixPatterns = chanceMixPatterns * adjust;
		return chanceMixPatterns;
	}
	
	public int increaseMixPatternBalance(boolean increase) {
		if (increase)
			return ++mixPatternsBalance;
		else
			return --mixPatternsBalance;
	}
	
	public int getMixPatternsBalance() {
		return mixPatternsBalance;
	}
	
	public String userData() {
		String s = "";
		s += "Username: " + username+ "$";
		s += "Password: " + password+ "$";
		s += "Name: " + name+ "$";
		s += "Clothing preference: " + clothingPrefToString()+ "$";
		s += "chanceMixPatterns: " + chanceMixPatterns+ "$";
		s += "chanceAccessorize: " + chanceAccessorize+ "$";
		s += "maxAccessories: " + maxAccessories + "$";
		s += "mixPatternsBalance: " + mixPatternsBalance + "$";
		s += "chancePrefMatchAcc: " + chancePrefMatchAcc + "$";
		s += "patternTypes: ";
		for (int i = 0; i < patternTypes.size(); i++) {
			s += patternTypes.get(i);
			if (i < patternTypes.size() - 1)
				s += ", ";
		}
		return s;
	}
	
	/**
	 * 
	 * @return - true if it gets saved
	 */
	public boolean saveUserInfo(String username) {
		try {
		String filename = username + "UserData.txt";
		File saveFile = new File(filename);
		saveFile.createNewFile();
		FileWriter fileWriter = new FileWriter(filename);
	
		fileWriter.write(userData());
		fileWriter.close();
		System.out.println("Successfully saved user info.");
		return true;
			
		} catch (IOException e) {
			System.out.println("An error occurred");
			e.printStackTrace();
			return false;
		}	
	}
	
	public boolean importUserInfo(String username) {
		try {
			String filename = username + "UserData.txt";
			List<String> fileLines = Files.readAllLines(Paths.get(filename));
			String command = "";
			String[] commandParts; // commandParts[0] stores the type of data or header, commandParts[1]
									// stores the actual data
			String[] sections; // stores the different category of data
			

			for (int i = 0; i < fileLines.size(); i++) {
				sections = fileLines.get(i).split("\\$"); // splits the different sections of data
				for (int j = 0; j < sections.length; j++) {
					commandParts = sections[j].split(":"); // splits the sections by header and actual data
					command = commandParts[0].toLowerCase(); // the header
					String setting = commandParts[1].toLowerCase(); // the data
					if (command.contains("username"))
						username = setting.trim();
					if (command.contains("password"))
						password = setting.trim();
					if (command.contains("name"))
						name = setting.trim();
					if (command.contains("clothing preference")) {
						if (setting.contains("masculine"))
							pref = ClothingPreference.MASCULINE;
						if (setting.contains("feminine"))
							pref = ClothingPreference.FEMININE;
						else
							pref = ClothingPreference.BOTH;
					} if (command.contains("chancemixpatterns"))
						chanceMixPatterns = Double.parseDouble(setting.trim());
					if (command.contains("chanceaccessorize")) {
						chanceAccessorize = Double.parseDouble(setting.trim());
						System.out.println("chanceaccessorize: " + chanceAccessorize);
					}
						
					if (command.contains("maxaccessories"))
						maxAccessories = Integer.parseInt(setting.trim());
					if (command.contains("chanceprefmatchacc"))
						chancePrefMatchAcc = Double.parseDouble(setting.trim());
					if (command.contains("mixpatternsbalance"))
						mixPatternsBalance = Integer.parseInt(setting.trim());
					if (command.contains("patterntypes")) {
						if (!setting.trim().equals("")) {
							String[] types = setting.split(",");
							for (int k = 0; i < types.length; i++)
								addPatternType(types[i].trim());
						}
					}
				}
			}
			System.out.println("Successfully imported user info");
			return true;
		} catch (Exception e) {
			System.out.println("An error occurred");
			e.printStackTrace();
			return false;
		}
	}
	
	public void setup() {
		setClothingPreference();
		setPatternPreference();
		setAccessoryPreference();
	}
	
	//PATTERNS
	
	public String addPatternType(String newPattern) {
		if (newPattern == null) {
			return newPattern;
		}
		if (patternNameExists(newPattern))
			System.out.println("Pattern '" + newPattern + "' already exists! Was not added");
		else {
			patternTypes.add(newPattern);
			System.out.println(newPattern + " pattern successfully added");
			saveUserInfo(username);
		}
		return newPattern;
	}
	
	public ArrayList<String> getPatternTypes() {
		return patternTypes;
	}
	
	public boolean patternNameExists(String patternName) {
		for (int i = 0; i < patternTypes.size(); i++) {
			if (patternName.trim().toLowerCase().equals(patternTypes.get(i).trim().toLowerCase()))
				return true;
		}
		return false;
	}
	
	public String getPatternPreference() {
		if (chanceMixPatterns >= .6) 
			return "Likes mixing patterns";
		if (chanceMixPatterns < .3) 
			return "Dislikes mixing patterns";
		else
			return "Sometimes mixes patterns";
	}
	
	public double setPatternPreference()  {
		ArrayList<Option> options = new ArrayList<Option>();
		options.add(new Option("Yes", 'y'));
		options.add(new Option("No", 'n'));
		options.add(new Option("Sometimes", 's'));
		Choice choice = new Choice("Do you like to mix patterns?", options);
		char choiceChar = choice.performChoice();
		switch (choiceChar) {
		case 'y':
			chanceMixPatterns = .6;
		case 'n':
			chanceMixPatterns = .1;
		default:
			chanceMixPatterns = .3;
		}
		return chanceMixPatterns;
	}
	
	//USERNAMES
	
	// if false, maybe direct to login screen
	public boolean availableUsername(String possibleUsername) {
		if (possibleUsername.contains("$") || possibleUsername.contains(" ") || possibleUsername.contains(":")) {
			System.out.println("Oops! Your username contained either '$' or ' '");
			return false;
		}
		File tmpFile = new File(possibleUsername + "OutfitGenerator.txt");
		if (!tmpFile.exists()) {
			return true;
		} else
			return false;
	}
	
	public String getUsername() {
		return username;
	}
	
	/**
	 * 
	 * @param username - username to set
	 * @return true if was able to be set, false if not
	 */
	public boolean setUsername(String username) {
		if (availableUsername(username)) {
			this.username = username;
			return true;
		}
		else {
			System.out.println("This username is already in use!");
			return false;
		}
	}
	
	public String askUsername() {
		boolean repeat = false;
		String possibleUsername;
		do {
		System.out.println("Enter a username. May not contain '$', ':', or ' '. Is case sensitive: ");
		possibleUsername = scnr.nextLine();
		ArrayList<Option> options = new ArrayList<Option>();
		options.add(new Option("Yes", 'y'));
		options.add(new Option("Enter new username", 'n'));
		Choice choice = new Choice("Choose username " + possibleUsername, options);
		char choiceChar = choice.performChoice();
		switch (choiceChar) {
		case 'y':
			if (availableUsername(possibleUsername)) {
				username = possibleUsername;
				return username;
			}
			else {
				//TODO: if false, add direction to login screen maybe
				System.out.println("That username is already in use or used off limits characters.");
				repeat = true;
			}
			break;
		case 'n':
			repeat = true;
			break;
		}
		} while (repeat);
		return "No username set";
	}
	
	
	//PASSWORDS
	
	public boolean setPassword(String possiblePassword) {
		if (possiblePassword.contains(" ") || possiblePassword.length() < 5)  {
			System.out.println("Password did not meet criteria of no spaces and minimum 5 characters long");
			return false;
		}
		password = possiblePassword;
		return true;
	}
	
	//TODO: include more restrictions in password
	public String askPassword() {
		boolean repeat = false;
		String possiblePassword;
		do {
			System.out.println("Setup a password. Minimum 5 characters long. May not contain '$', ':', or ' '. Is case sensitive.");
//			scnr.nextLine(); // advances scanner to next line
			possiblePassword = scnr.nextLine();
			if (possiblePassword.contains(" ") || possiblePassword.contains("$") || possiblePassword.contains(" ") || possiblePassword.length()<5) {
				System.out.println("Password did not meet criteria");
				repeat = true;
			}
			System.out.println("Reenter password");
			if (possiblePassword.equals(scnr.next()))
				password = possiblePassword;
			else {
				System.out.println("Passwords do not match");
				repeat = false;
			}
		} while(repeat);
		if (username != null && password != null) 
			createNewAccount(username, password);
		return password;
	}
	
	public String getPassword() {
		return password;
	}
	
	/**
	 * 
	 * @param username
	 * @param password
	 * @return true if successful
	 */
	public boolean createNewAccount(String username, String password) {
		try {
		//FileWriter fileWriter = new FileWriter("accounts.txt");
		String filename = username + "OutfitGenerator.txt";
		File saveFile = new File(filename);
			saveFile.createNewFile();
			List<String> fileLines = Files.readAllLines(Paths.get("accounts.txt"));
			System.out.println("Writing to file...");
			try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("accounts.txt", true)));) {
			    String data = "\nUsername: " + username + " $ Password: " + password;
			    File file = new File("accounts.txt");
			    out.println(data);
			    //out.close();
			} catch(IOException e) {
			}
		} catch (IOException e) {
			System.out.println("An error occurred");
			e.printStackTrace();
		}
		return true;
	}
	
	public String getName() {
		return name;
	}

	public String setName(String name) {
		this.name = name;
		return name;
	}
	
	public String askName() {
		System.out.println("What is your name?");
		name = scnr.nextLine();
		return name;
	}
	
	public int changeMaxAccessories() {		
		do {
		    System.out.println("Enter max number of accessories on an outfit: ");
		    while (!scnr.hasNextInt()) {
		        System.out.println("That's not a number!");
		        scnr.next(); // this is important!
		    }
		    maxAccessories = scnr.nextInt();
		} while (maxAccessories <= 0);

		return maxAccessories;
	}
	
	public int getMaxAccessories() {
		return maxAccessories;
	}
	
	public String getAccessoryPreference() {
		if (chanceAccessorize >= .8)
			return "Likes accessories a lot.";
		if (chanceAccessorize <.3)
			return "Never wears accessories.";
		else
			return "Likes one or two accessories.";
	}
	
	public double setAccessoryPreference() {
		ArrayList<Option> options = new ArrayList<Option>();
		options.add(new Option("A lot", 'y'));
		options.add(new Option("I never wear accessories", 'n'));
		options.add(new Option("I like one or two accessories with an outfit", 's'));
		Choice choice = new Choice("How much do you like to accessorize?", options);
		char choiceChar = choice.performChoice();
		switch (choiceChar) {
		case 'y':
			chanceAccessorize = .8;
			maxAccessories = 5;
		case 'n':
			chanceAccessorize = .2;
			maxAccessories = 1;
		default:
			chanceAccessorize = .8;
			maxAccessories = 2;
		}
		return chanceMixPatterns;
	}
	
	public String getClothingPreference() {
		switch (pref) {
		case MASCULINE:
			return "Masculine";
		case FEMININE:
			return "Feminine";
		default:
			return "No preference";
		}
	}
	
	public String setClothingPreference() {
		// get clothing preference info
		char clothingPreference = askClothingPreference();
		
		// set clothing preference
		if (clothingPreference == 'm') {
			masculine = true;
			feminine = false;
			pref = ClothingPreference.MASCULINE;
			
		} else if (clothingPreference == 'f') {
			feminine = true;
			masculine = false;
			pref = ClothingPreference.FEMININE;
		} else {
			masculine = true;
			feminine = true;
			pref = ClothingPreference.BOTH;
		}
		return getClothingPreference();
	}
	
	private char askClothingPreference() {
		ArrayList<Option> options = new ArrayList<Option>();
		options.add(new Option("Masculine", 'm'));
		options.add(new Option("Feminine", 'f'));
		options.add(new Option("No preference", 'x'));
		Choice choice = new Choice("What is your preferred way to dress?", options);
		char choiceChar = choice.performChoice();
		return choiceChar;
	}

}
