$(document).ready(function() {
    $.ajax({
        type: 'GET',
        url : 'http://localhost:8080/ellentex-livrariales/Cliente',
        data : {
            operacao : "ATUALIZAR_CLI_LOGADO"
        },
        success : function() {
            console.log("Cliente atualizado!");
        }
    });
});