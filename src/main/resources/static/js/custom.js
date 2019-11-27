function loginBtnFunction(){
    console.log("AAAAA")
}

function registerBtnFunction(){
    console.log("AAAAA")
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