function calculaateActivity() {
    f = setInterval(function () {
        now = new Date();
        var nowTime = now.getTime();
        var distance = countDownDate - nowTime;

        var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
        var seconds = Math.floor((distance % (1000 * 60)) / 1000);

        document.getElementById("demo").innerHTML = "Czas do wylogowania:"
            + minutes + "m " + seconds + "s ";

        if (distance < 0) {
            clearInterval(f);
            $("#logoutPopUp").dialog({
                title: "Uwaga!",
                width: 645,
                height: 300,
                modal: true
            });
            $('#SignOutSubmit').click(function () {
                $(this).dialog('close');
            });
            document.getElementById("demo").innerHTML = "EXPIRED";
        }
    }, 1000);
}

function reactOnMouseClick() {
    $(document).mousedown(function () {
        now = new Date();
        countDownDate = new Date();
        countDownDate.setMinutes(now.getMinutes()+5);
        console.log(countDownDate.getMinutes())
    })  ;
}

function sendApplication(){
    $.ajax({
        url: 'sendApplication',
        type:'GET',
        error: function () {
            alert("Błąd wysyłania zgłoszenia - czy aplikowałeś już wcześniej?");
        },
        success: function () {
            alert("Zgłoszenie zostało wysłane");
        }
    })
}


function applicationButtonFunction() {
    $("#describeHeatingDevicePopUp").dialog({
        title: "Podaj parametry twojego urządzenia grzewczego",
        width: 645,
        height: 300,
        modal: true,
        buttons: {
            Zatwierdz:
                function () {
                    var producer = $("#producer").val();
                    var yearOfProduction = $("#yearOfProduction").val();
                    var warrantyTerminationDate = $("#warrantyTerminationDate").val();
                    var fuel = $("#fuel").val();
                    var otherComments = $("#otherComments").val();
                    $.ajax({
                        url: 'sendApplication2',
                        method: 'GET',
                        dataType: 'json',
                        data: {
                            'producer': producer,
                            'date': yearOfProduction,
                            'warranty': warrantyTerminationDate,
                            'fuel':fuel,
                            'otherComments':otherComments
                        },
                        error: function () {
                            console.log('błąd');
                        }
                        ,
                        success: function () {
                            console.log('OK');
                        }
                    });
                    $(this).dialog('close');
                }
        }
    })
}

//TODO Rozdzielić to na więcej plików JS