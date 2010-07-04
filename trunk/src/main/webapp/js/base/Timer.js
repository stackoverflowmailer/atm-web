//check session validity every 5 seconds

var checkSessionExpiry = function() {
    var cookie = Ext.util.Cookies.get('atm-web');
    if (cookie) {
        //console.debug("User logged in");
    } else {
        //console.debug("Cookie not present in the session...");
    }

}
var task = {
    run: checkSessionExpiry,
    interval: 4000 //4 seconds
}
var runner = new Ext.util.TaskRunner();
runner.start(task);