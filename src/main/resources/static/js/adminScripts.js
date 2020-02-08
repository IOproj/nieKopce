var now = new Date();
var countDownDate = new Date();
countDownDate.setMinutes(countDownDate.getMinutes() + 5);
reactOnMouseClick();


function closeModal() {
    modal = document.getElementById("planVisitPopUp");
    document.getElementById("planNextVisitButton").value=-1;
    modal.style.display = "none";
}

function openModal(certificationID) {
    modal = document.getElementById("planVisitPopUp");
    modal.style.display = "block";
    document.getElementById("planNextVisitButton").value=certificationID;
}


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
        var tableinfo = "<tr>" + "<th>ID</th>" + "<th>Data wizyty</th>" + " <th>Komentarz</th>" + " <th> </th>" + "</tr>";
        $('#visitsTable').append(tableinfo);
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
            'fuel': fuel,
            'otherComments': otherComments
        }
    }).done(function (data) {
        var detailsTable = {};
        $('#heat').empty();
        var tableinfo = "<tr>" + "<th>ID</th>" + "<th>Producent</th>" + " <th>Rok prod.</th>" + " <th>Data gwarancji </th>" + " <th>Paliwo </th>" + " <th>Inne uwagi </th>" + " <th> </th>" + "</tr>"
        $('#heat').append(tableinfo);
        $.each(data, function (i, parameters) {
            var date = parameters.producer;
            var yearOfProduction = parameters.yearOfProduction;
            var warrantyTerminationDate = parameters.warrantyTerminationDate;
            var fuel = parameters.fuel;
            var otherComments = parameters.otherComments;
            detailsTable = "<tr><td>" + certificationID + "</td><td>" + date + "</td><td>" + yearOfProduction + "</td>" +
                "<td>" + warrantyTerminationDate + "</td><td>" + fuel + "</td><td>" + otherComments + "</td><td></tr>";
            $('#heat').append(detailsTable);
        })
    })
}

function handleCertification() {
    var certificationID=document.getElementById("planNextVisitButton").value;
                    var inputDate = $("#inputDateField").val();
                    $.ajax({
                        url: 'planVisit',
                        method: 'GET',
                        dataType: 'json',
                        data: {
                            'certificationID': certificationID,
                            'inputDate': inputDate
                        }
                    });
                    closeModal();
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
                        }
                    });
                    $(this).dialog('close');
                }
        }
    })
}

var nextVisitDate;
var isFinished;
var certificationID;
var date;
var comment;

$(document).ready(function () {
    $('#getApplicationButton').click(function () {
        $.ajax({
            url: '/admin/ajax/testlist',
            type: 'GET',
            dataType: "json",
            data: {
                'nextVisitDate': nextVisitDate,
                'isFinished': isFinished,
                'certificationID': certificationID
            }
        }).done(function (data) {
            var table = {};
            var tableinfo = {};
            $('#certificationsTable').empty();
            tableinfo = "<tr>" + "<th>ID</th>" + "<th>Następna wizyta</th>" + " <th>Status</th>" + " <th> </th>" + " <th> </th>" + " <th> </th>" + " <th> </th>" + "</tr>"
            $('#certificationsTable').append(tableinfo);
            $.each(data, function (i, parameters) {
                var nextVisitDate = parameters.nextVisitDate;
                var isFinished = parameters.isFinished;
                if (!isFinished) {
                    isFinished = "Nie zakończono"
                } else {
                    isFinished = "Zakończono"
                }
                var certificationID = parameters.certificationID;
                table = "<tr>" +
                    "<td class=\"CustomTH\">" + certificationID + "</td>" +
                    "<td class=\"CustomTH\">" + nextVisitDate + "</td>" +
                    "<td class=\"CustomTH\">" + isFinished + "</td>" +
                    "<td><button id='viewVisitsHistory' class='smallButton' onclick='showDetails(" + certificationID + ")'>Szczegóły</button>"
                if (isFinished === "Nie zakończono") {
                    table +=
                        "<td><button id='handleVisitButton' class='smallButton' onclick='openModal(" + certificationID + ")'>Obsłuż</button></td>"
                } else {
                    table += "<td></td>"
                }
                table += "<td><button id='viewVisitsHistory' class='smallButton' onclick='viewHistory(" + certificationID + ")'>Historia</button>" +
                    "<td><button id='addVisitRaport' class='smallButton' onclick='addVisit(" + certificationID + ")'>Dodaj wizytę</button>" +
                    "</tr>";
                $('#certificationsTable').append(table);
            })
        })
    })
});
