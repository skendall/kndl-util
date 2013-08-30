package org.kndl.util.console;

/**
 * Root class for a menu and a menu item.  This allows
 * either to be accessed in the same fashion, while
 * also allowing custom behavior for both.
 * 
 * @author skendall
 *
 */

public abstract class MenuAction {
	
	/** Menu label to be printed in the banner and
	 * as a menu option.
	 */
	
	private String label;
	
	/** Parent of this action **/
	
	private MenuAction parent;
	
	/**
	 * Principle overridden method used to implement
	 * the behavior of either a menu or menu item.
	 * 
	 * @return
	 */
	
	public abstract MenuAction execute();

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public MenuAction getParent() {
		return parent;
	}

	public void setParent(MenuAction parent) {
		this.parent = parent;
	}
	
	public boolean hasParent() {
		return getParent() != null;
	}

}
