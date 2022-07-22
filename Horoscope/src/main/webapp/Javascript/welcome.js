


  

  let button= document.getElementById("button");


  button.addEventListener("click",() =>{

  //do Ajax!
  // 1- Create an xmlhttp request

  let xhttp= new XMLHttpRequest();


  //1.5- get the value from the input form
  // with the id("field")

  let inputValue= document.getElementById("field").value


  

xhttp.onreadystatechange = function(){


    if (this.readyState==4 && this.status==200){
        let data=JSON.parse(xhttp.responseText);
        console.log(data);
        renderHTML(data);

    }

};

xhttp.open("GET",`http://sandipbgt.com/theastrologer/api/horoscope/pisces/today${inputValue}`);


xhttp.send();

});


function renderHTML(data){

}