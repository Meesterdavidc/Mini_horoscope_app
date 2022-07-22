let submitButton = document.getElementById("loginButton");



submitButton.addEventListener("click", (event) => {
event.preventDefault();
    
    

    let xhttp = new XMLHttpRequest();

    // //next were going to get the values from the form

    let email = document.getElementById("email-sign-in").value;
    let password = document.getElementById("password-sign-in").value;

    let loginInfo = {
        email: email,
        last_name: password
    }

    console.log(loginInfo);

   

        xhttp.onreadystatechange = function(){
        
            //200 status code is a OK response
       //which means that everything was processed correctly
       if(this.readyState == 4 && xhttp.status == 200){

        console.log(xhttp.responseText);
        let data = JSON.parse(xhttp.responseText);
         console.log(data);

         //global caching funciton

         localStorage.setItem('currentUser', JSON.stringify(data));

        window.location.replace("welcome.html")
        } else if(this.readyState == 4 && xhttp.status ===204) {
            console.log(xhttp.responseText)
            console.log()
                alert("Failed. Status Code: " + xhttp.status)
              
        }

    };

    //STEP 3: OPEN THE REQUEST
    xhttp.open("POST",`http://localhost:8080/Horoscope/userlogin`)

    xhttp.setRequestHeader("Content-Type", "application/json");
    xhttp.setRequestHeader("Access-Control-Allow-Origin", "*");
    
     console.log(xhttp);
    //STEP 4- send the request
    xhttp.send(JSON.stringify(loginInfo));

});