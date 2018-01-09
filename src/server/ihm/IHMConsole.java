package server.ihm;

import server.config.language.LanguageManager;

/**
 * IHM class used to write and read using console
 * @author jonatjano
 */
public class IHMConsole extends IHM
{
	/**
	 * ANSI style to apply at the end of each messages to reset style
	 */
	static final String RESET = "\u001B[0m";

	/**
	 * ANSI style to apply when asked BLACK_TEXT style
	 */
	static final String BLACK_TEXT = "\u001B[30m";
	/**
	 * ANSI style to apply when asked RED_TEXT style
	 */
	static final String RED_TEXT = "\u001B[31m";
	/**
	 * ANSI style to apply when asked GREEN_TEXT style
	 */
	static final String GREEN_TEXT = "\u001B[32m";
	/**
	 * ANSI style to apply when asked YELLOW_TEXT style
	 */
	static final String YELLOW_TEXT = "\u001B[33m";
	/**
	 * ANSI style to apply when asked BLUE_TEXT style
	 */
	static final String BLUE_TEXT = "\u001B[34m";
	/**
	 * ANSI style to apply when asked PURPLE_TEXT style
	 */
	static final String PURPLE_TEXT = "\u001B[35m";
	/**
	 * ANSI style to apply when asked CYAN_TEXT style
	 */
	static final String CYAN_TEXT = "\u001B[36m";
	/**
	 * ANSI style to apply when asked WHITE_TEXT style
	 */
	static final String WHITE_TEXT = "\u001B[37m";

	/**
	 * ANSI style to apply when asked BLACK_BACKGROUND style
	 */
	static final String BLACK_BACKGROUND = "\u001B[40m";
	/**
	 * ANSI style to apply when asked RED_BACKGROUND style
	 */
	static final String RED_BACKGROUND = "\u001B[41m";
	/**
	 * ANSI style to apply when asked GREEN_BACKGROUND style
	 */
	static final String GREEN_BACKGROUND = "\u001B[42m";
	/**
	 * ANSI style to apply when asked YELLOW_BACKGROUND style
	 */
	static final String YELLOW_BACKGROUND = "\u001B[43m";
	/**
	 * ANSI style to apply when asked BLUE_BACKGROUND style
	 */
	static final String BLUE_BACKGROUND = "\u001B[44m";
	/**
	 * ANSI style to apply when asked PURPLE_BACKGROUND style
	 */
	static final String PURPLE_BACKGROUND = "\u001B[45m";
	/**
	 * ANSI style to apply when asked CYAN_BACKGROUND style
	 */
	static final String CYAN_BACKGROUND = "\u001B[46m";
	/**
	 * ANSI style to apply when asked WHITE_BACKGROUND style
	 */
	static final String WHITE_BACKGROUND = "\u001B[47m";

	/**
	 * print message on console
	 * styles are ANSI and should work only on Unix systems
	 *
	 * @param message message to print
	 * @param styles  style to add to message
	 */
	@Override
	public void printMsg(String message, String... styles)
	{
		String stylePrefix = "";
		String messagePrefix = "";

		for (String style : styles)
		{
			switch (style)
			{
				// text colors
				case IHM.BLACK_TEXT : stylePrefix += BLACK_TEXT; break;
				case IHM.RED_TEXT : stylePrefix += RED_TEXT; break;
				case IHM.GREEN_TEXT : stylePrefix += GREEN_TEXT; break;
				case IHM.YELLOW_TEXT : stylePrefix += YELLOW_TEXT; break;
				case IHM.BLUE_TEXT : stylePrefix += BLUE_TEXT; break;
				case IHM.PURPLE_TEXT : stylePrefix += PURPLE_TEXT; break;
				case IHM.CYAN_TEXT : stylePrefix += CYAN_TEXT; break;
				case IHM.WHITE_TEXT : stylePrefix += WHITE_TEXT; break;

				// background color
				case IHM.BLACK_BACKGROUND : stylePrefix += BLACK_BACKGROUND; break;
				case IHM.RED_BACKGROUND : stylePrefix += RED_BACKGROUND; break;
				case IHM.GREEN_BACKGROUND : stylePrefix += GREEN_BACKGROUND; break;
				case IHM.YELLOW_BACKGROUND : stylePrefix += YELLOW_BACKGROUND; break;
				case IHM.BLUE_BACKGROUND : stylePrefix += BLUE_BACKGROUND; break;
				case IHM.PURPLE_BACKGROUND : stylePrefix += PURPLE_BACKGROUND; break;
				case IHM.CYAN_BACKGROUND : stylePrefix += CYAN_BACKGROUND; break;
				case IHM.WHITE_BACKGROUND : stylePrefix += WHITE_BACKGROUND; break;

				// special styles
				case IHM.ERROR :
					stylePrefix = RED_TEXT + BLACK_BACKGROUND;
					messagePrefix = LanguageManager.get("ERROR");
				break;
				case IHM.INFO :
					stylePrefix = WHITE_TEXT + BLUE_BACKGROUND;
					messagePrefix = LanguageManager.get("INFO");
				break;
			}
		}
		// add reset at the end the next message will keep this style
		// (unless it has a defined background and text color)
		System.out.println(stylePrefix + messagePrefix + message + RESET);
	}
}
