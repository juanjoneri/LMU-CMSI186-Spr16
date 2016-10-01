public class DiceGame {

    public enum Player {
        PLAYER_ONE("Player One"), PLAYER_TWO("Player Two");
        
        private String representation;
        private Player(String representation) {
            this.representation = representation;
        }

        @Override
        public String toString() {
            return representation;
        }
    }//End of Plater enum

    public class Round {
        public Player winner; // null if tied.
        public Die playerOneDie;
        public Die playerTwoDie;
        public long points;
        
        public Round(Die playerOneDie, Die playerTwoDie) {
            this.playerOneDie = playerOneDie;
            this.playerTwoDie = playerTwoDie;
            this.points = (playerOneDie.getFaceUpSide() - playerTwoDie.getFaceUpSide());
            
            if(points > 0){
                this.winner = Player.PLAYER_ONE;
            } else if(points < 0) {
                this.winner = Player.PLAYER_TWO;
            } else { 
                this.winner = null;
            }
        }
        
        public long getPoints(){
            return this.points;
        }
        
        @Override
        public String toString() {
            if (winner == null) {
                return "It's a tie between " + Player.PLAYER_ONE + "'s " + playerOneDie + " and " +
                        Player.PLAYER_TWO + "'s " + playerTwoDie + "!";
            } else {
                Player loser = winner == Player.PLAYER_TWO ? Player.PLAYER_ONE : Player.PLAYER_TWO;
                Die winningDie = winner == Player.PLAYER_TWO ? playerTwoDie : playerOneDie;
                Die losingDie = winner == Player.PLAYER_TWO ? playerOneDie : playerTwoDie;
                return winner + "'s " + winningDie + " beats " + loser + "'s " + losingDie + 
                        ", scoring " + Math.abs(points) + " points!";
            }
        }
    }//End of Round class

    //variables for DiceGame class
    private DiceBag playerOneBag;
    private DiceBag playerTwoBag;
    private long playerOneScore;
    private long playerTwoScore;
    private long elapsedRounds;
    
    public DiceGame() {
        this(new DiceBag(), new DiceBag());
    }
    
    public DiceGame(DiceBag playerOneBag, DiceBag playerTwoBag) {
        if (!playerOneBag.equals(playerTwoBag)) {
            throw new IllegalArgumentException();
        }

        playerOneScore = 0;
        playerTwoScore = 0;
        this.playerOneBag = playerOneBag;
        this.playerTwoBag = playerTwoBag;
    }
    
    public DiceBag getPlayerTwoBag() {
        return playerTwoBag;
    }
    
    public DiceBag getPlayerOneBag() {
        return playerOneBag;
    }

    public boolean isMatchOver() {
        return (playerOneScore >= 30 || playerTwoScore >= 30 || elapsedRounds>=25);
    }
    
    public Player getLeader() {
        if(playerOneScore > playerTwoScore){            
            return Player.PLAYER_ONE;
        } else if(playerOneScore < playerTwoScore) {
            return Player.PLAYER_TWO;
        } else {
            return null;
        }
    }
    
    public Round playRound() {
        Round round = this.new Round(getPlayerOneBag().getRandomDie(), getPlayerTwoBag().getRandomDie());
        long roundPoints = round.getPoints();
        if(roundPoints > 0){
            playerOneScore += roundPoints;
        } else if(roundPoints < 0){
            playerTwoScore -= roundPoints;
        }
        return round;
    }

    public Round[] playMatch() {
        Round[] roundResults = new Round[25];
        elapsedRounds = 0; 
        while (!isMatchOver()){
            roundResults[(int) elapsedRounds] = this.playRound();
            elapsedRounds++;
        }
        return roundResults;
    }

    public static void main(String[] args) {
        
        DiceGame diceGame = null;
        boolean playGame = true;

        if (args.length == 0){
            diceGame = new DiceGame();
            System.out.println("You are about to witness a \"Standar\" Dice Game");
        } else {
            long[] argsLong = new long[args.length];
            for (int i=0; i<args.length; i++){
                if(isArgumentValid(args[i])){                    
                    argsLong[i] = Long.parseLong(args[i]);
                } else {
                    System.out.println("The variable \"" + args[i] + "\" is not a valid argument");
                    playGame = false;
                }
            }
            
            if (playGame){
                DiceBag diceBag1 = new DiceBag(argsLong);
                DiceBag diceBag2 = new DiceBag(argsLong);
            
                for (int i=0; i < Math.floor(Math.random()*20); i++){
                    diceBag1.reorderDice();
                    diceBag2.reorderDice();
                }
            
                diceGame = new DiceGame(diceBag1, diceBag2);
                System.out.println("You are about to witness a \"Freeform\" Dice Game");  
            }
                      
        }
        
        if (playGame){
            System.out.println();
            Round[] rounds = diceGame.playMatch();
        
            for (Round round : rounds){
            if(round != null){
                System.out.println(round.toString());
            }
        } 
        
        System.out.println();
        System.out.println("Rounds Elapsed: " + diceGame.elapsedRounds);
        System.out.println("Player One Scored: " + diceGame.playerOneScore);
        System.out.println("Player Two Scored: " + diceGame.playerTwoScore);
        System.out.println("The Winner is: " + diceGame.getLeader().toString());
        } else {
            System.out.println();
            System.out.println("Cannot create a dice bag based on the supplied arguments");
            System.out.println("Make sure to only include long integers grater or equal to 2");
        }
        

    }
    
    public static boolean isArgumentValid(String arg){
        long l;
        try {
            l = Long.parseLong(arg);
        } catch(IllegalArgumentException ex) {
            return false;
        }
        return l>=2;
    }

}