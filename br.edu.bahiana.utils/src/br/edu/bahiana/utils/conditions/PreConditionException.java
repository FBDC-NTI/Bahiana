package br.edu.bahiana.utils.conditions;

import java.awt.Component.BaselineResizeBehavior;

public class PreConditionException extends Exception {
	public PreConditionException(Exception inner){
		initCause(inner.getCause());
	}
	public PreConditionException(){
		
	}
}
