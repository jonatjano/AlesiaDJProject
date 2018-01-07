package server.config.language;

public enum Language
{
	FRfr(new FRfr()),
	ENus(new ENus());

	private AbstractLanguage language;

	private Language(AbstractLanguage language)
	{
		this.language = language;
	}

	public String get(String translationKey)
	{
		return language.get(translationKey);
	}

	public static Language getLanguage(String languageName)
	{
		return getLanguage(languageName, LanguageManager.getActiveLanguage());
	}

	public static Language getLanguage(String languageName, Language fallbackIfNotFound)
	{
		for (Language language : values())
		{
			if (language.name().equals(languageName))
			{
				return language;
			}
		}
		return fallbackIfNotFound;
	}
}
