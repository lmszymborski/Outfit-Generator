import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Outfit {
	public Top top;
	private Bottoms bottoms;
	private Dress dress;
	private Shoes shoes;
	private Socks socks;
	private Accessory[] unusedAccessories;
	private Jacket jacket;
	
	private boolean viewed = false;
	private boolean chosenOrLiked = false;
	private double outfitScore;

	private Accessory[] chosenAccessories;

	private boolean mixPatterns;
	private int numDifferentPatterns;
	private int numAccessories;
	private int numPatterns;

	private User user;

	private Random rand;

	private ArrayList<ClothingItem> clothingItems;

	// type 1
	public Outfit(User user, Top top, Bottoms bottoms, Shoes shoes) {
		
		
		this.top = top;
		this.bottoms = bottoms;
		this.shoes = shoes;
		numDifferentPatterns = 0;
		this.user = user;
		rand = new Random();
		this.clothingItems = new ArrayList<ClothingItem>();
		this.clothingItems = getArrayList();
		
		outfitScore = getAverage(clothingItems);
	}

// type 2
	public Outfit(User user, Top top, Bottoms bottoms, Shoes shoes, Socks socks) {
		this.top = top;
		this.bottoms = bottoms;
		this.shoes = shoes;
		this.socks = socks;
		numDifferentPatterns = 0;
		this.user = user;
		rand = new Random();
		this.clothingItems = new ArrayList<ClothingItem>();
		this.clothingItems = getArrayList();
		outfitScore = getAverage(clothingItems);
	}

// type 3
	public Outfit(User user, Top top, Bottoms bottoms, Accessory[] accessories, Shoes shoes) {
		System.out.println("outfit with accessory");
		this.top = top;
		
		//this.clothingItems = new ArrayList<ClothingItem>();
		this.bottoms = bottoms;
		this.unusedAccessories = accessories;
		this.shoes = shoes;
		numDifferentPatterns = 0;
		this.user = user;
		rand = new Random();
		this.clothingItems = new ArrayList<ClothingItem>();
		this.clothingItems = getArrayList();
		chosenAccessories = determineAccessories(filterOutNull(accessories));
		outfitScore = getAverage(clothingItems);
	}

// type 4
	public Outfit(User user, Top top, Bottoms bottoms, Jacket jacket, Shoes shoes) {
		this.top = top;
		
		//this.clothingItems = new ArrayList<ClothingItem>();
		this.bottoms = bottoms;
		this.jacket = jacket;
		this.shoes = shoes;
		numDifferentPatterns = 0;
		this.user = user;
		rand = new Random();
		this.clothingItems = new ArrayList<ClothingItem>();
		this.clothingItems = getArrayList();
		outfitScore = getAverage(clothingItems);
	}

// type 5
	public Outfit(User user, Top top, Bottoms bottoms, Accessory[] accessories, Jacket jacket, Shoes shoes) {
		this.top = top;

		//this.clothingItems = new ArrayList<ClothingItem>();
		this.bottoms = bottoms;
		this.unusedAccessories = accessories;
		this.jacket = jacket;
		this.shoes = shoes;
		numDifferentPatterns = 0;
		this.user = user;
		rand = new Random();
		this.clothingItems = new ArrayList<ClothingItem>();
		this.clothingItems = getArrayList();
		chosenAccessories = determineAccessories(filterOutNull(accessories));
		outfitScore = getAverage(clothingItems);
	}

// type 6
	public Outfit(User user, Top top, Bottoms bottoms, Accessory[] accessories, Socks socks, Shoes shoes) {
		this.top = top;

		//this.clothingItems = new ArrayList<ClothingItem>();
		this.bottoms = bottoms;
		this.unusedAccessories = accessories;
		this.socks = socks;
		this.shoes = shoes;
		numDifferentPatterns = 0;
		this.user = user;
		rand = new Random();
		this.clothingItems = new ArrayList<ClothingItem>();
		this.clothingItems = getArrayList();
		chosenAccessories = determineAccessories(filterOutNull(accessories));
		outfitScore = getAverage(clothingItems);
	}

//type 7
	public Outfit(User user, Top top, Bottoms bottoms, Jacket jacket, Socks socks, Shoes shoes) {
		this.top = top;

		//this.clothingItems = new ArrayList<ClothingItem>();
		this.bottoms = bottoms;
		this.jacket = jacket;
		this.jacket = jacket;
		this.shoes = shoes;
		this.user = user;
		this.clothingItems = new ArrayList<ClothingItem>();
		this.clothingItems = getArrayList();
		rand = new Random();
		outfitScore = getAverage(clothingItems);
	}

//type 8
	public Outfit(User user, Top top, Bottoms bottoms, Accessory[] accessories, Jacket jacket, Socks socks,
			Shoes shoes) {
		this.top = top;

		//this.clothingItems = new ArrayList<ClothingItem>();
		this.bottoms = bottoms;
		this.unusedAccessories = accessories;
		this.jacket = jacket;
		this.shoes = shoes;
		this.socks = socks;
		this.user = user;
		rand = new Random();
		this.clothingItems = new ArrayList<ClothingItem>();
		this.clothingItems = getArrayList();
		chosenAccessories = determineAccessories(filterOutNull(accessories));
		outfitScore = getAverage(clothingItems);
	}

//type 9
	public Outfit(User user, Dress dress, Shoes shoes) {
		this.dress = dress;

		//this.clothingItems = new ArrayList<ClothingItem>();
		this.shoes = shoes;
		this.user = user;
		rand = new Random();
		this.clothingItems = new ArrayList<ClothingItem>();
		this.clothingItems = getArrayList();
		outfitScore = getAverage(clothingItems);
	}

//type 10
	public Outfit(User user, Dress dress, Shoes shoes, Socks socks) {
		this.dress = dress;

		//this.clothingItems = new ArrayList<ClothingItem>();
		this.shoes = shoes;
		this.socks = socks;
		this.user = user;
		rand = new Random();
		this.clothingItems = new ArrayList<ClothingItem>();
		this.clothingItems = getArrayList();
		outfitScore = getAverage(clothingItems);
	}

// type 11
	public Outfit(User user, Dress dress, Accessory[] accessories, Shoes shoes) {
		this.dress = dress;

		//this.clothingItems = new ArrayList<ClothingItem>();
		this.shoes = shoes;
		this.unusedAccessories = accessories;
		this.user = user;
		rand = new Random();
		this.clothingItems = new ArrayList<ClothingItem>();
		this.clothingItems = getArrayList();
		chosenAccessories = determineAccessories(filterOutNull(accessories));
		outfitScore = getAverage(clothingItems);
	}

//type 12
	public Outfit(User user, Dress dress, Jacket jacket, Shoes shoes) {
		this.dress = dress;

		//this.clothingItems = new ArrayList<ClothingItem>();
		this.shoes = shoes;
		this.jacket = jacket;
		this.user = user;
		rand = new Random();
		this.clothingItems = new ArrayList<ClothingItem>();
		this.clothingItems = getArrayList();
		outfitScore = getAverage(clothingItems);
	}

//type 13
	public Outfit(User user, Dress dress, Accessory[] accessories, Jacket jacket, Shoes shoes) {
		this.dress = dress;

		//this.clothingItems = new ArrayList<ClothingItem>();
		this.shoes = shoes;
		this.jacket = jacket;
		this.unusedAccessories = accessories;
		this.user = user;
		rand = new Random();
		this.clothingItems = new ArrayList<ClothingItem>();
		this.clothingItems = getArrayList();
		chosenAccessories = determineAccessories(filterOutNull(accessories));
		outfitScore = getAverage(clothingItems);
	}

//type 14
	public Outfit(User user, Dress dress, Accessory[] accessories, Socks socks, Shoes shoes) {
		this.dress = dress;

		//this.clothingItems = new ArrayList<ClothingItem>();
		this.shoes = shoes;
		this.socks = socks;
		this.unusedAccessories = accessories;
		this.user = user;
		rand = new Random();
		this.clothingItems = new ArrayList<ClothingItem>();
		this.clothingItems = getArrayList();
		chosenAccessories = determineAccessories(filterOutNull(accessories));
		outfitScore = getAverage(clothingItems);
	}

//type 15
	public Outfit(User user, Dress dress, Jacket jacket, Socks socks, Shoes shoes) {
		this.dress = dress;

		//this.clothingItems = new ArrayList<ClothingItem>();
		this.shoes = shoes;
		this.jacket = jacket;
		this.socks = socks;
		this.user = user;

		rand = new Random();
		this.clothingItems = new ArrayList<ClothingItem>();
		this.clothingItems = getArrayList();
		outfitScore = getAverage(clothingItems);
	}

//type 16
	public Outfit(User user, Dress dress, Accessory[] accessories, Jacket jacket, Socks socks, Shoes shoes) {
		this.user = user;
		this.clothingItems = getArrayList();
		//this.clothingItems = new ArrayList<ClothingItem>();
		this.dress = dress;
		this.shoes = shoes;
		this.jacket = jacket;
		this.unusedAccessories = accessories;
		this.socks = socks;
		rand = new Random();
		this.clothingItems = new ArrayList<ClothingItem>();
		chosenAccessories = determineAccessories(filterOutNull(accessories));
		outfitScore = getAverage(clothingItems);
	}

	/**
	 * 
	 * @param boost - if true, the user specifically said they disliked the outfit. Have a higher penalty
	 */
	public void decreaseScores(boolean boost) {
		for (int i = 0; i < clothingItems.size(); i ++) {
			clothingItems.get(i).increaseBalance(false);
			int balance = clothingItems.get(i).getBalance();
			double adjust = 0;
			if (balance < -4) { // if the item has been used disliked in the past, the computer will
				adjust = .85; // be more harsh on the score
			} else if (balance < -2) {
				adjust = .87;
			} else if (balance == 0) {
				adjust = .9;
			} else if (balance < 2) {
				adjust = .93;
			} else if (balance < 4) {
				adjust = .95;
			}  else if (balance >= 4) { // if the user consistently likes this outfit, 
				adjust = 1; // the computer will decrease the score less harshly
			}
			clothingItems.get(i).adjustScore(adjust);
		}
		if (isMixedPattern()) {
			user.increaseMixPatternBalance(false);
			int balance = user.getMixPatternsBalance();
			double adjust = 0;
			if (balance < -2)
				adjust = .7;
			if (balance < 0)
				adjust = .8;
			else if (balance == 0) 
				adjust = .9;
			else if (balance > 0)
				adjust = .95;
			
			user.adjustMixPatterns(adjust);
		}
	}
	
	/**
	 * 
	 * @param boost - if true, the user specifically said they liked the outfit. Have a higher reward
	 */
	public void increaseScores(boolean boost) {

		
		// this algorithm is more generous because increaseScores occurs less often
		for (int i = 0; i < clothingItems.size(); i ++) {
			clothingItems.get(i).increaseBalance(true);
			int balance = clothingItems.get(i).getBalance();
			double adjust = 0;
			if (balance < -4) { // if the item has been used disliked in the past, the computer will
				adjust = 1.2; // be less apt to increase the score
			} else if (balance < -2) {
				adjust = 1.3;
			} else if (balance == 0) {
				adjust = 1.4;
			} else if (balance < 2) {
				adjust = 1.5;
			} else if (balance < 4) {
				adjust = 1.6;
			}  else if (balance >= 4) { // if the user consistently likes this outfit, 
				adjust = 1.7; // the computer will increase the score more liberally
			}
			clothingItems.get(i).adjustScore(adjust);
		}
		if (isMixedPattern()) {
			user.increaseMixPatternBalance(true);
			int balance = user.getMixPatternsBalance();
			double adjust = 0;
			if (balance < -2)
				adjust = 1.2;
			if (balance < 0)
				adjust = 1.3;
			else if (balance == 0) 
				adjust = 1.4;
			else if (balance > 0)
				adjust = 1.5;
			
			user.adjustMixPatterns(adjust);
		}
	}
	
	public double getAverage(ArrayList<ClothingItem> clothingItems) {
		int sum = 0;
		for (int i = 0; i < clothingItems.size(); i++) {
			sum += clothingItems.get(i).getScore();
		}
		return sum / clothingItems.size();
	}
	
	public boolean isMixedPattern() {
		if (getNumDifferentPatterns() > 1)
			return true;
		return false;
	}

	public int getNumPatterns() {
		//ArrayList<ClothingItem> clothesInOutfit = filterOutNull(getArrayList());
		ArrayList<ClothingItem> clothesInOutfit = getArrayList();
		String patternType = "";
		numPatterns = 0;
		numDifferentPatterns = 0;
		for (int i = 0; i < clothesInOutfit.size(); i++) {
			if (clothesInOutfit.get(i).isPatterned()) {
				numPatterns++;
				if (!clothesInOutfit.get(i).getPatternType().equals(patternType))
					numDifferentPatterns++;
				patternType = clothesInOutfit.get(i).getPatternType();
			}
		}
		return numPatterns;
	}

	public int getNumDifferentPatterns() {
		getNumPatterns();
		return numDifferentPatterns;
	}
	
	public boolean isViewed() {
		if (viewed)
			return true;
		return false;
	}
	
	public boolean setViewed(boolean viewed) {
		this.viewed = viewed;
		return this.viewed;
	}
	
	public boolean isChosenOrLiked() {
		if (chosenOrLiked)
			return true;
		return false;
	}
	
	public boolean setChosenOrLiked(boolean chosenOrLiked) {
		this.chosenOrLiked = chosenOrLiked;
		return this.chosenOrLiked;
	}

	public ArrayList<ClothingItem> getArrayList() {
		ArrayList<ClothingItem> newList = new ArrayList<ClothingItem>();
		if (top != null)
			newList.add(top);
		if (bottoms != null)
			newList.add(bottoms);
		if (dress != null)
			newList.add(dress);
		if (shoes != null)
			newList.add(shoes);
		if (jacket != null)
			newList.add(jacket);
		if (socks != null)
			newList.add(socks);
		if (chosenAccessories != null) {
			for (int i = 0; i < chosenAccessories.length; i++) {
				newList.add(chosenAccessories[i]);
			}
		}
		return newList;
	}

	public ArrayList<ClothingItem> getArrayListNoAccessories() {
		ArrayList<ClothingItem> clothingItemsNoAccessories = new ArrayList<ClothingItem>();
		if (top != null)
			clothingItemsNoAccessories.add(top);
		if (bottoms != null)
			clothingItemsNoAccessories.add(bottoms);
		if (dress != null)
			clothingItemsNoAccessories.add(dress);
		if (shoes != null)
			clothingItemsNoAccessories.add(shoes);
		if (jacket != null)
			clothingItemsNoAccessories.add(jacket);
		if (socks != null)
			clothingItemsNoAccessories.add(socks);
		return clothingItemsNoAccessories;
	}

	private int determineNewMaxAccessories(int originalMax) {
		int newMaxAccessories = 0;
		for (int i = 0; i < originalMax; i++)  {
			System.out.println("user.chanceAccessorize: " + user.chanceAccessorize);
			if (determineMakeDecision(user.chanceAccessorize))
				newMaxAccessories++;
		}
		
		return newMaxAccessories;
	}
	
	/**
	 * determine which accessories will be used in the outfit might need to move
	 * this into outfit class
	 */
	private Accessory[] determineAccessories(Accessory[] noNullAccessories) {
		//TODO: fix issue where it adds the same accessories because there is nothing preventing this
		//noNullAccessories = filterOutNull(noNullAccessories);
		//System.out.println("noNullAccessories: " + Arrays.toString(noNullAccessories));
		int newMaxAccessories = determineNewMaxAccessories(user.maxAccessories);
		Accessory[] chosenAccessories = new Accessory[newMaxAccessories];
		int numAccessories = 0;
		System.out.println("newMaxAccessories: " + newMaxAccessories);
		while (numAccessories < newMaxAccessories) {
			System.out.println("user.chancePrefMatchAcc " + user.chancePrefMatchAcc);
			if (determineMakeDecision(user.chancePrefMatchAcc)) {
				//ArrayList<ClothingItem> clothesInOutfitNoAccessories = filterOutNull(getArrayListNoAccessories());
				ArrayList<ClothingItem> clothesInOutfitNoAccessories = filterOutNull(getArrayListNoAccessories());
				System.out.print("hi2");
				// add accessory by seeing if there are any matching patterns in the outfit
				ArrayList<Pattern> matchingPatterns = new ArrayList<Pattern>();
				ArrayList<ClothingItem[]> matches = new ArrayList<ClothingItem[]>();
				if (hasPattern(noNullAccessories)) {
					System.out.print("hi");
					for (int i = 0; i < clothesInOutfitNoAccessories.size(); i++) {
						for (int j = 0; j < noNullAccessories.length; j++) {
							if (clothesInOutfitNoAccessories.get(i).getPatternType()!= null &&
									noNullAccessories[j].getPatternType() != null
									&& clothesInOutfitNoAccessories.get(i).getPatternType()
									.equals(noNullAccessories[j].getPatternType())) {
								// adds any similar patterns to matchingPatterns
								matchingPatterns.add(new Pattern(noNullAccessories[j].getPatternType()));
								matches.add(new ClothingItem[] { clothesInOutfitNoAccessories.get(i), noNullAccessories[j] });
							}
						}
					}
					if (matchingPatterns.size() > 0) { // checks if there is a matching pattern set
						for (int i = 0; i < matchingPatterns.size(); i++) { // iterates through each possible matching pattern
							if (!beenChosen((Accessory) matches.get(i)[1])) {
								// can later add in that this decision is weighted slightly by clothing scores or user learned preferences
								if (determineMakeDecision(1 / matchingPatterns.size()))  {
									// add the matching accessory
									chosenAccessories[numAccessories++] = (Accessory) matches.get(i)[1];
									System.out.println("adding an accessory");
								}
							}
						}
					}
				} else {
					chosenAccessories[numAccessories++] = noNullAccessories[rand.nextInt(noNullAccessories.length)];
					System.out.println("adding an accessory");
				}
			} else {
				chosenAccessories[numAccessories++] = noNullAccessories[rand.nextInt(noNullAccessories.length)];
				System.out.println("adding an accessory");
			}
		}
		//System.out.println("chosenAccessories: " + Arrays.toString(chosenAccessories));
		return chosenAccessories;
	}

	private Accessory[] filterOutNull(Accessory[] clothingItems) {
		Accessory[] newList = new Accessory[8];
		System.out.println("clothingItems.length in filterOutNull for accessory: " + clothingItems.length);
		int numAccessories = 0;
		for (int i = 0; i < clothingItems.length; i++) {
			if (clothingItems[i] != null) {
				newList[numAccessories++] = clothingItems[i];
			}
		}
		newList = Arrays.copyOf(newList, numAccessories);
		return newList;
	}
	
	private ArrayList<ClothingItem> filterOutNull(ArrayList<ClothingItem> clothingItems) {
		ArrayList<ClothingItem> newList = new ArrayList<ClothingItem>();
		System.out.println("clothingItems.size() in filterOutNull for clothingItems: " + clothingItems.size());
		for (int i = 0; i < clothingItems.size(); i++) {
			if (clothingItems.get(i) != null) {
				newList.add(clothingItems.get(i));
			}
		}
		return newList;
	}
	
	private boolean beenChosen(Accessory accessory) {
		for (int i = 0; i < chosenAccessories.length; i++) {
			if (chosenAccessories[i].getName().equals(accessory.getName()))
				return true;
		}
		return false;
	}

	private boolean hasPattern(Accessory[] accessories) {
		for (int i = 0; i < accessories.length; i++) {
			if (accessories[i] != null) {
				if (accessories[i].isPatterned())
					return true;
			}
		}
		return false;
	}

	private boolean determineMakeDecision(double chance) {
		return (rand.nextInt(100) < chance * 100);
	}
	
	@Override
	public String toString() {
		String s = "";
		if (top != null) {
			s += "\tTop: \n";
			s += top.toString() + "\n";
		}
		if (bottoms != null) {
			s += "\tBottoms: \n";
			s += bottoms.toString() + "\n";
		}
		s += "\tShoes:  \n";
		s += shoes.toString() + "\n";
		if (jacket != null) {
			s += "\tJacket: \n";
			s += jacket.toString() + "\n";
		}
		if (dress!= null) {
			s += "\tDress: \n";
			s += dress.colorsToString()+ "\n";
		}	
		if (socks != null) {
			s += "\tSocks: \n";
			s += socks.toString()+ "\n";
		}
		if (chosenAccessories != null && chosenAccessories.length > 0) {
			s += "\tAccessories: \n";
			for (int i = 0; i < chosenAccessories.length; i++) {
				s += chosenAccessories[i].toString()+ "\n";
			}
		}
		return s;
	}
	
	public String toStringNames() {
		String s = "";
		if (top != null) {
			s += "\tTop: \n";
			s += top.getName() + "\n";
		}
		if (bottoms != null) {
			s += "\tBottoms: \n";
			s += bottoms.getName() + "\n";
		}
		s += "\tShoes:  \n";
		s += shoes.getName() + "\n";
		if (jacket != null) {
			s += "\tJacket: \n";
			s += jacket.getName() + "\n";
		}
		if (dress!= null) {
			s += "\tDress: \n";
			s += dress.getName()+ "\n";
		}	
		if (socks != null) {
			s += "\tSocks: \n";
			s += socks.getName()+ "\n";
		}
		if (chosenAccessories != null && chosenAccessories.length > 0) {
			s += "\tAccessories: \n";
			for (int i = 0; i < chosenAccessories.length; i++) {
				s += chosenAccessories[i].getName()+ "\n";
			}
		}
		return s;
	}

}
