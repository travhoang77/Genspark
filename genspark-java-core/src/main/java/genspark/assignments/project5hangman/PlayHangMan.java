public class PlayHangMan {

    public static void main(String args[]) {
        HangMan game = new HangMan("cat");
        game.play();

        while(game.replay()) {
            game.reset();
            game.play();
        }
    }
}
