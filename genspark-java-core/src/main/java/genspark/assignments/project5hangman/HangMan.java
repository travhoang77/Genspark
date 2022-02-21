import java.util.*;

public class HangMan {
    private String word;
    private Character[] answer;
    private Set<Character> missed;
    private static int attempts = 0;

    public String getWord() {
        return word;
    }

    public HangMan(String word) {
        this.word = word.toLowerCase();
        this.answer = new Character[word.length()];
        missed = new HashSet<>();
    }

    public void play() {
        printTitle();
        while(!isSolved() && !isHanged()) {
            drawHangman();
            printMissedLetters();
            printPuzzle();

            Character guess = takeGuess();

            try {
                solve(guess);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            if(isHanged()) printLosingMessage();
            if(isSolved()) printWinningMessage();
        }
    }

    protected void printTitle() {
        System.out.println("H A N G M A N");
    }

    protected void printPuzzle() {
        String puzzle = "";
        for(int i = 0; i < this.answer.length; i++) {
            if (answer[i] == null) puzzle = puzzle.concat("_");
            else puzzle = puzzle.concat(String.valueOf(answer[i]));
            if(this.answer.length > 1) puzzle = puzzle.concat(" ");
        }
        System.out.println(puzzle);
    }

    protected void printMissedLetters() {
        String result = "";
        Iterator it = this.missed.iterator();

        while(it.hasNext()) {
            result = result.concat(String.valueOf(it.next()));
        }
        System.out.println(String.format("Missed letters: %s", result));
    }

    private boolean existsInAnswer(Character c) {
        for(Character character : answer){
            if (character != null && character.equals(c)) {
              return true;
            }
        }
        return false;
    }

    private boolean alreadyEntered(Character c){
        return this.missed.contains(c) || existsInAnswer(c);
    }

    private boolean wordHasCharacter(Character c){
        return word.indexOf(String.valueOf(c)) > -1;
    }


    protected Character takeGuess() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Guess a letter.\n");
            String input;
            input = scanner.next().toLowerCase();
            if(input.length() != 1) {
                continue;
            };
            if(alreadyEntered(input.charAt(0))) {
                System.out.println("You have already guessed that letter. Choose again.");
                continue;
            }
            return input.charAt(0);
        }
    }

    protected void setInstancesOfCharacter(Character c){
        for(int i = 0; i < word.length(); i++) {
            if(word.charAt(i) == c) answer[i] = c;
        }
    }

    protected void solve(Character c) throws Exception {
        if (this.alreadyEntered(c)) throw new Exception("This character has already been entered");

        if(wordHasCharacter(c)) {
            setInstancesOfCharacter(c);
        } else {
            missed.add(c);
            attempts++;
        }
    }

    protected boolean isSolved() {
        for(Character c: answer) if(c == null) return false;
        return true;
    }

    protected boolean isHanged() {
        return attempts == 7;
    }

    protected void printWinningMessage() {
        System.out.println(String.format("Yes! The secret word is \"%s\"! You have won!", getWord()));
    }

    protected void printLosingMessage() {
        drawHangman();
        System.out.println("No! You failed to guess the word. You have been hang!");
    }

    protected boolean replay() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to play again? (yes or no)\n");

        while(true) {
            String input = scanner.next().trim().toLowerCase();
            if(input.equals("yes")) return true;
            if(input.equals("no")) return false;
        }
    }

    protected void reset() {
        this.answer = new Character[word.length()];
        missed = new HashSet<>();
        attempts = 0;
    }

    protected void drawHangman() {
        System.out.println(" +---+");
        switch (this.attempts) {
            case 1:
                System.out.println(" O   |");
                System.out.println("     |");
                System.out.println("     |");
                System.out.println("    ===");
                break;
            case 2:
                System.out.println(" O   |");
                System.out.println(" |   |");
                System.out.println("     |");
                System.out.println("    ===");
                break;
            case 3:
                System.out.println(" O   |");
                System.out.println("_|   |");
                System.out.println("     |");
                System.out.println("    ===");
                break;
            case 4:
                System.out.println(" O   |");
                System.out.println("_|_  |");
                System.out.println("     |");
                System.out.println("    ===");
                break;
            case 5:
                System.out.println(" O   |");
                System.out.println("_|_  |");
                System.out.println(" |   |");
                System.out.println("    ===");
                break;
            case 6:
                System.out.println(" O   |");
                System.out.println("_|_  |");
                System.out.println(" |   |");
                System.out.println("/   ===");
                break;
            case 7:
                System.out.println(" O   |");
                System.out.println("_|_  |");
                System.out.println(" |   |");
                System.out.println("/ \\ ===");
                break;
            default:
                System.out.println("     |");
                System.out.println("     |");
                System.out.println("     |");
                System.out.println("    ===");
                break;
        }
    }
 }
