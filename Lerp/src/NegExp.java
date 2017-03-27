/**
 * A representation of the negation lerp expression.
 *
 * @author Arthur Nunes-Harwitt
 * @author Aidan Rubenstein
 */
public class NegExp extends UnaryExp {

    /**
     * Construct a negation expression.
     *
     * @param exp the Expression that is the first operand of the
     * negation expression
     */
    public NegExp(Expression exp){
        super(exp);

    }

    @Override
    public Triple<ANFVarExp, ANFOp, Expression> extract(){
        if(getExp() instanceof Holder ){
            ANFVarExp t = new ANFVarExp();
            return new Triple<>(t, new ANFNegOp(((Holder) getExp()).getVar()), new Holder(t));
        }
        else{
            Triple<ANFVarExp, ANFOp, Expression> temp1 = getExp().extract();
            return new Triple<>(temp1.first(), temp1.second(), new NegExp(temp1.third()));
        }
    }

    @Override
    public String toString(){
        return "(- " + super.toString() + ")";
    }
}
