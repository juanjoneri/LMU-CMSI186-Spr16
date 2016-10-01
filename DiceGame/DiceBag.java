import java.util.HashMap;
import java.util.Map;

public class DiceBag {
    public static final int DEFAULT_NUMBER_OF_DICE = 2; //Implement me!
    private Die[] dice;
    
    // Constructs a DiceBag with the default number of Die objects, each 
    // with the default number of sides.
    public DiceBag() {
        this.dice = new Die[DEFAULT_NUMBER_OF_DICE];
        for(int i=0; i<dice.length; i++){
            this.dice[i] = new Die();
        }
    }
    
    // Constructs a DiceBag with the given number of Die objects, each 
    // with the default number of sides.
    public DiceBag(int numberOfDice) {
        this.dice = new Die[numberOfDice];
        for(int i=0; i<dice.length; i++){
            this.dice[i] = new Die();
        }
    }
    
    // Constructs a DiceBag with the given of Die objects, each 
    // with the given number of sides.
    public DiceBag(int numberOfDice, long sidesPerDie) {
        this.dice = new Die[numberOfDice];
        for(int i=0; i<dice.length; i++){
            this.dice[i] = new Die(sidesPerDie);
        }
    }
    
    // Constructs a DiceBag containing one die of each number 
    // of sides within sidesPerDie array.
    public DiceBag(long[] sidesPerDie) {
        this.dice = new Die[sidesPerDie.length];
        for (int i=0; i<sidesPerDie.length; i++){
            this.dice[i] = new Die(sidesPerDie[i]);
        }
    }
    
    // Roll every Die within the DiceBag and reorder the Dice within the 
    // bag randomly.
    public void shakeBag() {
        for(Die die: dice){
			die.roll();
		}
        
        reorderDice();
    }
    
    // Reorder the Dice within the bag randomly.
    public void reorderDice() {
        Die[] tempBag = new Die[dice.length];
        
        for (int i = dice.length -1 ; i >= 0; i--){
            
            int index = ((int) randomValue(i + 1) -1);
            Die tempDie = dice[index];
            tempBag[dice.length - i - 1] = tempDie;
            dice[index] = dice[i];
            
        }
        this.dice = tempBag;
    }
    
    public int getPosition(Die die){
        for (int i=0; i<dice.length; i++){
            if(new Die().exactMake(dice[i], die)){
                return i;
            }
        }
        return -1;
    }
    
    // Return the first non null Die chosen randomly from the DiceBag.
    public Die getRandomDie() {
        return dice[(int) randomValue(dice.length)-1];
    }
    
    private static long randomValue(long max){
        return (long) Math.floor(Math.random()*max+1);
    }
    
    // Replace the Die at givenindex within the DiceBag with the provided Die.
    public void setDie(int index, Die die) {
        if(index<=(dice.length-1)){
            dice[index] = die;
        }
    }
    
    // Returns the Die at given index within the DiceBag.
    public Die getDie(int index) {
        if(index<=(dice.length-1)){
            return dice[index];
        }
        throw new IndexOutOfBoundsException("There are not " + index + " in the bag");
    }
    
    public int getBagSize(){
        return this.dice.length;
    }
    
    public Die[] getDies(){
        return this.dice;
    }
    
    
    // Returns true if this DiceBag contains the equivalent Dice in the same 
    // order, regardless of face-up side.
    public boolean orderedEquals(DiceBag diceBag) {
        boolean sameSize = diceBag.getBagSize() == this.getBagSize();
        boolean sameDice = true;
        if (sameSize){
            Die[] bag1 = this.dice;
            Die[] bag2 = diceBag.getDies();
            
            for (int i=0; i<bag1.length; i++){
                //inneficient code#
                sameDice = new Die().sameMake(bag1[i], bag2[i]);
                if(!sameDice){
                    break;
                }
            }
        }
        return sameSize && sameDice;
    }
    
    public Die[] orderBySize(Die[] initialBag){
        Die[] bag = initialBag;
        
        for (int i=0; i<bag.length; i++){
            int biggest = 0;
            int index = 0;
            //I repeat until -i because i now from there on they are in order
            for(int j=0; j<bag.length-i; j++){
                if(bag[j].getNumberOfSides() > biggest){
                    biggest = (int) bag[j].getNumberOfSides();
                    index = j;
                }
            }
            Die replacedDie = bag[bag.length - i - 1];
            Die toMoveDie = bag[index];
            
            bag[bag.length - i - 1] = toMoveDie;
            bag[index] = replacedDie;
        }
        return bag;
    }
    
    // Returns a string representation of the DiceBag.
    @Override
    public String toString() {
        String bagString = "{";
        for (int i = 0; i < dice.length; i++) {
            bagString += dice[i].toString();
            if (i != dice.length - 1) {
                bagString += ", ";
            }
        }
        bagString += "}";
        return bagString;
    }
    
    // Returns true if this DiceBag is contains the same number of Dice in 
    // the same ratio of sides.
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        DiceBag other = (DiceBag)obj;
        if ( this.dice.length != other.dice.length) {
            return false;
        } else {
            return this.tally().equals(other.tally());
        }
        
    }
    
    private Map<Long, Integer> tally() {
        Map<Long, Integer> result = new HashMap<Long, Integer>();
        for (Die die: dice) {
            Integer value = result.get(die.getNumberOfSides());
            if (value == null) {
                result.put(die.getNumberOfSides(), 1);
            } else {
                result.put(die.getNumberOfSides(), value + 1);
            }
        }
        
        return result;
    }
}