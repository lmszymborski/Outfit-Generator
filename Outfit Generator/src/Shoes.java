
public class Shoes extends ClothingItem{
	public Shoes (String name, User user) {
		super(name, user);
	}
	public Shoes(User user, String name, String description, boolean[] ranges, String patterned, boolean denim,
			boolean[] colors, boolean[] occasions, double score, int balance) {
		super(user, name, description, ranges, true, patterned, denim, colors, occasions, score, balance);
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
		s += "Type: shoes \n";
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
		s += "Type: shoes $";
		s += "Description: " + getDescription() + "$";
		s += "Temperature ranges: " + rangesToString() + " $ ";
		s += "Availability: ";
		s += "Patterned: " + patternedString + " $ ";
		s += "Denim: " + denimString + " $ ";
		s += "Colors: " + colorsToString() + " $ ";
		s += "Occasions: " + occasionsToString() + " $ ";
		s += "Balance: " + getBalance() + "$";
		s += "Score: " + getScore();
		return s;
	}
}
