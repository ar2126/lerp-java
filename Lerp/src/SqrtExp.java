/**
 * A representation of the square root lerp expression.
 *
 * @author Arthur Nunes-Harwitt
 * @author Aidan Rubenstein
 */
public class SqrtExp extends UnaryExp {

    /**
     * Construct a square root expression.
     *
     * @param exp the Expression that is the first operand of the
     * square root expression
     */
    public SqrtExp(Expression exp){
        super(exp);

    }

    @Override
    public Triple<ANFVarExp, ANFOp, Expression> extract(){
        if(getExp() instanceof Holder ){
            ANFVarExp t = new ANFVarExp();
            return new Triple<>(t, new ANFSqrtOp(((Holder) getExp()).getVar()), new Holder(t));
        }
        else{
            Triple<ANFVarExp, ANFOp, Expression> temp1 = getExp().extract();
            return new Triple<>(temp1.first(), temp1.second(), new SqrtExp(temp1.third()));
        }
    }

    @Override
    public String toString(){
        return "(sqrt " + super.toString() + ")";
    }
}
