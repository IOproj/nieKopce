function loginBtnFunction(){
    alert("Przycisk do logowania");
}

function registerBtnFunction(){
    alert("Przycisk do rejestracji");
}

function gituwaBtnFunction(){

    $.ajax({
        url: 'main/test',
        type:'GET',
        error: function () {
            console.log("ZLE")
        }
    })
}