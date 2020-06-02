import java.util.ArrayList;
import java.util.Scanner;


//TODO: throw error when two options with the same character are used
public class Choice {
	private String question;
	private ArrayList<Option> options;
	private char[] lettersToEnter;
	private Scanner scnr;

	public Choice(String question, ArrayList<Option> options) {
		scnr = new Scanner(System.in);
		this.question = question;
		this.options = options;
		lettersToEnter = new char[options.size()];
		createLettersToEnter();
	}
	
	public Choice(ArrayList<Option> options) {
		question = "";
		scnr = new Scanner(System.in);
		this.options = options;
		lettersToEnter = new char[options.size()];
		createLettersToEnter();
	}
	
	public Option getAnswer(int index) {
		return options.get(index);
	}
	
	public String getQuestion() {
		return question;
	}
	
	public ArrayList<Option> getAnswers() {
		return options;
	}
	
	private void createLettersToEnter() {
		for (int i = 0; i < options.size(); i++) {
			lettersToEnter[i] = options.get(i).getLetterToEnter();
		}
	}
	
	public char performChoice() {
		if (question != null)
		System.out.print(question + " ");

		
		boolean isValidInput = false;
		while (!isValidInput) {
			System.out.println("Type the letter in parenthesis for your choice.");
			System.out.println("------------------------------------");
			for (int i = 0; i < options.size(); i++) {
				System.out.print(options.get(i).getOption());
				if (options.get(i).getLetterToEnter() != ' ')
					System.out.print(" (" + options.get(i).getLetterToEnter() + ")");
				else
					System.out.print(" (Press any other character)");
				System.out.println("");
			}
			char input = scnr.next().charAt(0);
			
			for (int j = 0; j < lettersToEnter.length; j++) {
				if (input == lettersToEnter[j]) {
					isValidInput = true;
					return input;
				}
			}
		}
		return '?';
	}
}

