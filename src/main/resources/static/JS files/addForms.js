function addError(){
    if(window.location.href.indexOf("fail") != -1){
        console.log("im in here");
        document.getElementById("existing").style.display= "block";
    }
}
