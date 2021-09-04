function confirmarReentradaEstoque(){
    var validacao = confirm("Deseja Dar Entrada nos Itens de Estoque?");
    var informarRecebTroca = document.getElementById("informarRecebTroca");
    var linkAtual = informarRecebTroca.getAttribute('href');

    if(validacao){
        var novoLink = linkAtual + "&entradaEstoque=sim";
    }

    else{
        var novoLink = linkAtual + "&entradaEstoque=nao";
    }
    informarRecebTroca.setAttribute('href',novoLink);
}