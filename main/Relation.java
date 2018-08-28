package main;

public abstract class Relation extends Couple {
	
	public Relation() {}
	
	public Boolean[] Inversion(Boolean[] couple) {
		Boolean x1 = couple[0];
		Boolean x2 = couple[1];
		Boolean[] inv = {x2, x1};
		return inv;
	}
	
	public Boolean[] TrioV(Boolean x1, Boolean x2) {
		Boolean[] trioV = Trio(x1, x2, x1);
		return trioV;
	}

}
