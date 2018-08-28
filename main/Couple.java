package main;

public abstract class Couple {
	
	Boolean[] couple;
	
	public Couple() {}
	
	public Boolean[] Pair(Boolean x1, Boolean x2) {
		Boolean[] pair = {x1, x2};
		return pair;
	}
	
	public Boolean[] Trio(Boolean x1, Boolean x2, Boolean x3) {
		Boolean[] trio = {x1, x2, x3};
		return trio;
	}

}
