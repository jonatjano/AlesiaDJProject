package server.ihm;

import server.config.language.LanguageManager;

/**
 * abstract class defining IHM usefull constants and making it impossible to use two differents instance of IHM
 * @author jonatjano
 */
public abstract class IHM
{
	/**
	 * style for black text
	 */
	public static final String BLACK_TEXT = "BLACK_TEXT";
	/**
	 * style for red text
	 */
	public static final String RED_TEXT = "RED_TEXT";
	/**
	 * style for green text
	 */
	public static final String GREEN_TEXT = "GREEN_TEXT";
	/**
	 * style for yellow text
	 */
	public static final String YELLOW_TEXT = "YELLOW_TEXT";
	/**
	 * style for blue text
	 */
	public static final String BLUE_TEXT = "BLUE_TEXT";
	/**
	 * style for purple text
	 */
	public static final String PURPLE_TEXT = "PURPLE_TEXT";
	/**
	 * style for cyan text
	 */
	public static final String CYAN_TEXT = "CYAN_TEXT";
	/**
	 * style for white text
	 */
	public static final String WHITE_TEXT = "WHITE_TEXT";

	/**
	 * style for black background
	 */
	public static final String BLACK_BACKGROUND = "BLACK_BACKGROUND";
	/**
	 * style for red background
	 */
	public static final String RED_BACKGROUND = "RED_BACKGROUND";
	/**
	 * style for green background
	 */
	public static final String GREEN_BACKGROUND = "GREEN_BACKGROUND";
	/**
	 * style for yellow background
	 */
	public static final String YELLOW_BACKGROUND = "YELLOW_BACKGROUND";
	/**
	 * style for blue background
	 */
	public static final String BLUE_BACKGROUND = "BLUE_BACKGROUND";
	/**
	 * style for purple background
	 */
	public static final String PURPLE_BACKGROUND = "PURPLE_BACKGROUND";
	/**
	 * style for cyan background
	 */
	public static final String CYAN_BACKGROUND = "CYAN_BACKGROUND";
	/**
	 * style for white background
	 */
	public static final String WHITE_BACKGROUND = "WHITE_BACKGROUND";

	/**
	 * style for error messages
	 */
	public static final String ERROR = "ERROR";
	/**
	 * style for info messages
	 */
	public static final String INFO = "INFO";

	/**
	 * window type for IHM, create a graphical window
	 */
	public static final String WINDOW = "WINDOW";
	/**
	 * graphic type for IHM, create a graphical window
	 */
	public static final String GRAPHIC = "GRAPHIC";
	/**
	 * terminal type for IHM, use the console launching the program
	 */
	public static final String TERMINAL = "TERMINAL";
	/**
	 * console type for IHM, use the console launching the program
	 */
	public static final String CONSOLE = "CONSOLE";

	/**
	 * Unique instance of {@link IHM}
	 */
	private static IHM instance = null;

	/**
	 * get the unique instance of IHM or create a new one
	 * @return the instance of IHM or a new console one if not instantiated yet
	 */
	public static IHM getInstance()
	{
		return getInstance(CONSOLE);
	}

	/**
	 * get the unique instance of IHM or create a new one of type
	 * @param  type type of IHM to create if instance is not instantiated yet
	 * @return the unique of IHM
	 */
	public static IHM getInstance(String type)
	{
		if (instance == null)
		{
			// type to upperCase to allow config in file to be written in a no-case sensitive way
			type = type.toUpperCase();
			switch (type)
			{
				// graphical type
				case WINDOW:
				case GRAPHIC:
					// TODO set to new IHMGraphic when created
					instance = new IHMConsole();
					printMessage("graphical interfaces not available yet, using console", ERROR);
				break;

				// console type
				case TERMINAL:
				case CONSOLE:
					instance = new IHMConsole();
				break;

				// if type is invalid we create a IHMConsole in order to have something for the user
				default :
					instance = new IHMConsole();
					printMessage(LanguageManager.get("IHMTypeNotValid"), ERROR);
			}
		}
		return instance;
	}

	/**
	 * call printMessage(message, style) with no style
	 * @param message message to print
	 */
	public static void printMessage(String message)
	{
		printMessage(message, "");
	}

	/**
	 * tell the instantiated IHM to print message with styles
	 * if instance is null print using default text output
	 * @param message the text to display
	 * @param styles  the styles to use for printed message
	 */
	public static void printMessage(String message, String... styles)
	{
		if (instance != null)
		{
			instance.printMsg(message, styles);
		}
		else
		{
			System.out.println(message);
		}
	}

	/**
	 * printing method overrided in son class, called by printMessage
	 * @param message the text to display
	 * @param style   the styles to use for printed message
	 */
	public abstract void printMsg(String message, String... style);
}
