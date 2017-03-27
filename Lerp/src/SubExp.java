/**
 * A representation of the subtraction lerp expression.
 *
 * @author Arthur Nunes-Harwitt
 * @author Aidan Rubenstein
 */
public class SubExp extends BinaryExp {

    /**
     * Construct a subtraction expression.
     *
     * @param exp1 the Expression that is the first operand of the
     * subtraction expression
     * @param exp2 the Expression that is the second operand of the
     * subtraction expression
     */
    public SubExp(Expression exp1, Expression exp2){
        super(exp1, exp2);
    }

    @Override
    public Triple<ANFVarExp, ANFOp, Expression> extract(){
        if(getExp1() instanceof Holder && getExp2() instanceof Holder){
            ANFVarExp t = new ANFVarExp();
            return new Triple<>(t, new ANFSubOp(((Holder) getExp1()).getVar(),((Holder)getExp2()).getVar()), new Holder(t));
        }
        else if(getExp1() instanceof Holder){
            Triple<ANFVarExp, ANFOp, Expression> temp = getExp2().extract();
            return new Triple<>(temp.first(), temp.second(), new SubExp(getExp1(),temp.third()));
        }
        else{
            Triple<ANFVarExp, ANFOp, Expression> temp1 = getExp1().extract();
            return new Triple<>(temp1.first(), temp1.second(), new SubExp(temp1.third(), getExp2()));
        }
    }

    @Override
    public String toString(){
        return "(- " + super.toString() + ")";
    }
}
