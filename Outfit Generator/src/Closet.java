import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Closet {

	Scanner scnr;
	User user;
	public enum ClothingType {
		ACCESSORY, BOTTOMS, COAT, DRESS, JACKET, SHOES, SOCKS, TOP
	}

	ArrayList<ClothingItem> allClothes;

	ArrayList<ClothingItem> hamper;
	ArrayList<ClothingItem> available;

	ArrayList<ClothingItem> freezing;
	ArrayList<ClothingItem> cold;
	ArrayList<ClothingItem> medium;
	ArrayList<ClothingItem> hot;

	ArrayList<ClothingItem> patterned;
	ArrayList<ClothingItem> unpatterned;

	ArrayList<ClothingItem> denim;
	ArrayList<ClothingItem> notDenim;
	
	ArrayList<ClothingItem> tight;
	ArrayList<ClothingItem> loose;
	
	ArrayList<ClothingItem> red;
	ArrayList<ClothingItem> orange;
	ArrayList<ClothingItem> yellow;
	ArrayList<ClothingItem> green;
	ArrayList<ClothingItem> blue;
	ArrayList<ClothingItem> purple;
	ArrayList<ClothingItem> black;
	ArrayList<ClothingItem> brown;
	ArrayList<ClothingItem> tan;
	ArrayList<ClothingItem> white;
	ArrayList<ClothingItem> pink;
	ArrayList<ClothingItem> grey;
	
	ArrayList<ClothingItem> datenight;
	ArrayList<ClothingItem> formal;
	ArrayList<ClothingItem> nightout;
	ArrayList<ClothingItem> everyday;
	ArrayList<ClothingItem> business;

	
	public Closet(User user) {
		this.user = user;
		scnr = new Scanner(System.in);

		allClothes = new ArrayList<ClothingItem>();

		hamper = new ArrayList<ClothingItem>();
		available = new ArrayList<ClothingItem>();

		freezing = new ArrayList<ClothingItem>();
		cold = new ArrayList<ClothingItem>();
		medium = new ArrayList<ClothingItem>();
		hot = new ArrayList<ClothingItem>();

		patterned = new ArrayList<ClothingItem>();
		unpatterned = new ArrayList<ClothingItem>();

		denim = new ArrayList<ClothingItem>();
		notDenim = new ArrayList<ClothingItem>();
		
		tight = new ArrayList<ClothingItem>();
		loose = new ArrayList<ClothingItem>();
		
		red = new ArrayList<ClothingItem>();
		orange = new ArrayList<ClothingItem>();
		yellow = new ArrayList<ClothingItem>();
		green = new ArrayList<ClothingItem>();
		blue = new ArrayList<ClothingItem>();
		purple = new ArrayList<ClothingItem>();
		black = new ArrayList<ClothingItem>();
		brown = new ArrayList<ClothingItem>();
		tan = new ArrayList<ClothingItem>();
		white = new ArrayList<ClothingItem>();
		pink = new ArrayList<ClothingItem>();
		grey = new ArrayList<ClothingItem>();
		
		datenight = new ArrayList<ClothingItem>();
		formal = new ArrayList<ClothingItem>();
		nightout = new ArrayList<ClothingItem>();
		everyday = new ArrayList<ClothingItem>();
		business = new ArrayList<ClothingItem>();
	}

	/**
	 * sorts closet by all categories
	 */
	public void sort() {
		sortByAvailability();
		sortByTempRange();
		sortByPattern();
		sortByDenim();
		sortByColor();
		sortByOccasion();
		sortByTight();
	}

	private void sortByTight() {
		tight.clear();
		loose.clear();
		
		for (int i = 0; i < allClothes.size(); i++) {
			if (allClothes.get(i) instanceof Top || allClothes.get(i) instanceof Bottoms) {
				if (allClothes.get(i).isTight()) {
					tight.add(allClothes.get(i));
				} else {
					loose.add(allClothes.get(i));
				}
			}
		}
	}
	
	private void sortByOccasion() {
		datenight.clear();
		formal.clear();
		nightout.clear();
		everyday.clear();
		business.clear();
		
		for (int i = 0; i < allClothes.size(); i++) {
			if (allClothes.get(i).getOccasions()[0]) {
				datenight.add(allClothes.get(i));
			}
			if (allClothes.get(i).getOccasions()[1]) {
				formal.add(allClothes.get(i));
			}
			if (allClothes.get(i).getOccasions()[2]) {
				nightout.add(allClothes.get(i));
			}
			if (allClothes.get(i).getOccasions()[3]) {
				everyday.add(allClothes.get(i));
			}
			if (allClothes.get(i).getOccasions()[4]) {
				business.add(allClothes.get(i));
			}
		}
	}
	
	private void sortByColor() {
		red.clear();
		orange.clear();
		yellow.clear();
		green.clear();
		blue.clear();
		purple.clear();
		black.clear();
		brown.clear();
		tan.clear();
		white.clear();
		pink.clear();
		grey.clear();
		for (int i = 0; i < allClothes.size(); i++) {
			if (allClothes.get(i).getColors()[0]) {
				red.add(allClothes.get(i));
			}
			if (allClothes.get(i).getColors()[1]) {
				orange.add(allClothes.get(i));
			}
			if (allClothes.get(i).getColors()[2]) {
				yellow.add(allClothes.get(i));
			}
			if (allClothes.get(i).getColors()[3]) {
				green.add(allClothes.get(i));
			}
			if (allClothes.get(i).getColors()[4]) {
				blue.add(allClothes.get(i));
			}
			if (allClothes.get(i).getColors()[5]) {
				purple.add(allClothes.get(i));
			}
			if (allClothes.get(i).getColors()[6]) {
				black.add(allClothes.get(i));
			}
			if (allClothes.get(i).getColors()[7]) {
				brown.add(allClothes.get(i));
			}
			if (allClothes.get(i).getColors()[8]) {
				tan.add(allClothes.get(i));
			} if (allClothes.get(i).getColors()[9]) {
				white.add(allClothes.get(i));
			}
			if (allClothes.get(i).getColors()[10]) {
				pink.add(allClothes.get(i));
			} if (allClothes.get(i).getColors()[11])
				grey.add(allClothes.get(i));
		}
	}
	/**
	 * sorts closet by available and dirty
	 */
	private void sortByAvailability() {
		available.clear();
		hamper.clear();
		for (int i = 0; i < allClothes.size(); i++) {
			if (allClothes.get(i).isClean()) {
				available.add(allClothes.get(i));
			} else {
				hamper.add(allClothes.get(i));
			}
		}
	}

	/**
	 * sorts closet by the temperature ranges they are set to be suited for
	 */
	private void sortByTempRange() {
		// TODO: there's probably a better way to do this
		freezing.clear();
		cold.clear();
		medium.clear();
		hot.clear();
		for (int i = 0; i < allClothes.size(); i++) {
			if (allClothes.get(i).getRanges()[0]) {
				freezing.add(allClothes.get(i));
			}
			if (allClothes.get(i).getRanges()[1]) {
				cold.add(allClothes.get(i));
			}
			if (allClothes.get(i).getRanges()[2]) {
				medium.add(allClothes.get(i));
			}
			if (allClothes.get(i).getRanges()[3]) {
				hot.add(allClothes.get(i));
			}
		}
	}

	private void sortByPattern() {
		patterned.clear();
		unpatterned.clear();
		for (int i = 0; i < allClothes.size(); i++) {
			if (allClothes.get(i).isPatterned()) {
				patterned.add(allClothes.get(i));
			} else {
				unpatterned.add(allClothes.get(i));
			}
		}
	}

	private void sortByDenim() {
		denim.clear();
		notDenim.clear();
		for (int i = 0; i < allClothes.size(); i++) {
			if (allClothes.get(i).isDenim()) {
				denim.add(allClothes.get(i));
			} else {
				notDenim.add(allClothes.get(i));
			}
		}
	}

	public void edit() {
		boolean repeat = true;
		while (repeat) {
			System.out.println("------------------------------------");
			System.out.println("EDIT CLOSET");
			System.out.println("------------------------------------");
			System.out.println("To find item to edit, you must search for the item");
			System.out.println("If you are having trouble finding it, go to the viewing closet");
			boolean again = true;
			while (again) {
				System.out.println("Search:");
				System.out.println("Back (x)");

				String searchterm = scnr.nextLine().toLowerCase();
				if (searchterm.equals("x")) {
					repeat = false;
					break;
				}
				ArrayList<ClothingItem> matches = search(searchterm);
				displayClothes(matches);
				ArrayList<Option> options = new ArrayList<Option>();
				char letter = 'a';
				for (int i = 0; i < matches.size(); i++) {
					options.add(new Option(matches.get(i).getName(), letter++));
				}
				options.add(new Option("none of these are what I'm looking for, search again", 'x'));
				Choice choice = new Choice("Which item would you like to edit?", options);
				char option = choice.performChoice();
				again = false;

				if (option == 'x') {
					again = true;
					continue;
				}

				letter = 'a';
				for (int i = 0; i < matches.size(); i++) {
					if (option == letter++)
						editScreen(matches.get(i));
				}
			}

		}
	}

	private void editScreen(ClothingItem clothingItem) {
		boolean repeat = true;
		while (repeat) {
			ArrayList<Option> options = new ArrayList<Option>();
			options.add(new Option("Name", 'n'));
			options.add(new Option("Description", 'd'));
			options.add(new Option("Temperature ranges", 'r'));
			options.add(new Option("Availability", 'a'));
			options.add(new Option("Pattern", 'p'));
			options.add(new Option("Occasions", 'o'));
			options.add(new Option("Denim", 'e'));
			options.add(new Option("Tight", 't'));
			options.add(new Option("Color", 'c'));
			options.add(new Option("Back", 'x'));
			Choice choice = new Choice("What would you like to edit?", options);
			char option = choice.performChoice();

			switch (option) {
			case 'n':
				System.out.println("Current name: " + clothingItem.getName());
				clothingItem.askName();
				System.out.println("New name: " + clothingItem.getName());
				break;
			case 'd':
				System.out.println("Current description: " + clothingItem.getDescription());
				clothingItem.setDescription();
				System.out.println("New description: " + clothingItem.getDescription());
				break;
			case 'r':
				System.out.println("Current temperature ranges: " + clothingItem.rangesToString());
				clothingItem.setRanges();
				System.out.println("New temperature ranges: " + clothingItem.rangesToString());
				break;
			case 'a':
				System.out.println("Item is available: " + clothingItem.isClean());
				clothingItem.setAvailability();
				System.out.println("Item is available: " + clothingItem.isClean());
				break;
			case 'o':
				System.out.println("Current occasions: " + clothingItem.occasionsToString());
				clothingItem.setOccasions();
				System.out.println("New occasions: " + clothingItem.occasionsToString());
				break;
			case 't':
				System.out.println("Item is tight: " + clothingItem.isTight());
				clothingItem.setTight();
				System.out.println("Item is tight: " + clothingItem.isTight());
				break;
			case 'p':
				System.out.println("Item is patterned: " + clothingItem.isPatterned());
				clothingItem.askPattern();
				System.out.println("Item is patterned: " + clothingItem.isPatterned());
				break;
			case 'e':
				System.out.println("Item is denim: " + clothingItem.isDenim());
				clothingItem.setDenim();
				System.out.println("Item is denim: " + clothingItem.isDenim());
				break;
			case 'c':
				System.out.println("Current colors: " + clothingItem.colorsToString());
				clothingItem.setColors();
				System.out.println("New colors: " + clothingItem.colorsToString());
				break;
			default:
				repeat = false;
				break;
			}
		}
	}

	
	private ArrayList<ClothingItem> search(String searchterm) {
		ArrayList<ClothingItem> match = new ArrayList<ClothingItem>();
		searchterm = searchterm.toLowerCase();
		for (int i = 0; i < allClothes.size(); i++) {
			String clothingItemString = allClothes.get(i).toStringSave().toLowerCase();
			String[] sections;
			String[] commandParts;
			sections = clothingItemString.split("\\$");
			for (int j = 0; j < sections.length; j++) {
				commandParts = sections[j].split(":");
				String setting = commandParts[1];
				if (setting.contains(searchterm)) {
					match.add(allClothes.get(i));
					break;
				}
			}
		}
		return match;
	}

	private void displayClothes(ArrayList<ClothingItem> clothes) {
		for (int i = 0; i < clothes.size(); i++) {
			System.out.println(clothes.get(i).toString() + "\n");
		}
		if (clothes.size() == 0) {
			System.out.println("No clothes found with this description \n");
		}
	}

	public boolean exists(String name) {
		for (int i = 0; i < allClothes.size(); i++) {
			if (allClothes.get(i).getName().trim().equals(name.trim())) {
				return true;
			}
		}
		return false;
	}

	public void addItem() {
		boolean repeat = true;
		do {
			System.out.println("Creating new item...");
			System.out.println("------------------------------------");

			System.out.println("Enter a unique item name: ");
			String name = scnr.nextLine();

			do {
				if (exists(name)) {
					System.out.println("That name already exists. Please enter a new name.");
					name = scnr.nextLine();
				}
			} while (exists(name));

			ArrayList<Option> options = new ArrayList<Option>();
			options.add(new Option("Accessory", 'a'));
			options.add(new Option("Bottoms", 'b'));
			options.add(new Option("Coat", 'c'));
			options.add(new Option("Dress", 'd'));
			options.add(new Option("Jacket", 'j'));
			options.add(new Option("Shoes", 'h'));
			options.add(new Option("Socks", 's'));
			options.add(new Option("Top", 't'));
			Choice choice = new Choice("What type of clothing item?", options);
			char option = choice.performChoice();
			switch (option) {
			case 'a':
				allClothes.add(new Accessory(name, user));
				break;
			case 'b':
				allClothes.add(new Bottoms(name, user));
				break;
			case 'c':
				allClothes.add(new Coat(name, user));
				break;
			case 'd':
				allClothes.add(new Dress(name, user));
				break;
			case 'h':
				allClothes.add(new Shoes(name, user));
				break;
			case 'j':
				allClothes.add(new Jacket(name, user));
				break;
			case 's':
				allClothes.add(new Socks(name, user));
				break;
			default:
				allClothes.add(new Top(name, user));
				break;
			}
			ArrayList<Option> options1 = new ArrayList<Option>();
			options1.add(new Option("Yes", 'y'));
			options1.add(new Option("No", 'n'));
			Choice choice1 = new Choice("Add another item?", options1);
			char option1 = choice1.performChoice();
			if (option1 == 'n')
				repeat = false;
		} while (repeat);
	}

	public boolean importCloset(String filename) throws IOException {
		try {
			List<String> fileLines = Files.readAllLines(Paths.get(filename));
			String command = "";
			String[] commandParts; // commandParts[0] stores the command, commandParts[1] stores the description
			String[] sections; // stores each different category
			for (int i = 0; i < fileLines.size(); i++) {

				String type = "";
				String name = "";
				String description = "";
				String subtypeString = "";
				Accessory.AccessoryType subtype = Accessory.AccessoryType.OTHER;
				boolean[] ranges = new boolean[] { false, false, false, false };
				boolean clean = false;
				String patternName = null;
				int balance = 0;
				double score = 0;
				boolean denim = false;
				boolean tight = false;
				boolean[] colors = new boolean[] {false, false, false, false, false, false, false, false, false, false, false, false};
				boolean[] occasions = new boolean[] {false, false, false, false, false};
				sections = fileLines.get(i).split("\\$");
				for (int j = 0; j < sections.length; j++) {
					commandParts = sections[j].split(":");
					command = commandParts[0].toLowerCase();
					String setting = commandParts[1].toLowerCase();

					if (command.contains("type")) {
						String[] types = setting.split(",");
						if (types.length > 1) {
							type = types[0];
							subtypeString = types[1];
							if (subtypeString.contains("purse"))
								subtype = Accessory.AccessoryType.PURSE;
							else if (subtypeString.contains("necklace"))
								subtype = Accessory.AccessoryType.NECKLACE;
							else if (subtypeString.contains("bracelet"))
								subtype = Accessory.AccessoryType.BRACELET;
							else if (subtypeString.contains("anklet"))
								subtype = Accessory.AccessoryType.ANKLET;
							else if (subtypeString.contains("scrunchie"))
								subtype = Accessory.AccessoryType.SCRUNCHIE;
							else if (subtypeString.contains("glasses"))
								subtype = Accessory.AccessoryType.GLASSES;
							else if (subtypeString.contains("hairpiece"))
								subtype = Accessory.AccessoryType.HAIRPIECE;
							else
								subtype = Accessory.AccessoryType.OTHER;
						}
						type = setting;
					}
					
					if (command.contains("name")) {
						name = setting;
					} else if (command.contains("description")) {
						description = setting;
					}
					else if (command.contains("occasion")) {
						if (setting.contains("datenight"))
							occasions[0] = true;
						if (setting.contains("formal"))
							occasions[1] = true;
						if (setting.contains("nightout"))
							occasions[2] = true;
						if (setting.contains("everyday"))
							occasions[3] = true;
						if (setting.contains("business"))
							occasions[4] = true;
					}
					else if (command.contains("temperature ranges")) {
						if (setting.contains("freezing"))
							ranges[0] = true;
						if (setting.contains("cold"))
							ranges[1] = true;
						if (setting.contains("medium"))
							ranges[2] = true;
						if (setting.contains("hot"))
							ranges[3] = true;
					} else if (command.contains("availability")) {
						if (setting.contains("clean"))
							clean = true;
					} else if (command.contains("patterned")) {
						if (setting.contains("patterned")) {
							String[] types = setting.split(",");
							patternName = types[1].trim();
							user.addPatternType(patternName.trim());
						}
					} else if (command.contains("denim")) {
						if (setting.contains("denim"))
							denim = true;
					} else if (command.contains("tight")) {
						if (setting.contains("tight"))
							tight = true;
					} else if (command.contains("colors")) {
						if (setting.contains("red"))
							colors[0] = true;
						if (setting.contains("orange"))
							colors[1] = true;
						if (setting.contains("yellow"))
							colors[2] = true;
						if (setting.contains("green"))
							colors[3] = true;
						if (setting.contains("blue"))
							colors[4] = true;
						if (setting.contains("purple"))
							colors[5] = true;
						if (setting.contains("black"))
							colors[6] = true;
						if (setting.contains("brown"))
							colors[7] = true;
						if (setting.contains("tan"))
							colors[8] = true;
						if (setting.contains("white"))
							colors[9] = true;
						if (setting.contains("pink"))
							colors[10] = true;
						if (setting.contains("grey"))
							colors[11] = true;
					} else if (command.contains("score")) {
						System.out.println("score in import: " + setting);
						score = Double.parseDouble(setting.trim());
					} else if (command.contains("balance")) {
						balance = Integer.parseInt(setting.trim());
					}
				}

				if (!exists(name.trim())) {
					ClothingItem clothingItem = null;
					if (type.contains("accessory")) {
						clothingItem = new Accessory(user, name.trim(), subtype, description.trim(), ranges, patternName, denim,
								colors, occasions, score, balance);
					}
					else if (type.contains("bottoms")) {
						clothingItem = new Bottoms(user, name.trim(), description.trim(), ranges, clean, patternName, denim,
								tight, colors, occasions, score, balance);
					}
					else if (type.contains("coat")) {
						clothingItem = new Coat(user, name.trim(), description.trim(), ranges, clean, patternName, denim,
								colors, occasions, score, balance);
					}
					else if (type.contains("jacket")) {
						clothingItem = new Jacket(user, name.trim(), description.trim(), ranges, clean, patternName, denim,
								colors, occasions, score, balance);
					}
					else if (type.contains("shoes")) {
						clothingItem = new Shoes(user, name.trim(), description.trim(), ranges, patternName, denim,
								colors, occasions, score, balance);
					}
					else if (type.contains("socks")) {
						clothingItem = new Socks(user, name.trim(), description.trim(), ranges, clean, patternName, denim,
								colors, occasions, score, balance);
					} else {
						clothingItem = new Top(user, name.trim(), description.trim(), ranges, clean, patternName, denim,
								tight, colors, occasions, score, balance);
					}
					allClothes.add(clothingItem);
					System.out.println(name + " successfully imported");
				} else
					System.out.println("Duplicate name in imported file. \"" + name + "\" was not imported.");
			}
			return true;
		} catch (IOException ioe) {
			System.out.println("Oops! The file you entered isn't in our database");
			return false;
		}

	}

	//TODO: add boolean saveToAccount to parameters to get rid of additional questions about overriding and such
	public boolean saveCloset(String filename, boolean saveToAccount) {
		if (!saveToAccount) {
			if (filename.equals("accounts")) {
				System.out.println("Filename 'accounts' not allowed");
				return false;
			}
			ArrayList<Option> options1 = new ArrayList<Option>();
			options1.add(new Option("Yes", 'y'));
			options1.add(new Option("No", 'n'));
			Choice choice1 = new Choice("Save to file " + filename + "?", options1);
			char option1 = choice1.performChoice();
			if (option1 == 'y') {
				boolean writeToFile = true;
				try {
					File saveFile = new File(filename);
					if (saveFile.createNewFile()) {
						System.out.println("File created: " + saveFile.getName());
					} else {
						ArrayList<Option> options = new ArrayList<Option>();
						options.add(new Option("Yes", 'y'));
						options.add(new Option("No", 'n'));
						Choice choice = new Choice("File already exists. Overwrite file?", options);
						char option = choice.performChoice();
						if (option == 'n') {
							writeToFile = false;
						}
					}
				} catch (IOException e) {
					System.out.println("An error occurred.");
					e.printStackTrace();
					return false;
				}
				if (!writeToFile) {
					return false;
				} else {
					try {
						FileWriter fileWriter = new FileWriter(filename);
						for (int i = 0; i < allClothes.size(); i++) {
							fileWriter.write(allClothes.get(i).toStringSave() + "\n");
						}
						fileWriter.close();
						System.out.println("Successfully saved closet.");
						return true;
					} catch (IOException e) {
						System.out.println("An error occurred.");
						e.printStackTrace();
						return false;
					}
				}
			} else {
				return false;
			}
		} else {
			try {
				FileWriter fileWriter = new FileWriter(filename);
				for (int i = 0; i < allClothes.size(); i++) {
					fileWriter.write(allClothes.get(i).toStringSave() + "\n");
				}
				fileWriter.close();
				System.out.println("Successfully saved closet.");
				return true;
			} catch (IOException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
				return false;
			}
		}

	}

	// TODO: repeated code in search algorithm with this and edit metohd
public void deleteItem() {
		boolean repeat = true;
		while (repeat) {
			System.out.println("------------------------------------");
			System.out.println("DELETE ITEM");
			System.out.println("------------------------------------");
			System.out.println("To find item to delete, you must search for the item");
			System.out.println("If you are having trouble finding it, go to the viewing closet");
			boolean again = true;
			while (again) {
				System.out.println("Search:");
				System.out.println("Back (x)");

				String searchterm = scnr.nextLine().toLowerCase();
				if (searchterm.equals("x")) {
					repeat = false;
					break;
				}
				ArrayList<ClothingItem> matches = search(searchterm);
				displayClothes(matches);
				ArrayList<Option> options = new ArrayList<Option>();
				char letter = 'a';
				for (int i = 0; i < matches.size(); i++) {
					options.add(new Option(matches.get(i).getName(), letter++));
				}
				options.add(new Option("none of these are what I'm looking for, search again", 'x'));
				Choice choice = new Choice("Which item would you like to edit?", options);
				char option = choice.performChoice();
				again = false;

				if (option == 'x') {
					again = true;
					continue;
				}

				letter = 'a';
				for (int i = 0; i < matches.size(); i++) {
					if (option == letter++) {
						ArrayList<Option> options1 = new ArrayList<Option>();
						options1.add(new Option("Yes", 'y'));
						options1.add(new Option("No", 'n'));
						String deleteName = matches.get(i).getName();
						Choice choice1 = new Choice("Are you sure you want to delete " + deleteName + "?",
								options1);
						char option1 = choice1.performChoice();

						if (option1 == 'y') {
							allClothes.remove(matches.get(i));
							System.out.println(deleteName + " successfully deleted");
						} else {
							break;
						}
					}
				}
			}
		}
	}

private void viewOccasion() {
	boolean repeat = true;
	System.out.println("------------------------------------");
	System.out.println("VIEW BY OCCASION");
	System.out.println("------------------------------------");
	while (repeat) {
		ArrayList<Option> options = new ArrayList<Option>();
		options.add(new Option("View date night clothes", 'd'));
		options.add(new Option("View formal clothes", 'f'));
		options.add(new Option("View night out clothes", 'n'));
		options.add(new Option("View everyday clothes", 'e'));
		options.add(new Option("View business clothes", 'b'));
		options.add(new Option("View all", 'a'));
		options.add(new Option("Back", 'x'));
		Choice choice = new Choice(options);
		char option = choice.performChoice();

		switch (option) {
		case 'd':
			System.out.println("Date night: ");
			displayClothes(datenight);
			break;
		case 'f':
			System.out.println("Formal: ");
			displayClothes(formal);
			break;
		case 'n':
			System.out.println("Night out: ");
			displayClothes(nightout);
			break;
		case 'e':
			System.out.println("Everyday: ");
			displayClothes(everyday);
			break;
		case 'b':
			System.out.println("Business: ");
			displayClothes(business);
			break;
		case 'a':
			System.out.println("Date night: ");
			displayClothes(datenight);
			System.out.println("Formal: ");
			displayClothes(formal);
			System.out.println("Night out: ");
			displayClothes(nightout);
			System.out.println("Everyday: ");
			displayClothes(everyday);
			System.out.println("Business: ");
			displayClothes(business);
			break;
		default:
			repeat = false;
			break;
		}
	}
}

	private void viewColor() {
	boolean repeat = true;
		System.out.println("------------------------------------");
		System.out.println("VIEW BY COLORS");
		System.out.println("------------------------------------");
		while (repeat) {
			ArrayList<Option> options = new ArrayList<Option>();
			options.add(new Option("View red clothes", 'r'));
			options.add(new Option("View orange clothes", 'o'));
			options.add(new Option("View yellow clothes", 'y'));
			options.add(new Option("View green clothes", 'g'));
			options.add(new Option("View blue clothes", 'b'));
			options.add(new Option("View purple clothes", 'p'));
			options.add(new Option("View black clothes", 'l'));
			options.add(new Option("View brown clothes", 'n'));
			options.add(new Option("View tan clothes", 't'));
			options.add(new Option("View white clothes", 'w'));
			options.add(new Option("View pink clothes", 'i'));
			options.add(new Option("View all", 'a'));
			options.add(new Option("Back", 'x'));
			Choice choice = new Choice(options);
			char option = choice.performChoice();

			switch (option) {
			case 'r':
				System.out.println("Red: ");
				displayClothes(red);
				break;
			case 'o':
				System.out.println("Orange: ");
				displayClothes(orange);
				break;
			case 'y':
				System.out.println("Yellow: ");
				displayClothes(yellow);
				break;
			case 'g':
				System.out.println("Green: ");
				displayClothes(green);
				break;
			case 'b':
				System.out.println("Blue: ");
				displayClothes(blue);
				break;
			case 'p':
				System.out.println("Purple: ");
				displayClothes(purple);
				break;
			case 'l':
				System.out.println("Black: ");
				displayClothes(black);
				break;
			case 'n':
				System.out.println("Brown: ");
				displayClothes(brown);
				break;
			case 'h':
				System.out.println("Tan: ");
				displayClothes(tan);
				break;
			case 'w':
				System.out.println("White: ");
				displayClothes(white);
				break;
			case 'i':
				System.out.println("Pink: ");
				displayClothes(green);
				break;
			case 'a':
				System.out.println("Red: ");
				displayClothes(red);
				System.out.println("Orange: ");
				displayClothes(orange);
				System.out.println("Yellow: ");
				displayClothes(yellow);
				System.out.println("Green: ");
				displayClothes(green);
				System.out.println("Blue: ");
				displayClothes(blue);
				System.out.println("Purple: ");
				displayClothes(purple);
				System.out.println("Black: ");
				displayClothes(black);
				System.out.println("Brown: ");
				displayClothes(brown);
				System.out.println("Tan: ");
				displayClothes(tan);
				System.out.println("White: ");
				displayClothes(white);
				System.out.println("Pink: ");
				displayClothes(green);
			default:
				repeat = false;
				break;
			}
		}
	}


	public void viewItems() {
		boolean repeat = true;
		System.out.println("------------------------------------");
		System.out.println("VIEWING CLOSET");
		System.out.println("------------------------------------");
		while (repeat) {
			ArrayList<Option> options = new ArrayList<Option>();
			options.add(new Option("View by occasion", 'o'));
			options.add(new Option("View by color", 'c'));
			options.add(new Option("Search for an item", 's'));
			options.add(new Option("View entire closet", 'e'));
			options.add(new Option("Back", 'x'));
			Choice choice = new Choice("How would you like to filter your closet items?", options);
			char option = choice.performChoice();

			switch (option) {
			case 'o':
				viewOccasion();
				break;
			case 'c':
				viewColor();
				break;
			case 'e':
				displayClothes(allClothes);
				break;
			case 's':
				System.out.println("Enter a searchterm: ");
				String searchterm = scnr.nextLine();
				displayClothes(search(searchterm));
				break;
			default:
				repeat = false;
				break;
			}
		}
	}

}
