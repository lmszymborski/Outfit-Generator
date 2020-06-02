import java.util.ArrayList;
import java.util.Scanner;

public class ClothingItem {
	private Scanner scnr;

	
	//TODO: each item has a score assigned for how much the user likes the item, this gets increased or decreased by
	// how much they like outfits with the item
	private double score; // percent of favorability - will have to be careful not to make this favor outfits with more items
	// maybe take average of scores?? have to decide out of 100 or out of 1
	private String name;
	private String description;
	private boolean[] ranges;
	private boolean clean;
	private Pattern patterned;
	private boolean[] occasions;
	private boolean denim;
	private boolean[] colors;
	private boolean tight;
	private User user;
	private int balance;

	public enum TemperatureRange {
		FREEZING, COLD, MEDIUM, HOT
	}

	public enum Occasion {
		DATENIGHT, FORMAL, NIGHTOUT, EVERYDAY, BUSINESS
	}

	public enum Colors {
		RED, ORANGE, YELLOW, GREEN, BLUE, PURPLE, BLACK, BROWN, TAN, WHITE, PINK, GREY
	}

	public ClothingItem(String name, User user) {
		this.name = name;
		this.user = user;
		ranges = new boolean[] { true, true, true, true };
		clean = true;
		description = "N/A";
		patterned = new Pattern();
		occasions = new boolean[] { true, true, true, true };
		denim = false;
		colors = new boolean[] { true, true, true, true, true, true, true, true, true, true, true, true };
		occasions = new boolean[] { true, true, true, true, true };
		tight = true;
		score = 80;
		balance = 0;

		scnr = new Scanner(System.in);
		setDescription();
		getAdditionalInfo();
		System.out.println(name + " added to closet!");
	}
	

	public ClothingItem(User user, String name, String description, boolean[] ranges, boolean clean, String patternName,
			boolean denim, boolean[] colors, boolean[] occasions, double score, int balance) {
		scnr = new Scanner(System.in);
		this.user = user;
		this.name = name;
		this.description = description;
		this.ranges = ranges;
		this.clean = clean;
		this.patterned = new Pattern(setPattern(patternName));
		this.occasions = occasions;
		this.denim = denim;
		this.colors = colors;
		this.occasions = occasions;
		this.score = score;
		this.balance = balance;
		System.out.print("Name: " + name + ", ");
		System.out.println("score: " + score);
	}

	public void getAdditionalInfo() {
		System.out.println("Now, a few questions about your clothing item.");
		setOccasions();
		setRanges();
		setAvailability();
		askPattern();
		setDenim();
		setColors();
	}

	public double setScore(double newScore) {
		score = newScore;
		return score;
	}
	
	public double adjustScore(double percent) {
		score = score * percent;
		if (score > 100)
			score = 100;
		return score;
	}
	
	public double getScore() {
		return score;
	}
	
	public int increaseBalance(boolean increase) {
		if (increase)
			balance++;
		else
			balance--;
		return balance;
	}
	
	public int getBalance() {
		return balance;
	}

	
	public String getPatternType() {
		if (patterned != null) {
			return patterned.getPatternName();
		}
		return null;
	}

	public boolean isTight() {
		return tight;
	}

	public boolean setTight() {
		ArrayList<Option> options = new ArrayList<Option>();
		options.add(new Option("Yes", 'y'));
		options.add(new Option("No", 'n'));
		options.add(new Option("Skip", 's'));
		Choice choice = new Choice("Is this item tight?", options);
		char option = choice.performChoice();
		if (option == 'y')
			tight = true;
		else if (option == 'n') {
			tight = false;
		}
		return tight;
	}

	public boolean[] getOccasions() {
		return occasions;
	}

	public boolean[] setOccasions() {
		System.out.println(
				"Selecting an occasion will take your clothing item out of that category. Selecting it again will put it back in.");
		System.out.println("Press skip/done when satisfied");
		char option;
		// TODO: need a better way to approach this
		do {
			System.out.println(
					"Currently, this item is set to be suited for the following occasions: " + occasionsToString());
			ArrayList<Option> options = new ArrayList<Option>();
			options.add(new Option("Datenight", 'd'));
			options.add(new Option("Formal", 'f'));
			options.add(new Option("Nightout", 'n'));
			options.add(new Option("Everyday", 'e'));
			options.add(new Option("Business", 'b'));
			options.add(new Option("Reset: this sets all to false", 'r'));
			options.add(new Option("Skip/done", 's'));
			Choice choice = new Choice("Select occasions to delete from this item:", options);
			option = choice.performChoice();
			switch (option) {
			case 'd':
				occasions[0] = !occasions[0];
				break;
			case 'f':
				occasions[1] = !occasions[1];
				break;
			case 'n':
				occasions[2] = !occasions[2];
				break;
			case 'e':
				occasions[3] = !occasions[3];
				break;
			case 'b':
				occasions[4] = !occasions[4];
				break;
			case 'r':
				occasions = new boolean[] { false, false, false, false, false };
				break;
			}
		} while (option != 's');
		return ranges;
	}

	private String stringOccasions(Occasion occasion) {
		switch (occasion) {
		case DATENIGHT:
			return "datenight";
		case FORMAL:
			return "formal";
		case NIGHTOUT:
			return "nightout";
		case EVERYDAY:
			return "everyday";
		default:
			return "business";
		}
	}

	public String occasionsToString() {
		Occasion[] occasionEquivalent = new Occasion[] { Occasion.DATENIGHT, Occasion.FORMAL, Occasion.NIGHTOUT,
				Occasion.EVERYDAY, Occasion.BUSINESS };
		String s = "";
		int numTrue = 0;
		int count = 0;
		for (int i = 0; i < occasions.length; i++) {
			if (occasions[i]) {
				numTrue++;
			}
		}
		for (int i = 0; i < occasions.length; i++) {
			if (occasions[i]) {
				count++;
				s += stringOccasions(occasionEquivalent[i]);
				if (count < numTrue)
					s += ", ";
			}
		}
		if (s.equals("")) {
			s = "no occasions";
		}
		return s;
	}

	public String setPattern(String patternName) {
		patterned = new Pattern();
		if (patternName == null)
			return null;
		if (!user.patternNameExists(patternName))
			user.addPatternType(patternName);
		patterned.setPatternName(patternName);
		return patternName;
	}

	public Pattern askPattern() {
		ArrayList<Option> options = new ArrayList<Option>();
		options.add(new Option("Yes", 'y'));
		options.add(new Option("No", 'n'));
		options.add(new Option("Skip", 's'));
		Choice choice = new Choice("Is this item patterned?", options);
		char option = choice.performChoice();
		if (option == 'y') {

			// display options of pattern along with the option to add new pattern
			ArrayList<Option> options1 = new ArrayList<Option>();
			char letter = 'a';
			for (int i = 0; i < user.getPatternTypes().size(); i++) {
				options1.add(new Option(user.getPatternTypes().get(i), letter++));
			}
			options1.add(new Option("add new pattern", '0'));
			Choice choice1 = new Choice("What type of pattern?", options1);
			char option1 = choice1.performChoice();

			// set new pattern name and save it to user
			letter = 'a';
			if (option1 == '0') {
				System.out.println("Enter the name for your new pattern:");
				String newPatternName = scnr.nextLine().trim();
				setPattern(newPatternName);
			}

			for (int i = 0; i < user.getPatternTypes().size(); i++) {
				if (option1 == letter++)
					patterned.setPatternName(user.getPatternTypes().get(i));
			}
		} else if (option == 'n') {
			patterned = null;
		}
		return patterned;
	}

	public boolean isPatterned() {
		if (getPatternType() ==  null)
			return false;
		return true;
	}

	public boolean setDenim() {
		ArrayList<Option> options = new ArrayList<Option>();
		options.add(new Option("Yes", 'y'));
		options.add(new Option("No", 'n'));
		options.add(new Option("Skip", 's'));
		Choice choice = new Choice("Is this item denim?", options);
		char option = choice.performChoice();
		if (option == 'y')
			denim = true;
		else if (option == 'n') {
			denim = false;
		}
		return denim;
	}

	public boolean isDenim() {
		return denim;
	}

	public boolean setAvailability() {
		ArrayList<Option> options = new ArrayList<Option>();
		options.add(new Option("Yes", 'y'));
		options.add(new Option("No", 'n'));
		options.add(new Option("Skip", 's'));
		Choice choice = new Choice("Is this item clean/ready to be worn?", options);
		char option = choice.performChoice();
		if (option == 'y')
			clean = true;
		else if (option == 'n') {
			System.out.println(name + " moved to hamper");
			clean = false;
		}
		return clean;
	}

	public boolean isClean() {
		return clean;
	}

	public boolean[] setColors() {
		System.out.println("Selecting a color will put it in that color category. Selecting it again will take it out");
		System.out.println("Press skip/done when satisfied");
		char option;

		do {
			System.out
					.println("Currently, this item is set to be suited for the following colors: " + colorsToString());
			ArrayList<Option> options = new ArrayList<Option>();
			options.add(new Option("Red", 'e'));
			options.add(new Option("Orange", 'o'));
			options.add(new Option("Yellow", 'y'));
			options.add(new Option("Green", 'g'));
			options.add(new Option("Blue", 'b'));
			options.add(new Option("Purple", 'p'));
			options.add(new Option("Black", 'l'));
			options.add(new Option("Brown", 'n'));
			options.add(new Option("Tan", 't'));
			options.add(new Option("White", 'w'));
			options.add(new Option("Pink", 'i'));
			options.add(new Option("Grey", 'v'));
			options.add(new Option("Reset: this sets all to false", 'r'));
			options.add(new Option("Skip/done", 's'));
			Choice choice = new Choice("Select a color to add or delete to this item:", options);
			option = choice.performChoice();

			switch (option) {
			case 'e':
				colors[0] = !colors[0];
				break;
			case 'o':
				colors[1] = !colors[1];
				break;
			case 'y':
				colors[2] = !colors[2];
				break;
			case 'g':
				colors[3] = !colors[3];
				break;
			case 'b':
				colors[4] = !colors[4];
				break;
			case 'p':
				colors[5] = !colors[5];
				break;
			case 'l':
				colors[6] = !colors[6];
				break;
			case 'n':
				colors[7] = !colors[7];
				break;
			case 't':
				colors[8] = !colors[8];
				break;
			case 'w':
				colors[9] = !colors[9];
				break;
			case 'i':
				colors[10] = !colors[10];
				break;
			case 'v':
				colors[11] = !colors[11];
				break;
			case 'r':
				colors = new boolean[] { false, false, false, false, false, false, false, false, false, false, false,
						false };
				break;
			}
		} while (option != 's');
		return colors;
	}

	private String stringColors(Colors color) {
		switch (color) {
		case RED:
			return "red";
		case ORANGE:
			return "orange";
		case YELLOW:
			return "yellow";
		case GREEN:
			return "green";
		case BLUE:
			return "blue";
		case PURPLE:
			return "purple";
		case BLACK:
			return "black";
		case BROWN:
			return "brown";
		case TAN:
			return "tan";
		case WHITE:
			return "white";
		case GREY:
			return "grey";
		default:
			return "pink";
		}
	}

	public String colorsToString() {
		Colors[] colorEquivalent = new Colors[] { Colors.RED, Colors.ORANGE, Colors.YELLOW, Colors.GREEN, Colors.BLUE,
				Colors.PURPLE, Colors.BLACK, Colors.BROWN, Colors.TAN, Colors.WHITE, Colors.PINK, Colors.GREY };
		String s = "";
		int numTrue = 0;
		int count = 0;
		for (int i = 0; i < colors.length; i++) {
			if (colors[i]) {
				numTrue++;
			}
		}
		for (int i = 0; i < colors.length; i++) {
			if (colors[i]) {
				count++;
				s += stringColors(colorEquivalent[i]);
				if (count < numTrue)
					s += ", ";
			}
		}
		if (s.equals("")) {
			s = "no colors selected";
		}
		return s;
	}

	public boolean[] getColors() {
		return colors;
	}

	public boolean[] setRanges() {
		System.out.println(
				"Selecting a temperature range will take it out of that category. Selecting it again will take it out.");
		System.out.println("Press skip/done when satisfied");
		char option;
		// TODO: need a better way to approach this
		do {
			System.out.println("Currently, this item is set to be suited for the following temperature ranges: "
					+ rangesToString());
			ArrayList<Option> options = new ArrayList<Option>();
			options.add(new Option("Freezing", 'f'));
			options.add(new Option("Cold", 'c'));
			options.add(new Option("Medium", 'm'));
			options.add(new Option("Hot", 'h'));
			options.add(new Option("Reset: this sets all to false", 'r'));
			options.add(new Option("Skip/done", 's'));
			Choice choice = new Choice("Select temperatures to delete from temperature ranges:", options);
			option = choice.performChoice();
			switch (option) {
			case 'f':
				ranges[0] = !ranges[0];
				break;
			case 'c':
				ranges[1] = !ranges[1];
				break;
			case 'm':
				ranges[2] = !ranges[2];
				break;
			case 'h':
				ranges[3] = !ranges[3];
				break;
			case 'r':
				ranges = new boolean[] { false, false, false, false };
				break;
			}
		} while (option != 's');
		return ranges;
	}

	private String stringTempRange(TemperatureRange range) {
		switch (range) {
		case FREEZING:
			return "freezing";
		case COLD:
			return "cold";
		case MEDIUM:
			return "medium";
		default:
			return "hot";
		}
	}

	public String rangesToString() {
		TemperatureRange[] rangeEquivalent = new TemperatureRange[] { TemperatureRange.FREEZING, TemperatureRange.COLD,
				TemperatureRange.MEDIUM, TemperatureRange.HOT };
		String s = "";
		int numTrue = 0;
		int count = 0;
		for (int i = 0; i < ranges.length; i++) {
			if (ranges[i]) {
				numTrue++;
			}
		}
		for (int i = 0; i < ranges.length; i++) {
			if (ranges[i]) {
				count++;
				s += stringTempRange(rangeEquivalent[i]);
				if (count < numTrue)
					s += ", ";
			}
		}
		if (s.equals("")) {
			s = "no temperature ranges";
		}
		return s;
	}

	public boolean[] getRanges() {
		return ranges;
	}

	public String setName() {
		askName();
		return name;
	}

	public String askName() {
		System.out.println("What would you like to name this clothing item?");
		name = scnr.nextLine();
		return name;
	}

	public String getName() {
		return name;
	}

	public String setDescription() {
		askDescription();
		return description;
	}

	private void askDescription() {
		System.out.println("How would you describe this clothing item?");
		System.out.println("Skip (s)");
		String tempDescription = scnr.nextLine();
		if (tempDescription.equals("s")) {
			return;
		}
		description = tempDescription;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		String patternedString;
		String denimString;
		String tightString;
		if (patterned != null)
			patternedString = "patterned, " + patterned.getPatternName();
		else
			patternedString = "false";
		if (denim)
			denimString = "denim";
		else
			denimString = "false";
		if (tight)
			tightString = "tight";
		else
			tightString = "false";
		String s = "";
		s += "Name: " + name + "\n";
		s += "Description: " + description + "\n";
		s += "Temperature ranges: " + rangesToString() + "\n";
		s += "Availability: ";
		if (clean)
			s += "clean";
		else
			s += "dirty";
		s += "\n";
		s += "Patterned: " + patternedString + "\n";
		s += "Denim: " + denimString + "\n";
		s += "Colors: " + colorsToString() + "\n";
		s += "Occasions: " + occasionsToString();
		return s;
	}

	public String toStringSave() {
		String tightString;
		String patternedString;
		String denimString;
		if (patterned != null)
			patternedString = "patterned, " + patterned.getPatternName();
		else
			patternedString = "false";
		if (denim)
			denimString = "denim";
		else
			denimString = "false";
		if (tight)
			tightString = "tight";
		else
			tightString = "false";
		String s = "";
		s += "Name: " + name + " $ ";
		s += "Description: " + description + " $ ";
		s += "Temperature ranges: " + rangesToString() + " $ ";
		s += "Availability: ";
		if (clean)
			s += "clean";
		else
			s += "dirty";
		s += " $ ";
		s += "Patterned: " + patternedString + " $ ";
		s += "Denim: " + denimString + " $ ";
		s += "Colors: " + colorsToString() + " $ ";
		s += "Occasions: " + occasionsToString() + " $ ";
		s += "Score: " + score;
		return s;
	}

}
