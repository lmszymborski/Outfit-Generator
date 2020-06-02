import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Navigation {
	private static Scanner scnr;
	private static User user;
	private static Closet closet;

	public Navigation(User user) {
		this.user = user;
	}

	public void outfitGenerator() {
		OutfitGenerator outfitGenerator = new OutfitGenerator(user, closet, new OutfitPreference());
	}
	
	public void settings() {
		boolean repeat = true;
		while (repeat) {
			System.out.println("------------------------------------");
			System.out.println("SETTINGS");
			System.out.println("------------------------------------");
			ArrayList<Option> options = new ArrayList<Option>();
			options.add(new Option("Edit name", 'n'));
			options.add(new Option("Edit clothing preference", 'c'));
			options.add(new Option("Edit pattern preference", 'p'));
			options.add(new Option("Edit accessory preference", 'a'));
			options.add(new Option("Back", 'x'));
			Choice choice = new Choice("What action would you like to do?", options);
			char settingsOptions = choice.performChoice();
			switch (settingsOptions) {
			case 'n':
				System.out.println("Current name: " + user.getName());
				System.out.println("New name: " + user.askName());
				break;
			case 'c':
				System.out.println("Current clothing preference: " + user.getClothingPreference());
				System.out.println("New clothing preference: " + user.setClothingPreference());
				break;
			case 'p':
				System.out.println("Current pattern preference: " + user.getPatternPreference());
				System.out.println("New pattern preference: " + user.setPatternPreference());
				break;
			case 'a':
				System.out.println("Current accessory preference: " + user.getAccessoryPreference());
				System.out.println("Current max accessories: " + user.getMaxAccessories());
				ArrayList<Option> options1 = new ArrayList<Option>();
				options1.add(new Option("Edit max accessories", 'm'));
				options1.add(new Option("Edit accessory preference", 'p'));
				options1.add(new Option("Edit both", 'b'));
				Choice choice1 = new Choice(options1);
				char option = choice1.performChoice();
				switch (option) {
				case 'm':
					user.changeMaxAccessories();
					break;
				case 'p':
					user.setAccessoryPreference();
					break;
				case 'b':
					user.setAccessoryPreference();
					user.changeMaxAccessories();
					break;
				}
				System.out.println("New accessory preference: " + user.getAccessoryPreference());
				System.out.println("New max accessories: " + user.getMaxAccessories());
				break;
			case 'x':
				repeat = false;
				break;
			}
		}
	}

	public void closet() throws IOException {
		boolean repeat = true;
		while (repeat) {
			String filename = "";
			System.out.println("------------------------------------");
			System.out.println("CLOSET");
			System.out.println("------------------------------------");
			ArrayList<Option> options = new ArrayList<Option>();
			options.add(new Option("Add clothing item", 'a'));
			options.add(new Option("Edit clothing item", 'e'));
			options.add(new Option("Delete clothing item", 'd'));
			options.add(new Option("View closet", 'v'));
			options.add(new Option("Import closet", 'i'));
			options.add(new Option("Save closet", 's'));
			options.add(new Option("Back", 'x'));
			Choice choice = new Choice("What action would you like to do?", options);
			char option = choice.performChoice();
			switch (option) {
			case 'a':
				closet.addItem();
				//closet.sort();
				closet.saveCloset(user.getUsername() + "OutfitGenerator.txt", true);
				break;
			case 'e':
				closet.edit();
				closet.sort();
				break;
			case 'd':
				closet.deleteItem();
				break;
			case 'x':
				repeat = false;
				break;
			case 'i':
				do {
					System.out.println("What is your file name? Filename 'x' not allowed");
					System.out.println("Press x to go back");
					filename = scnr.nextLine();
					if (filename.equals("x")) {
						repeat = false;
						break;
					}
				} while (!closet.importCloset(filename));
				closet.saveCloset(user.getUsername() + "OutfitGenerator.txt", true);
				break;
			case 's':
				boolean breakLoop = false;
				boolean saveToAccount = true;
				do {
					ArrayList<Option> options1 = new ArrayList<Option>();
					options1.add(new Option("Save to new file", 'n'));
					options1.add(new Option("Save to account", 'a'));
					Choice choice1 = new Choice(options1);
					char option1 = choice1.performChoice();
					switch(option1) {
					case 'n':
						System.out.println("What would you like to name your file? Filename 'x' not allowed");
						System.out.println("Press x to go back");
						filename = scnr.nextLine();
						if (filename.equals("x")) {
							repeat = false;
							break;
						}
						saveToAccount = false;
						break;
					default:
						filename = user.getUsername() + "OutfitGenerator";
						saveToAccount = true;
					}
				} while (!closet.saveCloset(filename + ".txt", saveToAccount));
				break;
			case 'v':
				closet.viewItems();
				break;
			}
		}
	}

	public void homeScreen() throws IOException {
		boolean repeat = true;
		while (repeat) {
			System.out.println("------------------------------------");
			System.out.println("HOME SCREEN");
			System.out.println("------------------------------------");
			ArrayList<Option> options = new ArrayList<Option>();
			options.add(new Option("Get generated outfit", 'o'));
			options.add(new Option("Access or modify closet", 'c'));
			options.add(new Option("Settings", 's'));
			options.add(new Option("Logout", 'l'));
			Choice choice = new Choice("What action would you like to do?", options);
			char option = choice.performChoice();
			switch (option) {
			case 'o':
				outfitGenerator();
				break;
			case 'c':
				System.out.println("Opening closet...");
				closet();
				break;
			case 's':
				System.out.println("Opening settings...");
				settings();
				user.saveUserInfo(user.getUsername());
				break;
			case 'l':
				ArrayList<Option> options1 = new ArrayList<Option>();
				options1.add(new Option("Yes", 'y'));
				options1.add(new Option("No", 'n'));
				options1.add(new Option("Logout, but don't save my closet", 'l'));
				Choice choice1 = new Choice("Are you sure you want to log out? Your closet will be automatically saved.", options1);
				char option1 = choice1.performChoice();
				switch(option1) {
				case 'y':
					closet.saveCloset(user.getUsername() + "OutfitGenerator.txt", true);
					repeat = false;
					logout();
					break;
				case 'n':
					break;
				case 'l':
					logout();
					break;
				}
				break;
			}
		}
	}
	
	public void logout() {
		System.out.println("You're not actually logged out. This hasn't been developed yet haha. Your info is saved tho\n");
		entryScreen();
	}

	/**
	 * 
	 * @param testUsername - username to test
	 * @param testPassword - password to test
	 * @return - true if the username and password have a matching account
	 * @throws IOException
	 */
	public static boolean checkLoginInfo(String testUsername, String testPassword) throws IOException {
		try {
			List<String> fileLines = Files.readAllLines(Paths.get("accounts.txt"));
			String username = "";
			String password = "";
			String command = "";
			String[] commandParts; // commandParts[0] stores the username or password description, commandParts[1]
									// stores the actual username or password
			String[] sections; // stores the different category of username info or password info

			for (int i = 0; i < fileLines.size(); i++) {
				sections = fileLines.get(i).split("\\$"); // username info in sections[0], password info in sections[1]
				for (int j = 0; j < sections.length; j++) {
					commandParts = sections[j].split(":");
					command = commandParts[0].toLowerCase();
					String setting = commandParts[1].toLowerCase();
					if (command.contains("username"))
						username = commandParts[1];
					else {
						password = commandParts[1];
					}
				}
				if (testUsername.trim().equals(username.trim()) && testPassword.trim().equals(password.trim()))
					return true;
			}
			return false;
		} catch (IOException ioe) {
			return false;
		}
	}

	public static User loginOrNewAccount() {
		boolean repeat = false;
		User user;
		do {
			ArrayList<Option> options = new ArrayList<Option>();
			options.add(new Option("Set up new account", 'n'));
			options.add(new Option("Login", 'l'));
			Choice choice = new Choice(options);
			char option = choice.performChoice();
			if (option == 'n') {
				System.out.println(
						"We're going to get you set up with a new account so you can get outfits personalized for you.");
				user = new User();
				Navigation navigation = new Navigation(user);
				closet = new Closet(user);
				return user;
			} else {
				boolean innerRepeat = false;
				do {
					System.out.println("Enter username: ");
					String attemptedUsername = scnr.nextLine();
					System.out.println("Enter password: ");
					String attemptedPassword = scnr.nextLine();
					try {
						if (checkLoginInfo(attemptedUsername, attemptedPassword)) {
							user = new User(attemptedUsername, closet);
							closet = new Closet(user);
							closet.importCloset(attemptedUsername + "OutfitGenerator.txt");
							return user;
						} else {
							System.out.println("No matching accounts found with that information");
							ArrayList<Option> options1 = new ArrayList<Option>();
							options1.add(new Option("Set up new account", 'n'));
							options1.add(new Option("Reenter information", 'r'));
							Choice choice1 = new Choice(options1);
							char option1 = choice1.performChoice();
							switch (option1) {
							case 'n':
								repeat = true;
								break;
							default:
								innerRepeat = true;
								break;
							}
						}
					} catch (IOException e) {
						System.out.println("An error occurred");
						e.printStackTrace();
					}
				} while (innerRepeat);
			}
		} while (repeat);
		return new User();
	}

	public static void entryScreen() {
		scnr = new Scanner(System.in);
		System.out.println("Welcome to the outfit generator!");
		boolean repeat = false;
		user = loginOrNewAccount();
		Navigation navigation = new Navigation(user);
		
		try {
			navigation.homeScreen();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException {
		entryScreen();
	}
}
