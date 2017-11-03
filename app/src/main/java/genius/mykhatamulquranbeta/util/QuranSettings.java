package genius.mykhatamulquranbeta.util;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import genius.mykhatamulquranbeta.data.ApplicationConstants;


public class QuranSettings {
	
	private static QuranSettings instance = new QuranSettings();
	private int lastPage = 0;

	private QuranSettings() {
		
	}



    public Integer getLastPage() {
		return lastPage;
	}
	public static QuranSettings getInstance(){
		return instance;
	}

	public void setLastPage(Integer lastPage) {
		this.lastPage = (lastPage == null)? ApplicationConstants.NO_PAGE_SAVED : lastPage;
	}


	public static void load(SharedPreferences preferences) {

		instance.lastPage = preferences.getInt(ApplicationConstants.PREF_LAST_PAGE, ApplicationConstants.NO_PAGE_SAVED);
	}
	
	public static void save(SharedPreferences preferences) {

		Editor editor = preferences.edit();
		editor.putInt(ApplicationConstants.PREF_LAST_PAGE, instance.lastPage);
		editor.commit();
	}
}
