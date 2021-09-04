$(document).ready(function() {
    $('#cepEndEntrega1').change(function() {
        console.log("TO AQUI");
        $.ajax({

            url : 'http://localhost:8080/ellentex-livrariales/Frete',
            data : {
                cepEndEntrega1 : $('#cepEndEntrega1').val(),
                idEndEntregaSelecionado : $('#idEndEntregaSelecionado').val(),
                operacao : "CALCULAR_FRETE"
            },
            success : function(responseText) {

                if(responseText === "null"){
                    console.log(responseText);
                    $('#cepEndEntrega1').val('');
                }

                else {
                    $('#valorFreteExib').val('0.00');
                    console.log(responseText);
                }
            }
        });
    });
});

function calcularFrete() {
    console.log("TO AQUI");

    var selectEndEntrega = document.getElementById("idEndCobrancaSelecionado").value;

    $.ajax({
        url : 'http://localhost:8080/ellentex-livrariales/Frete',
        data : {
            cepEndEntrega1 : $('#cepEndEntrega1').val(),
            idEndEntregaSelecionado : $('#idEndEntregaSelecionado').val(),
            operacao : "CALCULAR_FRETE"
        },
        success : function(responseText) {

            if(responseText === "null"){
                console.log(responseText);
                $('#cepEndEntrega1').val('');
                document.getElementById("valorFrete").value = 0;
            }

            else {
                document.getElementById("valorFrete").value = responseText;
            }

            atualizarValorFrete();
        }
    });
}


function atualizarValorFrete(){

    var valorFrete = document.getElementById("valorFrete").value;
    var valorTotalCarrinho = document.getElementById("valorTotalCarrinho").value;
    var valorTotal = parseFloat(valorTotalCarrinho) + parseFloat(valorFrete);
    document.getElementById("valorTotalCompra").value = valorTotal
    var frete = document.getElementById("valorFreteExib");

    if(valorFrete == 0){
        frete.innerText = '';
    }

    else{
        frete.innerText = '';
        frete.innerText += 'R$' + parseFloat(valorFrete).toFixed(2);
    }

    soma();
    calcularTotalAPagar();
}