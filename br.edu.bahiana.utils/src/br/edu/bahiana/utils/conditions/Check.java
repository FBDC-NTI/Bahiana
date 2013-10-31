package br.edu.bahiana.utils.conditions;

public class Check {
	public static void require(Boolean condition, Exception inner) throws PreConditionException{
		if (condition == false)
			throw new PreConditionException(inner);
	}
	public static void require(Boolean condition) throws PreConditionException{
		if (condition == false)
			throw new PreConditionException();
	}
	
	public static void ensure(Boolean condition) throws PosConditionException{
		if (condition == false)
			throw new PosConditionException();
	}
	public static void ensure(Boolean condition, Exception inner) throws PosConditionException{
		if (condition == false)
			throw new PosConditionException(inner);
	}
}
