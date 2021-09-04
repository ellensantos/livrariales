function validarDataPesquisa(){
    var dtIni = document.getElementById("dataInicio").value;
    var dtFim = document.getElementById("dataFim").value;

    console.log(dtIni);
    console.log(dtFim);

    var partesdtIni = dtIni.split("/");
    var dataInicio = new Date(partesdtIni[2], partesdtIni[1] - 1, partesdtIni[0]);

    var partesdtFim = dtFim.split("/");
    var dataFim = new Date(partesdtFim[2], partesdtFim[1] - 1, partesdtFim[0]);

    if(dataInicio > dataFim) {
        alert("Data Inicio superior a Data Fim selecionada.");
        return false;
    }

    else if (dataFim > new Date()){
        alert("Data Final superior a data Atual.");
        return false;
    }

    return true;

}


function validaData(){
    let dataInicial = new Date($("input[name='dataInicio']").val());
    let dataFinal = new Date($("input[name='dataFim']").val());
    if (!dataInicial || !dataFinal) return false;
    if (dataInicial >= dataFinal) {
        alert("Data inicial não deve ser superior a data final!");
        console.log(false);
        return false;
    }else if(dataFinal > new Date()){
        alert("Data final superior a data atual!");
        return false;
    }
    else {
        return true
    }

}