async function makeNewUser(event){
    event.preventDefault();
    let firstname = document.getElementById("firstname-input").value;
    let lastname = document.getElementById("lastname-input").value;
    let email = document.getElementById("email-input").value;
    let zodiacsign = document.getElementById("zodiac-sign-input").value;
    let userInfo = {
       first_name: firstname,
        last_name: lastname,
        email: email,
        zodiac_sign: zodiacsign
    }
    console.log(userInfo);
    
    try {
        const raw_response = await fetch(`http://localhost:8080/Horoscope/createuser`,
          {
            method: "POST", //the http verb
            headers: { //headers
              "Content-Type": "application/json",
              "Access-Control-Allow-Origin": "*"
             },
             body: JSON.stringify(userInfo)
    
          });
          console.log(json_data);
          //check for a successful response
          if (!raw_response.ok) {
           // throw new Error(raw_response.status); //this is a js error class that we are throwing
          }
          console.log(json_data);
          const json_data = await raw_response.json();
          //save token into a sessionStorage variable
          // localStorage.setItem("tickets", JSON.stringify(json_data));
          // console.log(localStorage.getItem("tickets"))
          alert(`User #: ${json_data} has been added`)
          setTimeout( () => {
          window.location.replace("index.html")
          },1000)
      } catch (error) {
        //this catch block is for network errors
        window.location.replace("index.html")
        console.log(error);
    }
}