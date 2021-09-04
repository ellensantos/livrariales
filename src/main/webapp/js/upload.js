var inputNome = document.getElementById('nomeArquivo');
var inputFicheiro = document.getElementById('file');


function capturarNomeArquivo(){
    var nome = inputFicheiro.files[0].name;
    inputNome.value = nome;
}