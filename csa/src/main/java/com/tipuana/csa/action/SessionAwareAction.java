package com.tipuana.csa.action;

import java.util.Map;

public interface SessionAwareAction {

	public abstract Map<String,Object> getSession();
}
