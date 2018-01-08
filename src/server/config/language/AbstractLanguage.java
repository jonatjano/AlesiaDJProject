package server.config.language;

import java.util.HashMap;
import java.util.Map;

/**
 * abstract class to define Language classes used for translations
 * @author jonatjano
 */
abstract class AbstractLanguage
{
	/**
	 * map containning the translations for language
	 */
	private Map<String, String> translations;

	/**
	 * call createTranslationMap in order to fill map
	 */
	AbstractLanguage()
	{
		translations = createTranslationMap();
	}

	/**
	 * method used to get translation for the translationKey
	 * @param  translationKey key of the translation to get
	 * @return the translation corresponding to the key
	 */
	public String get(String translationKey)
	{
		return translations.get(translationKey);
	}

	/**
	 * method used to define translation pairs : key -> value
	 * @return the map containning the translations linked to the keys
	 */
	abstract Map<String, String> createTranslationMap();
}
