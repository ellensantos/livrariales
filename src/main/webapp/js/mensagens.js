// Iniciará quando todo o corpo do documento HTML estiver pronto.
$().ready(function() {
    setTimeout(function () {
        $('#msgRetorno').hide(); // "foo" é o id do elemento que seja manipular.
    }, 5000); // O valor é representado em milisegundos.
});