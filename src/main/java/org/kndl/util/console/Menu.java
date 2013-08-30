package org.kndl.util.console;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Component that provides methods for creating a list
 * of selectable items in a text-based console menu. All
 * items are added via the addOption method, which uses
 * STDIN to collect an integer from the keyboard.  That
 * integer is then matched to the index of an item and
 * that item's action is executed.
 * 
 * Sample usage of this object would look like so:
 * 
 * 	Menu menu1 = new Menu("Menu1");
 *	MenuItem item1 = new MenuItem("item1");
 *  MenuItem item2 = new MenuItem("item2");
 *  MenuItem item3 = new MenuItem("item3");
 *  menu1.addOption(item1);
 *  menu1.addOption(item2);
 *  menu1.addOption(item3);	
 *  MenuAction action = menu1.getOption();
 *  while(action != null) {
 *  	action = action.execute();
 *  }
 * 
 * Since this object is also a MenuAction it may be
 * used to add nested menus as well, like so:
 * 
 *  Menu menu1 = new Menu("Menu1");
 *  Menu menu2 = new Menu("SubMenu1");
 *  menu1.addOption(menu2);
 *  
 * This allows for a simple building and traversal of
 * the menu tree.
 * 
 * @author skendall
 *
 */

public class Menu extends MenuAction {
	
	/** Default character used to build each menu banner **/
	
	public static final char DEFAULT_BANNER_CHAR = '-';
	
	/** Default prompt used to request menu input from the user **/
	
	public static final String DEFAULT_PROMPT = "> ";
	
	/** Prompt used to request input **/
	
	private String prompt = DEFAULT_PROMPT;
	
	/** Character used to create the menu banner **/
	
	private char bannerCharacter = DEFAULT_BANNER_CHAR;
	
	/** List of menu actions to provide as a menu **/
	
	private List<MenuAction> actions;
	
	/**
	 * Constructs an object and sets a human readable
	 * label to be used on the menu banner.  All menu 
	 * objects must have a label.
	 * 
	 * @param label
	 */
	
	public Menu(String label) {
		setLabel(label);
		this.actions = new ArrayList<MenuAction>();
	}
	
	public String getPrompt() {
		return prompt;
	}

	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	public char getBannerCharacter() {
		return bannerCharacter;
	}

	public void setBannerCharacter(char bannerCharacter) {
		this.bannerCharacter = bannerCharacter;
	}

	public List<MenuAction> getActions() {
		return actions;
	}

	/**
	 * Principle method used to retrieve the next action
	 * to perform.
	 * 
	 * @return MenuAction
	 */
	
	public MenuAction getOption() {
		
		printMenuBanner();
		
		Scanner scanner = new Scanner(System.in);
		int idx = 1;
		for(MenuAction action : actions) {
			System.out.println(idx++ + ") " + action.getLabel());
		}
		System.out.println();
		printOutOption();
		System.out.print(prompt);
		int selection = scanner.nextInt();
		
		if((selection-1) >= actions.size()) {
			System.out.println("Invalid option.");
			return this;
		} else if(selection == 0) {
			return getParent();
		} else {
			return actions.get(selection-1);
		}
	}
	
	/**
	 * Adds an option to the menu and sets the current
	 * menu as the parent.  This is useful for traversing
	 * the tree backwards.
	 * 
	 * @param action
	 */
	
	public void addOption(MenuAction action) {
		action.setParent(this);
		actions.add(action);
	}
	
	/**
	 * In the case of a menu object, the course of
	 * execution is the retrieve another option.
	 */

	@Override
	public MenuAction execute() {
		return getOption();
	}
	
	/**
	 * Prints the menu banner.
	 */
	
	public void printMenuBanner() {
		int lineBreakSize = getLabel().length();
		for(int i=0;i<lineBreakSize+5;i++) {
			System.out.print(bannerCharacter);
		}
		System.out.println("\n" + getLabel() + " menu");
		for(int i=0;i<lineBreakSize+5;i++) {
			System.out.print(bannerCharacter);
		}
		System.out.println();
	}

	/**
	 * Decide whether the root menu is shown or not
	 * and print the appropriate option.
	 */
	
	private void printOutOption() {
		if(hasParent()) {
			System.out.println("0) Back");
		} else {
			System.out.println("0) Quit");
		}
	}
	
}
