//import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
//import java.io.FileReader;
//import java.io.BufferedReader;

class GetMovies {
    static String[] movies() {
        String[] movies;

        movies = new String[]{
                "The Godfather",
                "The Shawshank Redemption",
                "Schindler'S List",
                "Raging Bull",
                "Casablanca",
                "Batman: The Dark Knight",
                "There Will Be Blood",
                "12 Years A Slave",
                "Boyhood",
                "Under The Skin",
                "In The Mood For Love",
                "Synecdoche New York",
                "Moonlight",
                "Team America: World Police",
                "Mulholland Drive",
                "Son Of Saul",
                "Far From Heaven",
                "Spirited Away",
                "Interstellar",
                "The Martian",
                "The Lion King",
                "The Reluctant Dragon",
                "Snow White And The Seven Dwarfs",
                "Alice In Wonderland",
                "Monsters, Inc.",
                "Finding Nemo",
                "Prince Of Persia: The Sands Of Time",
                "Toy Story 3",
                "Tron: Legacy",
                "Winnie The Pooh",
                "Growing Up Wild",
                "Fifty Shades Of Grey",
                "Babysitter",
                "Homecoming",
                "Mr.Bean",
                "Infinity War",
                "Black Panther",
                "Grudge",
                "Fifty Shades Of Grey",
                "Transformer",
                "The Amazing Spiderman",
                "Spiderman Homecoming",
                "Lala Land",
                "Original Sin",
                "Now You See Me",
                "Catch Me If You Can",
                "Pixels",
                "Captian America",
                "Infinity War",
                "Star Wars",
                "The Dark Knight Rises",
                "Dr.Strange",
                "Journey To The Center Of The Earth",
                "Guardians Of The Galaxy",
                "Jurrasic World",
                "Gravity",
                "Into A Wormhole"
        };

        return movies;
    }

}


class MovieKeeper{
    private static String[] allMovies = GetMovies.movies();
    private String[] current;
    private String[] guessing;
    private char[] guessedLetters = new char[27];
    private int movieLength;
    private Random rInt = new Random();


    void randomMovie(){
        setGuessedLetters();
        String movie = allMovies[rInt.nextInt(allMovies.length)];
        movieLength = movie.length();
        current = new String[movieLength];
        guessing = new String[movieLength];
        for(int i=0;i<movieLength;i++){
            current[i] = String.valueOf(movie.charAt(i));
            if (current[i].equals(" ") ||current[i].equals("-")){
                guessing[i] = current[i];
            } else {
                guessing[i] = "_";
            }
        }
    }

    private void setGuessedLetters(){
        for(int i=0;i<27;i++){
            guessedLetters[i] = '#';
        }
    }
    String getCurrentMovie(){
        String movie="";
        for (String s : current) {
            movie += s;
        }
        return movie;
    }

    String getGuessingMovie(){
        String movie="";
        for (String s : guessing) {
            movie += s+" ";
        }
        return movie;
    }

    boolean checkCompleted(){
        String movie="";
        for (String s : guessing) {
            movie += s;
        }
        return movie.equals(getCurrentMovie());
    }

    int getMovieLength(){
        return movieLength;
    }

    private boolean checkInGuessed(char letter){
        for (char guessedLetter : guessedLetters) {

            if (guessedLetter == '#') {
                return false;
            } else if (guessedLetter == letter) {
                return true;
            }
        }
        return false;
    }

    private void addLetter(char letter){
        for (int i=0; i<guessedLetters.length; i++){
            if (guessedLetters[i] == '#'){
                guessedLetters[i] = letter;
                break;
            }
        }
    }

    int checkLetter(char letter){
        if (checkInGuessed(letter)) return 1;
        String l = String.valueOf(letter);
        if(l.equals(" ")||l.equals("-")){
            return 0;
        }
        l = l.toLowerCase();
        addLetter(letter);
        int flag=0;
        for(int i=0;i<movieLength;i++){
            if(l.equals(current[i].toLowerCase())){
                guessing[i] = current[i];
                flag++;
            }
        }
        return flag;
    }

}


class GamePlayer{

    int lives;
    String name;
    int wins = 0;
    int totalGames = 0;

    GamePlayer(int life, String n){
        lives = life;
        name = n;
    }
}


class GameBoard{
    private GamePlayer player;
    void playGame(){
        newPlayer(10, "Player");
        String choice;
        player.totalGames++;
        Scanner in = new Scanner(System.in);
        MovieKeeper keeper = new MovieKeeper();
        keeper.randomMovie();
        System.out.print("\n*** Starting new game ***\n** Movie Length :");
        System.out.println(keeper.getMovieLength());
        System.out.println();
        while(true){
            if (keeper.checkCompleted()){
                System.out.println(keeper.getCurrentMovie());
                System.out.println("You win !!!");
                break;
            } else if (player.lives == 0){
                System.out.println("GameOver, you lose !");
                System.out.println("The movie was :"+keeper.getCurrentMovie());
                break;
            }
            System.out.print("** Lives :");
            System.out.println(player.lives);
            System.out.println(keeper.getGuessingMovie());
            System.out.print("\nEnter a letter/word :");
            choice = in.next();
            int correct = 0;

            if ("stop".equals(choice.toLowerCase())){
                break;
            } else {
                for (int i=0; i<choice.length(); i++){
                    correct = keeper.checkLetter(choice.charAt(i));
                    if (correct==0){
                        player.lives --;
                        System.out.println("The char "+choice.charAt(i)+" is not present");
                    }

                }
            }

        }
    }

    private void newPlayer(int lives, String name){
        player = new GamePlayer(lives, name);
    }
}


public class Main {

    public static void main(String[] args) {
        String input = "";
        do {

            Scanner in = new Scanner(System.in);
            System.out.println("The game is starting ...");
            GameBoard board = new GameBoard();
            board.playGame();
            System.out.println("Enter 'c' to continue, 'exit' to exit");
            System.out.println("Your choice :");
            input = in.next();
        } while (!"exit".equals(input));
    }
}
