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

function dropdownLoginOpen() {
    document.getElementById('loginDropdown').classList.toggle('show')
}

// Close the all dropdown menu if the user clicks outside of it
window.onclick = function(event) {

    // Check that event is not from button click.
    // TODO: Zrobic to po ludzku.
    if (event.target != (document.getElementById("loginButton"))
        && !document.getElementsByClassName('dropdown-content')[0].contains(event.target)) {
        var dropdowns = document.getElementsByClassName("dropdown-content");
        var i
        for (i = 0; i < dropdowns.length; i++) {
            var openDropdown = dropdowns[i]
            if (openDropdown.classList.contains('show')) {
                openDropdown.classList.remove('show');
            }
        }
    }
}