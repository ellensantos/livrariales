$(document).ready(function() {
    $('#cupomDesconto').change(function() {
        $.ajax({
            url : 'http://localhost:8080/ellentex-livrariales/Cupom',
            data : {
                cupomDesconto : $('#cupomDesconto').val(),
                operacao : "VALIDAR_CUPOM_DESCONTO"
            },
            success : function(responseText) {

                if(responseText === "cupom invalido"){
                    console.log(responseText);
                    $('#respErroCupomDesconto').show();
                    $('#cupomDesconto').val('');
                }

                else {
                    responseText = responseText.replace(",", ".");
                    console.log(responseText);

                    $('#validarCupomDesconto').attr('disabled','true');
                    $('#respSucessoCupomDesconto').show();
                    $('#cupomDescontoDetalhes').show();
                    $('#valorCupomDesconto').val(responseText.replace(",", "."));
                    $('#cupomDesconto').attr('readonly', 'true');
                    validarValorCupomDesconto();
                    validarValorTotalCupom();
                    bloquearCuponsTroca();
                    validarTodasMsgValPend();
                }

            }
        });
    });
});

$(document).ready(function() {
    $('#cupomTroca').blur(function() {
        $.ajax({
            url : 'http://localhost:8080/ellentex-livrariales/Cupom',
            data : {
                cupomTroca : $('#cupomTroca').val(),
                operacao : "VALIDAR_CUPOM_TROCA"
            },
            success : function(responseText) {
                $('#respCupomTroca').text(responseText);
                $('#cupomTroca').val('');
            }
        });
    });
});


function testeCalculo(){
    var num1 = document.getElementById("valorPag1").value;
    //num1 = num1.replace(".", ",");
    console.log(num1);
    var num2 = document.getElementById("valorCupomDesconto").value;
    //num2 = num2.replace(".", ",");
    var soma  = parseFloat(num1) + parseFloat(num2);
    alert(num1);
}