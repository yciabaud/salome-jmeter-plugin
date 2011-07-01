/*
 * Created on 10 juin 2005
 * SalomeTMF is a Test Managment Framework
 * Copyright (C) 2005 France Telecom R&D
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 *
 * Contact: mikael.marche@rd.francetelecom.com
 */
package salomeTMF_plug.JMeterIntegration.languages;

import java.util.Locale;
import java.util.ResourceBundle;

import org.objectweb.salome_tmf.api.Api;

import salomeTMF_plug.JMeterIntegration.languages.Language;

public class Language {
	/** Static variable */
	private static Language language = null;

	/** Local variable */
	Locale currentLocale = null;

	/** For the resource file */
	ResourceBundle i18n = null;
	ResourceBundle i18nErr = null;
	ResourceBundle i18nLog = null;

	/** Get the instance of the language */
	public static Language getInstance() {
		if (language == null) {
			language = new Language();
		}
		return language;
	}

	/** Constructor of the language */
	private Language() {
		Locale locale ;
		/*ResourceBundle localeProperties = ResourceBundle.getBundle(
				"salomeTMF_plug/simpleJunit/languages/languageConf");
		*/
		//  \warning : if  locale is empty then  locale du i18n.properties
		//             if  locale doesn't exist then locale = JVM locale
		//locale = new Locale(localeProperties.getString("locale"));
		locale = new Locale(Api.getUsedLocale());
		setLocale(locale);
	}

	/** Set the local values */
	public void setLocale(Locale locale) {
		currentLocale = locale;
		i18n = ResourceBundle.getBundle(
				"salomeTMF_plug/simpleJunit/languages/i18n", currentLocale);
		//i18nErr = ResourceBundle.getBundle(
		//		"salomeTMF_plug/simpleJunit/languages/i18nErr", currentLocale);
		//i18nLog = ResourceBundle.getBundle(
		//		"salomeTMF_plug/simpleJunit/languages/i18nLog", currentLocale);
	}



	/* \brief Return the text corresponding to the key 
	 * \param 
	 */
	public String getText(String key) {
		return i18n.getString(key);
	}
	
	/*
	 * \brief Return the log text corresponding to the key 
	 * \param key : a log key belong i18nLog
	 */
	public String getTextLog(String key){
		return i18nLog.getString(key);
	}
	
	/*
	 * \brief Return the error text corresponding to the key 
	 * \param key : an error key belong i18nErr
	 */
	public String getTextErr(String key){
		return i18nErr.getString(key);
	}
}
