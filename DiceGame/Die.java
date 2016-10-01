public class Die {
    public static final long DEFAULT_SIDES = 6;
    private long numberOfSides;
    private long faceUpSide;
    
    // Constructs a Die with the default number of sides (6) and with a 
    // random side face up.
    public Die() {
        this.numberOfSides = DEFAULT_SIDES;
        this.faceUpSide = randFace(DEFAULT_SIDES);
    }
    
    // Constructs a Die with the given number of sides with a random side face up.
    public Die(long numberOfSides) {
        if(numberOfSides>1){
            this.numberOfSides = numberOfSides;
            this.faceUpSide = randFace(numberOfSides);
        } else { 
            throw new IllegalArgumentException();
        }        
    }
    
    // Constructs a Die with the default number of sides with the given side
    // face up, provided the face is valid for the number of sides.
    //If it is invalid will just set it to a random face
    public Die(long numberOfSides, long faceUpSide) {
        if(isValidForDice(numberOfSides, faceUpSide)){
            this.numberOfSides = numberOfSides;
            this.faceUpSide = faceUpSide;
        }else {
            throw new IllegalArgumentException();
        }        
    }
    
    // Constructs a Die with the same number of sides and face of side as the
    // provided Die.
    public Die(Die die) {
        this.numberOfSides = die.getNumberOfSides();
        this.faceUpSide = die.getFaceUpSide();
    }
    
    //Returns a random face to be facing up
    private static long randFace(long numOfFaces){
        return (long) Math.floor(Math.random()*numOfFaces+1);
    }
    
    //Returns true when the dice has enough sides for the required side to be up
    private static boolean isValidForDice(long numberOfSides, long faceUpSide){
        return (numberOfSides>=faceUpSide && faceUpSide>0);
    }
    
    // Rolls the Die, changing the face up side to a random face valid for 
    // the number of sides.
    public void roll() {
        this.faceUpSide = randFace(this.numberOfSides);
    }
    
    // Changes the face up side of the Die to the provided face, provided it is 
    // valid for the number of sides.
    public void setFaceUpSide(long side) {
        if(isValidForDice(this.numberOfSides, side)){
            this.faceUpSide = side;
        } else {
            throw new IllegalArgumentException();
        }
    }
    
    // Returns the current face up side.
    public long getFaceUpSide() {
        return this.faceUpSide;
    }
    
    // Returns the number of sides.
    public long getNumberOfSides() {
        return this.numberOfSides;
    }
    
    // Returns true if the first and second Die have the same number of sides.
    public static boolean sameMake(Die firstDie, Die secondDie) {
        return(firstDie.getNumberOfSides() == secondDie.getNumberOfSides());
    }
    
    //Rerutns true if the two Die are equals and in the same state
    public static boolean exactMake(Die firstDie, Die secondDie) {
        return(firstDie.getFaceUpSide()==secondDie.getFaceUpSide() && firstDie.getNumberOfSides() == secondDie.getNumberOfSides());
    }
    
    // Returns a string representation of the Die.
    @Override
    public String toString() {
        return faceUpSide + " (d" + numberOfSides + ")";
    }
    
    // Indicates whether some other object is "equal to" this one.
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

        Die other = (Die)obj;
        return (this.numberOfSides == other.numberOfSides) && (this.faceUpSide == other.faceUpSide);
    }
}