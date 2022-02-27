import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FunctionalHangMan {
    private String word;
    private Character[] answer;
    private Set<Character> missed;
    private int attempts = 0;

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    private String player;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    private int score;

    public FunctionalHangMan(String word) {
        this.word = word.toLowerCase();
        this.answer = new Character[word.length()];
        missed = new HashSet<>();
    }

    public void play() throws IOException {
        printTitle();
        if(getPlayer() == null) askPlayerName();
        while(!isSolved() && !isHanged()) {
            drawHangman();
            printMissedLetters();
            printPuzzle();

            Character guess = takeGuess();

            solve(guess);

            if(isHanged()) {
                tabulateScore();
                printLosingMessage();
                printScore();
            }
            if(isSolved()) {
                tabulateScore();
                printWinningMessage();
                printScore();
                if(isHighScore()) printYouGotHighScore();
            }
        }
    }

    private String getWord() {
        return word;
    }

    protected void printTitle() {
        System.out.println("H A N G M A N");
    }

    protected void askPlayerName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your name:");
        setPlayer(scanner.nextLine());
    }

    protected void printPuzzle() {

        String puzzle = Arrays.stream(answer).reduce("", (s, c) -> s + ((c == null) ? (answer.length > 1) ? "_ " : "_" :  (answer.length > 1) ? c + " " : c), (s1, s2) -> s1 + s2);
        System.out.println(puzzle);
    }

    protected void printMissedLetters() {
        System.out.printf("Missed letters: %s%n", missed.stream().reduce("", (s, c) -> s+c, (s1, s2) -> s1+s2));
    }

    private boolean existsInAnswer(Character c) {
        return Arrays.stream(answer).allMatch(character -> character != null && character.equals(c));
    }

    protected void solve(Character c) {

        if(wordHasCharacter(c)) {
            setInstancesOfCharacter(c);
        } else {
            missed.add(c);
            attempts++;
        }
    }

    protected boolean isSolved() {
        return Arrays.stream(answer).allMatch(Objects::nonNull);
    }

    private boolean alreadyEntered(Character c){
        return this.missed.contains(c) || existsInAnswer(c);
    }

    private boolean wordHasCharacter(Character c){
        return word.contains(String.valueOf(c));
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

    protected boolean isHanged() {
        return attempts == 7;
    }

    protected void printScore() {
        System.out.printf("%s, your score is %s\n", getPlayer(), getScore());
    }
    protected void printWinningMessage() {
        System.out.printf("Yes! The secret word is \"%s\"! You have won!%n\n", getWord());
    }
    protected void printLosingMessage() throws FileNotFoundException {
        drawHangman();
        System.out.println("No! You failed to guess the word. You have been hang!");
    }

    protected void tabulateScore() {
        long score = Arrays.stream(answer).filter(Objects::nonNull).count() * 1000;
        long bonus = (long) (7 - attempts) * 1000;

        setScore((int) (score + bonus));
    }

    protected boolean isHighScore() {
        return  getScore() == answer.length * 1000 + 7000;
    }

    protected void printYouGotHighScore() {
        System.out.println("You got high score!");
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
    protected void setInstancesOfCharacter(Character c){
        IntStream.range(0, word.length()).filter(i -> word.charAt(i) == c).forEach(i -> answer[i] = c);
    }

    protected void reset() {
        this.answer = new Character[word.length()];
        missed = new HashSet<>();
        attempts = 0;
        setScore(0);
    }

    protected void drawHangman() throws FileNotFoundException {
        BufferedReader bufReader = new BufferedReader(new FileReader("src/resources/sprite.txt"));
        ArrayList<String> arrayList = (ArrayList<String>) bufReader.lines().collect(Collectors.toList());

        switch (this.attempts) {
            case 1:
                arrayList.subList(5, 10).forEach(System.out::println);
                break;
            case 2:
                arrayList.subList(10, 15).forEach(System.out::println);
                break;
            case 3:
                arrayList.subList(15, 20).forEach(System.out::println);
                break;
            case 4:
                arrayList.subList(20, 25).forEach(System.out::println);
                break;
            case 5:
                arrayList.subList(25, 30).forEach(System.out::println);
                break;
            case 6:
                arrayList.subList(30, 35).forEach(System.out::println);
                break;
            case 7:
                arrayList.subList(35, 40).forEach(System.out::println);
                break;
            default:
                arrayList.subList(0, 5).forEach(System.out::println);
                break;
        }
    }

    protected void recordScore() throws IOException {
        File file = new File("src/resources/scores.txt");
        if(!file.exists()) {
            FileWriter myWriter = new FileWriter(file);
            myWriter.write(String.format("%s score: %s", getPlayer(), getScore()));
            myWriter.close();

        } else {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
            bufferedWriter.newLine();
            bufferedWriter.write(String.format("%s score: %s", getPlayer(), getScore()));
            bufferedWriter.close();
        }
    }

}
