$(document).ready(function() {
getJSON("http://localhost:8084/REST-With-JAX-RS-Person/api/persons/", Received, "GET");
});


function getJSON(url, func, type) {
    var xmlhttp = new XMLHttpRequest();
    var JSONObjects;

    xmlhttp.onreadystatechange = function ()
    {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200)
        {
            JSONObjects = JSON.parse(xmlhttp.responseText);
            console.log(JSONObjects);
            func(JSONObjects);
        }
    };

    xmlhttp.open(type, url, true);
    xmlhttp.send();
}

function Received(json)
{
    json.forEach(function(l) {
            $("#persons").append("<tr><td>" + l.fName + " " + l.lName + "</td><td>" + l.phone + "</td><td><a href='#' class='btn btn-danger btn-xs' data-personid='" + l.id + "'>delete</a></td></tr>");
        });
};