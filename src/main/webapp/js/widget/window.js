function openwindowtocenter( url, winName, width, height) {

	xposition=0; yposition=0;
	if ((parseInt(navigator.appVersion) >= 4 ))
	{
	  //xposition = (screen.width - width) / 2;
	  xposition = (window.screen.availWidth - width) / 2;
	  yposition = (window.screen.availWidth - height) / 2;
	}
	yposition = 0;
	  theproperty= "width=" + width + "," 
	+ "height=" + height + "," 
	+ "location=0," 
	+ "menubar=0,"
	+ "resizable=1,"
	+ "scrollbars=yes,"
	+ "status=0," 
	+ "titlebar=0,"
	+ "toolbar=0,"
	+ "hotkeys=0,"
	+ "screenx=" + xposition + "," 
	+ "screeny=" + yposition + "," 
	+ "left=" + xposition + "," 
	+ "top=" + yposition; 
	window.open( url,winName,theproperty );
}