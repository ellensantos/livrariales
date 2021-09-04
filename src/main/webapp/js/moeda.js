function mascaraMoeda(obj) {
    var elemento = document.getElementById(obj.id);
    var valor = elemento.value;

    valor = valor + '';
    valor = parseInt(valor.replace(/[\D]+/g, ''));
    valor = valor + '';
    valor = valor.replace(/([0-9]{2})$/g, ",$1");

    if (valor.length > 6) {
        valor = valor.replace(/([0-9]{3}),([0-9]{2}$)/g, ".$1,$2");
    }

    elemento.value = valor;
    if(valor == 'NaN') elemento.value = '';
}



/*
function soma(){
    var soma = 0;
    var ipts = document.querySelectorAll('input[onblur="soma()"]');
    for(var x=-0; x<ipts.length; x++){
        var valorItem = parseFloat(ipts[x].value);
        !isNaN(valorItem) ? soma += parseFloat(valorItem) : null;
    }
    console.log(soma);
    document.querySelector('#valorPago').value = soma.toFixed(2);
}*/

