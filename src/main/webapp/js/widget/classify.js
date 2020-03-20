
function show(obj)
{
	obj.style.display='block';
}

function hide(obj)
{
	obj.style.display='none';
}

function openwindowforpreviewquestion(url, winName, width, height) 
{
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

function openwindowforpreviewpaper( url, winName, width, height) 
{
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
	
function openCourseSelect()
{
window.open('./pub/selectSingleCourseTree.jsp','教育培训系统','width=800,height=600,location=0,toolbar=no,scrollbars=yes,titlebar=0,menubar=no,screenX=100,screenY=100')
}

function openTrainSelect()
{
window.open('onlineTraining.do?method=searchTrainForTestPaper&research=false','教育培训系统','width=800,location=0,height=600,titlebar=0,menubar=0,toolbar=no,scrollbars=yes,menubar=no,screenX=100,screenY=100')
}
function openPlanTrainSelect()
{
window.open('allMsgQueryTrainPlanAction.do?method=queryCheck','123456','width=800,height=600,menubar=0,toolbar=no,scrollbars=yes,menubar=no,screenX=100,screenY=100')
}

