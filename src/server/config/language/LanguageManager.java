package server.config.language;

public abstract class LanguageManager
{
	private static Language activeLanguage = Language.FRfr;
	private static Language fillLanguage = Language.ENus;

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

	public static void setFillLanguage(Language language)
	{
		if (language != null)
		{
			fillLanguage = language;
		}
	}

	public static void setActiveLanguage(Language language)
	{
		if (language != null)
		{
			activeLanguage = language;
		}
	}

	static Language getActiveLanguage()
	{
		return activeLanguage;
	}

	static Language getFillLanguage()
	{
		return fillLanguage;
	}
}
