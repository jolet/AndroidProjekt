package com.tvz.hr.androidprojekt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

public class MainActivity extends Activity {

	public static final String googleQuerry = "http://www.google.com/search?q=";
	public static final String wolframQuerry = "http://m.wolframalpha.com/input/?i=";
	public static final String wolframDevQuerry = "http://api.wolframalpha.com/v2/query?input=";
	public static final String wolframDevID = "&appid=8X3UE9-L8YJ6QPQ9L";

	public static String link;
	
	EditText input;
	TextView textView;
	Button buttonGoogle;
	Button buttonWolfram;
	Button buttonWolframDevAPI;
	Intent intent;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		input = (EditText) findViewById(R.id.input);
		textView = (TextView) findViewById(R.id.header);
		intent = new Intent(getApplicationContext(), BrowserActivity.class);

		buttonGoogle = (Button) findViewById(R.id.buttonGoogle);
		buttonGoogle.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				link = googleQuerry + fixInputText();
				startActivity(intent);
			}
		});

		buttonWolfram = (Button) findViewById(R.id.buttonWolfram);
		buttonWolfram.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				
				String fixAd = "&x=0&y=0&js=off"; // fix za reklamu od wolframa..not working

				link = wolframQuerry + fixInputText() + fixAd; //ne radi :/
//				 Toast.makeText(getApplicationContext(), link,
//				 Toast.LENGTH_SHORT).show();
				startActivity(intent);

			}

		});
		buttonWolframDevAPI = (Button) findViewById(R.id.devAPI);
		buttonWolframDevAPI.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				
				link = wolframDevQuerry + fixInputText() + wolframDevID;
//				 Toast.makeText(getApplicationContext(), link,
//				 Toast.LENGTH_SHORT).show();
				startActivity(intent);

			}

		});
		
		
		
		NumberPicker picker = (NumberPicker)findViewById(R.id.numberPicker1);
		picker.setMinValue(0);
		picker.setMaxValue(9);
//		picker.setFocusable(true);
//		picker.setFocusableInTouchMode(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public String fixInputText(){
		// %2B <---> +
		// - <---> -
		// * <---> *
		// %2F <---> /
		return input.getText().toString().replace("+", "%2B").replace("/", "%2F").replace(" ", "%20");
	}

	/* treba nam workaround/bypass za ovu stranicu //-> ne treba, imamo wolfram alpha dev API
	 * http://api.wolframalpha.com/v2/query?input=pi&appid=8X3UE9-L8YJ6QPQ9L ... XML file...styling?
	 * http://products.wolframalpha.com/api/documentation.html

<?xml version="1.0"  encoding="utf-8" ?>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.1//EN" "http://www.openmobilealliance.org/tech/DTD/xhtml-mobile11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
        <title>Wolfram|Alpha Mobile&mdash;Computational Knowledge Engine</title>
        <meta name="description" content="Wolfram|Alpha gives you access to the world's facts, without searching. See what everyone is talking about." />

        <link rel="search" type="application/opensearchdescription+xml" href="http://www.wolframalpha.com/searchDescription.xml" title="Wolfram|Alpha"/>

        <meta name="viewport" content="width=360, initial-scale=0.9, maximum-scale=1.0" />
        <link rel="Shortcut Icon" href="//www.wolframcdn.com/alphaFav.ico" />
        <link rel="icon" href="//www.wolframcdn.com/alphaFav.png" type="image/png" />
        <link rel="apple-touch-icon" href="http://www.wolframcdn.com/wa-iphoneicon.png"/>
        <script src="/input/javascript/mobile.compatibility.js"></script>

	<link href="/css/splash.css" rel="stylesheet" />
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
	<script type="text/javascript" src="/javascript/jq.clicktrack.js"></script>
</head>
<body>

<div id="splashAdContain" class="android">

  <img id="logo" src="/images/logo-big.jpeg" />

  <div id="splashAdBg">
    <div id="splashAd">
      <div class="ad-image">&nbsp;</div>
      <div class="ad-txt"><p>Optimized mobile <br /> experience for <br/>Android devices</p></div>
      <div id="amazonBtn" class="marketBtn ad-bttn">
          <a class="track-me" href="http://www.amazon.com/Wolfram-Alpha-LLC/dp/B004J1DBJI">
            <img src="/images/amazon.png">
            <div class="textPos">
              <span class="downloadNow">Download now</span> on Amazon Appstore
            </div>
          </a>
      </div>
      <div id="googleplayBtn" class="marketBtn ad-bttn">
          <a class="track-me" href="market://details?id=com.wolfram.android.alpha">
            <img src="/images/google-play.png">
            <div class="textPos">
              <span class="downloadNow">Download now</span> on Google Play
            </div>
          </a>
      </div>
    </div>
  </div>

  <div id="splashAdContinue">
    <a href='/input/?i=4+5&amp;x=0&amp;y=0&amp;js=off'>or continue to <b>wolframalpha.com &raquo;</b></a>
    <span id="splashad-cookie">(You must have cookies enabled)</span>
  </div>

</div>

<script>
function getExpireDate(offset){
  var date;
  if (typeof offset == 'number') {
    date = new Date();
    date.setTime(date.getTime() + (offset * 24 * 60 * 60 * 1000));
  } else {
    date = offset;
  }
  return date;
}
function setSplashAdCookies(){
  var ckstring = "";
  ckstring += "WolframAlphaSplashOff=true; ";
  ckstring += "expires="+getExpireDate(1).toUTCString()+"; ";
  ckstring += "path=/; ";
  document.cookie = ckstring;
}
setSplashAdCookies();
$("#splashAd .ad-bttn a").live("click", function(e){
  $(this).clicktrack({
    "type":"mobilead",
    "text":"Download now from the App Store",
    "device":"Android"
  }, "m.click.txt");
});
$(document).ready(function(){
  var link = $("#splashAdContinue a").slice(0,1);
  if ($.trim(link.attr("href")) == "") link.attr("href","/");
  $.get("ads.txt", {
    "type":"splashad",
    "for":"android",
    "src":"/splash-android.html",
    "request":$("#splashAdContinue a").get(0).href
  });
});

if (!navigator.cookieEnabled) $("span#splashad-cookie").show();
</script>

</body>
</html>
 */
}
