function habilitarValorManual(){
    var linkValorVendaManual = document.getElementById("linkValorVendaManual");
    linkValorVendaManual.setAttribute('hidden', 'hidden');

    var valorVendaProduto = document.getElementById("valorVendaProduto");
    valorVendaProduto.removeAttribute('disabled');
    valorVendaProduto.setAttribute('required', 'required');

    var linkValorVendaAutomatico = document.getElementById("linkValorVendaAutomatico");
    linkValorVendaAutomatico.removeAttribute('hidden');
}

function habilitarValorAutomatico(){
    var linkValorVendaManual = document.getElementById("linkValorVendaManual");
    linkValorVendaManual.removeAttribute('hidden');

    var valorVendaProduto = document.getElementById("valorVendaProduto");

    valorVendaProduto.setAttribute('disabled', 'disabled');
    valorVendaProduto.removeAttribute('required');

    var linkValorVendaAutomatico = document.getElementById("linkValorVendaAutomatico");
    linkValorVendaAutomatico.setAttribute('hidden', 'hidden');
}