function showDate() { 

	var now = new Date(); 
	var currDate = now.getDate();
	
	var mNames = new Array("January", "February", "March", 
		"April", "May", "June", "July", "August", "September", 
		"October", "November", "December");	
	
	var sup = "";
	if (currDate == 1 || currDate == 21 || currDate ==31) {
		sup = "st";
	} else if (currDate == 2 || currDate == 22) {
		sup = "nd";
	} else if (currDate == 3 || currDate == 23) {
		sup = "rd";
	} else {
		sup = "th";
	}

	var currMonth = now.getMonth();
	var currYear = now.getFullYear();

	var then = mNames[currMonth] + ", " + currDate + "<SUP>" + sup + "</SUP> " + currYear;
	then += " at "+now.getHours()+":"+now.getMinutes()+":"+now.getSeconds(); 

	return then;
} 

function showDate2() {
	return "a"
}