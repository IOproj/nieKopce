var now=new Date();
var countDownDate = new Date();
countDownDate.setMinutes(countDownDate.getMinutes()+5);
reactOnMouseClick();


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

var modal;
window.onload = function (){};

function closeModal() {
    modal = document.getElementById("describeHeatingDevicePopUp");
    modal.style.display="none";
}


function openModal() {
    modal = document.getElementById("describeHeatingDevicePopUp");
    modal.style.display="block";
}


function validateInput(){
    var regex= RegExp('^[0-9][0-9][0-9][0-9](\-)[0-9]{2}(\-)[0-9]{2}$');
    var output= [false,false,false,false,false];
    var producer = document.getElementById("producer");
    var year = document.getElementById("yearOfProduction");
    var warranty = document.getElementById("warrantyTerminationDate");
    var fuel = document.getElementById("fuel");
    var producerEmpty = document.getElementById("producerError");
    var yearEmpty = document.getElementById("yearError");
    var yearNotMatches = document.getElementById("yearNotMatches");
    var warrantyEmpty = document.getElementById("warrantyError");
    var fuelEmpty = document.getElementById("fuelError");

    if(producer.value===""){
        producerEmpty.style.display="inline";
       output[0]=false;
    }
    else {
        producerEmpty.style.display="none";
        output[0]=true;
    }
    if(warranty.value===""){
        warrantyEmpty.style.display="inline";
        output[1]=false;
    }
    else {
        warrantyEmpty.style.display="none";
        output[1]=true;
    }
    if(fuel.value===""){
        fuelEmpty.style.display="inline";
        output[2]=false;
    }
    else {
        fuelEmpty.style.display="none";
        output[2]=true;
    }
    if(year.value===""){
        yearEmpty.style.display="inline";
        output[3]=false;
    }
    else {
        yearEmpty.style.display="none";
        output[3]=true;
    }
    if(year.value!=="" &&!year.value.match(regex)){
        yearNotMatches.style.display="inline";
        output[3]=false;
    }
    else {
        yearNotMatches.style.display="none";
        output[4]=true;
    }
    return output[0] && output[1] && output[2] && output[3]&& output[4];
}

function sendApplication(){
    var producer = $("#producer").val();
    var yearOfProduction = $("#yearOfProduction").val();
    var warrantyTerminationDate = $("#warrantyTerminationDate").val();
    var fuel = $("#fuel").val();
    var otherComments = $("#otherComments").val();

    if(!validateInput()){
        return;
    }
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
           alert("Błąd podczas próby wysłania zgłoszenia")
        }
    });
    closeModal();
}

//TODO Rozdzielić to na więcej plików JS