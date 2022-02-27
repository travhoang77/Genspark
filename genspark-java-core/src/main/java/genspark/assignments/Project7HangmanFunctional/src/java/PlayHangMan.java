import java.io.FileNotFoundException;
import java.io.IOException;

public class PlayHangMan {
    public static void main(String args[]) throws IOException {
        FunctionalHangMan game = new FunctionalHangMan("tree");
        game.play();
        game.recordScore();

        while(game.replay()) {
            game.reset();
            game.play();
            game.recordScore();
        }
    }
}
