$(document).ready(function() {
        $.ajax({
            type: 'GET',
            url : 'http://localhost:8080/ellentex-livrariales/Carrinho',
            data : {
                operacao : "TEMPO_SESSAO"
            },
            success : function(responseText) {
                //console.log("TEMPO = " + responseText);
                let segRestante = parseInt(responseText);

                if(segRestante >= 0) {
                    tempoSessao(segRestante);
                }
            }
        });
});

function startTimer(duration, display) {
    let timer = duration, minutes, seconds;
    let intervalo = setInterval(function () {
        minutes = parseInt(timer / 60, 10);
        seconds = parseInt(timer % 60, 10);
        minutes = minutes < 10 ? "0" + minutes : minutes;
        seconds = seconds < 10 ? "0" + seconds : seconds;
        display.textContent = minutes + ":" + seconds;

        if (--timer <= 0) {
            clearInterval(intervalo);
            alert('A sessao expirou e os itens foram removidos do carrinho.');
            location.replace('http://localhost:8080/ellentex-livrariales/carrinhoCompra.jsp');
        }
    }, 1000);
}


function tempoSessao (segRestantes) {
    let duration = segRestantes; // Converter para segundos pegando a parte inteira * 60 e somando os segundos
    let display = document.querySelector('#tempoSessao'); // selecionando o timer
    startTimer(duration, display); // iniciando o timer
}
