


function adicionarCategoria(){
    var contador = document.getElementById("qtdeCategoria").value;
    contador++;
    document.getElementById("qtdeCategoria").value = contador;

    var elementoNovaCateg = document.getElementById("novaCategoria");
    var divCategoria = document.createElement("div");
    divCategoria.setAttribute('id', 'novaCategoria' + contador);

    elementoNovaCateg.appendChild(divCategoria);
    divCategoria.innerHTML +=

        '<br>'+
        '<div class="form-row">'+
        '<div class="form-group col-md-4">'+
        '<label for="categoria' +contador+ '">Categoria ' +contador+ '</label>'+
        '<select class="form-control" name="categoria' +contador+ '" id="categoria' +contador+ '" onchange="validarCategorias(this)" required>'+
        '<option selected hidden="true" data-default value=""></option>'+
        '</select>'+
        '</div>'+
        '</div>'+
        '<button type="button" class="btn btn-danger btn-sm " name = "remover' +contador+'" id = "remover' +contador+ '" onclick="deletarCategoria(this)">Excluir Categoria '+contador+' </button>';

    popularSelectCategoriaLivro("categoria" + contador);

}

function deletarCategoria(obj){
    //ID do Botão
    var idBotao = obj.id;

    //Quantidade Caracter do ID do Botão Recebido
    var qtdeCaracter = idBotao.length - 1;

    //Descobrindo o número do Botão
    var numBotao = idBotao.charAt(qtdeCaracter);

    console.log(numBotao);

    var elementoCategoria = document.querySelector("#novaCategoria" + numBotao);
    console.log(elementoCategoria);
    elementoCategoria.remove();

}

function popularSelectCategoriaLivro(nomeCombo){

    var qtdeCategoriaLivro = document.getElementById("qtdeCategoriaLivro").value;
    var ele = document.getElementById(nomeCombo);

    console.log(ele);
    console.log(nomeCombo);

    for(var j = 1; j <= qtdeCategoriaLivro; j++){
        var categoriaLivro = document.getElementById("categoriaLivro" + j).value;
        var idcategoriaLivro = document.getElementById("idCategoriaLivro" + j).value;

        //console.log(idcategoriaLivro);
        //console.log(categoriaLivro);

        ele.innerHTML = ele.innerHTML +
            '<option value="' + idcategoriaLivro + '">' + categoriaLivro + '</option>';
    }
}

function validarFotoLivro(){
    const checkUploadFoto = document.getElementById("validacaoImagemProduto");

    if(!checkUploadFoto){
        alert("Faça upload da foto do livro.");
        return false;
    }
}

function validarCategorias(obj){
//qtdeCategoria
    var qtdeCategoria = document.getElementById("qtdeCategoria").value;

    //ID do Botão
    var idCombo = obj.id;

    //Quantidade Caracter do ID do Botão Recebido
    var qtdeCaracter = idCombo.length - 1;

    //Descobrindo o número do Botão
    var numCombo = idCombo.charAt(qtdeCaracter);

    console.log("NUMERO COMBO = " + numCombo);

    var ele = document.getElementById("categoria" + numCombo);

    console.log("ELE = " + ele);

    for(var i = 1; i < qtdeCategoria; i++){
        var selectCategAtual = document.getElementById("categoria" + i);
        var optionCategAtual = (selectCategAtual.selectedIndex);

        console.log("VALUE OPT ATUAL = " + optionCategAtual);

        for(var j = i + 1; j <= qtdeCategoria; j++){

            var selectCategDps = document.getElementById("categoria" + j);
            var optionCategDps = (selectCategDps.selectedIndex);

            console.log("VALUE OPT DPS = " + optionCategDps);

            if(optionCategAtual == optionCategDps){
                alert("Selecione categorias diferentes!");
                ele.selectedIndex = 0;
            }
        }
    }
}

function somarUnidade(obj){

    //ID do Botão
    var idBotao = obj.id;

    var nomeInput = idBotao.replace("add", "qtde");

    var quantidade = document.getElementById(nomeInput).value;
    quantidade++;

    console.log("qtde = " + quantidade);

    var elemento = document.getElementById(nomeInput);

    console.log("elemento " + elemento);

    elemento.value = quantidade;

    console.log("elemento dps " + elemento);
    console.log("valor elemento dps " + elemento.value);



}

function subtrairUnidade(obj){
    //ID do Botão
    var idBotao = obj.id;

    //Quantidade Caracter do ID do Botão Recebido
    var qtdeCaracter = idBotao.length - 1;

    //Descobrindo o número do Botão
    var numBotao = idBotao.charAt(qtdeCaracter - 1) + idBotao.charAt(qtdeCaracter);

    console.log(numBotao);

    var quantidade = document.getElementById("qtdeLivroSelecionado" + numBotao).value;

    if(quantidade > 1) {
        quantidade--;
        document.getElementById("qtdeLivroSelecionado" + numBotao).value = quantidade;
    }
}