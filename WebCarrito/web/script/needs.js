// JavaScript Document
function lTrim(sStr){
   while (sStr.charAt(0) == " ")
	sStr = sStr.substr(1, sStr.length - 1);
   return sStr;
}
 
function rTrim(sStr){
   while (sStr.charAt(sStr.length - 1) == " ")
	sStr = sStr.substr(0, sStr.length - 1);
   return sStr;
}

function allTrim(sStr){
   return rTrim(lTrim(sStr));
}