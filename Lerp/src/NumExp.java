/**
 * A representation of a number lerp expression.
 *
 * @author Arthur Nunes-Harwitt
 * @author Aidan Rubenstein
 */
public class NumExp implements Expression {

    private double num;

    /**
     * Construct a number expression.
     *
     * @param num the double that is the number
     */
    public NumExp(double num){
        this.num = num;
    }

    @Override
    public ANFExp toANF(){
        ANFVarExp t = new ANFVarExp();
        return new ANFLetExp(t, new ANFConstOp(num), t);
    }

    @Override
    public Triple<ANFVarExp, ANFOp, Expression> extract(){
        ANFVarExp t = new ANFVarExp();
        return new Triple<>(t, new ANFConstOp(num), new Holder(t));
    }

    @Override
    public String toString(){
        return ""+this.num;
    }
}
