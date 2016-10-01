public class BigInteger{

    //CLASS LEVEL VARIABLES -----------------------------

    public static final BigInteger ZERO = new BigInteger(0); // a classwide constant for zero
    public static final BigInteger ONE = new BigInteger(1);; // a classwide constant for one
    public static final BigInteger TEN = new BigInteger(10);; // a classwide constant for ten


    private int[] values;
    private byte sign;

    //CONSTRUCTORS --------------------------------------

    public BigInteger(int[] valuesArr, byte sign){
        if(sign == 1 || sign == -1 || sign == 0){
            this.sign = sign;
        }else {
            throw new IllegalArgumentException("sign {1, 0, -1}");
        }

        if(valuesArr[0] == 0){
            if(valuesArr.length != 1){
                throw new IllegalArgumentException("first value of 0");
            }else{
                //meaning + or - 0 should be just 0
                this.sign = (byte) 0;
            }
        }

        this.values = new int[valuesArr.length];
        for(int i=0; i<values.length; i++){
            if((valuesArr[i] >= 0) && (valuesArr[i] < 10)){
                this.values[values.length - i -1] = valuesArr[i];
            }else {
                throw new IllegalArgumentException("array values [0, 9)");
            }
        }
    }

    public BigInteger(String val){
        //take out the white space and check if first char is \-
        val = val.trim();
        if(val.charAt(0) == '-'){
            this.sign = -1;
        } else{
            this.sign = 1;
        }

        //take out anything that is not a number and all not significant 0's
        val = val.replaceAll("\\D+","");

        if(val.length() == 0){
            //this means the provided argument did not contain numbers
            throw new IllegalArgumentException();
        }

        while(val.charAt(0) == '0' && val.length() > 1){
            val = val.substring(1);
        }

        if(val.charAt(0) == '0'){
            this.sign = 0;
        }
        populateValuesArray(val);
    }

    public BigInteger(long val){
        this.sign = (val > 0) ? (byte) 1 : (byte) -1;
        if(val == 0){
            this.sign = 0;
            this.values = new int[] {0};
        } else {
            String valName = String.valueOf(val);
            populateValuesArray(valName);
        }
    }

    //PUBLIC METHODS, MAIN ARITHMETIC-------------------------------------

    public BigInteger opposite(){
        //returns a BigInteger with the opsite value without altering this
        BigInteger val = new BigInteger(this.toString());
        val.switchSign();
        return val;
    }

    public BigInteger add(BigInteger val){
        // returns a BigInteger whose value is this + val
        //Default cases

        if(val.equals(ZERO)){
            return this;
        } else if(this.equals(ZERO)){
            return val;
        }else if(this.equals(val.opposite())){
            return ZERO;
        }
        val.opposite();

        int thisSign = (int) this.getSign();
        int valSign = (int) val.getSign();

        //check to see if it is a substraction
        if(valSign == -1 || thisSign == -1){
            //this is a substraction
            if(valSign == -1 && thisSign == 1){
                //val is negative
                return this.subtract(val.opposite());
            } else if(valSign == 1 && thisSign == -1){
                // this is negative
                return val.subtract(this.opposite());
            } else {
                //both are negative
                //we return the opposite of the additin of their opposite
                //-1 + -1 == -(1 + 1)
                return this.opposite().add(val.opposite()).opposite();
            }
        }

        int repe = Math.max(this.length(), val.length());
        int[] tempValues = new int[repe + 1];
        String tempBigIntName = "";

        for(int i = 0; i < repe; i++){
            if(i < Math.min(this.length(), val.length())){
                tempValues[i] = this.getValues()[i] + val.getValues()[i];
            } else {
                tempValues[i] = (this.length() == repe) ?  this.getValues()[i] : val.getValues()[i];
            }
        }

        normalizeValues(tempValues);
        for(int v: tempValues){
            tempBigIntName = String.valueOf(v) + tempBigIntName;
        }
        return new BigInteger(tempBigIntName);
    }

    public BigInteger subtract(BigInteger val){
        // returns a BigInteger whose value is this - val
        //Default cases
        if(val.equals(ZERO)){
            return this;
        } else if(this.equals(ZERO)){
            return val.opposite();
        }else if(this.equals(val)){
            return ZERO;
        }

        int thisSign = (int) this.getSign();
        int valSign = (int) val.getSign();

        //check to see if it is an addition
        if(valSign == -1 || thisSign == -1){
            //this might be an addition
            if(valSign == -1 && thisSign == 1){
                //t - (-v) == t + v
                //the opposite of val should be added
                return this.add(val.opposite());
            } else if(valSign == 1 && thisSign == -1){
                // (-t) - v = -(t + v)
                // the opposite of this should ve aded to val and the opposite retrned
                return val.add(this.opposite()).opposite();
            } else {
                //(-t) - (-v) == (-t) + v == v - t
                //we simply reverse the order
                return val.opposite().subtract(this.opposite());
            }
        }//at this point we only have to handle positive values

        if(val.compareTo(this) == 1){
            //val is greater than this
            //they are both positive
            //4 - 5 == -( 5 - 4 )
            return (val.subtract(this)).opposite();
        }

        int repe = Math.max(this.length(), val.length());
        int[] tempValues = new int[repe + 1];
        String tempBigIntName = "";

        for(int i = 0; i < repe; i++){
            if(i < Math.min(this.length(), val.length())){
                tempValues[i] = this.getValues()[i] - val.getValues()[i];
            } else {
                tempValues[i] = (this.length() == repe) ?  this.getValues()[i] : -val.getValues()[i];
            }
        }

        normalizeValues(tempValues);
        for(int v: tempValues){
            tempBigIntName = String.valueOf(v) + tempBigIntName;
        }
        return new BigInteger(tempBigIntName);
    }

    public BigInteger multiply(BigInteger val){
        // returns a BigInteger whose value is this * val
        //Default cases
        if(val.equals(ZERO)){
            return ZERO;
        } else if(this.equals(ZERO)){
            return ZERO;
        }else if(this.equals(ONE)){
            return val;
        }else if(val.equals(ONE)){
            return this;
        }

        //we take care of the sign
        if(this.getSign() == -1 || val.getSign() == -1){

            if(this.getSign() == 1 && val.getSign() == -1){
                //It is better if this is negative and not val
                return this.opposite().multiply(val.opposite());
            }else if(this.getSign() == -1 && val.getSign() == -1){
                return this.opposite().multiply(val.opposite());
            }
        }

        BigInteger result = ZERO;
        BigInteger thisCopy = new BigInteger(this.toString());
        //Russian peasant algorithm
        while(!val.equals(ZERO)){
            if(val.getValues()[0] % 2 != 0){
                result = result.add(thisCopy);
                val = val.subtract(ONE);
            }
            thisCopy = thisCopy.twice();
            val = val.halve();
        }
        return result;
    }

    public BigInteger divide(BigInteger val){
        // returns a BigInteger whose value is this / val
        //Default cases
        if(val.equals(ZERO)){
            throw new IllegalArgumentException("division by 0");
        }else if(val.equals(ONE)){
            return this;
        }else if(this.equals(val)){
            return ONE;
        }else if(this.equals(val.opposite())){
            return ONE.opposite();
        }else if(this.absoluteValue().compareTo(val.absoluteValue()) == -1){
            //if the numerator is numerically grater than the numerator we know the divisio gives 0
            return ZERO;
        }

        //we take care of the sign
        if(this.getSign() == -1 || val.getSign() == -1){
            if(this.getSign() == 1 && val.getSign() == -1){
                //To simplify the code only this can be negative
                return this.opposite().divide(val.opposite());
            }else if(this.getSign() == -1 && val.getSign() == -1){
                return this.opposite().divide(val.opposite());
            }
        }

        //we perfom the division
        int exp = (this.length() - val.length());
        BigInteger result = ZERO;
        BigInteger thisCopy = new BigInteger(this.toString());
        boolean isNegative = (thisCopy.getSign() == -1);
        if (isNegative){
            //we switch the sign at the end because before we made sure val cant be negative
            thisCopy.setSign((byte) 1);
        }

        //division algorithm so we dont have to keep adding to get to the result
        while(exp >= 0){
            BigInteger fact = ONE;
            while(thisCopy.compareTo(val.multiply(fact.exponent(exp))) == 1){
                //while( val*(fact*(10^exp)) < this )
                fact = fact.add(ONE);
            }
            //we exited the loop which means:
            //either we exeded this and we need to use the prevous fact and try with smaller exp
            //or we just hit val
            if(thisCopy.compareTo(val.multiply(fact.exponent(exp))) == -1){
                fact = fact.subtract(ONE);
                result = result.add(fact.exponent(exp));
                thisCopy = thisCopy.subtract(val.multiply(fact.exponent(exp)));
                exp --;
            } else {
                result = result.add(fact.exponent(exp));
                return (!isNegative) ? result : result.opposite();
            }
        }
        return (!isNegative) ? result : result.opposite();
    }

    //OTHER PUBLIC METHODS --------------------------------------------------------

    public BigInteger halve(){
        //default values
        if(this.equals(ZERO)){
            return ZERO;
        }else if(this.equals(TEN)){
            return new BigInteger(5);
        }
        if(this.getValues()[0] % 2 == 0){
            BigInteger result = new BigInteger(this.getValues()[0]/2);
            BigInteger tempInt = new BigInteger(0);

            for(int i=1; i<this.length(); i++){
                if(this.getValues()[i] % 2 == 0){
                    tempInt = new BigInteger(this.getValues()[i]/2);
                    tempInt = tempInt.exponent(i);
                } else {
                    tempInt = new BigInteger((this.getValues()[i]*10)/2);
                    tempInt = tempInt.exponent(i-1);
                }
                result = result.add(tempInt);
            }
            return (this.getSign() == 1) ? result : result.opposite();
        } else {
            throw new IllegalArgumentException("cant halve odd numbers");
        }
    }

    public BigInteger twice(){
        BigInteger thisCopy = new BigInteger(this.toString());
        thisCopy = thisCopy.add(thisCopy);
        return thisCopy;
    }

    public BigInteger remainder(BigInteger val){
        // returns a BigInteger whose value is floor(this % val)
        //10/3 = 3 => remainder = 1 = 10 - (10/3)3
        //-10/3 = -3 => remainder = -1 = -10 - (-10/3)3
        return this.subtract(this.divide(val).multiply(val));
    }

    public BigInteger exponent(int exp){
        //returns this*10^exp
        if(exp == 0){
            //a * 10^0 = a
            return this;
        } else if(exp > 0){
            String result = this.toString();
            for(int i=1; i<= exp; i++){
                result += "0";
            }
            return new BigInteger(result);
        }else {
            return this.divide(ONE.exponent(-exp));
        }
    }

    public void switchSign(){
        //switches the sign but does not return a new BibInteger
        this.sign = (byte) -this.sign;
    }

    public String toString(){
        // returns the decimal string represention of this BigInteger
        String BigIntName = "";

        for(int value: values){
            BigIntName = value + BigIntName;
        }

        switch(sign){
            //can use this to change sign naming convenstion, example:
                // case 1: BigIntName = "(+)" + BigIntName;
                    //would show positive BigInts as (+)31415926
            case 1: BigIntName = "" + BigIntName;
            break;
            case 0: BigIntName = "" + BigIntName;
            break;
            case -1: BigIntName = "-" + BigIntName;
            break;
        }

        return BigIntName;
    }

    public int compareTo(BigInteger val){
        // returns -1/0/1 as this BigInteger is numerically less than/equal to/greater than val
        if(this.equals(val)){
            return 0;
        }

        if(this.getSign() < val.getSign()){
            return -1;
        } else if(this.getSign() > val.getSign()){
            return 1;
        }

        if(this.sign == 1){
            //this means they are both positive
            if(this.length() > val.length()){
                return 1;
            } else if (this.length() < val.length()){
                return -1;
            }
            for(int i=(this.length() -1); i>=0; i--){
                if(this.values[i] > val.getValues()[i]){
                    return 1;
                } else if(this.values[i] < val.getValues()[i]) {
                    return -1;
                }
                //if they are the same we continue with an order of magnitude less
            }
        } else if(this.sign == -1){
            //this means they are both negative
            return - this.opposite().compareTo(val.opposite());
        }
        return 0;
    }

    public boolean equals(Object obj){
        // returns true iff x is a BigInteger whose value is numerically equal to this BigInteger
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        BigInteger other = (BigInteger)obj;
        return (this.toString().equals(other.toString()));
    }


    //STATIC CLASS METHODS ------------------------------

    public static BigInteger valueOf(long val){
        // "static factory" for constructing BigIntegers out of longs
        BigInteger bigInt = new BigInteger(val);
        return bigInt;
    }

    //GETTERS AND SETTERS -------------------------------

    public byte getSign(){
        return this.sign;
    }

    public int[] getValues(){
        return this.values;
    }

    public int length(){
        return this.values.length;
    }

    public void setSign(byte sign){
        if((sign == 1 || sign == -1) && (this.sign != 0)){
            this.sign = sign;
        }else{
            throw new IllegalArgumentException();
        }
    }

    //PRIVATE MEHTODS -----------------------------------
    public BigInteger absoluteValue(){
        return (this.getSign() == 1) ? this : this.opposite();
    }

    private void normalizeValues(int[] values){
        //changes the ints in values to the standard form
        for(int i=0; i<values.length; i++){
            if(values[i] >= 10){
                values[i] = values[i] % 10;
                values[i+1] ++;
            } else if (values[i] < 0){
                values[i] = 10 + values[i];
                values[i+1] --;
            }
        }
    }

    private void populateValuesArray(String valName){
        valName = valName.replaceAll("\\D+","");
        int valLen = valName.length();
        this.values = new int[valLen];

        for(int i=0; i < valLen; i++){
            this.values[(valLen - 1) - i] = Character.getNumericValue(valName.charAt(i));
        }
    }
}
