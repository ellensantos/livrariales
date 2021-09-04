function adicionarCartao(){

    var contador = document.getElementById("qtdeCartao").value;
    contador++;

    document.getElementById("qtdeCartao").value = contador;

    var elementoNovoCartao = document.getElementById("novoCartao");
    var divCartao = document.createElement("div");
    divCartao.setAttribute('id', 'novoCartao' + contador);

    elementoNovoCartao.appendChild(divCartao);
    divCartao.innerHTML +=

        '<!-- Cartão -->' +

        '<br/><hr class="mb-4">' +
        '<label for="cartao' +contador+ '" id= "label-titulos"><b>CARTAO:</b></label>' +
        '<div class="card" id = "card-cadastro">' +
        '<div class="card-body" id = "card-cadastro">' +
        '<div class="form-row">' +
        '<div class="form-group col-md-4">' +
        '<input type="text" name="idCartao' +contador+ '" id="idCartao' +contador+ '" value="0" hidden="true">'+

        '<label for="numeroCartao' +contador+ '" id= "label-titulos">Numero do Cartao</label>' +
        '<input type="text" class="form-control" name="numeroCartao' +contador+ '" id="numeroCartao' +contador+ '" pattern="[0-9]+$" minlength="16" maxlength="16" required>' +
        '</div>' +


        '<div class="col-md-6">' +
        '<label for="nomeCartao' +contador+ '" id= "label-titulos">Nome Impresso no Cartao</label>' +
        '<input type="text" class="form-control" name="nomeCartao' +contador+ '" id="nomeCartao' +contador+ '" required>' +
        '</div>' +
        '</div>' +

        '<div class="form-row">' +
        '<div class="col-md-4">' +
        '<label for="bandeiraCartao' +contador+ '" id= "label-titulos">Bandeira do Cartao</label>' +
        '<select class="form-control" id="bandeiraCartao' +contador+ '" name="bandeiraCartao' +contador+ '" required>' +
        '</select>' +
        '</div>' +

        '<div class="col-md-2">' +
        '<label for="cvv' +contador+ '" id= "label-titulos">CVV</label>' +
        '<input type="text" class="form-control" name="cvv' +contador+ '" id="cvv' +contador+ '" minlength="3" maxlength="3" required>' +
        '</div>' +

        '<div class="col-md-2">'+
        '<label for="mesVencimento' +contador+ '">Mes</label>'+
        '<select class="form-control" id="mesVencimento' +contador+ '" name="mesVencimento' +contador+ '" required>'+
        '<option selected data-default value=""></option>'+
        '<option>1</option>'+
        '<option>2</option>'+
        '<option>3</option>'+
        '<option>4</option>'+
        '<option>5</option>'+
        '<option>6</option>'+
        '<option>7</option>'+
        '<option>8</option>'+
        '<option>9</option>'+
        '<option>10</option>'+
        '<option>11</option>'+
        '<option>12</option>'+
        '</select>'+
        '</div>'+

        '<div class="col-md-2">'+
        '<label for="anoVencimento' +contador+ '">Ano</label>'+
        '<select class="form-control" id="anoVencimento' +contador+ '" name="anoVencimento' +contador+ '" required>'+
        '<option selected data-default value=""></option>'+
        '<option>2015</option>'+
        '<option>2016</option>'+
        '<option>2017</option>'+
        '<option>2018</option>'+
        '<option>2019</option>'+
        '<option>2020</option>'+
        '<option>2021</option>'+
        '<option>2022</option>'+
        '<option>2023</option>'+
        '<option>2024</option>'+
        '<option>2025</option>'+
        '<option>2026</option>'+
        '<option>2027</option>'+
        '<option>2028</option>'+
        '<option>2029</option>'+
        '<option>2030</option>'+
        '</select>'+
        '</div>'+

        '</div>' +

        '<br/>' +
        '<div align="center">' +
        '<button type="button" class="btn btn-danger" name = "remover' +contador+ '" id = "remover' +contador+ '" onclick="deletarCartao(this)">Excluir</button>' +
        '</div>' +
        '</div>' +
        '</div>' +
        '</br>' +
        '</div>' ;

    //popularBandeiraCartao("bandeiraCartao" + contador);
    popularSelectBandeiraCartao("bandeiraCartao" + contador);


}

function deletarCartao(obj){
    //ID do Botão
    var idBotao = obj.id;

    var numBotao = idBotao.replace("remover", "");

    var elementoCartao = document.querySelector("#novoCartao" + numBotao);
    elementoCartao.remove();
}

//Popular Select Bandeira Cartão
function popularSelectBandeiraCartao(nomeCombo) {

    //var qtdeCartao = document.getElementById("qtdeCartao");
    var qtdeBandeira = document.getElementById("qtdeBandeiraCartao").value;
    var ele = document.getElementById(nomeCombo);

    for(var j = 1; j <= qtdeBandeira; j++){
        var bandeira = document.getElementById("bandCartao" + j).value;
        var idBandeira = document.getElementById("idBandCartao" + j).value;

        ele.innerHTML = ele.innerHTML +
            '<option value="' + idBandeira + '">' + bandeira + '</option>';
    }
}

function popularSelectBandeiraCartaoEdicao() {
    var qtdeCartao = document.getElementById("qtdeCartao").value;
    for (var i= 1; i <= qtdeCartao; i++){
        popularSelectBandeiraCartao("bandeiraCartao" + i);
    }
}

function addCartaoCheckOut(){

    //esconderCartaoCheckout();

    var qtdeCartaoCliente = document.getElementById("qtdeCartao").value;

    var contador = document.getElementById("qtdeCartaoPag").value;

    contador++;
    console.log(contador);


    document.getElementById("qtdeCartaoPag").value = contador;

    var elementoNovoCartao = document.getElementById("novoCartao");
    var divCartao = document.createElement("div");
    divCartao.setAttribute('id', 'novoCartao' + contador);

    elementoNovoCartao.appendChild(divCartao);
    divCartao.innerHTML +=

        '<!-- Cartão -->' +
        '<br/>' +
        '<div class="card" id="cardCartaoCheckOut">' +
        '<div class="card-body" id="cardCartaoCheckOut">' +
        '<div class="form-row">' +
        '<div class="form-group col-md-4">' +
        '<input type="text" name="idCartao' +contador+ '" id="idCartao' +contador+ '" value="0" hidden="true">'+

        '<label for="numeroCartao' +contador+ '">Numero do Cartao</label>' +
        '<input type="text" class="form-control" name="numeroCartao' +contador+ '" id="numeroCartao' +contador+ '" pattern="[0-9]+$" minlength="16" maxlength="16" required>' +
        '</div>' +

        '<div class="col-md-5">' +
        '<label for="nomeCartao' +contador+ '">Nome Impresso no Cartao</label>' +
        '<input type="text" class="form-control" name="nomeCartao' +contador+ '" id="nomeCartao' +contador+ '" required>' +
        '</div>' +

        '<div class="col-md-4">' +
        '<label for="bandeiraCartao' +contador+ '">Bandeira do Cartao</label>' +
        '<select class="form-control" id="bandeiraCartao' +contador+ '" name="bandeiraCartao' +contador+ '" required>' +
        '</select>' +
        '</div>' +
        '<br/>' +

        '<div class="col-md-2">' +
        '<label for="cvv' +contador+ '">CVV</label>' +
        '<input type="text" class="form-control" name="cvv' +contador+ '" id="cvv' +contador+ '" minlength="3" maxlength="3" required>' +
        '</div>' +

        '<div class="col-md-2">' +
        '<label for="mesVencimento' +contador+ '">Mes</label>' +
        '<select class="form-control" id="mesVencimento' +contador+ '" name="mesVencimento' +contador+ '" required>' +
        '<option selected data-default value=""></option>'+
        '<option>1</option>'+
        '<option>2</option>'+
        '<option>3</option>'+
        '<option>4</option>'+
        '<option>5</option>'+
        '<option>6</option>'+
        '<option>7</option>'+
        '<option>8</option>'+
        '<option>9</option>'+
        '<option>10</option>'+
        '<option>11</option>'+
        '<option>12</option>'+
        '</select>' +
        '</div>' +

        '<div class="col-md-2">' +
        '<label for="anoVencimento' +contador+ '">Ano</label>' +
        '<select class="form-control" id="anoVencimento' +contador+ '" name="anoVencimento' +contador+ '" required>' +
        '<option selected data-default value=""></option>'+
        '<option>2015</option>'+
        '<option>2016</option>'+
        '<option>2017</option>'+
        '<option>2018</option>'+
        '<option>2019</option>'+
        '<option>2020</option>'+
        '<option>2021</option>'+
        '<option>2022</option>'+
        '<option>2023</option>'+
        '<option>2024</option>'+
        '<option>2025</option>'+
        '<option>2026</option>'+
        '<option>2027</option>'+
        '<option>2028</option>'+
        '<option>2029</option>'+
        '<option>2030</option>'+
        '</select>' +
        '</div>' +
        '</div>' +
        '<br/>' +


        '<div class="row">' +
        '<div class="col-md-3">' +
        '<label for="valorPag' +contador+ '" style="color: red">Valor R$ </label>' +
        '<input type="text" class="form-control" id="valorPag' +contador+ '" name="valorPag' +contador+ '" onkeyup="mascaraMoeda(this)" onchange="validarValorCartao(this)" required>' +
        '</div>' +
        '</div>' +

        '<div class="row">'+
        '<div class="col-md-8" id="msgValorPendente' +contador+ '">'+
        '</div>'+
        '</div>'+

        '<div class="form-check-inline">'+
        '<label class="form-check-label">'+
        '<br/>'+
        '<input type = "checkbox" class="form-check-input" id = "salvarCartao' +contador+ '" name = "salvarCartao' +contador+ '"> Salvar Cartao'+
        '</label>'+
        '</div>'+


        '<div align="center">' +
        '<button type="button" class="btn btn-secondary btn-sm" name = "remover' +contador+ '" id = "remover' +contador+ '" onclick="deletarCartaoCheckOut(this)"><i class="bi bi-trash"></i> remover</button>' +
        '</div>' +
        '</div>' +
        '</div>' +
        '</div>' ;

    //popularBandeiraCartao("bandeiraCartao" + contador);
    popularSelectBandeiraCartao("bandeiraCartao" + contador);
    msgValorPendente("msgValorPendente" + contador);

}

function deletarCartaoCheckOut(obj){
    deletarCartao(obj);

    /*    document.getElementById("botaoAddCartao").style.display = "block";
        document.getElementById("labelCartao").style.display = "block";
        //document.getElementById("idEnderecoSelecionado").style.display = "block";

        var select = document.getElementById("idCartaoSelecionado");
        //var valorSelect = (select.selectedIndex = 0);
        select.value = 0;
        select.style.display = "block";*/
}

function exibirCartaoCheckout(obj){

    var idBotao = obj.id;

    var numeroSelect = idBotao.replace("idCartaoSelecionado", "");

    var select = document.getElementById("idCartaoSelecionado" + numeroSelect);
    var value = select.options[select.selectedIndex].value;


    if(value != "") {
        var divCart = document.getElementById("dadosCartao" + numeroSelect);
        divCart.style.display = "block";
    }


}

function addSelectCartaoCad(){
    var contador = document.getElementById("qtdeCartaoPagCad").value;
    contador++;
    document.getElementById("qtdeCartaoPagCad").value = contador;

    contador = document.getElementById("qtdeCartaoPag").value;
    contador++;
    document.getElementById("qtdeCartaoPag").value = contador;

    var elementoNovoCartao = document.getElementById("novoCartaoCadastrado");
    var divCartao = document.createElement("div");
    divCartao.setAttribute('id', 'novoCartaoCadastrado' + contador);

    elementoNovoCartao.appendChild(divCartao);
    divCartao.innerHTML +=
        '<br>'+
        '<div id="selecaoCartao' +contador+ '" style="background-color: #F0ECEC;">' +
        '<div class="form-row">'+

        '<div class="col-lg-8 my-lg-0 my-2"><br>'+
        '<label for="" id="labelSelectCartao' +contador+ '">Cartoes cadastrados</label>'+
        '</div>'+

        '<div class="col-lg-5 my-lg-0 my-2">'+
        '<select class="form-control" id="idCartaoSelecionado' +contador+ '" name="idCartaoSelecionado' +contador+ '" required>'+
        '<option selected data-default value="">Selecione ...</option>'+
        '</select>'+
        '</div>'+

        '<div class="form-inline">'+
        '<label for="valorPag' +contador+ '">Valor R$ </label>'+
        '<div class="col-lg-1 my-lg-0 my-2">'+
        '<input type="text" class="form-control" id="valorPag' +contador+ '" name="valorPag' +contador+ '" onkeyup="mascaraMoeda(this)" onchange="validarValorCartao(this)" required>'+
        '</div>'+
        '</div>'+

        '<div class="col-lg-12 my-lg-0 my-2" id="msgValorPendente' +contador+ '">'+
        '</div>'+
        '<br>'+

        '<div class="col-lg-12 my-lg-0 my-2" align="center">'+
        '<button type="button" class="btn btn-secondary btn-sm" name = "remover' +contador+ '" id = "remover' +contador+ '" onclick="deletarSelectCartao(this)"><i class="bi bi-trash"></i> remover</button>'+
        '</div>'+
        '</div>'+
        '<br>'+
        '</div>'+
        '<br>';

    preencherEmailsCad("idCartaoSelecionado" + contador);

    msgValorPendente("msgValorPendente" + contador);
}

function deletarSelectCartao(obj){
    //ID do Botão
    var idBotao = obj.id;

    var numBotao = idBotao.replace("remover", "");

    console.log(numBotao);


    var elementoCartao = document.querySelector("#novoCartaoCadastrado" + numBotao);
    elementoCartao.remove();
}

function preencherEmailsCad(obj){
    var qtdeCartaoCliente = document.getElementById("qtdeCartao").value;
    var ele = document.getElementById(obj);

    console.log(ele);

    for(var j = 1; j <= qtdeCartaoCliente; j++){
        var idCartao = document.getElementById("idCartaoCliente" + j).value;
        var numeroCartao = document.getElementById("numCartaoCliente" + j).value;

        ele.innerHTML = ele.innerHTML +
            '<option value="' + idCartao + '">' + numeroCartao + '</option>';
    }
}