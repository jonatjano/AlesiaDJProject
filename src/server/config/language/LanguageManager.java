package server.config.language;

/**
 * abstract class used to manage languages and get translations
 * @author jonatjano
 */
public abstract class LanguageManager
{
	/**
	 * the first {@link Language} to ask translations to
	 */
	private static Language activeLanguage = Language.FRfr;
	/**
	 * {@link Language} to ask translation to if not found for activeLanguage
	 */
	private static Language fillLanguage = Language.ENus;

	/**
	 * get the translation linked to the translationKey for the activeLanguage or fillLanguage if absent of activeLanguage
	 * @param  translationKey key of the text to get
	 * @return a String containing the asked text in the asked language
	 */
	public static String get(String translationKey)
	{
		String translation = activeLanguage.get(translationKey);
		if (translation == null)
		{
			translation = fillLanguage.get(translationKey);
			if (translation == null)
			{
				translation = "NoTranslationFound " + get("PleaseContactDevs") + " : " + translationKey + " for " + activeLanguage.name() + "-" + fillLanguage.name();
			}
			else
			{
				translation = "NoTranslationForThisLanguage " + translationKey + " for " + activeLanguage.name() + " using " + fillLanguage.name() + " : " + translation;
			}
		}
		return translation;
	}

	/**
	 * used to set fillLanguage do nothing if param is null
	 * @param language the language to set as fillLanguage
	 */
	public static void setFillLanguage(Language language)
	{
		if (language != null)
		{
			fillLanguage = language;
		}
	}

	/**
	 * used to set activeLanguage do nothing if param is null
	 * @param language the language to set as activeLanguage
	 */
	public static void setActiveLanguage(Language language)
	{
		if (language != null)
		{
			activeLanguage = language;
		}
	}

	/**
	 * get the current activeLanguage
	 * @return the current activeLanguage
	 */
	static Language getActiveLanguage()
	{
		return activeLanguage;
	}

	/**
	 * get the current fillLanguage
	 * @return the current fillLanguage
	 */
	static Language getFillLanguage()
	{
		return fillLanguage;
	}
}
