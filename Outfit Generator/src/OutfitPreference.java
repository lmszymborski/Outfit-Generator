import java.util.ArrayList;

public class OutfitPreference {

	TemperatureRange range;
	Occasion occasion;

	public enum TemperatureRange {
		FREEZING, COLD, MEDIUM, HOT
	}

	public enum Occasion {
		DATENIGHT, FORMAL, NIGHTOUT, EVERYDAY, BUSINESS
	}

	public OutfitPreference() {
		setTemperatureRange();
		setOccasion();
	}
	
	public TemperatureRange getRange() {
		return range;
	}
	
	public Occasion getOccasion() {
		return occasion;
	}

	public void setOccasion() {
		ArrayList<Option> options = new ArrayList<Option>();
		options.add(new Option("Date night", 'd'));
		options.add(new Option("Formal", 'f'));
		options.add(new Option("Night out", 'n'));
		options.add(new Option("Everyday", 'e'));
		options.add(new Option("Business", 'b'));
		Choice choice = new Choice("What occasion would you like an outfit for?", options);
		char choiceChar = choice.performChoice();

		switch (choiceChar) {
		case 'd':
			occasion = Occasion.DATENIGHT;
			break;
		case 'f':
			occasion = Occasion.FORMAL;
			break;
		case 'n':
			occasion = Occasion.NIGHTOUT;
			break;
		case 'e':
			occasion = Occasion.EVERYDAY;
			break;
		default:
			occasion = Occasion.BUSINESS;
			break;
		}
	}

	public void setTemperatureRange() {
		// get temperature info
		ArrayList<Option> options = new ArrayList<Option>();
		options.add(new Option("Freezing", 'f'));
		options.add(new Option("Cold", 'c'));
		options.add(new Option("Medium", 'm'));
		options.add(new Option("Hot", 'h'));
		Choice choice = new Choice("What temperature range do you want an outfit for?", options);
		char choiceChar = choice.performChoice();
		// set temperature range
		switch (choiceChar) {
		case 'f':
			range = TemperatureRange.FREEZING;
			break;
		case 'c':
			range = TemperatureRange.COLD;
			break;
		case 'm':
			range = TemperatureRange.MEDIUM;
			break;
		case 'h':
			range = TemperatureRange.HOT;
			break;
		}
	}
}
