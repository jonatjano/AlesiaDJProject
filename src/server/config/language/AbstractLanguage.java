package server.config.language;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractLanguage
{
	private Map<String, String> translations;

	AbstractLanguage()
	{
		translations = createTranslationMap();
	}

	public String get(String translationKey)
	{
		return translations.get(translationKey);
	}

	abstract Map<String, String> createTranslationMap();
}
