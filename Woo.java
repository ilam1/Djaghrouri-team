//Djaghrouri: Bayan Berri, Irene Lam, Jason Lam
//APCS1 pd5
//Final Project - JEOPARDY!
//2017-01-23

/*=============================================
  class Woo -- Driver file for JEOPARDY game.
  =============================================*/

import java.io.*;
import java.util.*;

import cs1.Keyboard;

public class Woo {

    // ~~~~~~~~~~~ INSTANCE VARIABLES ~~~~~~~~~~~
    private int totalPlayers;
    private String[] playerNames;
    private int[] questionWorth;
    private int[] points;
    private int[][] board = new int[5][5];
    protected String[] questions;
    protected String[] answers;
    private String[] buzzer;
    private boolean gameOn;
    private int[] ind = new int[5];
    private String[] categories =
    {"Nature", "Math", "Science", "History", "Logic", "StuyTrivia",
     "Literature", "Cheese", "China", "CompSci", "Geography",
     "Pop", "Women", "USA", "College", "Plays",
     "Food", "Tech", "Shows", "Music", "Earth", "Culture",
     "Literature", "Space", "Sports"};
    String[] newCat = createCategory();
    String buzzed;

    private BufferedReader in;
    private boolean diffPoints; //True if and only if the user creates a game with different point values

    //Default constructor
    public Woo() {
        gameOn = true;
    }
    /*
     *This method loads the game in the beginning. 
     * It prints out loading followed by periods with a lag time. 
     */
    public void load(){
	System.out.print("Loading");
        for (int i = 0; i < 3; i++) {
            try {
		Thread.sleep(1000); //What gives the 1 second interval
            } catch (InterruptedException ex) {
            }
            System.out.print(".");
        }
        System.out.println();
    }
    /*
      This method populates the category array in Custom based on what the user inputs.
      Limits the user to only 5 categories 
    */
    public String[] makeCat(){
	int ctr=1;
	while (ctr<=5){
	    System.out.println("what's the topic for category "+ ctr);
	    Custom.category[ctr-1]= Keyboard.readString();
	    ctr+=1;
	}
	return Custom.category;
    }
    /*
     * This method takes in an array as a parameter and populates it with the questions that the user inputs
     * Limits the user to only 5 questions per category
     */
    public String[] makeQues1(String[] arr){
	int ind=0;
	while(ind<5){
	    System.out.println("What is the question worth "+((ind+1)*100)+" points for the "+ Custom.category[0]+" category");
	    arr[ind]=Keyboard.readString();
	    ind+=1;
	}
	return arr;
    }
    /*
     * This method takes in an array as a parameter and populates it with the user's answer key. 
     */
    public String[]makeAns1(String[] arr){
    	int ind=0;
	while(ind<5){
	    System.out.println("What is the answer worth "+((ind+1)*100)+" points for the "+ Custom.category[0]+" category");
	    arr[ind]=Keyboard.readString();
	    ind+=1;
	}
	return arr;
    }

    /*
     *This method is used to answer the first question we ask the user:
     *whether they would like to create their own game or play premade questions. 
     *If they choose to create their own game they get the option of populating it the arrays of the class Custom
     */
    public void choice1(){
	System.out.println("You are now playing JEOPARDY \nDedicated to Ms. Djaghrouri (≧∀≦)\n");
        System.out.println("\t1: Create your own game!");
        System.out.println("\t2: Play our game!");
        int option = 0;

        try {
            option = Integer.parseInt(in.readLine());
        } catch (IOException e) {
        }

        if (option == 1) {
            //Implementation to create your own game
	     System.out.println("CURRENTLY EMPTY");
	    makeCat();
	    System.out.println("PRINTING CAT:");
	    //testPrintArr(Custom.category);
	    makeQues1(Custom.questions1);
	    //System.out.println("PRINTING Q1:");
	    //testPrintArr(Custom.questions1);
	    makeAns1(Custom.answers1);
	    //System.out.println("PRINTING A1:");
	    //testPrintArr(Custom.answers1);
	    makeQues1(Custom.questions2);
	    //System.out.println("PRINTING Q2:");
	    //testPrintArr(Custom.questions2);
	    makeAns1(Custom.answers2);
	    //System.out.println("PRINTING A2:");
	    //testPrintArr(Custom.answers2);
	    makeQues1(Custom.questions3);
	    makeAns1(Custom.answers3);
	    makeQues1(Custom.questions4);
	    makeAns1(Custom.answers4);
	    makeQues1(Custom.questions5);
	    makeAns1(Custom.answers5);
	   
            //System.exit(0);
	}
    }
    /*
     *This methos is just used to test whether or not the arrays are being populated or not.
     * loops through a string array and prints each element
     */
    public void testPrintArr(String[] arr){
	for (String x: arr){
	    System.out.println(x);
	}
    }
    /*
     *Updates the array playerNames to the size of the number of players
     *Populates the array playerNames with the names that the players input
     *Updates the array buzzer to include every players' buzzer
     */
    public void setBuzzer(int totalPlayers){
	 for (int i = 0; i < totalPlayers; i++) {
            System.out.println("\nWho is player " + (i + 1) + " ?");
            try {
                playerNames[i] = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
	    if (totalPlayers > 1) {
		System.out.println("\n" + playerNames[i] + " please select your ONE CHARACTER desired buzzer");

		while (buzzer[i] == null) {
		    boolean any = false;
		    String buzzerName="";
		    try {
			buzzerName = in.readLine();
		    } catch (IOException e) {
			e.printStackTrace();
		    }
		    for (String x: buzzer) {
			if (x != null && x.equals(buzzerName) ) {
			    any = true; }
		    }
		    if (any) {
			System.out.println("Your buzzer has already been taken! Please select another buzzer:");
		    }
		    else if (buzzerName.length() != 1) {
			System.out.println("Your buzzer is invalid! Please select another buzzer:");
		    }
		    else {
			buzzer[i] = buzzerName;
		    }
		}
	    }
	 }
    }
    public void newGame() {
	//	load();

        in = new BufferedReader(new InputStreamReader(System.in));

	choice1();

	System.out.println("\nHow many players are playing in total?"); //Currently only supports a single player
        try {
            totalPlayers = Integer.parseInt(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\nToday, one of these " + totalPlayers + " contestants will win the Jeopardy!");

        playerNames = new String[totalPlayers]; //Updates the array playerNames to the size of the number of players
	buzzer = new String[totalPlayers];

        //Updates the array playerNames to the size of the number of players
	/* for (int i = 0; i < totalPlayers; i++) {
            System.out.println("\nWho is player " + (i + 1) + " ?");
            try {
                playerNames[i] = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
	    if (totalPlayers > 1) {
		System.out.println("\n" + playerNames[i] + " please select your ONE CHARACTER desired buzzer");

		while (buzzer[i] == null) {
		    boolean any = false;
		    String buzzerName="";
		    try {
			buzzerName = in.readLine();
		    } catch (IOException e) {
			e.printStackTrace();
		    }
		    for (String x: buzzer) {
			if (x != null && x.equals(buzzerName) ) {
			    any = true; }
		    }
		    if (any) {
			System.out.println("Your buzzer has already been taken! Please select another buzzer:");
		    }
		    else if (buzzerName.length() != 1) {
			System.out.println("Your buzzer is invalid! Please select another buzzer:");
		    }
		    else {
			buzzer[i] = buzzerName;
		    }
		}
	    }
	    }*/
	setBuzzer(totalPlayers);
	//testPrintArr(buzzer);
	//testPrintArr(playerNames);
	
	if (totalPlayers < 2) {
	    System.out.println("\nWould you like to play a randomized game or customized game?");
	}
	else {
	    System.out.println("\nWould you all like to play a randomized game or a customized game?"); }
	System.out.println("\t1: Random Game\n\t2: Customized Game");

	String choice = "";

	while (!choice.trim().equals("1") && !choice.trim().equals("2"))
	    try {
		choice = in.readLine();
	    } catch (IOException e) {}

	if (choice.equals("2")) {

	    int i = 0;
	    int left = 5;       // categories to choose left
	    newCat = new String[5];
	    while (left != 0) {
		System.out.println("\n");
		for (String s : categories) {
		    boolean exists = false;
		    for (String str : newCat)
			if (s.equalsIgnoreCase(str))
			    exists = true;
		    if (exists)
			System.out.printf("%-12s", "");
		    else
			System.out.printf("%-12s", s);
		    if (i++ == 4) {
			System.out.println();
			i = 0;
		    }
		}
		System.out.printf("%nCategories left: %d%n_> ", left);

		String str = "";
		try {
		    str = in.readLine().trim().toUpperCase();
		} catch (IOException e) {
		}
		boolean invalid = false;
		for (String s : newCat)
		    if (str.equals(s))
			invalid = true;

		boolean exists = false;
		for (String s : categories)
		    if (str.equalsIgnoreCase(s))
			exists = true;

		if (exists && !invalid) {
		    newCat[5 - left] = str;
		    left--;
		    System.out.printf("%s has been added to our game.", str);
		    if (left > 0)
			System.out.printf("Select %d more categories.", left);
		    else
			System.out.println();
		} else if (invalid)
		    System.out.printf("%s is already taken, please select another category", str);
		else
		    System.out.printf("Sorry, we do not recognize \"%s\", please select another category", str);
	    }
	}
	System.out.println("\n*            *            *\n\nThank you, ladies and gentlemen. Hello & welcome to Jeopardy!, America's favorite answer-and-question game. Yes, we give the QUESTIONS, and then it's up to these " + totalPlayers + " contestants to come up with the ANSWERS. Players, as you know, whenever you recognize an answer you're free to ring in; but, I want to warn you about the Jeopardy!: if you are wrong, the value of the question will be deducted from your winnings. Right now, put your hands on the buttons, but please don't ring in until the answer is exposed. If all " + totalPlayers + " of you are ready, then let's play Jeopardy!");

	points = new int[totalPlayers];//Updates the points array to number of players in the game

	pointTable(board);
	for (int ind = 0; ind < (board[0].length) * ((board.length) - 1); ind++) {
	    System.out.println();
	    System.out.println(toString());
	    print2(board);

	    int col = 0;
	    do {
		System.out.println("\nSelect the categories you wish to play. Please type in the number before the points");

		if (board[0][0] + board[1][0] + board[2][0] + board[3][0] + board[4][0] != 0)
		    System.out.println("\t1: " + newCat[0]);
		if (board[0][1] + board[1][1] + board[2][1] + board[3][1] + board[4][1] != 0)
		    System.out.println("\t2: " + newCat[1]);
		if (board[0][2] + board[1][2] + board[2][2] + board[3][2] + board[4][2] != 0)
		    System.out.println("\t3: " + newCat[2]);
		if (board[0][3] + board[1][3] + board[2][3] + board[3][3] + board[4][3] != 0)
		    System.out.println("\t4: " + newCat[3]);
		if (board[0][4] + board[1][4] + board[2][4] + board[3][4] + board[4][4] != 0)
		    System.out.println("\t5: " + newCat[4]);

		col = Keyboard.readInt();
	    } while (!((col == 1 && (board[0][0] + board[1][0] + board[2][0] + board[3][0] + board[4][0] != 0))
		       || (col == 2 && (board[0][1] + board[1][1] + board[2][1] + board[3][1] + board[4][1] != 0))
		       || (col == 3 && (board[0][2] + board[1][2] + board[2][2] + board[3][2] + board[4][2] != 0))
		       || (col == 4 && (board[0][3] + board[1][3] + board[2][3] + board[3][3] + board[4][3] != 0))
		       || (col == 5 && (board[0][4] + board[1][4] + board[2][4] + board[3][4] + board[4][4] != 0))
		       || (col == 6 && (board[0][5] + board[1][5] + board[2][5] + board[3][5] + board[4][5] != 0))
		       ));

	    int row = 0;
	    do {
		System.out.println("\nHow many points would you like to play for? " +
				   "Please type in the number before the points.\n");
		if (board[0][col - 1] != 0)
		    System.out.println("\t 1: 100");
		if (board[1][col - 1] != 0)
		    System.out.println("\t 2: 200");
		if (board[2][col - 1] != 0)
		    System.out.println("\t 3: 300");
		if (board[3][col - 1] != 0)
		    System.out.println("\t 4: 400");
		if (board[4][col - 1] != 0)
		    System.out.println("\t 5: 500");
		row = Keyboard.readInt();
	    } while (!((row == 1 && board[0][col - 1] != 0)
		       || (row == 2 && board[1][col - 1] != 0)
		       || (row == 3 && board[2][col - 1] != 0)
		       || (row == 4 && board[3][col - 1] != 0)
		       || (row == 5 && board[4][col - 1] != 0)));

	    printQues(col, row);
	    System.out.println("Buzz to answer the question!");
	    try {
		buzzed = in.readLine().substring(0,1);
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	    while (playerToAnswer(buzzed).equals("Error")) {
		System.out.println("\nNo one has that buzzer!! Buzz again!\n");
		try {
		    buzzed = in.readLine().substring(0,1);
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }

	    System.out.println(playerToAnswer(buzzed) + " buzzed first, so you get to answer!");


	    String ans = Keyboard.readString();
	    checkAns(ans, col, row);//implementing modular design

	} //ends for loop

    } //ends the game
    /*
     *After the user chooses the category they would like and the number of points they would like to play for
     *This uses the user's input data to go into the array of category names and compares the category name to the class name
     *Then prints out the points worth index of the question array in the category subclass. 
     */
    public void printQues(int col, int row) {
	//prints the questions corresponding to the random array
	if (newCat[col - 1].trim().equalsIgnoreCase("Nature"))
	    System.out.println(Nature.questions[row - 1]);
	if (newCat[col - 1].trim().equalsIgnoreCase("Math"))
	    System.out.println(Mathematics.questions[row - 1]);
	if (newCat[col - 1].trim().equalsIgnoreCase("Science"))
	    System.out.println(Sci.questions[row - 1]);
	if (newCat[col - 1].trim().equalsIgnoreCase("History")) {
	    System.out.println(History.questions[row - 1]);
	}
	if (newCat[col - 1].trim().equalsIgnoreCase("Logic")) {
	    System.out.println(Misc.questions[row - 1]);
	}
	if (newCat[col - 1].trim().equalsIgnoreCase("StuyTrivia")) {
	    System.out.println(Stuy.questions[row - 1]);
	}
	if (newCat[col - 1].trim().equalsIgnoreCase("Literature")) {
	    System.out.println(Literature.questions[row - 1]);
	}
	if (newCat[col - 1].trim().equalsIgnoreCase("Cheese")) {
	    System.out.println(Cheese.questions[row - 1]);
	}
	if (newCat[col - 1].trim().equalsIgnoreCase("China")) {
	    System.out.println(China.questions[row - 1]);
	}
	if (newCat[col - 1].trim().equalsIgnoreCase("CompSci")) {
	    System.out.println(CompSci.questions[row - 1]);
	}
	if (newCat[col - 1].trim().equalsIgnoreCase("Geography")) {
	    System.out.println(Geography.questions[row - 1]);
	}
	if (newCat[col - 1].trim().equalsIgnoreCase("Pop")) {
	    System.out.println(Impossible.questions[row - 1]);
	}
	if (newCat[col - 1].trim().equalsIgnoreCase("Women")) {
	    System.out.println(Women.questions[row - 1]);
	}
	if (newCat[col - 1].trim().equalsIgnoreCase("USA")) {
	    System.out.println(USA.questions[row - 1]);
	}
	if (newCat[col - 1].trim().equalsIgnoreCase("College")) {
	    System.out.println(Universities.questions[row - 1]);
	}
	if (newCat[col - 1].trim().equalsIgnoreCase("Plays")) {
	    System.out.println(Theatre.questions[row - 1]);
	}
	if (newCat[col - 1].trim().equalsIgnoreCase("Food")) {
	    System.out.println(Food.questions[row - 1]);
	}
	if (newCat[col - 1].trim().equalsIgnoreCase("Tech")) {
	    System.out.println(Technology.questions[row - 1]);
	}
	if (newCat[col - 1].trim().equalsIgnoreCase("Shows")) {
	    System.out.println(Shows.questions[row - 1]);
	}
	if (newCat[col - 1].trim().equalsIgnoreCase("Music")) {
	    System.out.println(Music.questions[row - 1]);
	}
	if (newCat[col - 1].trim().equalsIgnoreCase("Earth")) {
	    System.out.println(Earth.questions[row - 1]);
	}
	if (newCat[col - 1].trim().equalsIgnoreCase("Culture")) {
	    System.out.println(Culture.questions[row - 1]);
	}
	if (newCat[col - 1].trim().equalsIgnoreCase("Composers")) {
	    System.out.println(Composers.questions[row - 1]);
	}
	if (newCat[col - 1].trim().equalsIgnoreCase("Space")) {
	    System.out.println(Astronomy.questions[row - 1]);
	}
	if (newCat[col - 1].trim().equalsIgnoreCase("Sports")) {
	    System.out.println(Sport.questions[row - 1]);
	}
    }

    /*
     *After the user answers the question, this method compares what the user inputs to the subclass's corresponding answer.
     *If the user gets it right then that part of the game board disappears and the user gets the points
     *Otherwise the game prints out the correct answer.
     */
    public void checkAns(String ans, int col, int row) {
	//this if statement checks if the user's answer is correct and adds points if it is.
	   if ((newCat[col - 1].trim().equalsIgnoreCase("Nature") && ans.equalsIgnoreCase(Nature.answers[row - 1]))
	    || (newCat[col - 1].trim().equalsIgnoreCase("Math")     && ans.equalsIgnoreCase(Mathematics.answers[row - 1]))
	    || (newCat[col - 1].trim().equalsIgnoreCase("Science")  && ans.equalsIgnoreCase(Sci.answers[row - 1]))
	    || (newCat[col - 1].trim().equalsIgnoreCase("History")  && ans.equalsIgnoreCase(History.answers[row - 1]))
	    || (newCat[col - 1].trim().equalsIgnoreCase("Logic")    && ans.equalsIgnoreCase(Misc.answers[row - 1]))
	    || (newCat[col - 1].trim().equalsIgnoreCase("StuyTrivia")&& ans.equalsIgnoreCase(Stuy.answers[row - 1]))
	    || (newCat[col - 1].trim().equalsIgnoreCase("Literature")     && ans.equalsIgnoreCase(Literature.answers[row - 1]))
	    || (newCat[col - 1].trim().equalsIgnoreCase("Cheese")   && ans.equalsIgnoreCase(Cheese.answers[row - 1]))
	    || (newCat[col - 1].trim().equalsIgnoreCase("China")    && ans.equalsIgnoreCase(China.answers[row - 1]))
	    || (newCat[col - 1].trim().equalsIgnoreCase("CompSci")  && ans.equalsIgnoreCase(CompSci.answers[row - 1]))
	    || (newCat[col - 1].trim().equalsIgnoreCase("Geography")&& ans.equalsIgnoreCase(Geography.answers[row - 1]))
	    || (newCat[col - 1].trim().equalsIgnoreCase("Pop")      && ans.equalsIgnoreCase(Impossible.answers[row - 1]))
	    || (newCat[col - 1].trim().equalsIgnoreCase("Women")    && ans.equalsIgnoreCase(Women.answers[row - 1]))
	    || (newCat[col - 1].trim().equalsIgnoreCase("USA")      && ans.equalsIgnoreCase(USA.answers[row - 1]))
	    || (newCat[col - 1].trim().equalsIgnoreCase("College")  && ans.equalsIgnoreCase(Universities.answers[row - 1]))
	    || (newCat[col - 1].trim().equalsIgnoreCase("Plays")    && ans.equalsIgnoreCase(Theatre.answers[row - 1]))
	    || (newCat[col - 1].trim().equalsIgnoreCase("Food")     && ans.equalsIgnoreCase(Food.answers[row - 1]))
	    || (newCat[col - 1].trim().equalsIgnoreCase("Tech")     && ans.equalsIgnoreCase(Technology.answers[row - 1]))
	    || (newCat[col - 1].trim().equalsIgnoreCase("Shows")    && ans.equalsIgnoreCase(Shows.answers[row - 1]))
	    || (newCat[col - 1].trim().equalsIgnoreCase("Music")    && ans.equalsIgnoreCase(Music.answers[row - 1]))
	    || (newCat[col - 1].trim().equalsIgnoreCase("Earth")    && ans.equalsIgnoreCase(Earth.answers[row - 1]))
	    || (newCat[col - 1].trim().equalsIgnoreCase("Culture")  && ans.equalsIgnoreCase(Culture.answers[row - 1]))
	    || (newCat[col - 1].trim().equalsIgnoreCase("Composers")&& ans.equalsIgnoreCase(Composers.answers[row - 1]))
	    || (newCat[col - 1].trim().equalsIgnoreCase("Space")    && ans.equalsIgnoreCase(Astronomy.answers[row - 1]))
	    || (newCat[col - 1].trim().equalsIgnoreCase("Sports")   && ans.equalsIgnoreCase(Sport.answers[row - 1]))) {
	    points[whichPlayer(buzzed)] += row * 100;
	    System.out.println("Congratulations! You answered the question correctly!");
	    board[row - 1][col - 1] = 0;
	} else {
	    System.out.println("You got it wrong");
	    if (totalPlayers == 1) {
		board[row - 1][col - 1] = 0;
		System.out.println("The correct answer is :");
		if ((newCat[col - 1].trim().equals("Nature"))) {
		    System.out.println(Nature.answers[row - 1]); }
		if ((newCat[col - 1].trim().equalsIgnoreCase("Math"))) {
		    System.out.println(Mathematics.answers[row - 1]); }
		if ((newCat[col - 1].trim().equalsIgnoreCase("Science"))) {
		    System.out.println(Sci.answers[row - 1]); }
		if ((newCat[col - 1].trim().equalsIgnoreCase("History"))) {
		    System.out.println(History.answers[row - 1]); }
		if ((newCat[col - 1].trim().equalsIgnoreCase("Logic"))) {
		    System.out.println(Misc.answers[row - 1]); }
		if ((newCat[col - 1].trim().equalsIgnoreCase("StuyTrivia"))) {
		    System.out.println(Stuy.answers[row - 1]); }
		if ((newCat[col - 1].trim().equalsIgnoreCase("Literature"))) {
		    System.out.println(Literature.answers[row - 1]); }
		if ((newCat[col - 1].trim().equalsIgnoreCase("Cheese"))) {
		    System.out.println(Cheese.answers[row - 1]); }
		if ((newCat[col - 1].trim().equalsIgnoreCase("China"))) {
		    System.out.println(China.answers[row - 1]); }
		if ((newCat[col - 1].trim().equalsIgnoreCase("Compsci"))) {
		    System.out.println(CompSci.answers[row - 1]); }
		if ((newCat[col - 1].trim().equalsIgnoreCase("Geography"))) {
		    System.out.println(Geography.answers[row - 1]); }
		if ((newCat[col - 1].trim().equalsIgnoreCase("Pop"))) {
		    System.out.println(Impossible.answers[row - 1]); }
		if ((newCat[col - 1].trim().equalsIgnoreCase("Women"))) {
		    System.out.println(Women.answers[row - 1]); }
		if ((newCat[col - 1].trim().equalsIgnoreCase("USA"))) {
		    System.out.println(USA.answers[row - 1]); }
		if ((newCat[col - 1].trim().equalsIgnoreCase("College"))) {
		    System.out.println(Universities.answers[row - 1]); }
		if ((newCat[col - 1].trim().equalsIgnoreCase("Plays"))) {
		    System.out.println(Theatre.answers[row - 1]); }
		if ((newCat[col - 1].trim().equalsIgnoreCase("Food"))) {
		    System.out.println(Food.answers[row - 1]); }
		if ((newCat[col - 1].trim().equalsIgnoreCase("Tech"))) {
		    System.out.println(Technology.answers[row - 1]); }
		if ((newCat[col - 1].trim().equalsIgnoreCase("Shows"))) {
		    System.out.println(Shows.answers[row - 1]); }
		if ((newCat[col - 1].trim().equalsIgnoreCase("Music"))) {
		    System.out.println(Music.answers[row - 1]); }
		if ((newCat[col - 1].trim().equalsIgnoreCase("Earth"))) {
		    System.out.println(Earth.answers[row - 1]); }
		if ((newCat[col - 1].trim().equalsIgnoreCase("Culture"))) {
		    System.out.println(Culture.answers[row - 1]); }
		if ((newCat[col - 1].trim().equalsIgnoreCase("Composers"))) {
		    System.out.println(Composers.answers[row - 1]); }
		if ((newCat[col - 1].trim().equalsIgnoreCase("Space"))) {
		    System.out.println(Astronomy.answers[row - 1]); }
		if ((newCat[col - 1].trim().equalsIgnoreCase("Sports"))) {
		    System.out.println(Sport.answers[row - 1]); }

	    }
	}
    }

    /*
     *This method takes in an int array and an input and checks that it is not repeating.
     *it is used in categoryChooser to make sure that the index is not repeating before appending it to the ind array
     */
    public boolean checkArray(int[] arr, int input) {
	boolean retBool = true;
	for (int i = 0; i < arr.length; i++) {
	    if (arr[i] == input) {
		return false;
	    }
	}
	return retBool;
    }

    /*
     * categoryChooser randomly generates a number between 0 and the length of the categories instance variable. 
     * then it returns an int array of randomly generated numbers that are not repeating.
     * these numbers represent the index of the category in the categories instance variable
     */
    public int[] categoryChooser(String[] categories, int[] ind) {
	//Randomly generates a number and checks that it is not repeating.
	//this is used to randomly generate categories.
	int ctr = 0;
	while (ctr < 5) {
	    int rand = (int) (Math.random() * categories.length);
	    if (checkArray(ind, rand)) {
		ind[ctr] = rand;
		ctr += 1;
	    } else {
		ctr += 0;
	    }
	}
	return ind;
    }

    /*
     *This method calls category chooser and fills the String array answer with the corresponding categories of the ind array 
     * that is generated using categoryChooser and checkArray.
     */
    public String[] createCategory(){
	String[] ans = new String[5];
	int[] ind = new int[5];
	ind = categoryChooser(categories, ind);
	for (int i = 0; i < 5; i++) {
	    ans[i] = categories[ind[i]] + "";
	}
	return ans;
    }


    //Overwritten toString that prints the game board headers
    public String toString() {
	//Adds the category names
	String ans = "";
	for (String category : newCat) {//newcat is an instance variable array that is randomly generated
	    ans += String.format("%-11s", category);
	}
	ans += "\n";
	return ans;
    }

    /*
     *This method is similar to the method we previously wrote when we were making the 2D array utils class.
     *it populates a 2D array with the points. 
     */
    public int[][] pointTable(int[][] board) {
	//Creates the two d array that will be used to print the points.
	for (int row = 0; row < board.length; row++) {
	    for (int col = 0; col < board[row].length; col++) {
		board[row][col] = (row + 1) * 100;
	    }
	}
	return board;
    }

    /*
     * This method is similaar to the method we previously wrote when we were making the 2D array utils class.
     * It loops through a 2D array and prints it out in the form of a table. 
     */
    public static void print2(int[][] board) {
	//prints two d array with a tab in between
	for (int[] k : board) {
	    for (int j : k) {
		if (j != 0)
		    System.out.print(j);
		else
		    System.out.printf("%3s", "");
		System.out.printf("%8s", "");
	    }
	    System.out.println();
	}
    }

    /*
     *String of each player ordered in terms of their points
     */
    public String sortRank() {
	for (int i = 0; i < playerNames.length - 1; i++) {
	    if (points[i] < points[i + 1]) {
		//switches points
		int temp = points[i];
		points[i] = points[i + 1];
		points[i + 1] = temp;
		//switches playerNames when points are switched
		String temps = playerNames[i];
		playerNames[i] = playerNames[i + 1];
		playerNames[i + 1] = temps;
	    }
	}
	return null;
    }

    public int whichPlayer(String buzz) {
	for (int i = 0; i < buzzer.length; i++) {
	    if (buzz == buzzer[i]) {
		return i; }
	}
	return 0;
    }
    
    //Returns a calculation of each player's total points
    public void addPoints(int index) {
	//player in index will have the amount of points in points[index]
	points[index] += questionWorth[0];
    }

    public String playerToAnswer(String character) {
	for (int x = 0; x < buzzer.length; x++) {
	    if (buzzer[x].equals(character)) {
		return playerNames[x];
	    }
	}
	return "Error";
    }

    public static void main(String[] args) {
	Woo jeopardy = new Woo();
	//System.out.println(jeopardy.categories.length);
	jeopardy.newGame();
    }
}
