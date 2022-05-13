function add(respone, saints, even){
    let table=document.getElementById("data");
    for(var i=0; i<respone.length; i++){
        let newRow=table.insertRow();
        if(i%2==0) {
			newRow.className="dark";
		}
        let cellTitle=newRow.insertCell();
        let cellValue=newRow.insertCell();
        cellTitle.innerHTML=saints[i];
        cellValue.innerHTML=respone[i];
    }
}