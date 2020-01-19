function viewHistory(certificationID) {
    $.ajax({
        url: '/admin/ajax/getVisits',
        type: 'GET',
        dataType: 'json',
        data: {
            'certificationID': certificationID,
            'date': date,
            'comment': comment
        }
    }).done(function (data) {
        var visitAjaxtable = {};
        $('#visitsTable').empty();
        $.each(data, function (i, parameters) {
            var date = parameters.date;
            var comment = parameters.comment;
            visitAjaxtable = "<tr><td>" + certificationID + "</td><td>" + date + "</td><td>" + comment + "</td>" +
                "<td></tr>"
            $('#visitsTable').append(visitAjaxtable);
        })
    })
}

var deviceId;
var producer;
var yearOfProduction;
var warrantyTerminationDate;
var fuel;
var otherComments;

function showDetails(certificationID) {
    $.ajax({
        url: '/admin/ajax/getDetails',
        type: 'GET',
        dataType: 'json',
        data: {
            'certificationID': certificationID,
            'deviceId': deviceId,
            'producer': producer,
            'yearOfProduction': yearOfProduction,
            'warrantyTerminationDate': warrantyTerminationDate,
            'fuel':fuel,
            'otherComments':otherComments
        }
    }).done(function (data) {
        var visitAjaxtable = {};
        $('#heat').empty();
        $.each(data, function (i, parameters) {
            var date = parameters.producer;
            var yearOfProduction = parameters.yearOfProduction;
            var warrantyTerminationDate = parameters.warrantyTerminationDate;
            var fuel = parameters.fuel;
            var otherComments = parameters.otherComments;
            visitAjaxtable = "<tr><td>" + certificationID + "</td><td>" + date + "</td><td>" + yearOfProduction + "</td>" +
                "<td>" + warrantyTerminationDate + "</td><td>" + fuel + "</td><td>" + otherComments + "</td><td></tr>";
            $('#heat').append(visitAjaxtable);
        })
    })
}

function handleCertification(certificationID) {
    $("#planVisitPopUp").dialog({
        title: "Podaj datę kolejnej wizyty",
        width: 645,
        height: 300,
        modal: true,
        buttons: {
            Zatwierdz:
                function () {
                    var inputDate = $("#inputDateField").val();
                    $.ajax({
                        url: 'planVisit',
                        method: 'GET',
                        dataType: 'json',
                        data: {
                            'certificationID': certificationID,
                            'inputDate': inputDate
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

function addVisit(certificationID) {
    $("#inputCertificationID").val(certificationID);
    var finish ="false";
    $("#addVisitPopuUp").dialog({
        title: "Złóż raport z wizyty",
        width: 645,
        height: 300,
        modal: true,
        buttons: {
            Zatwierdz:
                function () {
                    var date = $("#inputVisitsDate").val();
                    var comment = $("#inputComment").val();
                    var ischecked = $("#inputIsFinished").is(":checked");
                    if(ischecked){
                        finish = $("#inputIsFinished").val();
                    }
                    $.ajax({
                        url: '/admin/ajax/addVisit',
                        method: 'GET',
                        dataType: 'json',
                        data: {
                            'certificationID': certificationID,
                            'date': date,
                            'comment': comment,
                            'finish': finish
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