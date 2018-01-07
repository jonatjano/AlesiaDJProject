package server.ihm;

import server.config.language.LanguageManager;

public abstract class IHM
{
	public static final String BLACK_TEXT = "BLACK_TEXT";
	public static final String RED_TEXT = "RED_TEXT";
	public static final String GREEN_TEXT = "GREEN_TEXT";
	public static final String YELLOW_TEXT = "YELLOW_TEXT";
	public static final String BLUE_TEXT = "BLUE_TEXT";
	public static final String PURPLE_TEXT = "PURPLE_TEXT";
	public static final String CYAN_TEXT = "CYAN_TEXT";
	public static final String WHITE_TEXT = "WHITE_TEXT";

	public static final String BLACK_BACKGROUND = "BLACK_BACKGROUND";
	public static final String RED_BACKGROUND = "RED_BACKGROUND";
	public static final String GREEN_BACKGROUND = "GREEN_BACKGROUND";
	public static final String YELLOW_BACKGROUND = "YELLOW_BACKGROUND";
	public static final String BLUE_BACKGROUND = "BLUE_BACKGROUND";
	public static final String PURPLE_BACKGROUND = "PURPLE_BACKGROUND";
	public static final String CYAN_BACKGROUND = "CYAN_BACKGROUND";
	public static final String WHITE_BACKGROUND = "WHITE_BACKGROUND";

	public static final String ERROR = "ERROR";

	public static final String WINDOW = "WINDOW";
	public static final String GRAPHIC = "GRAPHIC";
	public static final String TERMINAL = "TERMINAL";
	public static final String CONSOLE = "CONSOLE";

	private static IHM instance = null;

	public IHM()
	{
		instance = this;
	}

	public static IHM getInstanceOrNull()
	{
		return instance;
	}

	public static IHM getInstance()
	{
		if (instance != null)
		{
			return instance;
		}
		return getInstance(CONSOLE);
	}

	public static IHM getInstance(String type)
	{
		if (instance == null)
		{
			type = type.toUpperCase();
			switch (type)
			{
				case WINDOW:
				case GRAPHIC:
					instance = new IHMConsole();
					printMessage("graphical interfaces not available yet, using console", ERROR);
				break;

				case TERMINAL:
				case CONSOLE:
					instance = new IHMConsole();
				break;

				default :
					instance = new IHMConsole();
					printMessage(LanguageManager.get("IHMTypeNotValid"), ERROR);
			}
		}
		return instance;
	}

	public static void printMessage(String message)
	{
		printMessage(message, new String[] {});
	}

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

	public abstract void printMsg(String message, String[] style);
}
