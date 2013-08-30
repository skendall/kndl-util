package org.kndl.util.console;

public abstract class MenuItem extends MenuAction {

	public MenuItem(String label) {
		setLabel(label);
	}
	
	public abstract void executeItem();
	
	@Override
	public MenuAction execute() {
		executeItem();
		return getParent();
	}
	
}
