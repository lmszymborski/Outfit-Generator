
public class Socks extends ClothingItem{
	public Socks (String name, User user) {
		super(name, user);
	}
	
	public Socks(User user, String name, String description, boolean[] ranges, boolean clean, String patterned, boolean denim,
			boolean[] colors, boolean[] occasions, double score, int balance) {
		super(user, name, description, ranges, clean, patterned, denim, colors, occasions, score, balance);
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
		s += "Type: socks \n";
		s += "Description: " + getDescription() + "\n";
		s += "Temperature ranges: " + rangesToString() + " \n ";
		s += "Availability: ";
		if (isClean())
			s += "clean";
		else
			s += "dirty";
		s += " \n ";
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
		s += "Type: socks $";
		s += "Description: " + getDescription() + "$";
		s += "Temperature ranges: " + rangesToString() + " $ ";
		s += "Availability: ";
		if (isClean())
			s += "clean";
		else
			s += "dirty";
		s += " $ ";
		s += "Patterned: " + patternedString + " $ ";
		s += "Denim: " + denimString + " $ ";
		s += "Colors: " + colorsToString() + " $ ";
		s += "Occasions: " + occasionsToString() + " $ ";
		s += "Balance: " + getBalance() + "$";
		s += "Score: " + getScore();
		return s;
	}
}
