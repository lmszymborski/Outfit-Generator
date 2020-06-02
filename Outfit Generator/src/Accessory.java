import java.util.ArrayList;

public class Accessory extends ClothingItem{
// purse
// jewelry
// hair accessories
	
private AccessoryType subtype;
public enum AccessoryType {
	PURSE, NECKLACE, BRACELET, ANKLET, SCRUNCHIE, GLASSES, HAIRPIECE, OTHER
}
public Accessory (String name, User user) {
	super(name, user);
}

public Accessory(User user, String name, AccessoryType subtype, String description, boolean[] ranges, String patterned, boolean denim,
		boolean[] colors, boolean[] occasions, double score, int balance) {
	super(user, name, description, new boolean[] {true, true, true, true}, true, patterned, denim, colors, occasions, score, balance);
	this.subtype = subtype;
}

@Override
public void getAdditionalInfo() {
	System.out.println("Now, a few questions about your clothing item.");
	setType();
	setOccasions();
	askPattern();
	setDenim();
	setColors();
}

public AccessoryType setType() {
	ArrayList<Option> options = new ArrayList<Option>();
	options.add(new Option("Purse", 'p'));
	options.add(new Option("Necklace", 'l'));
	options.add(new Option("Bracelet", 'b'));
	options.add(new Option("Anklet", 'a'));
	options.add(new Option("Scrunchie", 's'));
	options.add(new Option("Glasses", 'g'));
	options.add(new Option("Hairpiece", 'h'));
	options.add(new Option("Other", 'o'));
	Choice choice = new Choice("What type of accessory is " + getName() + "?", options);
	char option = choice.performChoice();
	if (option == 'p')
		subtype = AccessoryType.PURSE;
	else if (option == 'l')
		subtype =  AccessoryType.NECKLACE;
	else if (option == 'b')
		subtype = AccessoryType.BRACELET;
	else if (option == 'a')
		subtype = AccessoryType.ANKLET;
	else if (option == 's')
		subtype = AccessoryType.SCRUNCHIE;
	else if (option == 'g')
		subtype = AccessoryType.GLASSES;
	else if (option == 'h')
		subtype = AccessoryType.HAIRPIECE;
	else if (option == 'o')
		subtype = AccessoryType.OTHER;
	return subtype;
}

private String accessoryToString() {
	if (subtype == AccessoryType.PURSE)
		return "purse";
	else if (subtype == AccessoryType.NECKLACE)
		return "necklace";
	else if (subtype == AccessoryType.BRACELET)
		return "bracelet";
	else if (subtype == AccessoryType.ANKLET)
		return "anklet";
	else if (subtype == AccessoryType.SCRUNCHIE)
		return "scrunchie";
	else if (subtype == AccessoryType.GLASSES)
		return "glasses";
	else if (subtype == AccessoryType.HAIRPIECE)
		return "hairpiece";
	else
		return "other";
}

public AccessoryType getSubtype() {
	return subtype;
}

@Override
public String toString() {
	String patternedString;
	String denimString;
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
	s += "Type: accessory, " + accessoryToString() + "\n";
	s += "Description: " + getDescription() + "\n";
	s += "Temperature ranges: " + rangesToString() + " \n ";
	s += "Patterned: " + patternedString + " \n ";
	s += "Denim: " + denimString + " \n ";
	s += "Colors: " + colorsToString() + " \n ";
	s += "Occasions: " + occasionsToString();
	return s;
}

@Override
public String toStringSave() {
	String patternedString;
	String denimString;
	if (isPatterned())
		patternedString = "patterned, " + getPatternType();
	else 
		patternedString = "false";
	if (isDenim())
		denimString = "denim";
	else 
		denimString = "false";
	String s = "";
	s += "Name: " + getName() + "$";
	s += "Type: accessory, " + accessoryToString() + "$";
	s += "Description: " + getDescription() + "$";
	s += "Availability: ";
	s += "Patterned: " + patternedString + " $ ";
	s += "Denim: " + denimString + " $ ";
	s += "Colors: " + colorsToString() + " $ ";
	s += "Occasions: " + occasionsToString() + "$";
	s += "Balance: " + getBalance() + "$";
	s += "Score: " + getScore();
	return s;
}

}
