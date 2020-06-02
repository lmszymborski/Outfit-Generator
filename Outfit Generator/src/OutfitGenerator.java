import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class OutfitGenerator {
	private User user;
	private Closet closet;
	private Random rand;
	private OutfitPreference outfitPreference;

	public OutfitGenerator(User user, Closet closet, OutfitPreference outfitPreference) {
		System.out.println("here");
		this.user = user;
		this.closet = closet;
		this.outfitPreference = outfitPreference;
		rand = new Random();
		generate(outfitPreference.getOccasion(), outfitPreference.getRange());
	}

	public void generate(OutfitPreference.Occasion occasion, OutfitPreference.TemperatureRange range) {
		System.out.println("occasion: " + occasion.toString());
		System.out.println("range: " + range.toString());
		System.out.println("generate");
		int booleanNumOcc = getBooleanArrayNumberOccasion(occasion);
		int booleanNumRange = getBooleanArrayNumberRange(range);
		ArrayList<ClothingItem> availableClothes = new ArrayList<ClothingItem>();
		
		
		
		for (int i = 0; i < closet.allClothes.size(); i++) {
			// check if available, has correct occasion, and correct temperature range
			if (closet.allClothes.get(i).isClean() && closet.allClothes.get(i).getOccasions()[booleanNumOcc] && closet.allClothes.get(i).getRanges()[booleanNumRange]) {
					availableClothes.add(closet.allClothes.get(i));
			}
		}


		// essential components
		ArrayList<Top> possibleTops = new ArrayList<Top>();
		for (int i = 0; i < availableClothes.size(); i++) {
			if (availableClothes.get(i) instanceof Top) { // check if type top
				possibleTops.add((Top) availableClothes.get(i));
			}
		}

		ArrayList<Bottoms> possibleBottoms = new ArrayList<Bottoms>();
		for (int i = 0; i < availableClothes.size(); i++) {
			if (availableClothes.get(i) instanceof Bottoms) { // check if type bottoms
				possibleBottoms.add((Bottoms) availableClothes.get(i));
			}
		}
		// or
		ArrayList<Dress> possibleDresses = new ArrayList<Dress>();
		for (int i = 0; i < availableClothes.size(); i++) {
			if (availableClothes.get(i) instanceof Dress) { // check if type dress
				possibleDresses.add((Dress) availableClothes.get(i));

			}
		}
		
		// always essential
		ArrayList<Shoes> possibleShoes = new ArrayList<Shoes>();
		for (int i = 0; i < availableClothes.size(); i++) {
			if (availableClothes.get(i) instanceof Shoes) { // check if type shoes
				possibleShoes.add((Shoes) availableClothes.get(i));
			}
		}
		// if freezing, also an essential component
		ArrayList<Coat> possibleCoat = new ArrayList<Coat>();;
		for (int i = 0; i < availableClothes.size(); i++) {
			if (availableClothes.get(i) instanceof Coat) { // check if type coat
				possibleCoat.add((Coat) availableClothes.get(i));

			}
		}
		// normally essential, unless special type of shoe
		ArrayList<Socks> possibleSocks = new ArrayList<Socks>();
		for (int i = 0; i < availableClothes.size(); i++) {
			if (availableClothes.get(i) instanceof Socks) { // check if type Socks
				possibleSocks.add((Socks) availableClothes.get(i));

			}
		}

		// extra
		ArrayList<Accessory> possibleAccessories = new ArrayList<Accessory>();
		for (int i = 0; i < availableClothes.size(); i++) {
			if (availableClothes.get(i) instanceof Accessory) { // check if type Accessory
				possibleAccessories.add((Accessory) availableClothes.get(i));

			}
		}
		ArrayList<Jacket> possibleJackets = new ArrayList<Jacket>();
		for (int i = 0; i < availableClothes.size(); i++) {
			if (availableClothes.get(i) instanceof Jacket) { // check if type Jacket
				possibleJackets.add((Jacket) availableClothes.get(i));

				//TODO: remember to implement coat into this haha
			}
		}
		System.out.println("Possible tops: " + possibleTops.toString());
		System.out.println("Possible shoes: " + possibleShoes.toString());
		System.out.println("Possible bottoms: " + possibleBottoms.toString());
		System.out.println("Possible accessories: " + possibleAccessories.toString());
		readOutfits(filterOutfits(getPossibleOutfits(possibleTops, possibleBottoms, possibleDresses, possibleShoes, possibleCoat, possibleSocks, possibleAccessories, possibleJackets)));
			
		}


	public void readOutfits(ArrayList<Outfit> possibleOutfits) {
		System.out.println("readOutfits");
		if (possibleOutfits.size() == 0) {
			System.out.println("No possible outfits found");
		} else {
			// for (int i = 0; i < possibleOutfits.size(); i++) {
			int numOutfitBeingViewed = 0;
			boolean chosen = false;
			for (int i = 0; i < possibleOutfits.size(); i++) {
				possibleOutfits.get(i).setChosenOrLiked(false);
			}
			do {
			System.out.println(possibleOutfits.get(numOutfitBeingViewed).isMixedPattern());
			System.out.println("Viewing outfit " + (numOutfitBeingViewed + 1) + " of " + possibleOutfits.size());
			System.out.println(possibleOutfits.get(numOutfitBeingViewed).toString());
			possibleOutfits.get(numOutfitBeingViewed).setViewed(true);
			ArrayList<Option> options = new ArrayList<Option>();
			if (numOutfitBeingViewed != 0) {
				options.add(new Option("View previous outfit?", 'p'));

			}
			if (numOutfitBeingViewed != possibleOutfits.size() - 1) {
				options.add(new Option("View next outfit?", 'n'));
			}
			options.add(new Option("Choose this outfit", 'c'));
			options.add(new Option("I don't like any of these outfits", 'x'));
			options.add(new Option("I hate this outfit", 'h'));
			options.add(new Option("I like this outfit", 'l'));
			Choice choice = new Choice(options);
			char option = choice.performChoice();
			switch (option) {
			case 'p':
				numOutfitBeingViewed--;
				
				break;
			case 'n':
				numOutfitBeingViewed++;
				break;
			case 'c':
				chosen = true;
				System.out.println("Glad you found an outfit you liked! We've improved our system to show more outfits like this.");
				System.out.println("Score before: " + possibleOutfits.get(numOutfitBeingViewed).top.getScore());
				possibleOutfits.get(numOutfitBeingViewed).increaseScores(false);
				possibleOutfits.get(numOutfitBeingViewed).setChosenOrLiked(true);
				System.out.println("Score now: " + possibleOutfits.get(numOutfitBeingViewed).top.getScore());
				break;
			case 'x':
				System.out.println("Oh no! That's too bad. We're improving our system to get you better outfits next time.");
				chosen = true;
				possibleOutfits.get(numOutfitBeingViewed).decreaseScores(false);
				break;
			case 'h':
				System.out.println("Oh no! That's too bad. We're improving our system to get you better outfits next time.");
				System.out.println("Clothing items shown in this outfit will be shown less often");
				numOutfitBeingViewed++;
				possibleOutfits.get(numOutfitBeingViewed).decreaseScores(true);
				break;
			case 'l':
				//TODO: need to adjust scores for accessory preferences as well
				System.out.println("That's awesome! We've improved our system to show you more outfits like this.");
				numOutfitBeingViewed++;
				possibleOutfits.get(numOutfitBeingViewed).increaseScores(true);
				possibleOutfits.get(numOutfitBeingViewed).setChosenOrLiked(true);
				break;
			}
			} while (!chosen);
			for (int i = 0; i < possibleOutfits.size(); i++) {
				if (possibleOutfits.get(i).isViewed())
					possibleOutfits.get(i).decreaseScores(false);
			}
			closet.saveCloset(user.getUsername() + "OutfitGenerator.txt", true);
			user.saveUserInfo(user.getUsername());
			
			//TODO: decrease all outfits that were viewed and not chosen
		}
	}
	
    private int largest(int[]arr) 
    { 
        int i; 
          
        // Initialize maximum element 
        int max = arr[0]; 
       
        // Traverse array elements from second and 
        // compare every element with current max   
        for (i = 1; i < arr.length; i++) 
            if (arr[i] > max) 
                max = arr[i]; 
       
        return max; 
    } 
	
	private ArrayList<Outfit> getPossibleOutfits(ArrayList<Top> possibleTops, ArrayList<Bottoms> possibleBottoms,
			ArrayList<Dress> possibleDresses, ArrayList<Shoes> possibleShoes, ArrayList<Coat> possibleCoat,
			ArrayList<Socks> possibleSocks, ArrayList<Accessory> possibleAccessories,
			ArrayList<Jacket> possibleJackets) {
		
		System.out.println("getPossibleOutfits()");

		ArrayList<Outfit> possibleOutfits = new ArrayList<Outfit>();;
		Outfit possibleOutfit = null;
		ArrayList<Accessory[]> possibleAccessorySets = new ArrayList<Accessory[]>(); // maybe draw 2 randomly from
																						// here?? depending on user
																						// preferences??
		// sorts possible accessories by accessory type
		ArrayList<Accessory> purses = new ArrayList<Accessory>();
		ArrayList<Accessory> necklaces = new ArrayList<Accessory>();
		ArrayList<Accessory> bracelets = new ArrayList<Accessory>();
		ArrayList<Accessory> anklets = new ArrayList<Accessory>();
		ArrayList<Accessory> scrunchies = new ArrayList<Accessory>();
		ArrayList<Accessory> glasses = new ArrayList<Accessory>();
		ArrayList<Accessory> hairpieces = new ArrayList<Accessory>();
		ArrayList<Accessory> other = new ArrayList<Accessory>();
		for (int i = 0; i < possibleAccessories.size(); i++) {
			Accessory.AccessoryType subtype = possibleAccessories.get(i).getSubtype();
			switch (subtype) {
			case PURSE:
				purses.add(possibleAccessories.get(i));
				break;
			case NECKLACE:
				necklaces.add(possibleAccessories.get(i));
				break;
			case BRACELET:
				bracelets.add(possibleAccessories.get(i));
				break;
			case ANKLET:
				anklets.add(possibleAccessories.get(i));
				break;
			case SCRUNCHIE:
				scrunchies.add(possibleAccessories.get(i));
				break;
			case GLASSES:
				glasses.add(possibleAccessories.get(i));
				break;
			case HAIRPIECE:
				hairpieces.add(possibleAccessories.get(i));
				break;
			default:
				other.add(possibleAccessories.get(i));
				break;
			}
		}
		

		
		ArrayList<Accessory>[] possibleAccessoriesSorted = (ArrayList<Accessory>[])new ArrayList[8];
		possibleAccessoriesSorted[0] = purses;
		possibleAccessoriesSorted[1] = necklaces;
		possibleAccessoriesSorted[2] = bracelets;
		possibleAccessoriesSorted[3] = anklets;
		possibleAccessoriesSorted[4] = scrunchies;
		possibleAccessoriesSorted[5] = glasses;
		possibleAccessoriesSorted[6] = hairpieces;
		possibleAccessoriesSorted[7] = other;
		//System.out.println("possibleAccessoriesSorted: " + Arrays.toString(possibleAccessoriesSorted));
		int[] possibleAccSize = new int[8];
		for (int i = 0; i < possibleAccessoriesSorted.length; i++ ) {
			possibleAccSize[i] = possibleAccessoriesSorted[i].size();
		}
		
	
		Accessory[] newAccessorySet1 = new Accessory[8];
		possibleAccessorySets.add(new Accessory[8]);
		for (int i = 0; i < possibleAccessoriesSorted.length; i++) { // iterate through each category of possible accessories
			for (int j = 0; j < possibleAccessoriesSorted[i].size(); j++) { // iterate through each of those possible accessories
				for (int k = 0; k < possibleAccessorySets.size(); k++) { // go into all existing possibleAccessorySets
					newAccessorySet1 = possibleAccessorySets.get(k);
					if (newAccessorySet1[i] != null) {
						System.out.println("before else: " + newAccessorySet1[i].getName());
					}
					if (possibleAccessorySets.get(k)[i] == null) {
						possibleAccessorySets.get(k)[i] = possibleAccessoriesSorted[i].get(j); // if not being used, put in the accessory
						System.out.println("Changing to: " + possibleAccessoriesSorted[i].get(j).getName() + "\n");
					}
					else {
						System.out.println("else");
						
						Accessory[] tmpAccessorySet = Arrays.copyOf(newAccessorySet1, 8);
						tmpAccessorySet[i] = possibleAccessoriesSorted[i].get(j);
							//w++;
						
						if (!newAccessorySet1[i].getName().equals(tmpAccessorySet[i].getName())) {
							possibleAccessorySets.add(tmpAccessorySet); // add a new accessory set with all the same except for the new category
							System.out.println("Adding: " + tmpAccessorySet[i].getName() + "\n");
						}			
					}
				
				}
			}
		}
		
	
		
		for (int i = 0; i < possibleAccessorySets.size(); i++) {
			System.out.println("set " + i);
			for (int j = 0; j < possibleAccessorySets.get(i).length; j++) {
				if (possibleAccessorySets.get(i)[j] != null) {
					System.out.print(possibleAccessorySets.get(i)[j].getName() + ", ");
				}
				else {
					System.out.print("null, ");
				}
				
			}
			System.out.println("");
		}
		
		for (int i = 0; i < possibleAccessories.size(); i++) {
			
		}
		
		
		ArrayList<Outfit> possibleTypeOneOutfits = new ArrayList<Outfit>();
		// type 1
		if (possibleTops.size()>0 && possibleBottoms.size()>0 &&possibleShoes.size()>0) {
			for (int i = 0; i < possibleTops.size(); i++) {
				for (int j = 0; j < possibleBottoms.size(); j++) {
					for (int k = 0; k < possibleShoes.size(); k++) {
						System.out.println("type one");
						possibleOutfit = new Outfit(user, possibleTops.get(i), possibleBottoms.get(j), possibleShoes.get(k));
						possibleTypeOneOutfits.add(possibleOutfit);
						possibleOutfits.add(possibleOutfit);
					}
				}
			}
		}
		
		ArrayList<Outfit> possibleTypeTwoOutfits = new ArrayList<Outfit>();
		// type 2
		if (possibleTops.size()>0 && possibleBottoms.size()>0 &&possibleSocks.size()>0 &&possibleShoes.size()>0) {
			for (int i = 0; i < possibleTops.size(); i++) {
				for (int j = 0; j < possibleBottoms.size(); j++) {
					for (int k = 0; k < possibleShoes.size(); k++) {
						for (int l = 0; l < possibleSocks.size(); l++) {
							System.out.println("type two");
							possibleOutfit = new Outfit(user, possibleTops.get(i), possibleBottoms.get(j), possibleShoes.get(k),
									possibleSocks.get(l));
							possibleTypeTwoOutfits.add(possibleOutfit);
							possibleOutfits.add(possibleOutfit);
						}
					}
				}
			}
		}


		ArrayList<Outfit> possibleTypeThreeOutfits = new ArrayList<Outfit>();
		// type 3
		if (possibleTops.size()>0 && possibleBottoms.size()>0 &&possibleAccessorySets.size()>0 &&possibleShoes.size()>0)
		for (int i = 0; i < possibleTops.size(); i++) {
			for (int j = 0; j < possibleBottoms.size(); j++) {
				for (int k = 0; k < possibleShoes.size(); k++) {
					for (int l = 0; l < possibleAccessorySets.size(); l++) {
						System.out.println("type three");
						possibleOutfit = new Outfit(user, possibleTops.get(i), possibleBottoms.get(j),
								possibleAccessorySets.get(l), possibleShoes.get(k));
						possibleTypeThreeOutfits.add(possibleOutfit);
						possibleOutfits.add(possibleOutfit);
					}
				}
			}
		}
		ArrayList<Outfit> possibleTypeFourOutfits = new ArrayList<Outfit>();
		// type 4
		if (possibleTops.size()>0 && possibleBottoms.size()>0 &&possibleJackets.size()>0 &&possibleShoes.size()>0)
		for (int i = 0; i < possibleTops.size(); i++) {
			for (int j = 0; j < possibleBottoms.size(); j++) {
				for (int k = 0; k < possibleShoes.size(); k++) {
					for (int l = 0; l < possibleJackets.size(); l++) {
						System.out.println("type four");
						possibleOutfit = new Outfit(user, possibleTops.get(i), possibleBottoms.get(j), possibleJackets.get(l),
								possibleShoes.get(k));
						possibleTypeFourOutfits.add(possibleOutfit);
						possibleOutfits.add(possibleOutfit);
					}
				}
			}
		}
		ArrayList<Outfit> possibleTypeFiveOutfits = new ArrayList<Outfit>();
		// type 5
		if (possibleTops.size()>0 && possibleBottoms.size()>0 &&possibleAccessorySets.size()>0 &&possibleShoes.size()>0&&possibleJackets.size()>0)
		for (int i = 0; i < possibleTops.size(); i++) {
			for (int j = 0; j < possibleBottoms.size(); j++) {
				for (int k = 0; k < possibleShoes.size(); k++) {
					for (int l = 0; l < possibleJackets.size(); l++) {
						for (int m = 0; m < possibleAccessorySets.size(); m++) {
							System.out.println("type five");
							possibleOutfit = new Outfit(user, possibleTops.get(i), possibleBottoms.get(j),
									possibleAccessorySets.get(m), possibleJackets.get(l), possibleShoes.get(k));
							possibleTypeFiveOutfits.add(possibleOutfit);
							possibleOutfits.add(possibleOutfit);
						}
					}
				}
			}
		}

		ArrayList<Outfit> possibleTypeSixOutfits = new ArrayList<Outfit>();
		// type 6
		if (possibleTops.size()>0 && possibleBottoms.size()>0 &&possibleAccessorySets.size()>0 &&possibleShoes.size()>0&&possibleSocks.size()>0)
		for (int i = 0; i < possibleTops.size(); i++) {
			for (int j = 0; j < possibleBottoms.size(); j++) {
				for (int k = 0; k < possibleShoes.size(); k++) {
					for (int l = 0; l < possibleSocks.size(); l++) {
						for (int m = 0; m < possibleAccessorySets.size(); m++) {
							System.out.println("type six");
							possibleOutfit = new Outfit(user, possibleTops.get(i), possibleBottoms.get(j),
									possibleAccessorySets.get(m), possibleSocks.get(l), possibleShoes.get(k));
							possibleTypeSixOutfits.add(possibleOutfit);
							possibleOutfits.add(possibleOutfit);
						}
					}
				}
			}
		}

		ArrayList<Outfit> possibleTypeSevenOutfits = new ArrayList<Outfit>();
		// type 7
		if (possibleTops.size()>0 && possibleBottoms.size()>0 && possibleShoes.size()>0 &&possibleJackets.size()>0&&possibleSocks.size()>0)
		for (int i = 0; i < possibleTops.size(); i++) {
			for (int j = 0; j < possibleBottoms.size(); j++) {
				for (int k = 0; k < possibleShoes.size(); k++) {
					for (int l = 0; l < possibleSocks.size(); l++) {
						for (int m = 0; m < possibleJackets.size(); m++) {
							System.out.println("type seven");
							possibleOutfit = new Outfit(user, possibleTops.get(i), possibleBottoms.get(j),
									possibleJackets.get(m), possibleSocks.get(l), possibleShoes.get(k));
							possibleTypeSevenOutfits.add(possibleOutfit);
							possibleOutfits.add(possibleOutfit);
						}
					}
				}
			}
		}

		ArrayList<Outfit> possibleTypeEightOutfits = new ArrayList<Outfit>();
		// type 7
		if (possibleAccessorySets.size()>0 && possibleTops.size()>0 && possibleBottoms.size()>0 && possibleShoes.size()>0 &&possibleJackets.size()>0&&possibleSocks.size()>0)
		for (int i = 0; i < possibleTops.size(); i++) {
			for (int j = 0; j < possibleBottoms.size(); j++) {
				for (int k = 0; k < possibleShoes.size(); k++) {
					for (int l = 0; l < possibleSocks.size(); l++) {
						for (int m = 0; m < possibleJackets.size(); m++) {
							for (int n = 0; n < possibleAccessorySets.size(); n++) {
								System.out.println("type eight");
								possibleOutfit = new Outfit(user, possibleTops.get(i), possibleBottoms.get(j),
										possibleAccessorySets.get(n), possibleJackets.get(m), possibleSocks.get(l),
										possibleShoes.get(k));
								possibleTypeEightOutfits.add(possibleOutfit);
								possibleOutfits.add(possibleOutfit);
							}
						}
					}
				}
			}
		}
		
		ArrayList<Outfit> possibleTypeNineOutfits = new ArrayList<Outfit>();
		// type 9
		if (possibleDresses.size()>0 &&possibleShoes.size()>0)
		for (int i = 0; i < possibleDresses.size(); i++) {
			for (int j = 0; j < possibleShoes.size(); j++) {
				System.out.println("type ten");
				possibleOutfit = new Outfit(user, possibleDresses.get(i), possibleShoes.get(j));
				possibleTypeNineOutfits.add(possibleOutfit);
				possibleOutfits.add(possibleOutfit);
			}
		}
		ArrayList<Outfit> possibleTypeTenOutfits = new ArrayList<Outfit>();
		// type 10
		if (possibleDresses.size()>0 &&possibleShoes.size()>0&&possibleSocks.size()>0)
		for (int i = 0; i < possibleDresses.size(); i++) {
			for (int j = 0; j < possibleShoes.size(); j++) {
				for (int k = 0; k < possibleSocks.size(); k++) {
					System.out.println("type ten");
					possibleOutfit = new Outfit(user, possibleDresses.get(i), possibleShoes.get(j), possibleSocks.get(k));
					possibleTypeTenOutfits.add(possibleOutfit);
					possibleOutfits.add(possibleOutfit);
				}
			}
		}
		ArrayList<Outfit> possibleTypeElevenOutfits = new ArrayList<Outfit>();
		// type 11
		if (possibleDresses.size()>0 &&possibleShoes.size()>0&&possibleAccessorySets.size()>0)
		for (int i = 0; i < possibleDresses.size(); i++) {
			for (int j = 0; j < possibleShoes.size(); j++) {
				for (int k = 0; k < possibleAccessorySets.size(); k++) {
					System.out.println("type eleven");
					possibleOutfit = new Outfit(user, possibleDresses.get(i), possibleAccessorySets.get(k), possibleShoes.get(j));
					possibleTypeElevenOutfits.add(possibleOutfit);
					possibleOutfits.add(possibleOutfit);
				}
			}
		}
		ArrayList<Outfit> possibleTypeTwelveOutfits = new ArrayList<Outfit>();
		// type 12
		if (possibleDresses.size()>0 &&possibleShoes.size()>0&&possibleJackets.size()>0)
		for (int i = 0; i < possibleDresses.size(); i++) {
			for (int j = 0; j < possibleJackets.size(); j++) {
				for (int k = 0; k < possibleShoes.size(); k++) {
					System.out.println("type twelve");
					possibleOutfit = new Outfit(user, possibleDresses.get(i), possibleJackets.get(j), possibleShoes.get(k));
					possibleTypeTwelveOutfits.add(possibleOutfit);
					possibleOutfits.add(possibleOutfit);
				}
			}
		}
		ArrayList<Outfit> possibleTypeThirteenOutfits = new ArrayList<Outfit>();
		// type 13
		if (possibleDresses.size()>0 &&possibleShoes.size()>0&&possibleAccessorySets.size()>0&&possibleJackets.size()>0)
		for (int i = 0; i < possibleDresses.size(); i++) {
			for (int j = 0; j < possibleShoes.size(); j++) {
				for (int k = 0; k < possibleAccessorySets.size(); k++) {
					for (int l = 0; l < possibleJackets.size(); l++) {
						System.out.println("type thirteen");
						possibleOutfit = new Outfit(user, possibleDresses.get(i), possibleAccessorySets.get(k), possibleJackets.get(l), possibleShoes.get(j));
						possibleTypeThirteenOutfits.add(possibleOutfit);
						possibleOutfits.add(possibleOutfit);
					}
				}
			}
		}
		ArrayList<Outfit> possibleTypeFourteenOutfits = new ArrayList<Outfit>();
		// type 14
		if (possibleDresses.size()>0 &&possibleShoes.size()>0&&possibleAccessorySets.size()>0&&possibleSocks.size()>0)
		for (int i = 0; i < possibleDresses.size(); i++) {
			for (int j = 0; j < possibleShoes.size(); j++) {
				for (int k = 0; k < possibleAccessorySets.size(); k++) {
					for (int l = 0; l < possibleSocks.size(); l++) {
						System.out.println("type fourteen");
						possibleOutfit = new Outfit(user, possibleDresses.get(i), possibleAccessorySets.get(k), possibleSocks.get(l), possibleShoes.get(j));
						possibleTypeFourteenOutfits.add(possibleOutfit);
						possibleOutfits.add(possibleOutfit);
					}
				}
			}
		}
		ArrayList<Outfit> possibleTypeFifteenOutfits = new ArrayList<Outfit>();
		// type 15
		if (possibleDresses.size()>0 &&possibleShoes.size()>0&&possibleSocks.size()>0&&possibleJackets.size()>0)
		for (int i = 0; i < possibleDresses.size(); i++) {
			for (int j = 0; j < possibleShoes.size(); j++) {
				for (int k = 0; k < possibleJackets.size(); k++) {
					for (int l = 0; l < possibleSocks.size(); l++) {
						System.out.println("type fifteen");
						possibleOutfit = new Outfit(user, possibleDresses.get(i), possibleJackets.get(k), possibleSocks.get(l), possibleShoes.get(j));
						possibleTypeFifteenOutfits.add(possibleOutfit);
						possibleOutfits.add(possibleOutfit);
					}
				}
			}
		}
		ArrayList<Outfit> possibleTypeSixteenOutfits = new ArrayList<Outfit>();
		// type 16
		if (possibleDresses.size()>0 &&possibleShoes.size()>0&&possibleSocks.size()>0&&possibleAccessorySets.size()>0&&possibleJackets.size()>0)
		for (int i = 0; i < possibleDresses.size(); i++) {
			for (int j = 0; j < possibleShoes.size(); j++) {
				for (int k = 0; k < possibleAccessorySets.size(); k++) {
					for (int l = 0; l < possibleSocks.size(); l++) {
						for (int m = 0; m < possibleJackets.size(); m++) {
							System.out.println("type sixteen");
							possibleOutfit = new Outfit(user, possibleDresses.get(i), possibleAccessorySets.get(k), possibleSocks.get(l), possibleShoes.get(j));
							possibleTypeSixteenOutfits.add(possibleOutfit);
							possibleOutfits.add(possibleOutfit);
						}
					}
				}
			}
		}
		return possibleOutfits;
	}
	
	private boolean determineMakeDecision(double chance) {
		return (rand.nextInt(100) < chance * 100);
	}

	
	private boolean hasPattern(ArrayList<Accessory> accessories) {
		for (int i = 0; i < accessories.size(); i++) {
			if (accessories.get(i).isPatterned())
				return true;
		}
		return false;
	}
	
	/**
	 * filters out outfits with more patterns or accessories than allowed
	 * TODO: max 1 pattern for big items like shirt or bottoms
	 */
	private ArrayList<Outfit> filterOutfits(ArrayList<Outfit> possibleOutfits) {
		System.out.println("filterOutfits");
		for (int i = 0; i < possibleOutfits.size(); i++) {
			if (possibleOutfits.get(i).isMixedPattern()) {
				if (determineMakeDecision(user.chanceMixPatterns)) {
					System.out.println("MIXED OUTFIT MADE IT IN: " + possibleOutfits.get(i).toStringNames());
					possibleOutfits.remove(possibleOutfits.get(i)); // removes outfit from possible outfits if chance rules it so
				} else {
					System.out.println("MIXED OUTFIT NOT CHOSEN: " + possibleOutfits.get(i).toStringNames());
				}
			}	
		}
		return possibleOutfits;
	}
	

	private int getBooleanArrayNumberRange(OutfitPreference.TemperatureRange range) {
		switch (range) {
		case FREEZING:
			return 0;
		case COLD:
			return 1;
		case MEDIUM:
			return 2;
		default:
			return 3;
		}
	}

	private int getBooleanArrayNumberOccasion(OutfitPreference.Occasion occasion) {
		switch (occasion) {
		case DATENIGHT:
			return 0;
		case FORMAL:
			return 1;
		case NIGHTOUT:
			return 2;
		case EVERYDAY:
			return 3;
		default:
			return 4;
		}
	}
}
