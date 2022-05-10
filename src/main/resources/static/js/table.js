function add(respone, saints){
    let table=document.getElementById("data");
    for(var i=0; i<respone.length; i++){
        let newRow=table.insertRow();
        let cellTitle=newRow.insertCell();
        let cellValue=newRow.insertCell();
        cellTitle.innerHTML=saints[i];
        cellValue.innerHTML=respone[i];
    }
}