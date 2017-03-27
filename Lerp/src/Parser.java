/**
 * A class that contains the static methods to parse lerp expressions.
 * 
 * @author Arthur Nunes-Harwitt
 * @author Aidan Rubenstein
 */

public class Parser {

    private static int pos;
    private static String[] tokens;


    /**
     * Build a data structure representation of the lerp expression
     * written as a string.
     * 
     * @param s a String that contains the text of a lerp expression
     * @return an Expression data structure representing the lerp expression
     */
    public static Expression parse(String s){
        tokens = s.replace("(", " ( ").replace(")", " ) ").trim().split("( )+");
        pos = 0;
        try{
            Expression e = getExpression();
            if (pos < tokens.length){
                Errors.error("Too much input.", null);
            } else {
                return e;
            }
        } catch (NumberFormatException e) {
            Errors.error("Expression must start with a number or open parenthesis.", null);
        } catch (Exception e) {
            Errors.error("Unexpected error ", e);
        }
        return null; // Shouldn't get here. To satisfy Java.
    }


    /**
     * Gets the number entered by the user and creates a new Expression
     *
     * @return n a new instance of NumExp at the given position in tokens
     */
    private static Expression getNumber(){
        NumExp n = new NumExp(Double.parseDouble(tokens[pos]));
        pos++;
        return n;
    }

    /**
     * Creates a new Expression if the position at tokens is an open/closed parenthesis
     * position is then updated
     *
     * @return e a recursive call to getExpression
     */
    private static Expression getParens(){
        Expression e = getExpression();
        pos++;
        return e;
    }

    /**
     * Creates a new Expression invoking addition
     *
     * @return a new instance of AddExp between expressions
     */
    private static Expression getAddition(){
        return new AddExp(getExpression(), getExpression());
    }

    /**
     * Creates a new Expression invoking subtraction
     *
     * @return a new instance of SubExp between expressions
     */
    private static Expression getSub(){
        return new SubExp(getExpression(), getExpression());
    }

    /**
     * Creates a new Expression invoking multiplication
     *
     * @return a new instance of MulExp between expressions
     */
    private static Expression getMult(){
        return new MulExp(getExpression(), getExpression());
    }

    /**
     * Creates a new Expression invoking division
     *
     * @return a new instance of DivExp between expressions

    private static Expression getDiv(){
        return new DivExp(getExpression(), getExpression());
    }

    /**
     * Creates a new Expression invoking negative numbers
     *
     * @return a new instance of NegExp between expressions
     */
    private static Expression getNeg(){
        return new NegExp(getExpression());
    }

    /**
     * Creates a new Expression invoking square root of a number
     *
     * @return a new instance of SqrtExp between expressions
     */
    private static Expression getSqrt(){
        return new SqrtExp(getExpression());
    }

    /**
     * Reads through the position of tokens and executes helper methods when a number/mathematical symbol
     * is found
     *
     * @return helper functions invoking Expression
     */
    private static Expression getExpression(){
        if(tokens[pos].matches("[+-]?\\d+(\\.\\d+)?")) {
            return getNumber();
        }
        else if(tokens[pos].equals("(")){
            pos++;
            return getParens();
        }
        else if(tokens[pos].equals("+")){
            pos++;
            return getAddition();
        }
        else if(tokens[pos].equals("-")){
            pos++;
            if(tokens[pos].equals(")"))
                return getNeg();
            else
                return getSub();
        }
        else if(tokens[pos].equals("*")){
            pos++;
            return getMult();
        }
        else if(tokens[pos].equals("/")){
            pos++;
            return getDiv();
        }
        else if(tokens[pos].equals("sqrt")){
            pos++;
            return getSqrt();
        }
        return null; 
    }

}
