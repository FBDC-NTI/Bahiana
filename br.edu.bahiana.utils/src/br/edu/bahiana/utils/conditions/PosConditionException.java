package br.edu.bahiana.utils.conditions;

public class PosConditionException extends Exception {
	public PosConditionException(Exception inner) {
		initCause(inner.getCause());
	}
	public PosConditionException(){}
}
