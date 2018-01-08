package server.config.language;

/**
 * enum containning all available languages
 * it is a way to have constants refering to languages
 * @author jonatjano
 */
public enum Language
{
	/**
	 * french language from France
	 */
	FRfr(new FRfr()),
	/**
	 * english language from the USA
	 */
	ENus(new ENus());

	/**
	 * object with the translations for this language
	 */
	private AbstractLanguage language;

	/**
	 * constructor
	 * @param language the object containing the translations for the object
	 */
	private Language(AbstractLanguage language)
	{
		this.language = language;
	}

	/**
	 * method used to get the translation for the given key
	 * @param translationKey the key of the translation to get
	 * @return the translation linked to the key
	 */
	public String get(String translationKey)
	{
		return language.get(translationKey);
	}

	/**
	 * get a language from it's name
	 * @param  languageName the name of the language to get
	 * @return the asked language or the current active language if not found
	 */
	public static Language getLanguage(String languageName)
	{
		return getLanguage(languageName, LanguageManager.getActiveLanguage());
	}

	/**
	 * get a language from it's name or the fallback language if not found
	 * not case sensitive
	 * @param  languageName       the name of the language to get
	 * @param  fallbackIfNotFound the language return if the asked one is not found
	 * @return the asked language or the fallback if not found
	 */
	public static Language getLanguage(String languageName, Language fallbackIfNotFound)
	{
		for (Language language : values())
		{
			// using uppercase to make recognision easier
			if (language.name().toUpperCase().equals(languageName.toUpperCase()))
			{
				return language;
			}
		}
		return fallbackIfNotFound;
	}
}
