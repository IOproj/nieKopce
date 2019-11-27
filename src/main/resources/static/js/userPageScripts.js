
function calculaateActivity() {
    f = setInterval(function () {
        now = new Date();
        var nowTime = now.getTime();

        var distance = countDownDate - nowTime;

        var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
        var seconds = Math.floor((distance % (1000 * 60)) / 1000);

        document.getElementById("demo").innerHTML = "Czas do wylogowania:"
            + minutes + "m " + seconds + "s ";

        // If the count down is over, write some text
        if (distance < 0) {
            clearInterval(f);
            $("#logoutPopUp").dialog({
                title: "Uwaga!",
                width: 645,
                height: 300,
                modal: true,
            });
            $('#SignOutSubmit').click(function () {
                $(this).dialog('close');
            })
            document.getElementById("demo").innerHTML = "EXPIRED";
        }
    }, 1000);
}


function reactOnMouseClick() {
    $(document).mousedown(function () {
        now = new Date();
        countDownDate = new Date();
        countDownDate.setSeconds(now.getSeconds()+10);
        console.log(countDownDate.getMinutes())
    })  ;
}


function applicationButtonFunction(){

}