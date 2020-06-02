import java.util.ArrayList;

public class Bottoms extends ClothingItem{
	boolean tight;
	
	
	public Bottoms (String name, User user) {
		super(name, user);
	}
	
	public Bottoms(User user, String name, String description, boolean[] ranges, boolean clean, String patterned, boolean denim,
			boolean tight, boolean[] colors, boolean[] occasions, double score, int balance) {
		super(user, name, description, ranges, clean, patterned, denim, colors, occasions, score, balance);
		this.tight = tight;
	}
	
	@Override
	public void getAdditionalInfo() {
		System.out.println("Now, a few questions about your clothing item.");
		setOccasions();
		setRanges();
		setAvailability();
		askPattern();
		setDenim();
		setTight();
		setColors();
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
	
	@Override
	public String toString() {
		String tightString;
		String patternedString;
		String denimString;
		if (tight)
			tightString = "tight";
		else
			tightString = "loose";
		if (isPatterned())
			patternedString = "patterned, " + getPatternType();
		else 
			patternedString = "false";
		if (isDenim())
			denimString = "denim";
		else 
			denimString = "false";
		String s = "";
		s += "Name: " + getName() + "\n";
		s += "Type: bottoms" + "\n";
		s += "Description: " + getDescription() + "\n";
		s += "Temperature ranges: " + rangesToString() + " \n ";
		s += "Availability: ";
		if (isClean())
			s += "clean";
		else
			s += "dirty";
		s += " \n ";
		s += "Tight: " + tightString + "\n";
		s += "Patterned: " + patternedString + " \n ";
		s += "Denim: " + denimString + " \n ";
		s += "Colors: " + colorsToString() + " \n ";
		s += "Occasions: " + occasionsToString();
		return s;
	}
	
	@Override
	public String toStringSave() {
		String tightString;
		String patternedString;
		String denimString;
		if (tight)
			tightString = "tight";
		else
			tightString = "loose";
		if (isPatterned())
			patternedString = "patterned, " + getPatternType();
		else 
			patternedString = "false";
		if (isDenim())
			denimString = "denim";
		else 
			denimString = "false";
		String s = "";
		s += "Name: " + getName() + " $ ";
		s += "Type: bottoms" + "$";
		s += "Description: " + getDescription() + " $ ";
		s += "Temperature ranges: " + rangesToString() + " $ ";
		s += "Availability: ";
		if (isClean())
			s += "clean";
		else
			s += "dirty";
		s += " $ ";
		s += "Tight: " + tightString + "$";
		s += "Patterned: " + patternedString + " $ ";
		s += "Denim: " + denimString + " $ ";
		s += "Colors: " + colorsToString() + " $ ";
		s += "Occasions: " + occasionsToString() + " $ ";
		s += "Balance: " + getBalance() + "$";
		s += "Score: " + getScore();
		return s;
	}
}
