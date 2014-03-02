/**
 * 
 */
package org.frozend.teaMarket;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.content.res.AssetManager;
import android.util.Xml;

/**
 * @author frozenD
 *
 */
public class DataBase {

	private HashMap<String, List<Tea> > _data;
	public void init(InputStream is){
		XmlPullParser pullParaser = Xml.newPullParser();
		try {
			pullParaser.setInput(is, "utf-8");
			int eventType = pullParaser.getEventType();
			while(eventType != XmlPullParser.END_DOCUMENT){
				String name = pullParaser.getName();
				String tea_name = "";
				String tea_type = "";
				String tea_inner_type = "";
				for(int i=0;i<3;++i){
					if("")
				}
				pullParaser.getAttributeName(0);
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
