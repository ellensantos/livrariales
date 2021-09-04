function validarValorMinimoCartao(obj){

    var valorCupomDesconto = document.getElementById("valorCupomDesconto").value;

    var valor = obj.value;
    var novoValor = valor.replace(/[^0-9,]*/g, '').replace(',', '.');

    if(document.getElementById("qtdeCupomSelecionado").value <= 0 || valorCupomDesconto === 0){
        if(novoValor < 10.00){
            //alert(novoValor + " é menor que 10!");
            alert("Valor a ser pago no cartao nao pode ser inferior a 10 nem inferior ao valor pendente de pagamento.");
            obj.value = '';
            obj.focus();
        }
    }
}

function validarValorMaximoCartao(obj){

    var valorPendente = document.getElementById("valorPendente").value;
    var valor = obj.value;

    if(valor == ''){
        valor = 0;
    }

    else{
        var novoValor = valor.replace(/[^0-9,]*/g, '').replace(',', '.');
        novoValor = parseFloat(novoValor).toFixed(2);
    }

    if(parseFloat(novoValor) > parseFloat(valorPendente)){
        //alert(novoValor + " é menor que 10!");
        alert("Valor maior do que o pendente para pagamento!");
        obj.value = '';
        obj.focus();
    }
}

function soma(){
    var soma = 0;
    var ipts = document.querySelectorAll('input[onchange="validarValorCartao(this)"]');
    var iptsCupom = document.querySelectorAll('input[onchange="soma()"]');

    for(var x=0; x<ipts.length; x++){

        if (ipts[x].value == ""){
            soma += 0;
        }
        else {
            var format = ipts[x].value.replace('.', '');
            format = format.replace(',', '.');
            var valorItem = parseFloat(format);
            //var valorItem = parseFloat(ipts[x].value);
            //!isNaN(valorItem) ? soma += parseFloat(valorItem) : null;
            soma += parseFloat(valorItem);
        }
    }

    for(var i=0; i<iptsCupom.length; i++){
        if (iptsCupom[i].value == ""){
            soma += 0;
        }
        else {
            var valorItem = parseFloat(iptsCupom[i].value);
            soma += parseFloat(valorItem);
        }
    }

    document.querySelector('#valorPago').value = soma.toFixed(2);

    var valorPago = document.getElementById("valorPago").value;
    var valorTotalCompra = document.getElementById("valorTotalCompra").value;

    var valorPendente = (parseFloat(valorTotalCompra) - parseFloat(valorPago)).toFixed(2);

    if(parseFloat(valorPendente) <= 0){
        document.getElementById("valorPendente").value = 0;
    }
    else{
        document.getElementById("valorPendente").value = valorPendente;
    }
}

function validarValorCartao(obj){
    validarValorMaximoCartao(obj);
    soma();

    if(obj.value != ''){
        validarValorMinimoCartao(obj);
        soma();
    }

    validarTodasMsgValPend();
}

function validarCheckCupom(obj){
    var ele = document.getElementById(obj.id);
    var idCheckBox = obj.id;
    var idInputCheckBox = idCheckBox.replace("cupomTroca","valorCupomTroca");
    var qtdeCupomSelec = document.getElementById("qtdeCupomSelecionado").value;
    var valorTotalPagCupom = document.getElementById("valorTotalPagCupom").value;
    var valorCupom = document.getElementById(idInputCheckBox).value;

    if(ele.checked == true){
        document.getElementById(idInputCheckBox).setAttribute('onchange', 'soma()');
        qtdeCupomSelec++;
        document.getElementById("qtdeCupomSelecionado").value = qtdeCupomSelec;
        soma();
        validarTodasMsgValPend();
        valorTotalPagCupom = parseFloat(valorTotalPagCupom) + parseFloat(valorCupom);
        document.getElementById("valorTotalPagCupom").value = valorTotalPagCupom;
        bloquearCuponsTroca();
    }

    else if (ele.checked != true){
        document.getElementById(idInputCheckBox).setAttribute('onchange', '');
        qtdeCupomSelec--;
        document.getElementById("qtdeCupomSelecionado").value = qtdeCupomSelec;
        soma();
        validarValoresTodosCartoes();
        validarTodasMsgValPend();
        valorTotalPagCupom = parseFloat(valorTotalPagCupom) - parseFloat(valorCupom);
        document.getElementById("valorTotalPagCupom").value = valorTotalPagCupom;
    }

    validarValorTotalCupom();
}

function validarValorTotalCupom(){
    var valorTotalPagCupom = document.getElementById("valorTotalPagCupom").value;
    var valorTotalCompra = document.getElementById("valorTotalCompra").value;
    var qtdeCartaoPag = document.getElementById("qtdeCartaoPag").value;
    var qtdeCartaoPagCad = document.getElementById("qtdeCartaoPagCad").value;
    var qtdeCartaoCad = document.getElementById("qtdeCartao").value;
    var valorCupomDesconto = document.getElementById("valorCupomDesconto").value;


    //Se o valor dos cupons de troca atingirem o valor da compra, devo bloquear o input do cupom de desconto.

    if(parseFloat(valorTotalPagCupom) >= parseFloat(valorTotalCompra)){

        if(document.getElementById("valorFrete").value > 0){
            //Desativar botões de pagamento com cartão.
            document.getElementById("botaoAddCadastrado").style.display = "none";
            document.getElementById("botaoAddCartao").style.display = "none";
        }

        //Varrer todos os cartões e excluir.
        for(var i=1; i<=qtdeCartaoPag; i++){
            var elementoCartao = document.querySelector("#novoCartao" + i);
            if(elementoCartao != null){
                elementoCartao.remove();
            }
        }
        //Varrer todos os select cartão e excluir.
        for(var j=1; j<=qtdeCartaoPagCad; j++){
            var elementoCartaoCad = document.querySelector("#novoCartaoCadastrado" + j);
            console.log(elementoCartaoCad);
            if(elementoCartaoCad != null){
                elementoCartaoCad.remove();
            }
        }
        document.getElementById("cupomDesconto").setAttribute('disabled','readonly');
        soma();
    }

    var valdesconto = document.getElementById("valorTotalPagCupom").value;
    valdesconto = parseFloat(valdesconto).toFixed(2);

    var desconto = document.getElementById("valorDescontoExib");
    desconto.innerText = '';
    desconto.innerText += 'R$ -' + valdesconto;
    calcularTotalAPagar();

}

function validarValoresTodosCartoes(){
    var qtdeCartaoPag = document.getElementById("qtdeCartaoPag").value;

    var qtdeCupomSelec = document.getElementById("qtdeCupomSelecionado").value;

    if(qtdeCupomSelec == 0){
        for(var i=1; i<=qtdeCartaoPag; i++){
            var ele = document.getElementById("valorPag" + i);

            if(ele != null && ele.getAttribute('disabled') != "readonly"){
                var valor = document.getElementById("valorPag" + i).value;
                if(valor < 10.00){
                    alert("Valor a ser pago no cartao nao pode ser inferior a 10 nem inferior ao valor pendente de pagamento.");
                    ele.value = '';
                    ele.focus();
                }
            }
        }
    }
}

function msgValorPendente(div){
    var idDivMsg = div;

    var valorTotal = document.getElementById("valorTotalCompra").value;
    var valorPag = document.getElementById("valorPago").value;

    var diferenca = parseFloat(valorTotal) - parseFloat(valorPag);
    diferenca = diferenca.toFixed(2);

    var ele = document.getElementById(idDivMsg);
    ele.textContent = '';

    if(diferenca > 0){
        ele.innerText += 'Valor pendente de pagamento = R$' + diferenca;
    }

    // controlarBotoesAddCartao(diferenca);
}

function validarTodasMsgValPend(){

    var qtdeNovoCartaoPag = document.getElementById("qtdeCartaoPagCad").value;
    var qtdeCartaoCad = document.getElementById("qtdeCartaoPag").value;

    if(qtdeNovoCartaoPag > 0) {
        for (var i = 1; i <= qtdeNovoCartaoPag; i++) {
            var eleCartaoNovo = document.getElementById("msgValorPendente" + i);
            if(eleCartaoNovo != null){
                msgValorPendente(eleCartaoNovo.id);
            }
        }
    }

    if(qtdeCartaoCad > 0) {
        for (var i = 1; i <= qtdeCartaoCad; i++) {
            var eleCartaoCad = document.getElementById("msgValorPendente" + i);
            if(eleCartaoCad != null){
                msgValorPendente(eleCartaoCad.id);
            }
        }
    }

}

function validarPrimeiroValorSelectCad(){

    var qtdeCartao = document.getElementById("qtdeCartao").value;
    var select = document.getElementById("idCartaoSelecionado1").value;
    var valorPag = document.getElementById("valorPag1");

    if(select != ""){
        valorPag.setAttribute('required', 'required');
        valorPag.removeAttribute('disabled');
        valorPag.removeAttribute('readonly');
    }

    else{
        valorPag.setAttribute('disabled', 'disabled');
        valorPag.removeAttribute('required');
    }
}

//Bloquear cupons não selecionados
function bloquearCuponsTroca(){
    var valorTotalPagCupom = document.getElementById("valorTotalPagCupom").value;
    var valorTotalCompra = document.getElementById("valorTotalCompra").value;
    var qtdeCupomTroca = document.getElementById("qtdeCupomTroca").value;
    var valorCupomDesconto = document.getElementById("valorCupomDesconto").value;

    if(parseFloat(valorTotalPagCupom) >= parseFloat(valorTotalCompra)){
        //Devo varrer todos os check box de cupom e desabilitar.
        for(var i=1; i<= qtdeCupomTroca; i++){
            var eleCupom = document.getElementById("cupomTroca" + i);
            if(eleCupom != null && eleCupom.checked != true) {
                eleCupom.setAttribute('disabled', 'readonly');
            }
        }
    }
}

function desbloquearCuponsTroca(){
    var qtdeCupomTroca = document.getElementById("qtdeCupomTroca").value;
    var valorCupomDesconto = document.getElementById("valorCupomDesconto").value;

    for(var i=1; i<= qtdeCupomTroca; i++) {
        var eleCupom = document.getElementById("cupomTroca" + i);
        if (eleCupom != null) {
            eleCupom.removeAttribute('disabled');
            if (eleCupom != null && eleCupom.checked == true) {
                eleCupom.click();
            }
        }
    }

    //valorCupomDesconto = 0 reativo o input
    if(parseInt(valorCupomDesconto) == 0){
        var cup = document.getElementById("cupomDesconto");
        cup.removeAttribute('disabled');
    }

    if(document.getElementById("qtdeCartao").value > 0){
        //Reativar botões de pagamento com cartão cadastrado.
        var ele = document.getElementById("botaoAddCadastrado");
        if(ele != null){
            ele.style.display = "block";
        }
    }
    //Reativar botões de pagamento com cartão.
    document.getElementById("botaoAddCartao").style.display = "block";
}

function validarValorCupomDesconto(){
    soma();

    var valorTotalPagCupom = document.getElementById("valorTotalPagCupom").value;
    var valorCupomDesconto = document.getElementById("valorCupomDesconto").value;
    document.getElementById("valorTotalPagCupom").value = parseFloat(valorTotalPagCupom) + parseFloat(valorCupomDesconto);



    if(parseFloat(document.getElementById("valorPago").value) > parseFloat(document.getElementById("valorTotalCompra").value)){
        var qtdeCartaoPag = document.getElementById("qtdeCartaoPag").value;

        for(var i=1; i<=qtdeCartaoPag; i++){
            var ele = document.getElementById("valorPag" + i);

            if(ele != null){
                ele.value = '';
            }
        }

        alert("DESCONTO APLICADO. Se necessario, reajuste os valores a serem debitados de cada cartao.");
        soma();
        validarValorTotalCupom();
        bloquearCuponsTroca();


    }

    validarTodasMsgValPend();
    calcularTotalAPagar();
}

function calcularTotalAPagar(){
    var valorFrete = document.getElementById("valorFrete").value;
    var valorTotalCarrinho = document.getElementById("valorTotalCarrinho").value;
    var valorTotalPagCupom = document.getElementById("valorTotalPagCupom").value;
    var totalPagar = document.getElementById("totalPagar");
    var soma;

    totalPagar.innerText = '';

    if(valorFrete > 0){
        soma = ((parseFloat(valorTotalCarrinho) + parseFloat(valorFrete)) - parseFloat(valorTotalPagCupom));
        totalPagar.innerText += 'R$ ' + soma.toFixed(2);
    }

    validarTodasMsgValPend();
}

