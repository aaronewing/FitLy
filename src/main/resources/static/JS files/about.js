let earray = ["ewingae@gmail.com","ewingae@live.com"]; //my main email, not allowed to be used in the form


function closeForm(){
    let form = document.getElementById('formcontain')
    let name = document.getElementById('fname').value;
    let email = document.getElementById('email').value;
    console.log(email);
    let check = checkArray(email);

    if (check == true){
        document.getElementById('complete').innerHTML = `Thank you, ${name}. I will respond to your email (${email}) as soon as possible!`;
        document.getElementById('complete').style.display = "block";
        document.getElementById('alreadysent').style.display = "none";
    }

    if(check == false){
        document.getElementById('complete').style.display = "none";
        document.getElementById('alreadysent').style.display = "block";
    }
}


function checkArray(email){
    let flag = false;
    for(let i = 0; i < earray.length; i++){
        if (email == earray[i]){
            console.log("error");
            flag = true;
            return false;
        }
        else{
            flag = false;
        }
    }

    if(flag == false){
        earray.push(email);
        return true
    }
}