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
                    $.ajax({
                        url: '/admin/ajax/addVisit',
                        method: 'GET',
                        dataType: 'json',
                        data: {
                            'certificationID': certificationID,
                            'date': date,
                            'comment': comment
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