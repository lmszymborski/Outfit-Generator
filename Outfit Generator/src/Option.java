import java.util.ArrayList;

public class Option {
private String option;
private char letterToEnter;

public Option(String option, char letterToEnter) {
	this.option = option;
	this.letterToEnter = letterToEnter;
}

public Option(String option) {
	this.option = option;
	letterToEnter = ' ';
}

public String getOption() {
	return option;
}

public char getLetterToEnter() {
	return letterToEnter;
}
}